package com.vergl.filling.service.impl;

import com.vergl.filling.model.StatFormGroup;
import com.vergl.filling.repository.StatFormGroupRepository;
import com.vergl.filling.service.StatFormGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 20.03.17
 */
@Service
public class StatFormGroupServiceImpl implements StatFormGroupService {

    @Autowired
    private StatFormGroupRepository repository;

    @Override
    public List<StatFormGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(StatFormGroup statFormGroup) {
        repository.save(statFormGroup);
    }

    @Override
    public StatFormGroup findById(long id) {
        return repository.findOne(id);
    }

    @Override
    public void remove(StatFormGroup removing) {
        repository.delete(removing);
    }
}
