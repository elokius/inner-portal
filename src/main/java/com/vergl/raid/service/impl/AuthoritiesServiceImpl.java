package com.vergl.raid.service.impl;

import com.vergl.raid.repository.AuthoritiesRepository;
import com.vergl.raid.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 03.02.17
 */
@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public List<String> getRolesByUsername(String username) {
        return authoritiesRepository.getRolesByUsername(username);
    }

    @Override
    public List<String> getRolesByDepartmentCode(String depNumber) {
        return authoritiesRepository.getRolesByDepartmentCode(depNumber);
    }
}
