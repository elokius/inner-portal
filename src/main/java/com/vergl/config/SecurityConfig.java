package com.vergl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 11.01.17
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RaidLdapAuthoritiesPopulator authoritiesPopulator;

    @Autowired
    private UserDetailsContextMapper userDetailsContextMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    CustomUser details = (CustomUser) authentication.getPrincipal();
                    httpServletResponse.sendRedirect("/");
                })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    CustomUser details = (CustomUser) authentication.getPrincipal();
                });
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication().ldapAuthoritiesPopulator(authoritiesPopulator)
                .userDnPatterns("uid={0},ou=r60,ou=users")
                .userDetailsContextMapper(userDetailsContextMapper)
                .contextSource().url("ldap://0.0.0.0/dc=fssprus,dc=ru").port(389);
    }

}
