package com.vergl.filling.model;

import com.vergl.raid.model.Division;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "STAT_DATA")
public class StatData {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAT_FORM_ID", nullable = false)
    private StatForm statForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILTER_TYPE_ID", nullable = false)
    private FilterType filterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division division;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTUAL_DATE")
    private Date actualDate;

    @Column(name = "VALUE")
    private int value;

    public StatData(StatForm statForm, FilterType filterType, Division division, int value) {
        this.statForm = statForm;
        this.filterType = filterType;
        this.division = division;
        this.value = value;
    }

    public StatData() { }

    @Override
    public String toString() {
        return "StatData{" +
                "id=" + id +
                ", statForm=" + statForm +
                ", filterType=" + filterType +
                ", division=" + division +
                ", actualDate=" + actualDate +
                ", value=" + value +
                '}';
    }
}
