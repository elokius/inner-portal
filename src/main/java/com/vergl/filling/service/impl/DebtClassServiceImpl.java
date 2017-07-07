package com.vergl.filling.service.impl;

import com.vergl.filling.model.DebtClass;
import com.vergl.filling.repository.DebtClassRepository;
import com.vergl.filling.service.DebtClassService;
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
public class DebtClassServiceImpl implements DebtClassService {

    @Autowired
    private DebtClassRepository repository;

    @Override
    public DebtClass findById(long id) {
        return repository.findOne(id);
    }

    @Override
    public void remove(DebtClass removing) {
        repository.delete(removing);
    }

    @Override
    public void save(DebtClass newDebtClass) {
        repository.save(newDebtClass);
    }

    @Override
    public List<DebtClass> findAll() {
        return repository.findAll();
    }
}
