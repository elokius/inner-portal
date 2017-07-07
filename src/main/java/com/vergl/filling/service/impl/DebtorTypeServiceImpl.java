package com.vergl.filling.service.impl;

import com.vergl.filling.model.DebtorType;
import com.vergl.filling.repository.DebtorTypeRepository;
import com.vergl.filling.service.DebtorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.03.17
 */
@Service
public class DebtorTypeServiceImpl implements DebtorTypeService {

    @Autowired
    private DebtorTypeRepository debtorTypeRepository;

    @Override
    public DebtorType findOne(long i) {
        return debtorTypeRepository.findOne(i);
    }

    @Override
    public Object findAll() {
        return debtorTypeRepository.findAll();
    }

    @Override
    public void save(DebtorType debtorType) {
        debtorTypeRepository.save(debtorType);
    }

    @Override
    public DebtorType findById(long id) {
        return debtorTypeRepository.findOne(id);
    }

    @Override
    public void remove(DebtorType removing) {
        debtorTypeRepository.delete(removing);
    }
}
