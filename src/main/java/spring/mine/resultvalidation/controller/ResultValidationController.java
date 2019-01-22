package spring.mine.resultvalidation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.internationalization.MessageUtil;
import spring.mine.resultvalidation.form.ResultValidationForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationPaging;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationUtility;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class ResultValidationController extends BaseResultValidationController {

	@RequestMapping(value = "/ResultValidation", method = RequestMethod.GET)
	public ModelAndView showResultValidation(HttpServletRequest request,
			@ModelAttribute("form") ResultValidationForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ResultValidationForm();
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

		request.getSession().setAttribute(SAVE_DISABLED, "true");
		String testSectionId = (request.getParameter("testSectionId"));

		ResultValidationPaging paging = new ResultValidationPaging();
		String newPage = request.getParameter("page");

		TestSection ts = null;

		if (GenericValidator.isBlankOrNull(newPage)) {

			// load testSections for drop down
			TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
			PropertyUtils.setProperty(form, "testSections", DisplayListService.getList(ListType.TEST_SECTION));
			PropertyUtils.setProperty(form, "testSectionsByName",
					DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));

			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				ts = testSectionDAO.getTestSectionById(testSectionId);
				PropertyUtils.setProperty(form, "testSectionId", "0");
			}

			List<AnalysisItem> resultList;
			ResultsValidationUtility resultsValidationUtility = new ResultsValidationUtility();
			setRequestType(ts == null ? MessageUtil.getMessage("workplan.unit.types") : ts.getLocalizedName());
			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				resultList = resultsValidationUtility.getResultValidationList(getValidationStatus(), testSectionId);

			} else {
				resultList = new ArrayList<>();
			}
			paging.setDatabaseResults(request, form, resultList);

		} else {
			paging.page(request, form, newPage);
		}

		return findForward(forward, form);
	}

	public List<Integer> getValidationStatus() {
		List<Integer> validationStatus = new ArrayList<>();
		validationStatus
				.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
		if (ConfigurationProperties.getInstance()
				.isPropertyValueEqual(ConfigurationProperties.Property.VALIDATE_REJECTED_TESTS, "true")) {
			validationStatus
					.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
		}

		return validationStatus;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("resultValidationDefinition", "form", form);
		} else if ("elisaSuccess".equals(forward)) {
			return new ModelAndView("elisaAlgorithmResultValidationDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}
}
