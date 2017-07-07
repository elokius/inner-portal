package com.vergl.raid.service.impl;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Participant;
import com.vergl.raid.model.Raid;
import com.vergl.raid.repository.ParticipantRepository;
import com.vergl.raid.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public void save(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public Participant findParticipantByParticipantRaidAndParticipantDivision(Raid participantRaid, Division participantDivision) {
        return participantRepository.findParticipantByParticipantRaidAndParticipantDivision(participantRaid, participantDivision);
    }
}
