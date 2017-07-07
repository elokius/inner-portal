package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 08.02.17
 */
@Entity
@Setter
@Getter
@Table(name = "CHIEF_ASSISTANT")
public class ChiefAssistant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_ID", nullable = false)
    private Raid assistedRaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person assistedPerson;

}
