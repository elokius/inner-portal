package com.vergl.filling.controller;

import com.vergl.config.PrintForm;
import com.vergl.filling.model.*;
import com.vergl.filling.scheduledtasks.StatDataUpdaterThread;
import com.vergl.filling.service.SelectPartService;
import com.vergl.filling.service.StatDataService;
import com.vergl.filling.service.StatFormGroupService;
import com.vergl.filling.service.StatFormService;
import com.vergl.raid.model.Division;
import com.vergl.raid.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.03.17
 */
@Controller
@RequestMapping("/statforms")
public class StatController {


    @Autowired
    private DivisionService divisionService;

    @Autowired
    private StatFormService statFormService;

    @Autowired
    private StatDataService statDataService;

    @Autowired
    private StatFormGroupService statFormGroupService;

    @Autowired
    private SelectPartService selectPartService;

    @Autowired
    private PrintForm printForm;

    @Autowired
    private StatDataUpdaterThread statDataUpdaterThread;

    @GetMapping
    public String statFormsList(Model model) {

        List<SelectPart> selectParts = selectPartService.findAll();
        List<StatFormGroup> statFormGroups = statFormGroupService.findAll();
        List<StatForm> statForms = statFormService.findAll();
        model.addAttribute("selectParts", selectParts);
        model.addAttribute("statformgroups", statFormGroups);
        model.addAttribute("statforms", statForms);
        return "statformlist";
    }

    @GetMapping("/{formId}")
    public String showStatForm(@PathVariable Long formId, Model model) {

        //Коллекция, содержащая отдел и значения каждого столбца
        Map<Division, List<String>> values = new HashMap<>();

        //Коллекция, хранящая сумму всех значений столбца
        Map<String, List<String>> summary = new HashMap<>();

        StatForm statForm = statFormService.findById(formId);

        List<Division> divisions = divisionService.findOspDivisions();

        List<FilterType> filterTypes = statForm.getFilterTypes();

        //Список всех сумм актуальных значений столбцов
        List<Integer> sums = createListWithZeroValues(filterTypes);

        //Список разниц значений столбцов
        List<Integer> diffsums = createListWithZeroValues(filterTypes);

        generateLists(values, statForm, divisions, filterTypes, sums, diffsums);

        List<String> sumsOfString = createResultList(sums, diffsums);

        summary.put("Итого", sumsOfString);

        model.addAttribute("statform", statForm);
        model.addAttribute("values", values);
        model.addAttribute("filtertypes", filterTypes);
        model.addAttribute("summary", summary);

        return "statformdata";
    }



    @GetMapping("/{formId}/print")
    public void downloadFile(HttpServletResponse response, @PathVariable long formId) throws Exception {
        //Коллекция, содержащая отдел и значения каждого столбца
        Map<Division, List<String>> values = new LinkedHashMap<>();

        //Коллекция, хранящая сумму всех значений столбца
        Map<String, List<String>> summary = new HashMap<>();

        StatForm statForm = statFormService.findById(formId);

        List<Division> divisions = divisionService.findOspDivisions();

        List<FilterType> filterTypes = statForm.getFilterTypes();

        List<Integer> sums = createListWithZeroValues(filterTypes);

        List<Integer> diffsums = createListWithZeroValues(filterTypes);

        generateLists(values, statForm, divisions, filterTypes, sums, diffsums);

        List<String> sumsOfString = createResultList(sums, diffsums);

        summary.put("Итого", sumsOfString);

        File file = printForm.createOdsStatForm(statForm, values, filterTypes, summary);

        if (!file.exists()) {
            String errorMessage = "Произошла ошибка. Файл не существует.";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        String filename = new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + "_" + formId + ".ods";
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


    @GetMapping("/{formId}/recalculate")
    @ResponseStatus(value = HttpStatus.OK)
    public void recalculateStatForm(@PathVariable long formId) {

        List<Division> divisions = divisionService.findOspDivisions();
        StatForm statForm = statFormService.findById(formId);

        for (Division division : divisions) {
            statDataUpdaterThread.fire(statForm, division);
        }
    }

    private void generateLists(Map<Division, List<String>> values, StatForm statForm, List<Division> divisions, List<FilterType> filterTypes, List<Integer> sums, List<Integer> diffsums) {
        for (Division division : divisions) {
            //Список всех значений для выбранного отдела
            List<Integer> currentDivisionValues = new ArrayList<>();
            //Список всех значений разницы для выбранного отдела
            List<Integer> differences = new ArrayList<>();

            for (FilterType filterType : filterTypes) {
                List<StatData> results = statDataService.findFirst2ByStatFormAndFilterTypeAndDivisionOrderByActualDateDesc(statForm, filterType, division);
                int latestValue;
                int difference;
                if (results.size() != 0) {
                    latestValue = results.get(0).getValue();
                    if (results.size() > 1) {
                        difference = latestValue - results.get(1).getValue();
                    } else {
                        difference = 0;
                    }
                } else {
                    latestValue = 0;
                    difference = 0;
                }
                currentDivisionValues.add(latestValue);
                differences.add(difference);
            }
            //Список актуальных значений / значений разницы для выбранного отдела
            List<String> currentDivisionValuesOfStringType = new ArrayList<>();
            if (currentDivisionValues.size() != 0) {
                for (int k = 0; k < currentDivisionValues.size(); k++) {
                    String strToAdd = currentDivisionValues.get(k) + "/" + differences.get(k);
                    currentDivisionValuesOfStringType.add(strToAdd);
                }
            }
            values.put(division, currentDivisionValuesOfStringType);

            if (currentDivisionValues.size() != 0) {
                for (int i = 0; i < currentDivisionValues.size(); i++) {
                    sums.set(i, sums.get(i) + currentDivisionValues.get(i));
                }
            }

            if (differences.size() != 0) {
                for (int i = 0; i < differences.size(); i++) {
                    diffsums.set(i, diffsums.get(i) + differences.get(i));
                }
            }
        }
    }


    private List<String> createResultList(List<Integer> sums, List<Integer> diffsums) {
        List<String> sumsOfString = new ArrayList<>();
        for (int i = 0; i < sums.size(); i++) {
            String strToAdd = sums.get(i) + "/" + diffsums.get(i);
            sumsOfString.add(strToAdd);
        }
        return sumsOfString;
    }

    private List<Integer> createListWithZeroValues(List<FilterType> filterTypes) {
        //Список всех сумм актуальных значений столбцов
        List<Integer> sums = new ArrayList<>();
        for (int j = 0; j < filterTypes.size(); j++) {
            sums.add(j, 0);
        }
        return sums;
    }

}
