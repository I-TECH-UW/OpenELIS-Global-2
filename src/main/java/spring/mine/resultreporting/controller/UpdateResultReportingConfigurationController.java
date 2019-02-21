package spring.mine.resultreporting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.resultreporting.form.ResultReportingConfigurationForm;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.scheduler.LateStartScheduler;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class UpdateResultReportingConfigurationController extends BaseController {

	private SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
	private CronSchedulerDAO schedulerDAO = new CronSchedulerDAOImpl();
	private static final String NEVER = "never";
	private static final String CRON_POSTFIX = "? * *";
	private static final String CRON_PREFIX = "0 ";

	@RequestMapping(value = "/UpdateResultReportingConfiguration", method = RequestMethod.GET)
	public ModelAndView showUpdateResultReportingConfiguration(HttpServletRequest request,
			@ModelAttribute("form") ResultReportingConfigurationForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ResultReportingConfigurationForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		
		List<SiteInformation> informationList = new ArrayList<>();
		List<CronScheduler> scheduleList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<ReportingConfiguration> reports = (List<ReportingConfiguration>) form.get("reports");

		for (ReportingConfiguration config : reports) {
			informationList.add(setSiteInformationFor(config.getUrl(), config.getUrlId()));
			informationList.add(setSiteInformationFor(config.getEnabled(), config.getEnabledId()));

			if (config.getIsScheduled()) {
				CronScheduler scheduler = setScheduleInformationFor(config);
				if (scheduler != null) {
					scheduleList.add(scheduler);
				}
			}
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (SiteInformation info : informationList) {
				siteInformationDAO.updateData(info);
			}

			for (CronScheduler scheduler : scheduleList) {
				schedulerDAO.update(scheduler);
			}

			tx.commit();
			ConfigurationProperties.forceReload();
		} catch (HibernateException e) {
			tx.rollback();
		}

		ConfigurationProperties.forceReload();
		new LateStartScheduler().restartSchedules();

		return findForward(forward, form);
	}

	private CronScheduler setScheduleInformationFor(ReportingConfiguration config) {
		CronScheduler scheduler = schedulerDAO.getCronScheduleById(config.getSchedulerId());

		if (scheduler != null) {
			String cronStatement = createCronStatement(config.getScheduleHours(), config.getScheduleMin(), false);
			scheduler.setActive("enable".equals(config.getEnabled()));
			scheduler.setCronStatement(cronStatement);
			scheduler.setSysUserId(getSysUserId(request));
		}
		return scheduler;
	}

	private String createCronStatement(String hour, String min, boolean tweak) {
		StringBuilder cronBuilder = new StringBuilder();

		if (GenericValidator.isBlankOrNull(hour) || GenericValidator.isBlankOrNull(min)) {
			cronBuilder.append(NEVER);
		} else {
			cronBuilder.append(CRON_PREFIX);
			if (tweak) {
				int minute = Math.min(Integer.parseInt(min) + (int) (Math.random() * 9.0), 59);
				cronBuilder.append(String.valueOf(minute));
			} else {
				cronBuilder.append(min);
			}
			cronBuilder.append(" ");
			cronBuilder.append(hour);
			cronBuilder.append(" ");
			cronBuilder.append(CRON_POSTFIX);
		}

		return cronBuilder.toString();
	}

	private SiteInformation setSiteInformationFor(String value, String id) {
		SiteInformation siteInformation = siteInformationDAO.getSiteInformationById(id);

		if (siteInformation.getId() != null) {

			if ("boolean".equals(siteInformation.getValueType())) {
				siteInformation.setValue("enable".equals(value) ? "true" : "false");
			} else {
				siteInformation.setValue(value);
			}

			siteInformation.setSysUserId(getSysUserId(request));
		}
		return siteInformation;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("masterListsPageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "resultreporting.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "resultreporting.browse.title";
	}
}
