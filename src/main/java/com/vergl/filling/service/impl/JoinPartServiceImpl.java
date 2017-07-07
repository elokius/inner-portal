package com.vergl.filling.service.impl;

import com.vergl.filling.model.JoinPart;
import com.vergl.filling.repository.JoinPartRepository;
import com.vergl.filling.service.JoinPartService;
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
public class JoinPartServiceImpl implements JoinPartService {

    @Autowired
    private JoinPartRepository joinPartRepository;

    @Override
    public JoinPart findByParameterName(String parameterName) {
        return joinPartRepository.findByParameterName(parameterName);
    }
}
