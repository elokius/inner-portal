package com.vergl.raid.service.impl;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Person;
import com.vergl.raid.repository.PersonRepository;
import com.vergl.raid.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vergl on 31.01.2017.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findChiefs() {
        return personRepository.findChiefs();
    }

    @Override
    public List<Person> findPersonsByPersonDivision(Division personDivision) {
        return personRepository.findPersonsByPersonDivision(personDivision);
    }

    @Override
    public Person find(long personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public List<Person> findChiefAssistants() {
        return personRepository.findChiefAssistants();
    }

    @Override
    public List<Person> findByBirthDate(Date date) {
        List<Person> people = personRepository.findAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Iterator iterator = people.iterator();
        while(iterator.hasNext()) {
            Person person = (Person) iterator.next();
            if (person.getBirthDate() != null) {
                String today = sdf.format(new Date()).substring(0,5);
                String birthDate = sdf.format(person.getBirthDate()).substring(0,5);
                if (!today.equals(birthDate)) {
                    iterator.remove();
                }
            } else {
                iterator.remove();
            }
        }

        return people;
    }
}
