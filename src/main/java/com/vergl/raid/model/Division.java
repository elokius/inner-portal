package com.vergl.raid.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vergl on 29.01.2017.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "DIVISION")
public class Division implements Comparable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "NUMBER")
    private long number;

    @Column(name = "CAPTION")
    private String caption;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "IS_ACTUAL_OSP")
    private boolean actualOsp;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEPHONE_CODE")
    private String telephoneCode;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "DUTY_PHONE")
    private String dutyPhone;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personDivision")
    private List<Person> persons = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participantDivision")
    private Set<Participant> participants = new HashSet<>(0);

    @Override
    public int compareTo(Object o) {
        Division comparator = (Division) o;
        if (this.getNumber() > comparator.getNumber()) {
            return 1;
        } else if (this.getNumber() < comparator.getNumber()) {
            return -1;
        } else return 0;
    }
}
