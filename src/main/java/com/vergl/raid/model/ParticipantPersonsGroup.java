package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
@Table(name = "PARTICIPANT_PERSON_GROUP")
public class ParticipantPersonsGroup {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_ID", nullable = false)
    private Raid raid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division division;


    @Column(name = "GROUP_NUMBER")
    private int groupNumber;
}
