package com.vergl.raid.repository.impl;

import com.vergl.raid.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 03.02.17
 */
@Component
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository {

    private NamedParameterJdbcTemplate jdbc;

    private NamedParameterJdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    private void setDataSource(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", username);

        return jdbc.query("SELECT AUTHORITY FROM SPECIAL_AUTHORITIES WHERE USERNAME = :username", map, (resultSet, i) -> resultSet.getString("AUTHORITY"));

    }

    @Override
    public List<String> getRolesByDepartmentCode(String depNumber) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("dep_number", depNumber);

        return jdbc.query("SELECT AUTHORITY FROM DIVISION WHERE NUMBER = :dep_number", map, (resultSet, i) -> resultSet.getString("AUTHORITY"));

    }
}
