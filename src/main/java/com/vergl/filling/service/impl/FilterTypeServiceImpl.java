package com.vergl.filling.service.impl;

import com.vergl.filling.model.FilterType;
import com.vergl.filling.repository.FilterTypeRepository;
import com.vergl.filling.service.FilterTypeService;
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
public class FilterTypeServiceImpl implements FilterTypeService {

    @Autowired
    private FilterTypeRepository filterTypeRepository;

    @Override
    public FilterType findOne(long i) {
        return filterTypeRepository.findOne(i);
    }

    @Override
    public List<FilterType> findAll() {
        return filterTypeRepository.findAll();
    }

    @Override
    public void save(FilterType newFilterType) {
        filterTypeRepository.save(newFilterType);
    }

    @Override
    public FilterType findById(long id) {
        return filterTypeRepository.findOne(id);
    }

    @Override
    public void remove(FilterType removing) {
        filterTypeRepository.delete(removing);
    }
}
