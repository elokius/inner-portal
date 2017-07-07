package com.vergl.filling.scheduledtasks;

import com.vergl.filling.model.FilterType;
import com.vergl.filling.model.StatForm;
import com.vergl.filling.service.StatDataService;
import com.vergl.raid.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.04.17
 */
@Component
public class StatDataUpdaterThread {

    @Autowired
    private StatDataService statDataService;

    @Autowired
    private TaskExecutor taskExecutor;


    public void fire(StatForm statForm, Division division) {
        taskExecutor.execute(() -> {
            String url = "jdbc:firebirdsql:" + division.getIpAddress() + ":database-name?encoding=WIN1251";
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(DataSourceBuilder
                    .create()
                    .username("USER")
                    .password("PASSWORD")
                    .url(url)
                    .driverClassName("org.firebirdsql.jdbc.FBDriver")
                    .build());
            System.out.println("Начало - отдел: " + division.getCaption() + ", отчёт: " + statForm.getName());
            List<FilterType> filterTypeArrayList = statForm.getFilterTypes();
            for (FilterType filterType : filterTypeArrayList) {
                statDataService.updateStatDataValues(statForm, division, filterType, namedParameterJdbcTemplate);
            }
            System.out.println("Конец - отдел: " + division.getCaption() + ", отчёт: " + statForm.getName());
        });
    }

}
