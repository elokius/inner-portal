package com.vergl.raid.controller;

import com.vergl.filling.model.*;
import com.vergl.filling.service.*;
import com.vergl.raid.model.Division;
import com.vergl.raid.service.DivisionService;
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
import java.util.Objects;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 20.03.17
 */
@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private DebtClassService debtClassService;

    @Autowired
    private DebtorTypeService debtorTypeService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private DocstatusService docstatusService;

    @Autowired
    private StatFormGroupService statFormGroupService;

    @Autowired
    private StatFormService statFormService;

    @Autowired
    private FilterTypeService filterTypeService;


    @GetMapping("/debtclasses")
    public String showDebtClasses(Model model) {
        model.addAttribute("debtclasses", debtClassService.findAll());
        return "showdebtclasses";
    }

    @PostMapping("/debtclasses/add")
    public String addDebtClass(HttpServletRequest request) {

        int number = Integer.valueOf(request.getParameter("number"));
        String caption = request.getParameter("caption");
        if (number != 0 && caption != null) {
            DebtClass newDebtClass = new DebtClass();
            newDebtClass.setNumber(number);
            newDebtClass.setCaption(caption);
            debtClassService.save(newDebtClass);
        }

        return "redirect:/admin/debtclasses";
    }

    @GetMapping("/debtclass/{id}/remove")
    public String removeDebtClass(@PathVariable long id) {
        DebtClass removing = debtClassService.findById(id);
        debtClassService.remove(removing);

        return "redirect:/admin/debtclasses";
    }

    @GetMapping("/debtclass/{id}/edit")
    public String editDebtClass(@PathVariable long id, Model model) {
        DebtClass editable = debtClassService.findById(id);
        model.addAttribute("debtclass", editable);
        return "editdebtclass";
    }

    @PostMapping("/debtclass/{id}/save")
    public String saveDebtClass(@PathVariable long id, HttpServletRequest request) {
        DebtClass edited = debtClassService.findById(id);
        int debtClassNumber = Integer.parseInt(request.getParameter("number"));
        String debtClassCaption = request.getParameter("caption");

        edited.setNumber(debtClassNumber);
        edited.setCaption(debtClassCaption);

        debtClassService.save(edited);

        return "redirect:/admin/debtclasses";
    }

    @GetMapping("/debtortypes")
    public String showDebtorTypes(Model model) {
        model.addAttribute("debtortypes", debtorTypeService.findAll());
        return "showdebtortypes";
    }

    @PostMapping("/debtortypes/add")
    public String addDebtorType(HttpServletRequest request) {

        Integer number = Integer.valueOf(request.getParameter("number"));
        String caption = request.getParameter("caption");
        if (number != 0 && caption != null) {
            DebtorType debtorType = new DebtorType();
            debtorType.setNumber(number);
            debtorType.setCaption(caption);
            debtorTypeService.save(debtorType);
        }

        return "redirect:/admin/debtortypes";
    }

    @GetMapping("/debtortype/{id}/remove")
    public String removeDebtorType(@PathVariable long id) {
        DebtorType removing = debtorTypeService.findById(id);
        debtorTypeService.remove(removing);

        return "redirect:/admin/debtortypes";
    }

    @GetMapping("/debtortype/{id}/edit")
    public String editDebtorType(@PathVariable long id, Model model) {
        DebtorType editable = debtorTypeService.findById(id);
        model.addAttribute("debtortype", editable);

        return "editdebtortype";
    }

    @PostMapping("/debtortype/{id}/save")
    public String saveDebtorType(@PathVariable long id, HttpServletRequest request) {
        DebtorType edited = debtorTypeService.findById(id);
        int debtorTypeNumber = Integer.parseInt(request.getParameter("number"));
        String debtorTypeCaption = request.getParameter("caption");
        edited.setNumber(debtorTypeNumber);
        edited.setCaption(debtorTypeCaption);
        debtorTypeService.save(edited);

        return "redirect:/admin/debtortypes";
    }


    @GetMapping("/divisions")
    public String showDivisions(Model model) {
        model.addAttribute("divisions", divisionService.findAll());
        return "showdivisions";
    }

    @PostMapping("/divisions/add")
    public String addDivision(HttpServletRequest request) {

        Long number = Long.valueOf(request.getParameter("number"));
        String caption = request.getParameter("caption");
        String authority = request.getParameter("authority");
        boolean isActualOsp = request.getParameter("actualOsp") != null;
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String telephoneCode = request.getParameter("telephoneCode");
        String fax = request.getParameter("fax");
        String ipAddress = request.getParameter("ipAddress");
        String dutyPhone = request.getParameter("dutyPhone");

        if (number != 0 && caption != null) {
            Division division = new Division();
            division.setNumber(number);
            division.setCaption(caption);
            division.setAuthority(authority);
            division.setActualOsp(isActualOsp);
            division.setAddress(address);
            division.setEmail(email);
            division.setTelephoneCode(telephoneCode);
            division.setFax(fax);
            division.setIpAddress(ipAddress);
            division.setDutyPhone(dutyPhone);
            divisionService.save(division);
        }

        return "redirect:/admin/divisions";
    }

    @GetMapping("/division/{id}/remove")
    public String removeDivision(@PathVariable long id) {
        Division removing = divisionService.findById(id);
        divisionService.remove(removing);

        return "redirect:/admin/divisions";
    }

    @GetMapping("/division/{id}/edit")
    public String editDivision(@PathVariable long id, Model model) {
        Division editable = divisionService.findById(id);
        model.addAttribute("division", editable);
        return "editdivision";
    }

    @PostMapping("/division/{id}/save")
    public String editDivision(@PathVariable long id, HttpServletRequest request) {
        Division edited = divisionService.findById(id);
        long number = Long.parseLong(request.getParameter("number"));
        String caption = request.getParameter("caption");
        String authority = request.getParameter("authority");
        boolean isActualOsp = request.getParameter("actualOsp") != null;
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String telephoneCode = request.getParameter("telephoneCode");
        String fax = request.getParameter("fax");
        String ipAddress = request.getParameter("ipAddress");
        String dutyPhone = request.getParameter("dutyPhone");

        edited.setNumber(number);
        edited.setCaption(caption);
        edited.setAuthority(authority);
        edited.setActualOsp(isActualOsp);
        edited.setAddress(address);
        edited.setEmail(email);
        edited.setTelephoneCode(telephoneCode);
        edited.setFax(fax);
        edited.setIpAddress(ipAddress);
        edited.setDutyPhone(dutyPhone);
        divisionService.save(edited);

        return "redirect:/admin/divisions";
    }


    @GetMapping("/docstatuses")
    public String showDocstatuses(Model model) {
        model.addAttribute("docstatuses", docstatusService.findAll());
        return "showdocstatuses";
    }

    @PostMapping("/docstatuses/add")
    public String addDocstatus(HttpServletRequest request) {

        Integer number = Integer.valueOf(request.getParameter("number"));
        String caption = request.getParameter("caption");
        if (number != 0 && caption != null) {
            Docstatus docstatus = new Docstatus();
            docstatus.setNumber(number);
            docstatus.setCaption(caption);
            docstatusService.save(docstatus);
        }

        return "redirect:/admin/docstatuses";
    }

    @GetMapping("/docstatus/{id}/remove")
    public String removeDocstatus(@PathVariable long id) {
        Docstatus removing = docstatusService.findById(id);
        docstatusService.remove(removing);

        return "redirect:/admin/docstatuses";
    }

    @GetMapping("/docstatus/{id}/edit")
    public String editDocstatus(@PathVariable long id, Model model) {
        Docstatus editable = docstatusService.findById(id);
        model.addAttribute("docstatus", editable);

        return "editdocstatus";
    }

    @PostMapping("/docstatus/{id}/save")
    public String saveDocstatus(@PathVariable long id, HttpServletRequest request) {
        Docstatus edited = docstatusService.findById(id);
        int debtorTypeNumber = Integer.parseInt(request.getParameter("number"));
        String debtorTypeCaption = request.getParameter("caption");
        edited.setNumber(debtorTypeNumber);
        edited.setCaption(debtorTypeCaption);
        docstatusService.save(edited);

        return "redirect:/admin/docstatuses";
    }


    @GetMapping("/statformgroups")
    public String showStatFormGroups(Model model) {
        model.addAttribute("statformgroups", statFormGroupService.findAll());
        return "showstatformgroups";
    }

    @PostMapping("/statformgroups/add")
    public String addStatFormGroup(HttpServletRequest request) {

        String caption = request.getParameter("caption");
        if (caption != null) {
            StatFormGroup statFormGroup = new StatFormGroup();
            statFormGroup.setCaption(caption);
            statFormGroupService.save(statFormGroup);
        }

        return "redirect:/admin/statformgroups";
    }

    @GetMapping("/statformgroup/{id}/remove")
    public String removeStatFormGroup(@PathVariable long id) {
        StatFormGroup removing = statFormGroupService.findById(id);
        statFormGroupService.remove(removing);

        return "redirect:/admin/statformgroups";
    }

    @GetMapping("/statformgroup/{id}/edit")
    public String editStatFormGroup(@PathVariable long id, Model model) {
        StatFormGroup editable = statFormGroupService.findById(id);
        model.addAttribute("statformgroup", editable);

        return "editstatformgroup";
    }

    @PostMapping("/statformgroup/{id}/save")
    public String saveStatFormGroup(@PathVariable long id, HttpServletRequest request) {
        StatFormGroup edited = statFormGroupService.findById(id);
        String statFormGroupCaption = request.getParameter("caption");
        edited.setCaption(statFormGroupCaption);
        statFormGroupService.save(edited);

        return "redirect:/admin/statformgroups";
    }


    @GetMapping("/statforms")
    public String showStatForms(Model model) {
        model.addAttribute("statforms", statFormService.findAll());
        model.addAttribute("debtorTypes", debtorTypeService.findAll());
        model.addAttribute("debtClasses", debtClassService.findAll());
        model.addAttribute("statFormGroups", statFormGroupService.findAll());
        model.addAttribute("filterTypes", filterTypeService.findAll());
        model.addAttribute("docstatuses", docstatusService.findAll());
        return "showstatforms";
    }

    @PostMapping("/statforms/add")
    public String addStatForm(HttpServletRequest request) throws ParseException {
        StatForm statForm = new StatForm();

        String code = request.getParameter("code");
        System.out.println(code);
        if (code != null) {
            statForm.setCode(code);
        }

        String name = request.getParameter("name");
        System.out.println(name);
        if (name != null) {
            statForm.setName(name);
        }

        String debtorTypeId = request.getParameter("debtorType");
        System.out.println(debtorTypeId);
        if (debtorTypeId != null && !Objects.equals(debtorTypeId, "")) {
            DebtorType debtorType = debtorTypeService.findById(Long.parseLong(debtorTypeId));
            statForm.setDebtorType(debtorType);
        }

        String debtClassId = request.getParameter("debtClass");
        System.out.println(debtClassId);
        if (debtClassId != null && !Objects.equals(debtClassId, "")) {
            DebtClass debtClass = debtClassService.findById(Long.parseLong(debtClassId));
            statForm.setDebtClass(debtClass);
        }

        String statFormGroupId = request.getParameter("statFormGroup");
        System.out.println(statFormGroupId);
        if (statFormGroupId != null && !Objects.equals(statFormGroupId, "")) {
            StatFormGroup statFormGroup = statFormGroupService.findById(Long.parseLong(statFormGroupId));
            statForm.setStatFormGroup(statFormGroup);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String minRisedate = request.getParameter("minRisedate");
        System.out.println(minRisedate);
        if (minRisedate != null && !Objects.equals(minRisedate, "")) {
            statForm.setMinRisedate(sdf.parse(minRisedate));
        }

        String maxRisedate = request.getParameter("maxRisedate");
        System.out.println(maxRisedate);
        if (maxRisedate != null && !Objects.equals(maxRisedate, "")) {
            statForm.setMaxRisedate(sdf.parse(maxRisedate));
        }

        String minDebtsum = request.getParameter("minDebtsum");
        System.out.println(minDebtsum);
        if (minDebtsum != null && !Objects.equals(minDebtsum, "")) {
            statForm.setMaxDebtsum(Integer.parseInt(minDebtsum));
        }

        String maxDebtsum = request.getParameter("maxDebtsum");
        System.out.println(maxDebtsum);
        if (maxDebtsum != null && !Objects.equals(maxDebtsum, "")) {
            statForm.setMaxDebtsum(Integer.parseInt(maxDebtsum));
        }

        String additionalInfo = request.getParameter("additionalInfo");
        System.out.println(additionalInfo);
        if (additionalInfo != null && !Objects.equals(additionalInfo, "")) {
            statForm.setAdditionalInfo(additionalInfo);
        }

        String[] filterTypeCheckboxes = request.getParameterValues("filtertype");
        System.out.println(filterTypeCheckboxes);
        for (String filterTypeCheckbox : filterTypeCheckboxes) {

            FilterType filterType = filterTypeService.findOne(Long.parseLong(filterTypeCheckbox));
            statForm.getFilterTypes().add(filterType);
        }

        String[] docstatusCheckboxes = request.getParameterValues("docstatus");
        System.out.println(docstatusCheckboxes);
        for (String docstatusCheckbox : docstatusCheckboxes) {

            Docstatus docstatus = docstatusService.findById(Long.parseLong(docstatusCheckbox));
            statForm.getDocstatuses().add(docstatus);
        }

        statFormService.save(statForm);

        return "redirect:/admin/statforms";
    }

    @GetMapping("/statform/{id}/remove")
    public String removeStatForm(@PathVariable long id) {
        StatForm removing = statFormService.findById(id);
        System.out.println(removing);
        removing.getDocstatuses().clear();
        removing.getFilterTypes().clear();
        statFormService.remove(removing);

        return "redirect:/admin/statforms";
    }

    @GetMapping("/statform/{id}/edit")
    public String editStatForm(@PathVariable long id, Model model) {
        StatForm editable = statFormService.findById(id);
        model.addAttribute("statform", editable);
        model.addAttribute("debtorTypes", debtorTypeService.findAll());
        model.addAttribute("debtClasses", debtClassService.findAll());
        model.addAttribute("statFormGroups", statFormGroupService.findAll());
        model.addAttribute("filterTypes", filterTypeService.findAll());
        model.addAttribute("docstatuses", docstatusService.findAll());
        return "editstatform";
    }

    @PostMapping("/statform/{id}/save")
    public String saveStatForm(@PathVariable long id, HttpServletRequest request) throws ParseException {
        StatForm edited = statFormService.findById(id);
        String code = request.getParameter("code");
        if (code != null && !code.equals("")) {
            edited.setCode(code);
        } else {
            //Выбросить исключение
            System.out.println("Не заполнено поле: Код");
        }

        String name = request.getParameter("name");
        if (name != null && !name.equals("")) {
            edited.setName(name);
        } else {
            //Выбросить исключение
            System.out.println("Не заполнено поле: Название");
        }

        String debtorTypeId = request.getParameter("debtorType");
        if (debtorTypeId != null && !debtorTypeId.equals("")) {
            DebtorType debtorType = debtorTypeService.findById(Long.parseLong(debtorTypeId));
            edited.setDebtorType(debtorType);
        } else {
            edited.setDebtorType(null);
        }

        String debtClassId = request.getParameter("debtClass");
        if (debtClassId != null && !debtClassId.equals("")) {
            DebtClass debtClass = debtClassService.findById(Long.parseLong(debtClassId));
            edited.setDebtClass(debtClass);
        } else {
            edited.setDebtClass(null);
        }


        String statFormGroupId = request.getParameter("statFormGroup");
        if (statFormGroupId != null) {
            StatFormGroup statFormGroup = statFormGroupService.findById(Long.parseLong(statFormGroupId));
            edited.setStatFormGroup(statFormGroup);
        }



        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String minRisedate = request.getParameter("minRisedate");
        if (minRisedate != null && !minRisedate.equals("")) {
            edited.setMinRisedate(sdf.parse(minRisedate));
        } else {
            edited.setMinRisedate(null);
        }

        String maxRisedate = request.getParameter("maxRisedate");
        if (maxRisedate != null && !maxRisedate.equals("")) {
            edited.setMaxRisedate(sdf.parse(maxRisedate));
        } else {
            edited.setMaxRisedate(null);
        }

        String minDebtsum = request.getParameter("minDebtsum");
        if (minDebtsum != null && !minDebtsum.equals("")) {
            edited.setMinDebtsum(Integer.parseInt(minDebtsum));
        } else {
            edited.setMinDebtsum(0);
        }

        String maxDebtsum = request.getParameter("maxDebtsum");
        if (maxDebtsum != null && !maxDebtsum.equals("")) {
            edited.setMaxDebtsum(Integer.parseInt(maxDebtsum));
        } else {
            edited.setMaxDebtsum(0);
        }

        String additionalInfo = request.getParameter("additionalInfo");
        if (additionalInfo != null && !additionalInfo.equals("")) {
            edited.setAdditionalInfo(additionalInfo);
        } else {
            edited.setAdditionalInfo(null);
        }

        String[] filterTypeCheckboxes = request.getParameterValues("filterTypes");
        if (filterTypeCheckboxes != null) {
            edited.getFilterTypes().clear();
            for (String filterTypeCheckbox : filterTypeCheckboxes) {

                FilterType filterType = filterTypeService.findOne(Long.parseLong(filterTypeCheckbox));
                edited.getFilterTypes().add(filterType);
            }
        } else {
            edited.getFilterTypes().clear();
        }

        String[] docstatusCheckboxes = request.getParameterValues("docstatuses");
        if (docstatusCheckboxes != null) {
            edited.getDocstatuses().clear();
            for (String docstatusCheckbox : docstatusCheckboxes) {

                Docstatus docstatus = docstatusService.findById(Long.parseLong(docstatusCheckbox));
                edited.getDocstatuses().add(docstatus);
            }
        } else {
            edited.getDocstatuses().clear();
        }

        statFormService.save(edited);

        return "redirect:/admin/statforms";
    }

    @GetMapping("/filtertypes")
    public String showFilterTypes(Model model) {
        model.addAttribute("filtertypes", filterTypeService.findAll());
        return "showfiltertypes";
    }

    @PostMapping("/filtertypes/add")
    public String addFilterType(HttpServletRequest request) {
        FilterType newFilterType = new FilterType();

        String caption = request.getParameter("caption");
        if (caption != null && !caption.equals("")) {
            newFilterType.setCaption(caption);
        }
        String joinPart = request.getParameter("joinPart");
        if (joinPart != null && !joinPart.equals("")) {
            newFilterType.setJoinPart(joinPart);
        }
        String wherePart = request.getParameter("wherePart");
        if (wherePart != null && !wherePart.equals("")) {
            newFilterType.setWherePart(wherePart);
        }
        String description = request.getParameter("description");
        if (description != null && !description.equals("")) {
            newFilterType.setDescription(description);
        }

        filterTypeService.save(newFilterType);
        return "redirect:/admin/filtertypes";
    }

    @GetMapping("/filtertype/{id}/remove")
    public String removeFilterType(@PathVariable long id) {
        FilterType removing = filterTypeService.findById(id);
        filterTypeService.remove(removing);

        return "redirect:/admin/filtertypes";
    }

    @GetMapping("/filtertype/{id}/edit")
    public String editFilterType(@PathVariable long id, Model model) {
        FilterType editable = filterTypeService.findById(id);
        model.addAttribute("filtertype", editable);
        return "editfiltertype";
    }

    @PostMapping("/filtertype/{id}/save")
    public String saveFilterType(@PathVariable long id, HttpServletRequest request) {
        FilterType edited = filterTypeService.findById(id);
        String debtClassCaption = request.getParameter("caption");
        edited.setCaption(debtClassCaption);

        String joinPart = request.getParameter("joinPart");
        if (joinPart != null && !joinPart.equals("")) {
            edited.setJoinPart(joinPart);
        } else {
            edited.setJoinPart(null);
        }

        String wherePart = request.getParameter("wherePart");
        if (wherePart != null && !wherePart.equals("")) {
            edited.setWherePart(wherePart);
        } else {
            edited.setWherePart(null);
        }

        String description = request.getParameter("description");
        if (description != null && !description.equals("")) {
            edited.setDescription(description);
        } else {
            edited.setDescription("");
        }


        filterTypeService.save(edited);

        return "redirect:/admin/filtertypes";
    }


}
