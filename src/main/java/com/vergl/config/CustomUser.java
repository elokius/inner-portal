package com.vergl.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 21.02.17
 */
public class CustomUser extends User {

    private String firstName;
    private String lastName;
    private String surName;
    private String engName;
    private Date birthDate;
    private String divisionNumber;

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String surName, String engName, Date birthDate, String divisionNumber) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.engName = engName;
        this.birthDate = birthDate;
        this.divisionNumber = divisionNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }

    public String getEngName() {
        return engName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getDivisionNumber() {
        return divisionNumber;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + surName;
    }

    public String getFirstAndSurName() {
        return firstName + " " + surName;
    }

    public String getInitials() {
        return lastName + " " + firstName.substring(0, 1) + "." + surName.substring(0, 1) + ".";
    }
}
