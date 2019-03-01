package spring.mine.analyzerimport.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.analyzerimport.form.AnalyzerTestNameMenuForm;
import spring.mine.common.controller.BaseMenuController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.action.beans.NamedAnalyzerTestMapping;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;

@Controller
public class AnalyzerTestNameMenuController extends BaseMenuController {

	private static final int ANALYZER_NAME = 0;
	private static final int ANALYZER_TEST = 1;

	@RequestMapping(value = "/AnalyzerTestNameMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showAnalyzerTestNameMenu(HttpServletRequest request,
			@ModelAttribute("form") AnalyzerTestNameMenuForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new AnalyzerTestNameMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		return performMenuAction(form, request);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {

		request.setAttribute("menuDefinition", "AnalyzerTestNameMenuDefinition");

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		List<NamedAnalyzerTestMapping> mappedTestNameList = new ArrayList<>();
		List<String> analyzerList = AnalyzerTestNameCache.instance().getAnalyzerNames();
		AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
		Analyzer analyzer = new Analyzer();

		for (String analyzerName : analyzerList) {
			Collection<MappedTestName> mappedTestNames = AnalyzerTestNameCache.instance()
					.getMappedTestsForAnalyzer(analyzerName).values();
			if (mappedTestNames.size() > 0) {
				analyzer.setId(((MappedTestName) mappedTestNames.toArray()[0]).getAnalyzerId());
				analyzer = analyzerDAO.getAnalyzerById(analyzer);
				mappedTestNameList.addAll(convertedToNamedList(mappedTestNames, analyzer.getName()));
			}
		}

		setDisplayPageBounds(request, mappedTestNameList.size(), startingRecNo);

		return mappedTestNameList.subList(Math.min(mappedTestNameList.size(), startingRecNo - 1),
				Math.min(mappedTestNameList.size(), startingRecNo + getPageSize()));

		// return mappedTestNameList;
	}

	private List<NamedAnalyzerTestMapping> convertedToNamedList(Collection<MappedTestName> mappedTestNameList,
			String analyzerName) {
		List<NamedAnalyzerTestMapping> namedMappingList = new ArrayList<>();

		for (MappedTestName test : mappedTestNameList) {
			NamedAnalyzerTestMapping namedMapping = new NamedAnalyzerTestMapping();
			namedMapping.setActualTestName(test.getOpenElisTestName());
			namedMapping.setAnalyzerTestName(test.getAnalyzerTestName());
			namedMapping.setAnalyzerName(analyzerName);

			namedMappingList.add(namedMapping);
		}

		return namedMappingList;
	}

	private void setDisplayPageBounds(HttpServletRequest request, int listSize, int startingRecNo)
			throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(listSize));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

		int numOfRecs = 0;
		if (listSize != 0) {
			numOfRecs = Math.min(listSize, getPageSize());

			numOfRecs--;
		}

		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
	}

	@Override
	protected String getDeactivateDisabled() {
		return "false";
	}

	@Override
	protected String getEditDisabled() {
		return "true";
	}

	@RequestMapping(value = "/DeleteAnalyzerTestName", method = RequestMethod.POST)
	public ModelAndView showDeleteAnalyzerTestName(HttpServletRequest request,
			@ModelAttribute("form") AnalyzerTestNameMenuForm form) {
		String forward = FWD_SUCCESS_DELETE;
		if (form == null) {
			form = new AnalyzerTestNameMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		String[] selectedIDs = (String[]) form.get("selectedIDs");

		// String sysUserId = getSysUserId(request);
		List<AnalyzerTestMapping> testMappingList = new ArrayList<>();

		for (int i = 0; i < selectedIDs.length; i++) {
			String[] ids = selectedIDs[i].split(NamedAnalyzerTestMapping.getUniqueIdSeperator());
			AnalyzerTestMapping testMapping = new AnalyzerTestMapping();
			testMapping.setAnalyzerId(AnalyzerTestNameCache.instance().getAnalyzerIdForName(ids[ANALYZER_NAME]));
			testMapping.setAnalyzerTestName(ids[ANALYZER_TEST]);
			testMappingList.add(testMapping);
		}

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {

			AnalyzerTestMappingDAO testMappingDAO = new AnalyzerTestMappingDAOImpl();
			testMappingDAO.deleteData(testMappingList, getSysUserId(request));

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errors.reject("errors.OptimisticLockException");
			} else {
				errors.reject("errors.DeleteException");
			}
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			forward = FWD_FAIL_DELETE;

		} finally {
			HibernateUtil.closeSession();
		}
		if (forward.equals(FWD_FAIL_DELETE)) {
			return findForward(forward, form);
		}

		if (TRUE.equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		AnalyzerTestNameCache.instance().reloadCache();
		request.setAttribute("menuDefinition", "AnalyzerTestNameDefinition");

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("haitiMasterListsPageDefinition", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("redirect:/MasterListsPage.do", "form", form);
		} else if (FWD_SUCCESS_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/AnalyzerTestNameMenu.do", "form", form);
		} else if (FWD_FAIL_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/AnalyzerTestNameMenu.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
