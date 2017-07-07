package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.02.17
 */
@Entity
@Setter
@Getter
@Table(name = "PERSONAL_STATS")
public class PersonalStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTICIPANT_PERSON_GROUP_ID", nullable = false)
    private ParticipantPersonsGroup personStats;

    @Column(name = "PLANNED_ADDRESSES")
    private int plannedAddresses;

    @Column(name = "CHECKED_IP")
    private int checkedIp;

    @Column(name = "RECOVERED_SUM")
    private int recoveredSum;

    @Column(name = "IP_TO_FINISH_AMOUNT")
    private int ipToFinishAmount;

    @Column(name = "IP_TO_FINISH_SUM")
    private int ipToFinishSum;

    @Column(name = "ARRESTS_AMOUNT")
    private int arrestsAmount;

    @Column(name = "ARRESTS_SUM")
    private int arrestsSum;

    @Column(name = "KOAP_PROTOCOL_CODE")
    private int koapProtocolCode;

    @Column(name = "KOAP_PROTOCOL_AMOUNT")
    private int koapProtocolAmount;

    @Column(name = "HANDED_REQUIREMENTS")
    private int handedRequirements;

    @Column(name = "SPECIAL_RIGHTS")
    private int specialRights;

    @Column(name = "CRIMINAL_WARNINGS")
    private int criminalWarnings;

    @Column(name = "INCOMES_IDENTIFIED")
    private int incomesIdentified;

    @Column(name = "INSPECTION_ACTS")
    private int inspectionActs;

    @Column(name = "DEBTORS_WERE_ABSENT")
    private int debtorsWereAbsent;

    @Column(name = "DEBTORS_CHANGED_PLACE")
    private int debtorsChangedPlace;
}
