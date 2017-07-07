package com.vergl.filling.service;

import com.vergl.filling.model.DebtorType;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.03.17
 */

public interface DebtorTypeService {
    DebtorType findOne(long i);

    Object findAll();

    void save(DebtorType debtorType);

    DebtorType findById(long id);

    void remove(DebtorType removing);
}
