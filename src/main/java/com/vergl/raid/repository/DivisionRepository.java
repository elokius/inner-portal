package com.vergl.raid.repository;

import com.vergl.raid.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by vergl on 29.01.2017.
 */

public interface DivisionRepository extends JpaRepository<Division, Long> {

    List<Division> findByActualOspTrue();

    Division findByNumber(long divisionNumber);

    List<Division> findByActualOspFalse();
}
