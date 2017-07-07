package com.vergl.filling.service;

import com.vergl.filling.model.DebtClass;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
public interface DebtClassService {
    List<DebtClass> findAll();

    void save(DebtClass newDebtClass);

    DebtClass findById(long id);

    void remove(DebtClass removing);
}
