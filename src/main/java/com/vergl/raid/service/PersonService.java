package com.vergl.raid.service;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by vergl on 31.01.2017.
 */
public interface PersonService {
    List<Person> findChiefs();

    List<Person> findPersonsByPersonDivision(Division personDivision);

    Person find(long personId);

    List<Person> findChiefAssistants();

    List<Person> findByBirthDate(Date date);
}
