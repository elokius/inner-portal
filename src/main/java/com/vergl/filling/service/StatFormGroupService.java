package com.vergl.filling.service;

import com.vergl.filling.model.StatFormGroup;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 20.03.17
 */
public interface StatFormGroupService {
    List<StatFormGroup> findAll();

    void save(StatFormGroup statFormGroup);

    StatFormGroup findById(long id);

    void remove(StatFormGroup removing);
}
