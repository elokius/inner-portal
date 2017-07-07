package com.vergl.raid.controller;

import com.vergl.raid.model.*;
import com.vergl.raid.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 27.02.17
 */
@Controller
public class NewRaidController {

    @Autowired
    private RaidService raidService;

    @Autowired
    private PersonService personService;

    @Autowired
    private RaidStatusService raidStatusService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/createnewraid")
    public String createNewRaid(Model model) {
        Raid raid = new Raid();
        model.addAttribute("raid", raid);

        List<Person> chiefs = personService.findChiefs();
        model.addAttribute("chiefs", chiefs);

        List<Person> chiefAssistants = personService.findChiefAssistants();
        model.addAttribute("chiefassistants", chiefAssistants);

        List<Division> divisions = divisionService.findOspDivisions();
        model.addAttribute("divisions", divisions);

        List<Participant> participants = new ArrayList<>();
        for (Division division : divisions) {
            Participant newParticipant = new Participant();
            newParticipant.setParticipantDivision(division);
            participants.add(newParticipant);
        }
        model.addAttribute("participants", participants);

        List<RaidStatus> raidStatuses = raidStatusService.findAll();
        model.addAttribute("raidStatuses",raidStatuses);

        List<Place> places = placeService.findAll();
        model.addAttribute("raidPlaces", places);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("raidCategories", categories);

        return "editorcreateraid";
    }

    @PostMapping("/createnewraid")
    public String createRaid(@ModelAttribute("newRaid") Raid newRaid, HttpServletRequest request) {


        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            Date newStartDate = simpleDateFormat.parse(startDate);
            Date newEndDate = simpleDateFormat.parse(endDate);
            newRaid.setStartDate(newStartDate);
            newRaid.setEndDate(newEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "/error";
        }

        System.out.println(newRaid);



        raidService.addRaid(newRaid);


        return "redirect:/raid/" + newRaid.getId();
    }
}
