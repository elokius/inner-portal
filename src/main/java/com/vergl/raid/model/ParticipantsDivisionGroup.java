package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.03.17
 */
@Entity
@Setter
@Getter
@Table(name = "PARTICIPANT_DIVISION_GROUP")
public class ParticipantsDivisionGroup {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Participant> participantsDivisions = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURATOR_PERSON_ID", nullable = false)
    private Person curator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_ID", nullable = false)
    private Raid raid;

    @Column(name = "GROUP_NUMBER")
    private int groupNumber;
}
