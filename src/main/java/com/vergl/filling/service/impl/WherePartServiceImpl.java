package com.vergl.filling.service.impl;

import com.vergl.filling.model.WherePart;
import com.vergl.filling.repository.WherePartRepository;
import com.vergl.filling.service.WherePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 13.03.17
 */
@Service
public class WherePartServiceImpl implements WherePartService {

    @Autowired
    private WherePartRepository wherePartRepository;

    @Override
    public WherePart findByParameterName(String parameterName) {
        return wherePartRepository.findByParameterName(parameterName);
    }
}
