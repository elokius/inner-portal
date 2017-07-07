package com.vergl.phonebook.repository;

import com.vergl.raid.model.Person;
import com.vergl.raid.repository.DivisionRepository;
import com.vergl.raid.repository.PostRepository;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 27.03.17
 */
public interface LdapPersonRepository {

    void setLdapTemplate(LdapTemplate ldapTemplate);

    List<Person> getAllUsers(Filter filter);

    void setPostRepository(PostRepository postRepository);

    void setDivisionRepository(DivisionRepository divisionRepository);
}
