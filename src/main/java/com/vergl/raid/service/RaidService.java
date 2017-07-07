package com.vergl.raid.service;

import com.vergl.raid.model.Raid;

import java.util.Date;
import java.util.List;

/**
 * Created by vergl on 28.01.2017.
 */
public interface RaidService {
    Raid addRaid(Raid raid);
    void delete(long id);
    Raid getById(long id);
    List<Raid> getAll();
    List<Raid> getAllBeforeDate(Date date);
    List<Raid> getAllAfterDate(Date date);
    Raid getNextRaid(Date date);

    void save(Raid savedRaid);

    Raid find(long id);
}
