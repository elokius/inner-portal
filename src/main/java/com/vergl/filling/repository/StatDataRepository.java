package com.vergl.filling.repository;

import com.vergl.filling.model.FilterType;
import com.vergl.filling.model.StatData;
import com.vergl.filling.model.StatForm;
import com.vergl.raid.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
@Repository
public interface StatDataRepository extends JpaRepository<StatData, Long> {
    List<StatData> findFirst2ByStatFormAndFilterTypeAndDivisionOrderByActualDateDesc(StatForm statForm, FilterType filterType, Division division);
}
