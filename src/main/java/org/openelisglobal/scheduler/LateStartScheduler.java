/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package org.openelisglobal.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.MalariaSurveilance.MalariaSurveilanceJob;
import org.openelisglobal.dataexchange.aggregatereporting.AggregateReportJob;
import org.openelisglobal.scheduler.service.CronSchedulerService;
import org.openelisglobal.scheduler.valueholder.CronScheduler;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LateStartScheduler {

    @Autowired
    CronSchedulerService cronSchedulerService;

    private static final String NEVER = "never";

    private static Map<String, Class<? extends Job>> scheduleJobMap;

    private Scheduler scheduler;

    static {
        scheduleJobMap = new HashMap<>();
        scheduleJobMap.put("sendSiteIndicators", AggregateReportJob.class);
        scheduleJobMap.put("sendMalariaSurviellanceReport", MalariaSurveilanceJob.class);
    }

    public void restartSchedules() {
        new Restarter().start();
    }

    public class Restarter extends Thread {
        @Override
        public void run() {
            try {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.shutdown();
                checkAndStartScheduler();
            } catch (SchedulerException e) {
                LogEvent.logDebug(e);
            }
        }
    }

    public void checkAndStartScheduler() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            List<CronScheduler> schedulers = cronSchedulerService.getAll();

            for (CronScheduler schedule : schedulers) {
                addOrRunSchedule(scheduler, schedule);
            }

            scheduler.start();
        } catch (SchedulerException e) {
            LogEvent.logDebug(e);
        } catch (ParseException e) {
            LogEvent.logDebug(e);
        }
    }

    private void addOrRunSchedule(Scheduler scheduler, CronScheduler schedule)
            throws SchedulerException, ParseException {
        int currentHour = DateUtil.getCurrentHour();
        int currentMin = DateUtil.getCurrentMinute();

        if (!schedule.getActive() || NEVER.equals(schedule.getCronStatement())) {
            return;
        }

        String jobName = schedule.getJobName();
        LogEvent.logInfo(this.getClass().getName(), "method unkown", "Adding cron job: " + jobName);

        Class<? extends Job> targetJob = scheduleJobMap.get(jobName);

        if (targetJob == null) {
            return;
        }

        JobDetail job = newJob(targetJob).withIdentity(jobName + "Job", jobName).build();

        Trigger trigger = newTrigger().withIdentity(jobName + "Trigger", jobName)
                .withSchedule(cronSchedule(schedule.getCronStatement())).forJob(jobName + "Job", jobName).build();

        scheduler.scheduleJob(job, trigger);

        String[] cronParts = schedule.getCronStatement().split(" ");

        try {
            int cronHour = Integer.parseInt(cronParts[2]);

            if (cronHour < currentHour || (cronHour == currentHour && Integer.parseInt(cronParts[1]) < currentMin)) {
                new ImmediateRunner(scheduler, jobName).start();
            }
        } catch (NumberFormatException e) {
            LogEvent.logInfo(this.getClass().getName(), "method unkown", "Malformed cron statement." + schedule.getCronStatement());
        }
    }

    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    class ImmediateRunner extends Thread {
        private Scheduler scheduler;
        private String jobName;

        public ImmediateRunner(Scheduler scheduler, String jobName) {
            this.scheduler = scheduler;
            this.jobName = jobName;
        }

        @Override
        public void run() {
            try {
                // so everything doesn't happen at once
                long delay = 2000L * Math.round(Math.random() * 10);
                sleep(delay);
                synchronized (scheduler) {
                    if (!scheduler.isShutdown()) {
                        scheduler.triggerJob(new JobKey(jobName + "Job", jobName));
                    }
                }
            } catch (InterruptedException e) {
                LogEvent.logDebug(e);
            } catch (SchedulerException e) {
                LogEvent.logDebug(e);
            }
        }

    }
}
