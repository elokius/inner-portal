package com.vergl.raid.service;

import com.vergl.raid.model.RaidStatus;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */
public interface RaidStatusService {
    List<RaidStatus> findAll();

    RaidStatus find(long raidStatusId);
}
