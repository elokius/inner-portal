package com.vergl.config;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 21.02.17
 */
@Component
public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {

    private String lastName = null;
    private String firstName = null;
    private String surName = null;
    private String engName = null;
    private Date birthDate = null;
    private String divisionNumber = null;

    private CustomUser userDetails = null;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {

        Attributes attributes = dirContextOperations.getAttributes();
        try {
            lastName = (String) attributes.get("sn").get();
            if (attributes.get("givenName") != null) {
                firstName = (String) attributes.get("givenName").get();
            } else {
                firstName = "Не указано";
            }
            if (attributes.get("initials") != null) {
                surName = (String) attributes.get("initials").get();
            } else {
                surName = "Не указано";
            }
            if (attributes.get("alias") != null) {
                engName = (String) attributes.get("alias").get();
            } else {
                engName = "Unknown name";
            }
            if (attributes.get("ou") != null) {
                divisionNumber = (String) attributes.get("ou").get();
            } else {
                divisionNumber = "60000";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            if (attributes.get("birthDate") != null) {
                birthDate = sdf.parse((String) attributes.get("birthDate").get());
            } else {
                birthDate = sdf.parse("01.01.1970");
            }
        } catch (NamingException | ParseException e) {
            e.printStackTrace();
        }
        CustomUser userDetails = new CustomUser(s, "", true, true, true, true, collection, firstName, lastName, surName, engName, birthDate, divisionNumber);
        this.userDetails = userDetails;
        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {
        //Do nothing.
    }

    public CustomUser getUserDetails() {
        return this.userDetails;
    }
}
