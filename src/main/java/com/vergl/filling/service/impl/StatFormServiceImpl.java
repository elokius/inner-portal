package com.vergl.filling.service.impl;

import com.vergl.filling.model.StatForm;
import com.vergl.filling.repository.StatFormRepository;
import com.vergl.filling.service.StatFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */

@Service
public class StatFormServiceImpl implements StatFormService {

    @Autowired
    private StatFormRepository statFormRepository;

    @Override
    public StatForm findByCode(String code) {
        return statFormRepository.findByCode(code);
    }

    @Override
    public StatForm findOne(Long i) {
        return statFormRepository.findOne(i);
    }

    @Override
    public List<StatForm> findAll() {
        return statFormRepository.findAll();
    }

    @Override
    public void save(StatForm statForm) {
        statFormRepository.save(statForm);
    }

    @Override
    public StatForm findById(long id) {
        return statFormRepository.findById(id);
    }

    @Override
    public void remove(StatForm removing) {
        statFormRepository.delete(removing);
    }
}
