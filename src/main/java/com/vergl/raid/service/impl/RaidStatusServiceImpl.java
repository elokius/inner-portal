package com.vergl.raid.service.impl;

import com.vergl.raid.model.RaidStatus;
import com.vergl.raid.repository.RaidStatusRepository;
import com.vergl.raid.service.RaidStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */
@Service
public class RaidStatusServiceImpl implements RaidStatusService {

    @Autowired
    private RaidStatusRepository raidStatusRepository;

    @Override
    public List<RaidStatus> findAll() {
        return raidStatusRepository.findAll();
    }

    @Override
    public RaidStatus find(long raidStatusId) {
        return raidStatusRepository.findOne(raidStatusId);
    }
}
