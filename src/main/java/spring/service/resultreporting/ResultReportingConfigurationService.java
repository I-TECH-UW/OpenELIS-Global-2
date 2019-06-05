package spring.service.resultreporting;

import java.util.List;

import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public interface ResultReportingConfigurationService {

	void updateInformationAndSchedulers(List<SiteInformation> informationList, List<CronScheduler> scheduleList);

}
