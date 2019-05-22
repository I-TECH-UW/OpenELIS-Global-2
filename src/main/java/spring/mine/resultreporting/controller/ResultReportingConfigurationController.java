package spring.mine.resultreporting.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.controller.BaseController;
import spring.mine.resultreporting.form.ResultReportingConfigurationForm;
import spring.service.scheduler.CronSchedulerService;
import spring.service.siteinformation.SiteInformationService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService.ConfigurationDomain;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration;
import us.mn.state.health.lims.scheduler.LateStartScheduler;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class ResultReportingConfigurationController extends BaseController {

	private SiteInformationService siteInformationService;
	private CronSchedulerService schedulerService;
	private static final String NEVER = "never";
	private static final String CRON_POSTFIX = "? * *";
	private static final String CRON_PREFIX = "0 ";

	@RequestMapping(value = "/ResultReportingConfiguration", method = RequestMethod.GET)
	public ModelAndView showResultReportingConfiguration(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ResultReportingConfigurationForm form = new ResultReportingConfigurationForm();

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, "false");

		ExchangeConfigurationService configService = new ExchangeConfigurationService(ConfigurationDomain.REPORT);

		PropertyUtils.setProperty(form, "reports", configService.getConfigurations());
		PropertyUtils.setProperty(form, "hourList", DisplayListService.getList(ListType.HOURS));
		PropertyUtils.setProperty(form, "minList", DisplayListService.getList(ListType.MINS));

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/ResultReportingConfiguration", method = RequestMethod.POST)
	public ModelAndView showUpdateResultReportingConfiguration(HttpServletRequest request,
			@ModelAttribute("form") @Validated(ResultReportingConfigurationForm.ResultReportConfig.class) ResultReportingConfigurationForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}
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

		try {
			updateInformationAndSchedulers(informationList, scheduleList);
		} catch (HibernateException e) {
			return findForward(FWD_FAIL_INSERT, form);
		}

		ConfigurationProperties.forceReload();
		new LateStartScheduler().restartSchedules();

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	@Transactional 
	private void updateInformationAndSchedulers(List<SiteInformation> informationList,
			List<CronScheduler> scheduleList) {
		for (SiteInformation info : informationList) {
			siteInformationService.update(info);
		}

		for (CronScheduler scheduler : scheduleList) {
			schedulerService.update(scheduler);
		}

		ConfigurationProperties.forceReload();
	}

	private CronScheduler setScheduleInformationFor(ReportingConfiguration config) {
		CronScheduler scheduler = schedulerService.get(config.getSchedulerId());

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
		SiteInformation siteInformation = siteInformationService.get(id);

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
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "resultReportingConfigurationDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "resultReportingConfigurationDefinition";
		} else {
			return "PageNotFound";
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
