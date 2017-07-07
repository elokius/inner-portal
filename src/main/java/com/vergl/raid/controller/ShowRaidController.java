package com.vergl.raid.controller;

import com.vergl.raid.model.Division;
import com.vergl.raid.model.Raid;
import com.vergl.raid.service.DivisionService;
import com.vergl.raid.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by vergl on 28.01.2017.
 */
@Controller
public class ShowRaidController {

    @Autowired
    private RaidService raidService;

    @Autowired
    private DivisionService divisionService;

    @RequestMapping("/raid/{id}")
    public String getRaidById(@PathVariable long id, Model model) {


        Raid chosenRaid = raidService.getById(id);
        System.out.println(chosenRaid);
        model.addAttribute("raid", chosenRaid);

        Set<Division> participantDivisions = chosenRaid.getDivisions();

        ArrayList<String> participantDivisionsRoles = new ArrayList<>();

        for (Division participantDivision : participantDivisions) {
            participantDivisionsRoles.add(participantDivision.getAuthority());
        }
        model.addAttribute("roles", participantDivisionsRoles);

        List<Division> allDivisions = divisionService.findOspDivisions();
        model.addAttribute("allDivisions",allDivisions);

        return "showraid";
    }



    @RequestMapping("/futureraids")
    public String futureRaids(Model model) {

        Date date = new Date();
        List<Raid> raids = raidService.getAllAfterDate(date);
        model.addAttribute("raids", raids);

        return "raidlist";
    }


    @RequestMapping("/pastraids")
    public String pastRaids(Model model) {

        Date date = new Date();
        List<Raid> raids = raidService.getAllBeforeDate(date);
        model.addAttribute("raids", raids);

        return "raidlist";
    }

    @RequestMapping("/currentraid")
    public String currentRaid(Model model) {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        today = calendar.getTime();

        Raid actualRaid = raidService.getNextRaid(today);
        model.addAttribute("raid", actualRaid);

        Set<Division> participantDivisions = actualRaid.getDivisions();

        ArrayList<String> participantDivisionsRoles = new ArrayList<>();

        for (Division participantDivision : participantDivisions) {
            participantDivisionsRoles.add(participantDivision.getAuthority());
        }
        model.addAttribute("roles", participantDivisionsRoles);

        List<Division> allDivisions = divisionService.findOspDivisions();
        model.addAttribute("allDivisions",allDivisions);

        return "showraid";
    }


}
