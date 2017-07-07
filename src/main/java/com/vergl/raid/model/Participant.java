package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 06.02.17
 */
@Entity
@Setter
@Getter
@Table(name = "PARTICIPANT_DIVISION")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_ID", nullable = false)
    private Raid participantRaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division participantDivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPONSIBLE_PERSON_ID", nullable = false)
    private Person responsiblePerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTICIPANT_DIVISION_GROUP_ID", nullable = false)
    private ParticipantsDivisionGroup group;

    @Column(name = "GROUP_NUMBER")
    private int groupNumber;

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", participantDivision=" + participantDivision +
                ", responsiblePerson=" + responsiblePerson +
                ", groupNumber=" + groupNumber +
                '}';
    }
}
