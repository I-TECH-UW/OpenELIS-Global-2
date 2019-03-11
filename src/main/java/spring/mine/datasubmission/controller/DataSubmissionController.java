package spring.mine.datasubmission.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.datasubmission.form.DataSubmissionForm;
import spring.mine.internationalization.MessageUtil;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.datasubmission.DataIndicatorFactory;
import us.mn.state.health.lims.datasubmission.DataSubmitter;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.dao.TypeOfDataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.daoimpl.DataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.daoimpl.TypeOfDataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Controller
public class DataSubmissionController extends BaseController {
	@RequestMapping(value = "/DataSubmission", method = RequestMethod.GET)
	public ModelAndView showDataSubmission(HttpServletRequest request,
			@ModelAttribute("form") DataSubmissionForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new DataSubmissionForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		int month = GenericValidator.isBlankOrNull(request.getParameter("month")) ? DateUtil.getCurrentMonth() + 1
				: Integer.parseInt(request.getParameter("month"));
		int year = GenericValidator.isBlankOrNull(request.getParameter("year")) ? DateUtil.getCurrentYear()
				: Integer.parseInt(request.getParameter("year"));

		DataIndicatorDAO indicatorDAO = new DataIndicatorDAOImpl();
		TypeOfDataIndicatorDAO typeOfIndicatorDAO = new TypeOfDataIndicatorDAOImpl();

		List<DataIndicator> indicators = new ArrayList<>();
		List<TypeOfDataIndicator> typeOfIndicatorList = typeOfIndicatorDAO.getAllTypeOfDataIndicator();
		for (TypeOfDataIndicator typeOfIndicator : typeOfIndicatorList) {
			DataIndicator indicator = indicatorDAO.getIndicatorByTypeYearMonth(typeOfIndicator, year, month);
			if (indicator == null) {
				indicator = DataIndicatorFactory.createBlankDataIndicatorForType(typeOfIndicator);
			}
			indicator.setYear(year);
			indicator.setMonth(month);
			indicators.add(indicator);
		}

		form.setDataSubUrl(new SiteInformationDAOImpl().getSiteInformationByName("Data Sub URL"));
		form.setIndicators(indicators);
		form.setMonth(month);
		form.setYear(year);
		form.setSiteId(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));

		return findForward(forward, form);
	}

	@RequestMapping(value = "/DataSubmissionSave", method = RequestMethod.POST)
	public ModelAndView showDataSubmissionSave(HttpServletRequest request,
			@ModelAttribute("form") DataSubmissionForm form) throws IOException, ParseException {
		String forward = FWD_SUCCESS_INSERT;
		form.setFormAction("");
		Errors errors = new BaseErrors();

		int month = GenericValidator.isBlankOrNull(request.getParameter("month")) ? DateUtil.getCurrentMonth()
				: Integer.parseInt(request.getParameter("month"));
		int year = GenericValidator.isBlankOrNull(request.getParameter("year")) ? DateUtil.getCurrentYear()
				: Integer.parseInt(request.getParameter("year"));
		@SuppressWarnings("unchecked")
		List<DataIndicator> indicators = (List<DataIndicator>) form.get("indicators");
		boolean submit = "true".equalsIgnoreCase(request.getParameter("submit"));
		SiteInformation dataSubUrl = (SiteInformation) form.get("dataSubUrl");
		dataSubUrl.setSysUserId(getSysUserId(request));
		SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();
		siteInfoDAO.updateData(dataSubUrl);
		DataIndicatorDAO indicatorDAO = new DataIndicatorDAOImpl();
		boolean allSuccess = true;
		for (DataIndicator indicator : indicators) {
			if (submit && indicator.isSendIndicator()) {
				boolean success;
				success = DataSubmitter.sendDataIndicator(indicator);
				indicator.setStatus(DataIndicator.SENT);
				if (success) {
					indicator.setStatus(DataIndicator.RECEIVED);
				} else {
					allSuccess = false;
					indicator.setStatus(DataIndicator.FAILED);
					errors.reject("errors.IndicatorCommunicationException",
							new String[] { MessageUtil.getMessage(indicator.getTypeOfIndicator().getNameKey()) },
							"errors.IndicatorCommunicationException");
				}
			}

			DataIndicator databaseIndicator = indicatorDAO.getIndicatorByTypeYearMonth(indicator.getTypeOfIndicator(),
					year, month);
			if (databaseIndicator == null) {
				indicatorDAO.insertData(indicator);
			} else {
				indicator.setId(databaseIndicator.getId());
				indicatorDAO.updateData(indicator);
			}
		}

		if (!allSuccess) {
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
		}
		request.setAttribute(IActionConstants.FWD_SUCCESS, allSuccess);

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "dataSubmissionDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/DataSubmission.do";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "datasubmission.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "datasubmission.browse.title";
	}
}
