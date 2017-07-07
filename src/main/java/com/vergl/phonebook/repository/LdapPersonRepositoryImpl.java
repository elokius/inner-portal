package com.vergl.phonebook.repository;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Person;
import com.vergl.raid.model.Post;
import com.vergl.raid.repository.DivisionRepository;
import com.vergl.raid.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 27.03.17
 */
@Repository
public class LdapPersonRepositoryImpl implements LdapPersonRepository{

    private LdapTemplate ldapTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void setDivisionRepository(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }


    public List<Person> getAllUsers(Filter filter) {
        return ldapTemplate.search("", filter.encode(), (AttributesMapper) attr -> {
            Person person = new Person();
            person.setUsername((String) attr.get("uid").get());
            person.setLastName((String) attr.get("sn").get());
            if (attr.get("givenName")!= null) {
                person.setFirstName((String) attr.get("givenName").get());
            }
            if (attr.get("initials")!= null) {
                person.setSurName((String) attr.get("initials").get());
            }
            if (attr.get("telephoneNumber")!= null) {
                person.setWorkingNumber((String) attr.get("telephoneNumber").get());
            }
            if (attr.get("mobile")!= null) {
                person.setMobileNumber((String) attr.get("mobile").get());
            }
            if (attr.get("rdbPassword") != null) {
                person.setActive(true);
            } else {
                person.setActive(false);
            }
            try {
                if (attr.get("birthDate")!= null) {
                    Date birthDate = sdf.parse((String) attr.get("birthDate").get());
                    person.setBirthDate(birthDate);
                }
                if (attr.get("delayFrom")!= null) {
                    Date delayFrom = sdf.parse((String) attr.get("delayFrom").get());
                    person.setDelayFrom(delayFrom);
                }
                if (attr.get("delayTo")!= null) {
                    Date delayTo = sdf.parse((String) attr.get("delayTo").get());
                    person.setDelayTo(delayTo);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (attr.get("title") != null) {
                Integer postNumber = Integer.parseInt((String) attr.get("title").get());
                Post post = postRepository.findByNumber(postNumber);
                if (post != null) {
                    person.setPersonPost(post);
                }
            }
            if (attr.get("ou")!= null) {
                long divisionNumber = Long.parseLong((String) attr.get("ou").get());
                Division division = divisionRepository.findByNumber(divisionNumber);
                if (division != null) {
                    person.setPersonDivision(division);
                }
            }

            return person;
        });
    }
}
