package com.vergl.raid.repository;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 03.02.17
 */
public interface AuthoritiesRepository {

    List<String> getRolesByUsername(String username);

    List<String> getRolesByDepartmentCode(String depNumber);
}
