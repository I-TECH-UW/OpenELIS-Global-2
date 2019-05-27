package spring.service.scheduler;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;

public interface CronSchedulerService extends BaseObjectService<CronScheduler> {

	String insert(CronScheduler cronScheduler);

	List<CronScheduler> getAllCronSchedules();

	CronScheduler getCronScheduleByJobName(String jobName);

	CronScheduler getCronScheduleById(String schedulerId);
}
