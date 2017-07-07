package com.vergl.raid.service;

import com.vergl.raid.model.Division;

import java.util.List;

/**
 * Created by vergl on 29.01.2017.
 */
public interface DivisionService {
    List<Division> findAll();

    List<Division> findOspDivisions();

    List<Division> findLeadershipDivisions();

    Division find(long id);

    void save(Division division);

    Division findById(long id);

    void remove(Division removing);

    List<Division> findAllDivisionsForPhoneBook();

    List<Division> findOspDivisionsForPhoneBook();

    List<Division> findLeadershipDivisionsForPhoneBook();

}
