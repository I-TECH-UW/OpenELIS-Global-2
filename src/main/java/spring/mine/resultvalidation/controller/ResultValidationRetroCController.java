package spring.mine.resultvalidation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.resultvalidation.form.ResultValidationForm;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationPaging;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationRetroCIUtility;

@Controller
public class ResultValidationRetroCController extends BaseResultValidationRetroCIController {

	private ResultsValidationRetroCIUtility resultsValidationUtility;

	public ResultValidationRetroCController(ResultsValidationRetroCIUtility resultsValidationUtility) {
		this.resultsValidationUtility = resultsValidationUtility;
	}

	@RequestMapping(value = "/ResultValidationRetroC", method = RequestMethod.GET)
	public ModelAndView showResultValidationRetroC(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ResultValidationForm form = new ResultValidationForm();

		request.getSession().setAttribute(SAVE_DISABLED, "true");
		String testSectionName = (request.getParameter("type"));
		String testName = (request.getParameter("test"));

		ResultValidationPaging paging = new ResultValidationPaging();
		String newPage = request.getParameter("page");

		if (GenericValidator.isBlankOrNull(newPage)) {
			PropertyUtils.setProperty(form, "testSectionsByName", new ArrayList<IdValuePair>()); // required on jsp page
			PropertyUtils.setProperty(form, "displayTestSections", false);

			setRequestType(testSectionName);

			if (!GenericValidator.isBlankOrNull(testSectionName)) {
				String sectionName = Character.toUpperCase(testSectionName.charAt(0)) + testSectionName.substring(1);
				sectionName = getDBSectionName(sectionName);
				List<AnalysisItem> resultList = resultsValidationUtility.getResultValidationList(sectionName, testName,
						getValidationStatus(testSectionName));
				paging.setDatabaseResults(request, form, resultList);
			}

		} else {
			paging.page(request, form, newPage);
		}

		addFlashMsgsToRequest(request);
		if (testSectionName.equals("serology")) {
			return findForward("elisaSuccess", form);
		} else {
			return findForward(FWD_SUCCESS, form);
		}
	}

	public List<Integer> getValidationStatus(String testSection) {
		List<Integer> validationStatus = new ArrayList<>();

		if ("serology".equals(testSection)) {
			validationStatus
					.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
			validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
			// This next status determines if NonConformity analysis can still
			// be displayed on bio. validation page. We are awaiting feedback on
			// RetroCI
			// validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming)));
		} else {
			validationStatus
					.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
			if (ConfigurationProperties.getInstance()
					.isPropertyValueEqual(ConfigurationProperties.Property.VALIDATE_REJECTED_TESTS, "true")) {
				validationStatus.add(
						Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
			}
		}

		return validationStatus;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "resultValidationDefinition";
		} else if ("elisaSuccess".equals(forward)) {
			return "elisaAlgorithmResultValidationDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else {
			return "PageNotFound";
		}
	}
}
