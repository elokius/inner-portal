package com.vergl.raid.controller;

import com.vergl.raid.model.*;
import com.vergl.raid.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 27.02.17
 */

@Controller
@RequestMapping("/raid/{id}")
public class EditRaidController {

    @Autowired
    private RaidService raidService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ChiefAssistantService chiefAssistantService;

    @Autowired
    private RaidStatusService raidStatusService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/editassistant/{personId}")
    public String editAssistant(@PathVariable long id, @PathVariable long personId, Model model) {

        Raid chosenRaid = raidService.find(id);
        System.out.println(chosenRaid);
        model.addAttribute("raid", chosenRaid);

        Person assistantPerson = personService.find(personId);
        ChiefAssistant chiefAssistant = chiefAssistantService.findChiefAssistantByAssistedRaidAndAssistedPerson(chosenRaid, assistantPerson);
        System.out.println(chiefAssistant);
        model.addAttribute("chiefAssistant", chiefAssistant);

        Set<Person> raidAssistants = chosenRaid.getAssistantPersons();

        List<Person> assistantPersons = personService.findChiefAssistants();
        Iterator<Person> iterator = assistantPersons.iterator();

        while(iterator.hasNext()) {
            Person next = iterator.next();
            for (Person raidAssistant : raidAssistants) {
                if (raidAssistant.equals(next)) {
                    iterator.remove();
                    break;
                }
            }
        }


        model.addAttribute("assistantPersons", assistantPersons);


        return "editdata";
    }

    @PostMapping("/editassistant/{personId}")
    public String saveAssistants(@PathVariable long id, @PathVariable long personId, HttpServletRequest request) {

        String idParameter = request.getParameter("assistantPersons");

        if (idParameter != null) {
            long assistantPersonId = Long.parseLong(idParameter);
            Person oldPerson = personService.find(personId);
            Person newPerson = personService.find(assistantPersonId);

            Raid participantRaid = raidService.find(id);

            ChiefAssistant chiefAssistant = chiefAssistantService.findChiefAssistantByAssistedRaidAndAssistedPerson(participantRaid, oldPerson);
            chiefAssistant.setAssistedPerson(newPerson);
            chiefAssistantService.save(chiefAssistant);

        }

        return "redirect:/raid/" + id;
    }

    @GetMapping("/editstatus")
    public String editStatus(@PathVariable long id, Model model) {

        Raid chosenRaid = raidService.find(id);
        System.out.println(chosenRaid);
        model.addAttribute("raid", chosenRaid);

        List<RaidStatus> statuses = raidStatusService.findAll();
        model.addAttribute("statuses", statuses);

        return "editdata";
    }

    @PostMapping("/editstatus")
    public String saveStatus(@PathVariable long id, HttpServletRequest request) {

        String statusId = request.getParameter("raidStatus");

        if (statusId != null) {
            long raidStatusId = Long.parseLong(statusId);

            Raid raid = raidService.find(id);

            RaidStatus raidStatus = raidStatusService.find(raidStatusId);
            raid.setRaidStatus(raidStatus);
            raidService.save(raid);
        }

        return "redirect:/raid/" + id;
    }

    @GetMapping("/editchief")
    public String editChief(@PathVariable long id, Model model) {

        Raid chosenRaid = raidService.find(id);
        System.out.println(chosenRaid);
        model.addAttribute("raid", chosenRaid);

        List<Person> chiefs = personService.findChiefs();
        model.addAttribute("chiefs", chiefs);

        return "editdata";
    }

    @PostMapping("/editchief")
    public String saveChief(@PathVariable long id, HttpServletRequest request) {

        String chiefId = request.getParameter("chief");

        if (chiefId != null) {
            long newChiefId = Long.parseLong(chiefId);

            Raid raid = raidService.find(id);

            Person chief = personService.find(newChiefId);
            raid.setChief(chief);
            raidService.save(raid);
        }

        return "redirect:/raid/" + id;
    }

    @GetMapping("/editdate")
    public String editDate(@PathVariable long id, Model model) {
        Raid raid = raidService.find(id);
        model.addAttribute("raid", raid);
        return "editdata";
    }

    @PostMapping("/editdate")
    public String saveDate(@PathVariable long id, HttpServletRequest request) {
        Raid raid = raidService.find(id);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            Date newStartDate = simpleDateFormat.parse(startDate);
            Date newEndDate = simpleDateFormat.parse(endDate);
            raid.setStartDate(newStartDate);
            raid.setEndDate(newEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "/error";
        }

        raidService.save(raid);
        return "redirect:/raid/"+id;
    }

    @GetMapping("/responsible/{divisionNumber}")
    public String editResponsiblePerson(@PathVariable long id, @PathVariable int divisionNumber, Model model) {

        Raid participantRaid = raidService.find(id);
        model.addAttribute("raid", participantRaid);

        Division participantDivision = divisionService.find(divisionNumber);

        Participant currParticipant = participantService.findParticipantByParticipantRaidAndParticipantDivision(participantRaid, participantDivision);
        System.out.println(currParticipant);
        model.addAttribute("gottenparticipant", currParticipant);

        List<Person> divisionWorkers = personService.findPersonsByPersonDivision(participantDivision);
        model.addAttribute("divisionWorkers",divisionWorkers);
        return "editdata";
    }

    @PostMapping("/responsible/{divisionNumber}")
    public String saveResponsiblePerson(@PathVariable long id, @PathVariable int divisionNumber, HttpServletRequest request) {

        String idParameter = request.getParameter("responsiblePerson");

        if (idParameter != null) {
            long personId = Long.parseLong(idParameter);
            Person person = personService.find(personId);

            Raid participantRaid = raidService.find(id);
            Division participantDivision = divisionService.find(divisionNumber);

            Participant participant = participantService.findParticipantByParticipantRaidAndParticipantDivision(participantRaid, participantDivision);
            participant.setResponsiblePerson(person);

            participantService.save(participant);

        }

        return "redirect:/raid/"+id;
    }

    @GetMapping("/chooseassistant/{divisionNumber}")
    public String editAssistantPerson(@PathVariable long id, @PathVariable int divisionNumber, Model model) {

        Raid participantRaid = raidService.find(id);
        model.addAttribute("raid", participantRaid);

        Division participantDivision = divisionService.find(divisionNumber);

        Participant currParticipant = participantService.findParticipantByParticipantRaidAndParticipantDivision(participantRaid, participantDivision);
        System.out.println(currParticipant);
        model.addAttribute("gottenparticipant", currParticipant);

        Set<Person> assistants = participantRaid.getAssistantPersons();

        model.addAttribute("assistants",assistants);
        return "editdata";
    }

    @PostMapping("/chooseassistant/{divisionNumber}")
    public String saveAssistantPerson(@PathVariable long id, @PathVariable int divisionNumber, HttpServletRequest request) {

        String idParameter = request.getParameter("assistantPerson");

        if (idParameter != null) {
            long personId = Long.parseLong(idParameter);
            Person person = personService.find(personId);

            Raid participantRaid = raidService.find(id);
            Division participantDivision = divisionService.find(divisionNumber);

            Participant participant = participantService.findParticipantByParticipantRaidAndParticipantDivision(participantRaid, participantDivision);

            participantService.save(participant);

        }

        return "redirect:/raid/"+id;
    }

    @GetMapping("/addparticipants")
    public String addParticipants(@PathVariable long id, Model model) {
        Raid raid = raidService.find(id);
        model.addAttribute("raid", raid);

        Set<Division> raidDivisions = raid.getDivisions();
        List<Division> allDivisions = divisionService.findOspDivisions();

        Iterator<Division> iterator = allDivisions.iterator();
        while (iterator.hasNext()) {
            Division next = iterator.next();
            for (Division raidDivision : raidDivisions) {
                if (raidDivision.equals(next)) {
                    iterator.remove();
                    break;
                }
            }
        }

        System.out.println(allDivisions);

        model.addAttribute("divisions", allDivisions);

        return "editdata";
    }

    @PostMapping("/addparticipants")
    public String saveParticipants(@PathVariable long id, HttpServletRequest request) {
        Raid raid = raidService.find(id);

        String[] checkboxNamesList= request.getParameterValues("divisions");

        for (int i = 0; i < checkboxNamesList.length; i++) {

            int divisionNumber = Integer.parseInt(checkboxNamesList[i]);
            Division division = divisionService.find(divisionNumber);
            raid.getDivisions().add(division);
        }


        raidService.save(raid);
        return "redirect:/raid/" + id;
    }

    @GetMapping("/removeparticipant/{divisionNumber}")
    public String removeParticipant(@PathVariable long id, @PathVariable int divisionNumber) {
        Raid raid = raidService.find(id);
        Division division = divisionService.find(divisionNumber);
        Set<Division> participants = raid.getDivisions();
        participants.removeIf(next -> next.equals(division));
        raidService.save(raid);
        return "redirect:/raid/"+id;
    }

    @GetMapping("/editraid")
    public String editRaid(@PathVariable long id, Model model) {
        Raid raid = raidService.find(id);
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

}
