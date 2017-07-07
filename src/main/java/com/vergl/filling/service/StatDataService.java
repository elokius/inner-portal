package com.vergl.filling.service;

import com.vergl.filling.model.FilterType;
import com.vergl.filling.model.StatData;
import com.vergl.filling.model.StatForm;
import com.vergl.raid.model.Division;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
public interface StatDataService {
    List<StatData> findFirst2ByStatFormAndFilterTypeAndDivisionOrderByActualDateDesc(StatForm statForm, FilterType filterType, Division division);

    void save(StatData statData);

    void updateStatDataValues(StatForm statForm, Division division, FilterType filterType, NamedParameterJdbcTemplate namedParameterJdbcTemplate);
}
