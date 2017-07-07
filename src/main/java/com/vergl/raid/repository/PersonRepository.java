package com.vergl.raid.repository;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by vergl on 31.01.2017.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.personPost IN (1,2,3)")
    List<Person> findChiefs();

    List<Person> findPersonsByPersonDivision(Division personDivision);

    @Query("SELECT p FROM Person p WHERE p.personPost IN (3,4)")
    List<Person> findChiefAssistants();


    Person findByUsername(String username);
}
