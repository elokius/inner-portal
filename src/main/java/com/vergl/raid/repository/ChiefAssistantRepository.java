package com.vergl.raid.repository;

import com.vergl.raid.model.ChiefAssistant;
import com.vergl.raid.model.Person;
import com.vergl.raid.model.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 08.02.17
 */
public interface ChiefAssistantRepository extends JpaRepository<ChiefAssistant, Long> {
    List<ChiefAssistant> findChiefAssistantsByAssistedRaid(Raid raid);

    ChiefAssistant findChiefAssistantsByAssistedRaidAndAssistedPerson(Raid chosenRaid, Person assistantPerson);
}
