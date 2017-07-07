package com.vergl.raid.service;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Participant;
import com.vergl.raid.model.Raid;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */
public interface ParticipantService {
    Participant findParticipantByParticipantRaidAndParticipantDivision(Raid participantRaid, Division participantDivision);

    void save(Participant participant);
}
