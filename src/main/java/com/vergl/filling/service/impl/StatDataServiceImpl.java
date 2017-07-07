package com.vergl.filling.service.impl;

import com.vergl.filling.model.*;
import com.vergl.filling.repository.StatDataRepository;
import com.vergl.filling.service.JoinPartService;
import com.vergl.filling.service.StatDataService;
import com.vergl.filling.service.WherePartService;
import com.vergl.raid.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
@Service
public class StatDataServiceImpl implements StatDataService {

    @Autowired
    private StatDataRepository statDataRepository;

    @Autowired
    private WherePartService wherePartService;

    @Autowired
    private JoinPartService joinPartService;

    @Override
    public void save(StatData statData) {
        statDataRepository.save(statData);
    }

    @Override
    public List<StatData> findFirst2ByStatFormAndFilterTypeAndDivisionOrderByActualDateDesc(StatForm statForm, FilterType filterType, Division division) {
        return statDataRepository.findFirst2ByStatFormAndFilterTypeAndDivisionOrderByActualDateDesc(statForm, filterType, division);
    }

    @Override
    public void updateStatDataValues(StatForm statForm, Division division, FilterType filterType, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        //Инициализируем запрос и параметры
        StringBuilder queryBuilder = new StringBuilder();
        MapSqlParameterSource map = new MapSqlParameterSource();

        queryBuilder.append(statForm.getStatFormGroup().getSelectPart().getSelectQuery());

        //JOIN-часть запроса
        createJoinPart(statForm, division, filterType, queryBuilder, map);

        //WHERE-часть запроса
        createWherePart(statForm, filterType, queryBuilder, map);

        String query = queryBuilder.toString();
        //Убираем лишние пробелы из запроса
        query = query.replaceAll("\\s+"," ");

        //В РБД считаем значение
        int value = namedParameterJdbcTemplate.queryForObject(query, map, Integer.class);

        //Добавляем запись в базу MySQL
        synchronized (this) {
            StatData statData = new StatData(statForm,filterType,division,value);
            statDataRepository.save(statData);
        }

    }


    private void createJoinPart(StatForm statForm, Division division, FilterType filterType, StringBuilder queryBuilder, MapSqlParameterSource map) {

        //Если указан тип должника
        if (statForm.getDebtorType() != null) {
            JoinPart debtorType = joinPartService.findByParameterName("hcode");
            queryBuilder.append(" ").append(debtorType.getJoinQuery()).append(" ");
            long hcode = statForm.getDebtorType().getNumber();
            map.addValue("hcode", hcode);
        }

        //Если указана сущность исполнения
        if (statForm.getDebtClass() != null) {
            JoinPart debtCls = joinPartService.findByParameterName("debtcls");
            queryBuilder.append(" ").append(debtCls.getJoinQuery()).append(" ");
            long debtcls = statForm.getDebtClass().getNumber();
            map.addValue("debtcls", debtcls);
        }

        //JOIN-часть типа запроса, если она имеется
        if (filterType.getJoinPart() != null) {
            queryBuilder.append(" ").append(filterType.getJoinPart()).append(" ");
        }
    }

    private void createWherePart(StatForm statForm, FilterType filterType, StringBuilder queryBuilder, MapSqlParameterSource map) {
        //Признак, отобращающий добавлено ли что-нибудь к части WHERE,
        //если добавлено - перед WHERE-частью необходимо добавить AND
        boolean isAnythingAddedToWherePart = false;

        //Часть, касающаяся статусов ИП
        if (statForm.getDocstatuses() != null && statForm.getDocstatuses().size()!=0) {
            WherePart docstatus = wherePartService.findByParameterName("docstatusid");
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(docstatus.getWhereQuery()).append(" ");
            Set<Docstatus> docstatuses = statForm.getDocstatuses();

            //Получаем список ID
            List<Integer> docstatusid = new ArrayList<>();
            for (Docstatus iterator : docstatuses) {
                docstatusid.add(iterator.getNumber());
            }

            map.addValue("docstatusid", docstatusid);

        }

        //Часть, касающаяся минимальной даты возбуждения
        if (statForm.getMinRisedate() != null) {
            WherePart minRisedatePart = wherePartService.findByParameterName("minRisedate");
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(minRisedatePart.getWhereQuery()).append(" ");
            Date minRisedate = statForm.getMinRisedate();
            map.addValue("minRisedate", minRisedate);
        }

        //Часть, касающаяся максимальной даты возбуждения
        if (statForm.getMaxRisedate() != null) {
            WherePart maxRisedatePart = wherePartService.findByParameterName("maxRisedate");
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(maxRisedatePart.getWhereQuery()).append(" ");
            Date maxRisedate = statForm.getMaxRisedate();
            map.addValue("maxRisedate", maxRisedate);
        }

        //Часть, касающаяся минимальной суммы долга
        if (statForm.getMinDebtsum() != 0) {
            WherePart minDebtsumPart = wherePartService.findByParameterName("minDebtsum");
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(minDebtsumPart.getWhereQuery()).append(" ");
            int minDebtsum = statForm.getMinDebtsum();
            map.addValue("minDebtsum", minDebtsum);
        }

        //Часть, касающаяся максимальной суммы долга
        if (statForm.getMinDebtsum() != 0) {
            WherePart maxDebtsumPart = wherePartService.findByParameterName("maxDebtsum");
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(maxDebtsumPart.getWhereQuery()).append(" ");
            int maxDebtsum = statForm.getMaxDebtsum();
            map.addValue("maxDebtsum", maxDebtsum);
        }

        //WHERE-часть типа запроса, если она существует
        if (filterType.getWherePart() != null) {
            if (isAnythingAddedToWherePart) {
                queryBuilder.append(" AND ");
            } else {
                queryBuilder.append(" WHERE ");
                isAnythingAddedToWherePart = true;
            }
            queryBuilder.append(" ").append(filterType.getWherePart()).append(" ");
        }
    }

}
