package com.vergl.raid.repository;

import com.vergl.raid.model.Raid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by vergl on 28.01.2017.
 */
public interface RaidRepository extends JpaRepository<Raid, Long> {

    @Query("SELECT r FROM Raid r WHERE r.startDate < :date")
    List<Raid> findAllBeforeDate(@Param("date") Date date);

    @Query("SELECT r FROM Raid r WHERE r.startDate > :date")
    List<Raid> findAllAfterDate(@Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT * FROM RAID WHERE START_DATE > :date ORDER BY START_DATE LIMIT 1")
    Raid getNextRaid(@Param("date") Date date);
}
