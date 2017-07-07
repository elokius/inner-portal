package com.vergl.filling.service.impl;

import com.vergl.filling.model.Docstatus;
import com.vergl.filling.repository.DocstatusRepository;
import com.vergl.filling.service.DocstatusService;
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
public class DocstatusServiceImpl implements DocstatusService {

    @Autowired
    private DocstatusRepository docstatusRepository;

    @Override
    public List<Docstatus> findAll() {
        return docstatusRepository.findAll();
    }

    @Override
    public void save(Docstatus docstatus) {
        docstatusRepository.save(docstatus);
    }

    @Override
    public Docstatus findById(long id) {
        return docstatusRepository.findOne(id);
    }

    @Override
    public void remove(Docstatus removing) {
        docstatusRepository.delete(removing);
    }
}
