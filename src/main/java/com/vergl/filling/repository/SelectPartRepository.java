package com.vergl.filling.repository;

import com.vergl.filling.model.SelectPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 22.05.17
 */
@Repository
public interface SelectPartRepository extends JpaRepository<SelectPart, Long> {
}
