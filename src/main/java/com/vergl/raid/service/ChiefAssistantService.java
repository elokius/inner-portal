package com.vergl.raid.service;

import com.vergl.raid.model.ChiefAssistant;
import com.vergl.raid.model.Person;
import com.vergl.raid.model.Raid;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 08.02.17
 */
public interface ChiefAssistantService {
    List<ChiefAssistant> findChiefAssistantsByAssistedRaid(Raid chosenRaid);

    ChiefAssistant findChiefAssistantByAssistedRaidAndAssistedPerson(Raid chosenRaid, Person assistantPerson);

    void save(ChiefAssistant chiefAssistant);

}
