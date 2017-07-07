package com.vergl.filling.service;

import com.vergl.filling.model.Docstatus;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
public interface DocstatusService {

    List<Docstatus> findAll();

    void save(Docstatus docstatus);

    Docstatus findById(long id);

    void remove(Docstatus removing);
}
