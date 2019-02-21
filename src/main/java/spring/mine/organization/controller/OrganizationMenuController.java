package spring.mine.organization.controller;

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
import spring.mine.organization.form.OrganizationMenuForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;

@Controller
public class OrganizationMenuController extends BaseMenuController {

	@RequestMapping(value = { "/OrganizationMenu", "/SearchOrganizationMenu" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView showOrganizationMenu(HttpServletRequest request,
			@ModelAttribute("form") OrganizationMenuForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new OrganizationMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		return performMenuAction(form, request);
	}

	@Override
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {

		// System.out.println("I am in OrganizationMenuAction createMenuList()");

		List organizations = new ArrayList();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		// bugzilla 2372
		String searchString = request.getParameter("searchString");

		String doingSearch = request.getParameter("search");

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();

		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {
			organizations = organizationDAO.getPagesOfSearchedOrganizations(startingRecNo, searchString);
		} else {
			organizations = organizationDAO.getPageOfOrganizations(startingRecNo);
		}

		request.setAttribute("menuDefinition", "OrganizationMenuDefinition");

		// bugzilla 1411 set pagination variables
		// bugzilla 2372 set pagination variables for searched results
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {
			request.setAttribute(MENU_TOTAL_RECORDS,
					String.valueOf(organizationDAO.getTotalSearchedOrganizationCount(searchString)));
		} else {
			request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(organizationDAO.getTotalOrganizationCount()));
		}

		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		int numOfRecs = 0;
		if (organizations != null) {
			if (organizations.size() > SystemConfiguration.getInstance().getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance().getDefaultPageSize();
			} else {
				numOfRecs = organizations.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		// end bugzilla 1411

		// bugzilla 2372
		request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "organization.organizationName");
		// bugzilla 2372 set up a seraching mode so the next and previous action will
		// know
		// what to do

		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {

			request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");

			request.setAttribute(MENU_SELECT_LIST_HEADER_SEARCH_STRING, searchString);
		}

		return organizations;
	}

	@Override
	protected String getDeactivateDisabled() {
		return "false";
	}

	@Override
	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	@RequestMapping(value = "/DeleteOrganization", method = RequestMethod.POST)
	public ModelAndView showDeleteOrganization(HttpServletRequest request,
			@ModelAttribute("form") OrganizationMenuForm form) {
		String forward = FWD_SUCCESS_DELETE;
		if (form == null) {
			form = new OrganizationMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		String[] selectedIDs = (String[]) form.get("selectedIDs");

		// Vector organizations = new Vector();
		// get sysUserId from login module
		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		String sysUserId = String.valueOf(usd.getSystemUserId());

		List organizations = new ArrayList();

		for (int i = 0; i < selectedIDs.length; i++) {
			Organization organization = new Organization();
			organization.setId(selectedIDs[i]);
			organization.setSysUserId(sysUserId);
			organizations.add(organization);
		}

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			// selectedIDs = (List)PropertyUtils.getProperty(dynaForm,
			// "selectedIDs");
			// System.out.println("Going to delete Organization");
			OrganizationDAO organizationDAO = new OrganizationDAOImpl();
			organizationDAO.deleteData(organizations);
			// System.out.println("Just deleted Organization");
			// initialize the form
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("OrganizationDeleteAction", "performAction()", lre.toString());
			tx.rollback();

			String errorMsg;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errorMsg = "errors.OptimisticLockException";
			} else {
				errorMsg = "errors.DeleteException";
			}
			errors.reject(errorMsg);
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
		// System.out
		// .println("I am in OrganizationMenuDeleteAction setting menuDefinition");
		request.setAttribute("menuDefinition", "OrganizationMenuDefinition");

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("masterListsPageDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("redirect:/MasterListsPage.do", "form", form);
		} else if (FWD_SUCCESS_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/OrganizationMenu.do", "form", form);
		} else if (FWD_FAIL_DELETE.equals(forward)) {
			return new ModelAndView("redirect:/OrganizationMenu.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "organization.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "organization.browse.title";
	}

}
