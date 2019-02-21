package spring.mine.dictionary.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseMenuController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.dictionary.form.DictionaryMenuForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;

@Controller
public class DictionaryMenuController extends BaseMenuController {

	@RequestMapping(value = { "/DictionaryMenu", "/SearchDictionaryMenu" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView showDictionaryMenu(HttpServletRequest request, @ModelAttribute("form") DictionaryMenuForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new DictionaryMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		return performMenuAction(form, request);
	}

	@Override
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {

		List dictionarys = new ArrayList();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);
		// bugzilla 1413
		String searchString = request.getParameter("searchString");

		String doingSearch = request.getParameter("search");

		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {
			dictionarys = dictionaryDAO.getPagesOfSearchedDictionarys(startingRecNo, searchString);
		} else {
			dictionarys = dictionaryDAO.getPageOfDictionarys(startingRecNo);
			// end of bugzilla 1413
		}

		request.setAttribute("menuDefinition", "DictionaryMenuDefinition");

		// bugzilla 1411 set pagination variables
		// bugzilla 1413 set pagination variables for searched results
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {
			request.setAttribute(MENU_TOTAL_RECORDS,
					String.valueOf(dictionaryDAO.getTotalSearchedDictionaryCount(searchString)));
		} else {
			request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(dictionaryDAO.getTotalDictionaryCount()));
		}
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		int numOfRecs = 0;
		if (dictionarys != null) {
			if (dictionarys.size() > SystemConfiguration.getInstance().getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance().getDefaultPageSize();
			} else {
				numOfRecs = dictionarys.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		// end bugzilla 1411

		// bugzilla 1413
		request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "dictionary.dictEntry");
		// bugzilla 1413 set up a seraching mode so the next and previous action will
		// know
		// what to do

		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {

			request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");

			request.setAttribute(MENU_SELECT_LIST_HEADER_SEARCH_STRING, searchString);
		}

		return dictionarys;
	}

	@Override
	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	@Override
	protected String getDeactivateDisabled() {
		return "false";
	}

	@RequestMapping(value = "/DeleteDictionary", method = RequestMethod.POST)
	public ModelAndView showDeleteDictionary(HttpServletRequest request,
			@ModelAttribute("form") DictionaryMenuForm form) {
		String forward = FWD_SUCCESS_DELETE;
		if (form == null) {
			form = new DictionaryMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		String[] selectedIDs = (String[]) form.get("selectedIDs");

		// get sysUserId from login module
		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		String sysUserId = String.valueOf(usd.getSystemUserId());

		List dictionarys = new ArrayList();

		for (int i = 0; i < selectedIDs.length; i++) {
			Dictionary dictionary = new Dictionary();
			dictionary.setId(selectedIDs[i]);
			dictionary.setSysUserId(sysUserId);
			dictionarys.add(dictionary);
		}

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			// selectedIDs = (List)PropertyUtils.getProperty(form,
			// "selectedIDs");
			DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
			dictionaryDAO.deleteData(dictionarys);
			// initialize the form
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("DictionaryDeleteAction", "performAction()", lre.toString());
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

		if ("true".equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}
		// System.out.println("I am in DictionaryMenuDeleteAction setting
		// menuDefinition");
		request.setAttribute("menuDefinition", "DictionaryMenuDefinition");

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("masterListsPageDefinition", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("redirect:/MasterListsPage.do", "form", form);
		} else if (FWD_SUCCESS_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/DictionaryMenu.do", "form", form);
		} else if (FWD_FAIL_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/DictionaryMenu.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "dictionary.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "dictionary.browse.title";
	}

}
