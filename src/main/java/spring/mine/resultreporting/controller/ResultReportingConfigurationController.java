package spring.mine.resultreporting.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.resultreporting.form.ResultReportingConfigurationForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService.ConfigurationDomain;

@Controller
public class ResultReportingConfigurationController extends BaseController {
	@RequestMapping(value = "/ResultReportingConfiguration", method = RequestMethod.GET)
	public ModelAndView showResultReportingConfiguration(HttpServletRequest request,
			@ModelAttribute("form") ResultReportingConfigurationForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ResultReportingConfigurationForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, "false");

		ExchangeConfigurationService configService = new ExchangeConfigurationService(ConfigurationDomain.REPORT);

		PropertyUtils.setProperty(form, "reports", configService.getConfigurations());
		PropertyUtils.setProperty(form, "hourList", DisplayListService.getList(ListType.HOURS));
		PropertyUtils.setProperty(form, "minList", DisplayListService.getList(ListType.MINS));

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("resultReportingConfigurationDefinition", "form", form);
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
