package com.vergl.filling.scheduledtasks;

import com.vergl.filling.model.StatForm;
import com.vergl.filling.service.StatFormService;
import com.vergl.raid.model.Division;
import com.vergl.raid.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 13.03.17
 */
@Component
public class StatDataUpdater implements Runnable {

    @Autowired
    private StatFormService statFormService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private StatDataUpdaterThread statDataUpdaterThread;


    public void run() {

        List<StatForm> statForms = statFormService.findAll();
        List<Division> divisions = divisionService.findOspDivisions();

        System.out.println("Начался пересчет статистики");
        for (StatForm statForm : statForms) {
            //Все стат.формы, кроме Рег. МВВ
            if (statForm.getStatFormGroup().getSelectPart().getId() != 3) {
                for (Division division: divisions) {
                    statDataUpdaterThread.fire(statForm,division);
                }
                statForm.setActualDate(new Date());
                statFormService.save(statForm);
            }
        }
        System.out.println("Закончился пересчет статистики");
    }
}
