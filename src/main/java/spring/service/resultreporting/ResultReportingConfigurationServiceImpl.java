package spring.service.resultreporting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.scheduler.CronSchedulerService;
import spring.service.siteinformation.SiteInformationService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Service
public class ResultReportingConfigurationServiceImpl implements ResultReportingConfigurationService {

	@Autowired
	private SiteInformationService siteInformationService;
	@Autowired
	private CronSchedulerService schedulerService;

	@Override
	@Transactional
	public void updateInformationAndSchedulers(List<SiteInformation> informationList,
			List<CronScheduler> scheduleList) {
		for (SiteInformation info : informationList) {
			siteInformationService.update(info);
		}

		for (CronScheduler scheduler : scheduleList) {
			schedulerService.update(scheduler);
		}

		ConfigurationProperties.forceReload();
	}
}
