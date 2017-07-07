package com.vergl.config;

import com.vergl.raid.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 03.02.17
 */
@Component
public class RaidLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    @Autowired
    private AuthoritiesService authoritiesService;



    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String s) {

        //Инициализируем список прав
        List<GrantedAuthority> authorityList = new ArrayList<>();

        //Проверяем, если у пользователя особые права
        List<String> userRoles = authoritiesService.getRolesByUsername(s);
        //Если такие права есть, то их присваиваем и заканчиваем инициализацию
        if (!userRoles.isEmpty()) {
            for (String role : userRoles) {
                authorityList.add(new SimpleGrantedAuthority(role));
            }
            return authorityList;
        }

        //Если особых прав нет, тогда назначаем права в соответствии с отделом, в котором работает сотрудник
        String departmentCode = "";
        try {
            if (dirContextOperations.getAttributes().get("ou") != null) {
                departmentCode = (String) dirContextOperations.getAttributes().get("ou").get();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (!departmentCode.equals("")) {
            List<String> depRoles = authoritiesService.getRolesByDepartmentCode(departmentCode);
            if (!depRoles.isEmpty()) {
                for (String depRole : depRoles) {
                    authorityList.add(new SimpleGrantedAuthority(depRole));
                }
                return authorityList;
            }
        }

        //Если и эти права не удалось получить, даем дефолтные.
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }


}
