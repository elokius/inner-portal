package com.vergl.raid.service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 03.02.17
 */
public interface AuthoritiesService {

    List<String> getRolesByUsername(String username);

    List<String> getRolesByDepartmentCode(String depNumber);
}
