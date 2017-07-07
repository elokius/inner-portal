package com.vergl.raid.service.impl;

import com.vergl.raid.model.ChiefAssistant;
import com.vergl.raid.model.Person;
import com.vergl.raid.model.Raid;
import com.vergl.raid.repository.ChiefAssistantRepository;
import com.vergl.raid.service.ChiefAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 08.02.17
 */
@Service
public class ChiefAssistantServiceImpl implements ChiefAssistantService {
    @Autowired
    private ChiefAssistantRepository chiefAssistantRepository;

    @Override
    public void save(ChiefAssistant chiefAssistant) {
        chiefAssistantRepository.save(chiefAssistant);
    }

    @Override
    public ChiefAssistant findChiefAssistantByAssistedRaidAndAssistedPerson(Raid chosenRaid, Person assistantPerson) {
        return chiefAssistantRepository.findChiefAssistantsByAssistedRaidAndAssistedPerson(chosenRaid,assistantPerson);
    }

    @Override
    public List<ChiefAssistant> findChiefAssistantsByAssistedRaid(Raid chosenRaid) {
        return chiefAssistantRepository.findChiefAssistantsByAssistedRaid(chosenRaid);
    }
}
