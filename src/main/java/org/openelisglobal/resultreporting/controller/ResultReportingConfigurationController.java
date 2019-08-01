package org.openelisglobal.resultreporting.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.resultreporting.form.ResultReportingConfigurationForm;
import org.openelisglobal.resultreporting.service.ResultReportingConfigurationService;
import org.openelisglobal.scheduler.service.CronSchedulerService;
import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.ExchangeConfigurationService;
import org.openelisglobal.common.services.ExchangeConfigurationService.ConfigurationDomain;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.dataexchange.resultreporting.beans.ReportingConfiguration;
import org.openelisglobal.scheduler.LateStartScheduler;
import org.openelisglobal.scheduler.valueholder.CronScheduler;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;

@Controller
public class ResultReportingConfigurationController extends BaseController {

    @Autowired
    private SiteInformationService siteInformationService;
    @Autowired
    private CronSchedulerService schedulerService;
    @Autowired
    private ResultReportingConfigurationService resultReportingConfigurationService;
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
        PropertyUtils.setProperty(form, "hourList", DisplayListService.getInstance().getList(ListType.HOURS));
        PropertyUtils.setProperty(form, "minList", DisplayListService.getInstance().getList(ListType.MINS));

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
            resultReportingConfigurationService.updateInformationAndSchedulers(informationList, scheduleList);
        } catch (LIMSRuntimeException e) {
            return findForward(FWD_FAIL_INSERT, form);
        }

        ConfigurationProperties.forceReload();
        new LateStartScheduler().restartSchedules();

        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        return findForward(FWD_SUCCESS_INSERT, form);
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
