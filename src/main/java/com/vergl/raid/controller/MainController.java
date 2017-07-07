package com.vergl.raid.controller;

import com.vergl.config.CustomUser;
import com.vergl.filling.model.StatForm;
import com.vergl.filling.service.StatFormService;
import com.vergl.raid.model.Division;
import com.vergl.raid.model.Person;
import com.vergl.raid.service.DivisionService;
import com.vergl.raid.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 11.01.17
 */
@Controller
public class MainController {

    @Autowired
    private PersonService personService;
    @Autowired
    private StatFormService statFormService;
    @Autowired
    private DivisionService divisionService;

    @RequestMapping("/")
    public String welcome(Model model) {
        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", principal);

        List<Person> birthdays = personService.findByBirthDate(new Date());
        model.addAttribute("birthdays", birthdays);



        return "welcome";
    }

    @RequestMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/test")
    public String test(Model model) {
        List<StatForm> statForms = statFormService.findAll();
        List<Division> divisions = divisionService.findOspDivisions();

        model.addAttribute("statForms", statForms);
        model.addAttribute("divisions", divisions);

        return "test";
    }

}
