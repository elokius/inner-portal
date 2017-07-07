package com.vergl.raid.service.impl;

import com.vergl.raid.repository.RaidRepository;
import com.vergl.raid.model.Raid;
import com.vergl.raid.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by vergl on 28.01.2017.
 */
@Service
public class RaidServiceImpl implements RaidService {

    @Autowired
    private RaidRepository raidRepository;

    @Override
    public Raid addRaid(Raid raid) {
        return raidRepository.saveAndFlush(raid);
    }

    @Override
    public void delete(long id) {
        raidRepository.delete(id);
    }

    @Override
    public Raid getById(long id) {
        return raidRepository.findOne(id);
    }

    @Override
    public List<Raid> getAll() {
        return raidRepository.findAll();
    }

    @Override
    public List<Raid> getAllBeforeDate(Date date) {
        return raidRepository.findAllBeforeDate(date);
    }

    @Override
    public List<Raid> getAllAfterDate(Date date) {
        return raidRepository.findAllAfterDate(date);
    }

    @Override
    public Raid getNextRaid(Date date) {
        return raidRepository.getNextRaid(date);
    }

    @Override
    public void save(Raid savedRaid) {
        raidRepository.save(savedRaid);
    }

    @Override
    public Raid find(long id) {
        return raidRepository.findOne(id);
    }
}
