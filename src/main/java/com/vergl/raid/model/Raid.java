package com.vergl.raid.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vergl on 28.01.2017.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "RAID")
public class Raid {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHIEF_PERSON_ID", nullable = false)
    private Person chief;

    @Column(name = "PURPOSE")
    private String purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID", nullable = false)
    private Place raidPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category raidCategory;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PARTICIPANT_DIVISION", joinColumns = {
            @JoinColumn(name = "RAID_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "DIVISION_ID",
                    nullable = false, updatable = false)})
    private Set<Division> divisions = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CHIEF_ASSISTANT", joinColumns = {
            @JoinColumn(name = "RAID_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID",
            nullable = false, updatable = false)})
    private Set<Person> assistantPersons = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_STATUS_ID", nullable = false)
    private RaidStatus raidStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participantRaid")
    private Set<Participant> participants = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assistedRaid")
    private Set<ChiefAssistant> chiefAssistants = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "raid")
    private Set<ParticipantPersonsGroup> groups = new HashSet<>(0);

    @Override
    public String toString() {
        return "Raid{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", chief=" + chief +
                ", purpose='" + purpose + '\'' +
                ", raidPlace=" + raidPlace +
                ", raidCategory=" + raidCategory +
                ", divisions=" + divisions +
                ", raidStatus=" + raidStatus +
                ", participants=" + participants +
                '}';
    }
}
