package spring.mine.systemuser.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import spring.mine.systemuser.form.UnifiedSystemUserMenuForm;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser;

@Controller
public class UnifiedSystemUserMenuController extends BaseMenuController {

	@RequestMapping(value = "/UnifiedSystemUserMenu", method = RequestMethod.GET)
	public ModelAndView showUnifiedSystemUserMenu(HttpServletRequest request,
			@ModelAttribute("form") UnifiedSystemUserMenuForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new UnifiedSystemUserMenuForm();
		}
		form.setFormAction("UnifiedSystemUserMenu.do");
		Errors errors = new BaseErrors();
		

		performMenuAction(form, request);

		return findForward(forward, form);
	}

	@Override
	protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {
		List<SystemUser> systemUsers = new ArrayList<>();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUsers = systemUserDAO.getPageOfSystemUsers(startingRecNo);

		List<UnifiedSystemUser> unifiedUsers = getUnifiedUsers(systemUsers);

		request.setAttribute("menuDefinition", "UnifiedSystemUserMenuDefinition");

		setDisplayPageBounds(request, systemUsers.size(), startingRecNo, systemUserDAO, SystemUser.class);

		return unifiedUsers;
	}

	@SuppressWarnings("unchecked")
	private List<UnifiedSystemUser> getUnifiedUsers(List<SystemUser> systemUsers) {

		List<UnifiedSystemUser> unifiedUsers = new ArrayList<>();

		LoginDAO loginDAO = new LoginDAOImpl();
		List<Login> loginUsers = loginDAO.getAllLoginUsers();

		HashMap<String, Login> loginMap = createLoginMap(loginUsers);

		for (SystemUser user : systemUsers) {
			UnifiedSystemUser unifiedUser = createUnifiedSystemUser(loginMap, user);
			unifiedUsers.add(unifiedUser);
		}

		return unifiedUsers;
	}

	private UnifiedSystemUser createUnifiedSystemUser(HashMap<String, Login> loginMap, SystemUser user) {

		UnifiedSystemUser unifiedUser = new UnifiedSystemUser();
		unifiedUser.setFirstName(user.getFirstName());
		unifiedUser.setLastName(user.getLastName());
		unifiedUser.setLoginName(user.getLoginName());
		unifiedUser.setSystemUserId(user.getId());
		unifiedUser.setActive(user.getIsActive());

		Login login = loginMap.get(user.getLoginName());

		if (login != null) {
			unifiedUser.setExpDate(DateUtil.formatDateAsText(login.getPasswordExpiredDate()));
			unifiedUser.setDisabled(login.getAccountDisabled());
			unifiedUser.setLocked(login.getAccountLocked());
			unifiedUser.setTimeout(login.getUserTimeOut());
			unifiedUser.setLoginUserId(login.getId());
		}
		return unifiedUser;
	}

	private HashMap<String, Login> createLoginMap(List<Login> loginUsers) {
		HashMap<String, Login> loginMap = new HashMap<>();

		for (Login login : loginUsers) {
			loginMap.put(login.getLoginName(), login);
		}

		return loginMap;
	}

	@Override
	protected String getDeactivateDisabled() {
		return "false";
	}

	@Override
	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("haitiMasterListsPageDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("/MasterListsPage.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "unifiedSystemUser.browser.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "unifiedSystemUser.browser.title";
	}
}
