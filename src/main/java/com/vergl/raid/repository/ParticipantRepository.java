package com.vergl.raid.repository;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Participant;
import com.vergl.raid.model.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findParticipantByParticipantRaidAndParticipantDivision(Raid participantRaid, Division participantDivision);
}
