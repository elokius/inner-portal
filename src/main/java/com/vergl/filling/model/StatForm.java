package com.vergl.filling.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
@Entity
@Setter
@Getter
@Table(name = "STAT_FORM")
public class StatForm {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEBTOR_TYPE_ID", nullable = false)
    private DebtorType debtorType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEBT_CLASS_ID", nullable = false)
    private DebtClass debtClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STAT_FORM_GROUP_ID", nullable = false)
    private StatFormGroup statFormGroup;

    @Temporal(TemporalType.DATE)
    @Column(name = "MIN_RISEDATE")
    private Date minRisedate;

    @Temporal(TemporalType.DATE)
    @Column(name = "MAX_RISEDATE")
    private Date maxRisedate;

    @Column(name = "MIN_DEBTSUM")
    private int minDebtsum;

    @Column(name = "MAX_DEBTSUM")
    private int maxDebtsum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTUAL_DATE")
    private Date actualDate;

    @Column(name = "ADDITIONAL_INFO")
    private String additionalInfo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "STAT_FORM_FILTER_TYPE", joinColumns = {
            @JoinColumn(name = "STAT_FORM_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "FILTER_TYPE_ID",
                    nullable = false, updatable = false)})
    private List<FilterType> filterTypes = new ArrayList<>(0);

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "STAT_FORM_DOCSTATUS", joinColumns = {
            @JoinColumn(name = "STAT_FORM_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "DOCSTATUS_ID",
                    nullable = false, updatable = false)})
    private Set<Docstatus> docstatuses = new LinkedHashSet<>(0);

    @Override
    public String toString() {
        return "StatForm{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", debtorType=" + debtorType +
                ", debtClass=" + debtClass +
                ", minRisedate=" + minRisedate +
                ", maxRisedate=" + maxRisedate +
                ", minDebtsum=" + minDebtsum +
                ", maxDebtsum=" + maxDebtsum +
                ", actualDate=" + actualDate +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", filterTypes=" + filterTypes +
                ", docstatuses=" + docstatuses +
                '}';
    }
}
