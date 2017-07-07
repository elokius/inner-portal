package com.vergl.phonebook.scheduledtasks;

import com.vergl.phonebook.repository.LdapPersonRepository;
import com.vergl.phonebook.repository.LdapPersonRepositoryImpl;
import com.vergl.raid.model.Person;
import com.vergl.raid.repository.DivisionRepository;
import com.vergl.raid.repository.PersonRepository;
import com.vergl.raid.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.NotPresentFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.ldap.filter.PresentFilter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 27.03.17
 */
@Component
public class PersonScheduler implements Runnable {

    @Autowired
    @Qualifier(value = "ldapTemplate")
    private LdapTemplate ldapTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private PersonRepository personRepository;


    public void run() {
        LdapPersonRepository dao = new LdapPersonRepositoryImpl();
        dao.setLdapTemplate(ldapTemplate);
        dao.setPostRepository(postRepository);
        dao.setDivisionRepository(divisionRepository);

        //Обновляем сведения об активных и временно заблокированных сотрудниках
        updateActiveOrVacationProfiles(dao);

        //Блокируем уволенных сотрудников, которые имеются в базе данных
        updateDismissedProfiles(dao);


    }

    private void updateDismissedProfiles(LdapPersonRepository dao) {
        //Условие, которое отбирает записи с незаполненными полями "Пароль" и "Забокирован до"
        //(отбирает заблокированных на постоянной основе сотрудников)
        AndFilter dismissedProfiles = new AndFilter();
        dismissedProfiles.and(new NotPresentFilter("rdbPassword"));
        dismissedProfiles.and(new NotPresentFilter("delayFrom"));
        dismissedProfiles.and(new PresentFilter("uid"));
        dismissedProfiles.and(new PresentFilter("sn"));

        List<Person> dismissedEmployees = dao.getAllUsers(dismissedProfiles);

        for (Person dismissedEmployee : dismissedEmployees) {
            //Если сотрудник есть в базе данных - блокируем его.
            Person dismissedPerson = personRepository.findByUsername(dismissedEmployee.getUsername());
            if (dismissedPerson != null) {
                dismissedPerson.setActive(dismissedEmployee.isActive());
                dismissedPerson.setDelayFrom(dismissedEmployee.getDelayFrom());
                dismissedPerson.setDelayTo(dismissedEmployee.getDelayTo());
                personRepository.save(dismissedPerson);
            }
        }
    }

    private void updateActiveOrVacationProfiles(LdapPersonRepository dao) {
        //Условие, которое отбирает записи с заполненными полями "Пароль" и "Должность"
        //(отбирает активные учетные записи, исключая системные)
        AndFilter activeProfiles = new AndFilter();
        activeProfiles.and(new PresentFilter("rdbPassword"));
        activeProfiles.and(new PresentFilter("title"));

        //Условие, которое отбирает записи с незаполненным полем "Пароль",
        //но заполненным полем "Дата разблокировки"
        //(отбирает учетные записи, которые заблокированы временно)
        AndFilter vacationProfiles = new AndFilter();
        vacationProfiles.and(new NotPresentFilter("rdbPassword"));
        vacationProfiles.and(new PresentFilter("delayTo"));
        vacationProfiles.and(new PresentFilter("title"));


        //Связывает предыдущие два условия оператором "ИЛИ"
        OrFilter activeOrVacationProfiles = new OrFilter();
        activeOrVacationProfiles.or(activeProfiles);
        activeOrVacationProfiles.or(vacationProfiles);

        //Отбирает всех активных и временно заблокированных сотрудников
        List<Person> activeEmployees = dao.getAllUsers(activeOrVacationProfiles);

        for (Person person : activeEmployees) {
            //Ищем сотрудника в базе данных
            Person existedPerson = personRepository.findByUsername(person.getUsername());
            //Если сотрудник есть в базе - обновляем все его поля,
            //иначе - сохраняем в базу нового сотрудника
            if (existedPerson != null) {
                existedPerson.setFirstName(person.getFirstName());
                existedPerson.setLastName(person.getLastName());
                existedPerson.setSurName(person.getSurName());
                existedPerson.setWorkingNumber(person.getWorkingNumber());
                existedPerson.setMobileNumber(person.getMobileNumber());
                existedPerson.setActive(person.isActive());
                existedPerson.setBirthDate(person.getBirthDate());
                existedPerson.setDelayFrom(person.getDelayFrom());
                existedPerson.setDelayTo(person.getDelayTo());
                existedPerson.setPersonPost(person.getPersonPost());
                existedPerson.setPersonDivision(person.getPersonDivision());
                personRepository.save(existedPerson);
            } else {
                personRepository.save(person);
            }
        }
    }
}
