package com.vergl.filling.repository;

import com.vergl.filling.model.FilterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
@Repository
public interface FilterTypeRepository extends JpaRepository<FilterType, Long> {
}
