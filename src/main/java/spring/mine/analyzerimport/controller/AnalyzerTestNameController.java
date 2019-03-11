package spring.mine.analyzerimport.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.analyzerimport.form.AnalyzerTestNameForm;
import spring.mine.analyzerimport.validator.AnalyzerTestMappingValidator;
import spring.mine.analyzerimport.validator.AnalyzerTestNameFormValidator;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

@Controller
@SessionAttributes("form")
public class AnalyzerTestNameController extends BaseController {

	@Autowired
	AnalyzerTestNameFormValidator formValidator;
	@Autowired
	AnalyzerTestMappingValidator analyzerTestMappingValidator;

	@ModelAttribute("form")
	public BaseForm initForm() {
		return new AnalyzerTestNameForm();
	}

	@RequestMapping(value = "/AnalyzerTestName", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView showAnalyzerTestName(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form.getClass() != AnalyzerTestNameForm.class) {
			form = new AnalyzerTestNameForm();
			request.getSession().setAttribute("form", form);
		}
		form.setFormAction("");
		form.setCancelAction("CancelAnalyzerTestName.do");

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		List<Analyzer> analyzerList = getAllAnalyzers();
		List<Test> testList = getAllTests();

		PropertyUtils.setProperty(form, "analyzerList", analyzerList);
		PropertyUtils.setProperty(form, "testList", testList);

		String id = request.getParameter("selectedIDs");
		if (id != null) {
			String[] splitId = id.split("#");
			PropertyUtils.setProperty(form, "analyzerTestName", splitId[1]);
			PropertyUtils.setProperty(form, "testId", splitId[2]);
			PropertyUtils.setProperty(form, "analyzerId", splitId[0]);
		}

		if (GenericValidator.isBlankOrNull((String) PropertyUtils.getProperty(form, "analyzerId"))) {
			PropertyUtils.setProperty(form, "newMapping", true);
		} else {
			PropertyUtils.setProperty(form, "newMapping", false);
		}

		return findForward(forward, form);
	}

	private List<Analyzer> getAllAnalyzers() {
		AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
		return analyzerDAO.getAllAnalyzers();
	}

	private List<Test> getAllTests() {
		TestDAO testDAO = new TestDAOImpl();
		return testDAO.getAllActiveTests(false);
	}

	@RequestMapping(value = "/UpdateAnalyzerTestName", method = RequestMethod.POST)
	public ModelAndView showUpdateAnalyzerTestName(HttpServletRequest request,
			@ModelAttribute("form") AnalyzerTestNameForm form, SessionStatus status, BindingResult result) {

		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String forward;

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		forward = updateAnalyzerTestName(request, form, result);
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			status.setComplete();
		}
		return findForward(forward, form);
	}

	public String updateAnalyzerTestName(HttpServletRequest request, AnalyzerTestNameForm form, Errors errors) {
		String forward = FWD_SUCCESS_INSERT;
		String analyzerId = form.getString("analyzerId");
		String analyzerTestName = form.getString("analyzerTestName");
		String testId = form.getString("testId");
		boolean newMapping = (boolean) form.get("newMapping");

		AnalyzerTestMapping analyzerTestNameMapping;
		if (newMapping) {
			analyzerTestNameMapping = new AnalyzerTestMapping();
			analyzerTestNameMapping.setAnalyzerId(analyzerId);
			analyzerTestNameMapping.setAnalyzerTestName(analyzerTestName);
			analyzerTestNameMapping.setTestId(testId);
		} else {
			analyzerTestNameMapping = getAnalyzerAndTestName(analyzerId, analyzerTestName, testId);
		}

		AnalyzerTestMappingDAO mappingDAO = new AnalyzerTestMappingDAOImpl();
		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			if (newMapping) {
				analyzerTestMappingValidator.preInsertValidate(analyzerTestNameMapping, errors);
				if (errors.hasErrors()) {
					saveErrors(errors);
					return FWD_FAIL_INSERT;
				}
				mappingDAO.insertData(analyzerTestNameMapping, getSysUserId(request));
			} else {
				analyzerTestMappingValidator.preUpdateValidate(analyzerTestNameMapping, errors);
				if (errors.hasErrors()) {
					saveErrors(errors);
					return FWD_FAIL_INSERT;
				}
				mappingDAO.updateMapping(analyzerTestNameMapping, getSysUserId(request));
			}

		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			String errorMsg = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errorMsg = "errors.OptimisticLockException";
			} else {
				errorMsg = "errors.UpdateException";
			}

			persisteError(request, errorMsg);

			disableNavigationButtons(request);
			forward = FWD_FAIL_INSERT;
		} finally {
			if (!tx.wasRolledBack()) {
				tx.commit();
			}
			HibernateUtil.closeSession();
		}

		AnalyzerTestNameCache.instance().reloadCache();

		return forward;
	}

	private AnalyzerTestMapping getAnalyzerAndTestName(String analyzerId, String analyzerTestName, String testId) {

		AnalyzerTestMapping existingMapping = null;
		AnalyzerTestMappingDAO mappingDAO = new AnalyzerTestMappingDAOImpl();
		List<AnalyzerTestMapping> testMappingList = mappingDAO.getAllAnalyzerTestMappings();
		for (AnalyzerTestMapping testMapping : testMappingList) {
			if (analyzerId.equals(testMapping.getAnalyzerId())
					&& analyzerTestName.equals(testMapping.getAnalyzerTestName())) {
				existingMapping = testMapping;
				testMapping.setTestId(testId);
				break;
			}
		}

		return existingMapping;
	}

	private void persisteError(HttpServletRequest request, String errorMsg) {
		Errors errors;
		errors = new BaseErrors();

		errors.reject(errorMsg);
		saveErrors(errors);
		request.setAttribute(Globals.ERROR_KEY, errors);
	}

	private void disableNavigationButtons(HttpServletRequest request) {
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);
	}

	@RequestMapping(value = "/CancelAnalyzerTestName", method = RequestMethod.GET)
	public ModelAndView cancelAnalyzerTestName(HttpServletRequest request,
			@ModelAttribute("form") AnalyzerTestNameForm form, SessionStatus status) {
		status.setComplete();
		return findForward(FWD_CANCEL, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "analyzerTestNameDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/AnalyzerTestNameMenu.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "redirect:/AnalyzerTestName.do";
		} else if (FWD_CANCEL.equals(forward)) {
			return "redirect:/AnalyzerTestNameMenu.do";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "analyzerTestName.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "analyzerTestName.browse.title";
	}
}
