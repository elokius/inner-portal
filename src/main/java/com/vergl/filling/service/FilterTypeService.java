package com.vergl.filling.service;

import com.vergl.filling.model.FilterType;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
public interface FilterTypeService {
    FilterType findOne(long i);

    List<FilterType> findAll();

    void save(FilterType newFilterType);

    FilterType findById(long id);

    void remove(FilterType removing);
}
