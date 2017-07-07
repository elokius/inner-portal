package com.vergl.phonebook.controller;

import com.vergl.raid.model.Division;
import com.vergl.raid.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 28.03.17
 */
@Controller
@RequestMapping("/phonebook")
public class PhonebookController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping()
    public String getPhonebook(Model model) {
        List<Division> divisions = divisionService.findAllDivisionsForPhoneBook();
        model.addAttribute("divisions", divisions);
        return "phonebook";
    }

    @GetMapping("/osp")
    public String getOspPhonebook(Model model) {
        List<Division> divisions = divisionService.findOspDivisionsForPhoneBook();
        model.addAttribute("divisions", divisions);
        return "phonebook";
    }

    @GetMapping("/leadership")
    public String getLeadershipPhonebook(Model model) {
        List<Division> divisions = divisionService.findLeadershipDivisionsForPhoneBook();
        model.addAttribute("divisions", divisions);
        return "phonebook";
    }
}
