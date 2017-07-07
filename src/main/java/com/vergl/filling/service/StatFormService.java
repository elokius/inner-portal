package com.vergl.filling.service;

import com.vergl.filling.model.StatForm;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
public interface StatFormService {
    StatForm findByCode(String statFormNumber);

    StatForm findOne(Long i);

    List<StatForm> findAll();

    void save(StatForm statForm);

    StatForm findById(long id);

    void remove(StatForm removing);
}
