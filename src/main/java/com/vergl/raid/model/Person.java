package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vergl on 31.01.2017.
 */
@Entity
@Getter
@Setter
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "SURNAME")
    private String surName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "WORKING_NUMBER")
    private String workingNumber;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "IS_ACTIVE")
    private boolean active;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELAY_FROM")
    private Date delayFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELAY_TO")
    private Date delayTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post personPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division personDivision;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personStats")
    private Set<PersonalStats> personalStats = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsiblePerson")
    private Set<Participant> participation = new HashSet<>(0);

    public String getFullName() {
        return lastName + ' ' + firstName + ' ' + surName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workingNumber='" + workingNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", active=" + active +
                ", imagePath='" + imagePath + '\'' +
                ", birthDate=" + birthDate +
                ", delayFrom=" + delayFrom +
                ", delayTo=" + delayTo +
                ", personPost=" + personPost +
                ", personDivision=" + personDivision +
                '}';
    }
}
