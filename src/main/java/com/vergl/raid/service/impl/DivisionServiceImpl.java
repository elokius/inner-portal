package com.vergl.raid.service.impl;

import com.vergl.raid.model.Person;
import com.vergl.raid.repository.DivisionRepository;
import com.vergl.raid.model.Division;
import com.vergl.raid.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vergl on 29.01.2017.
 */
@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    @Override
    public List<Division> findOspDivisions() {
        return divisionRepository.findByActualOspTrue();
    }

    @Override
    public List<Division> findLeadershipDivisions() {
        return divisionRepository.findByActualOspFalse();
    }

    @Override
    public Division find(long id) {
        return divisionRepository.findOne(id);
    }

    @Override
    public void save(Division division) {
        divisionRepository.save(division);
    }

    @Override
    public Division findById(long id) {
        return divisionRepository.findOne(id);
    }

    @Override
    public void remove(Division removing) {
        divisionRepository.delete(removing);
    }

    @Override
    public List<Division> findAllDivisionsForPhoneBook() {
        List<Division> divisions = this.findAll();
        prepareDivisions(divisions);
        divisions.sort(Comparator.comparingLong(Division::getNumber).reversed());
        return divisions;
    }

    @Override
    public List<Division> findOspDivisionsForPhoneBook() {
        List<Division> divisions = this.findOspDivisions();
        prepareDivisions(divisions);
        divisions.sort(Comparator.comparingLong(Division::getNumber));
        return divisions;
    }

    @Override
    public List<Division> findLeadershipDivisionsForPhoneBook() {
        List<Division> divisions = this.findLeadershipDivisions();
        prepareDivisions(divisions);
        divisions.sort(Comparator.comparingLong(Division::getNumber).reversed());
        return divisions;
    }

    private void prepareDivisions(List<Division> divisions) {
        //Убираем тех, кто в декретном / на больничном
        removePeopleOnVacation(divisions);

        //Убираем ОУПДС-ников
        removeOupds(divisions);

        //Убираем уволенных
        removeDismissed(divisions);

        //Сортируем оставшихся
        sortPeopleInDivisions(divisions);
    }


    private void removePeopleOnVacation(List<Division> divisions) {
        //Убираем тех, кто в декретном / на больничном
        for (Division division : divisions) {
            Iterator iterator = division.getPersons().iterator();
            while (iterator.hasNext()) {
                Person person = (Person) iterator.next();
                if (person.getDelayFrom() != null && person.getDelayTo() != null) {
                    long diff = person.getDelayTo().getTime() - person.getDelayFrom().getTime();
                    long datediff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    if (datediff > 30) {
                        iterator.remove();
                    }
                }
            }
        }
    }



    private void removeOupds(List<Division> divisions) {
        //Убираем ОУПДС-ников
        for (Division division : divisions) {
            Iterator iterator = division.getPersons().iterator();
            while(iterator.hasNext()) {
                Person person = (Person) iterator.next();
                if (person.getPersonPost().getNumber() >= 93 && person.getPersonPost().getNumber() <=96) {
                    iterator.remove();
                }
            }
        }
    }


    private void removeDismissed(List<Division> divisions) {
        for (Division division : divisions) {
            Iterator iterator = division.getPersons().iterator();
            while(iterator.hasNext()) {
                Person person = (Person) iterator.next();
                if (!person.isActive() && person.getDelayFrom() == null) {
                    iterator.remove();
                }
            }
        }
    }


    private void sortPeopleInDivisions(List<Division> divisions) {
        //Сортируем оставшихся
        for (Division division : divisions) {
            division.getPersons().sort(Comparator.comparingInt(o -> o.getPersonPost().getNumber()));
        }
    }




}
