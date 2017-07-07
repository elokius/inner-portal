package com.vergl.filling.service.impl;

import com.vergl.filling.model.SelectPart;
import com.vergl.filling.repository.SelectPartRepository;
import com.vergl.filling.service.SelectPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 22.05.17
 */
@Service
public class SelectPartServiceImpl implements SelectPartService {

    @Autowired
    private SelectPartRepository selectPartRepository;

    @Override
    public List<SelectPart> findAll() {
        return selectPartRepository.findAll();
    }
}
