package com.vergl.config;

import com.vergl.filling.scheduledtasks.RegMvvUpdater;
import com.vergl.filling.scheduledtasks.StatDataUpdater;
import com.vergl.phonebook.scheduledtasks.PersonScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.04.17
 */
@Configuration
public class TaskConfiguration implements SchedulingConfigurer {

    @Autowired
    private StatDataUpdater statDataUpdater;

    @Autowired
    private PersonScheduler personScheduler;

    @Autowired
    private RegMvvUpdater regMvvUpdater;

    @Value("${scheduler.cron.updateStats}")
    private String statDataUpdaterCron;

    @Value("${scheduler.cron.updatePeople}")
    private String personSchedulerCron;

    @Value("${scheduler.cron.updateRegMvv}")
    private String regMvvDataUpdaterCron;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addCronTask(statDataUpdater,statDataUpdaterCron);
        scheduledTaskRegistrar.addCronTask(personScheduler,personSchedulerCron);
        scheduledTaskRegistrar.addCronTask(regMvvUpdater,regMvvDataUpdaterCron);
    }
}
