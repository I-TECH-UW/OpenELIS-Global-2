package spring.mine.systemuser.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.systemuser.form.UnifiedSystemUserForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.action.UnifiedSystemUserAction;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

@Controller
public class UpdateUnifiedSystemUserController extends BaseController {

	private static LoginDAO loginDAO = new LoginDAOImpl();
	private static final String RESERVED_ADMIN_NAME = "admin";

	@RequestMapping(value = "/UpdateUnifiedSystemUser", method = RequestMethod.POST)
	public ModelAndView showUpdateUnifiedSystemUser(HttpServletRequest request,
			@ModelAttribute("form") UnifiedSystemUserForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new UnifiedSystemUserForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		if (form.getUserLoginName() != null) {
			form.setUserLoginName(form.getUserLoginName().trim());
		} else {
			form.setUserLoginName("");
		}

		forward = validateAndUpdateSystemUser(request, form);

		if (forward.equals(FWD_SUCCESS)) {
			return getForward(findForward(forward, form), id, start, direction);
		} else if (forward.equals(FWD_SUCCESS_INSERT)) {
			Map<String, String> params = new HashMap<>();
			params.put("forward", FWD_SUCCESS);
			return getForwardWithParameters(findForward(forward, form), params);
		} else {
			return findForward(forward, form);
		}
	}

	public String validateAndUpdateSystemUser(HttpServletRequest request, UnifiedSystemUserForm form) {
		String forward = FWD_SUCCESS_INSERT;
		String loginUserId = form.getLoginUserId();
		String systemUserId = form.getSystemUserId();

		Errors errors = new BaseErrors();

		boolean loginUserNew = GenericValidator.isBlankOrNull(loginUserId);
		boolean systemUserNew = GenericValidator.isBlankOrNull(systemUserId);
		boolean passwordUpdated = false;

		passwordUpdated = passwordHasBeenUpdated(loginUserNew, form);
		validateUser(form, errors, loginUserNew, passwordUpdated, loginUserId);

		if (errors.hasErrors()) {
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			return FWD_FAIL;
		}

		String loggedOnUserId = getSysUserId(request);

		Login loginUser = createLoginUser(form, loginUserId, loginUserNew, passwordUpdated, loggedOnUserId);
		SystemUser systemUser = createSystemUser(form, systemUserId, systemUserNew, loggedOnUserId);

		String[] selectedRoles = form.getSelectedRoles();

		UserRoleDAO usrRoleDAO = new UserRoleDAOImpl();
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {

			if (loginUserNew) {
				loginDAO.insertData(loginUser);
			} else {
				loginDAO.updateData(loginUser, passwordUpdated);
			}

			if (systemUserNew) {
				systemUserDAO.insertData(systemUser);
			} else {
				systemUserDAO.updateData(systemUser);
			}

			List<String> currentUserRoles = usrRoleDAO.getRoleIdsForUser(systemUser.getId());
			List<UserRole> deletedUserRoles = new ArrayList<>();

			for (int i = 0; i < selectedRoles.length; i++) {
				if (!currentUserRoles.contains(selectedRoles[i])) {
					UserRole userRole = new UserRole();
					userRole.setSystemUserId(systemUser.getId());
					userRole.setRoleId(selectedRoles[i]);
					userRole.setSysUserId(loggedOnUserId);
					usrRoleDAO.insertData(userRole);
				} else {
					currentUserRoles.remove(selectedRoles[i]);
				}
			}

			for (String roleId : currentUserRoles) {
				UserRole userRole = new UserRole();
				userRole.setSystemUserId(systemUser.getId());
				userRole.setRoleId(roleId);
				userRole.setSysUserId(loggedOnUserId);
				deletedUserRoles.add(userRole);
			}

			if (deletedUserRoles.size() > 0) {
				usrRoleDAO.deleteData(deletedUserRoles);
			}
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			ObjectError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				error = new ObjectError("errors.OptimisticLockException", null);
			} else {
				error = new ObjectError("errors.UpdateException", null);
			}

			persisteError(request, error);

			disableNavigationButtons(request);
			forward = FWD_FAIL;
		} finally {
			if (!tx.wasRolledBack()) {
				tx.commit();
			}
			HibernateUtil.closeSession();
		}

		selectedRoles = new String[0];

		return forward;
	}

	private boolean passwordHasBeenUpdated(boolean loginUserNew, UnifiedSystemUserForm form) {
		if (loginUserNew) {
			return true;
		}

		String password = form.getUserPassword1();

		return !StringUtil.containsOnly(password, UnifiedSystemUserAction.DEFAULT_PASSWORD_FILLER);
	}

	private void validateUser(UnifiedSystemUserForm form, Errors errors, boolean loginUserIsNew,
			boolean passwordUpdated, String loginUserId) {
		boolean checkForDuplicateName = loginUserIsNew || userNameChanged(loginUserId, form.getUserLoginName());
		// check login name

		if (GenericValidator.isBlankOrNull(form.getUserLoginName())) {
			errors.reject("errors.loginName.required", "errors.loginName.required");
		} else if (checkForDuplicateName) {
			Login login = loginDAO.getUserProfile(form.getUserLoginName());
			if (login != null) {
				errors.reject("errors.loginName.duplicated", form.getUserLoginName());
			}
		}

		// check first and last name
		if (GenericValidator.isBlankOrNull(form.getUserFirstName())
				|| GenericValidator.isBlankOrNull(form.getUserLastName())) {
			errors.reject("errors.userName.required", "errors.userName.required");
		}

		if (passwordUpdated) {
			// check passwords match
			if (GenericValidator.isBlankOrNull(form.getUserPassword1())
					|| !form.getUserPassword1().equals(form.getUserPassword2())) {
				errors.reject("errors.password.match", "errors.password.match");
			} else if (!passwordValid(form.getUserPassword1())) { // validity
				errors.reject("login.error.password.requirement");
			}
		}

		// check expiration date
		if (!GenericValidator.isDate(form.getExpirationDate(), SystemConfiguration.getInstance().getDateLocale())) {
			errors.reject("errors.date", form.getExpirationDate());
		}

		// check timeout
		if (!timeoutValidAndInRange(form.getTimeout())) {
			errors.reject("errors.timeout.range", "errors.timeout.range");
		}
	}

	private boolean userNameChanged(String loginUserId, String newName) {
		if (GenericValidator.isBlankOrNull(loginUserId)) {
			return false;
		}

		Login login = new Login();
		login.setId(loginUserId);
		loginDAO.getData(login);

		return !newName.equals(login.getLoginName());
	}

	private boolean timeoutValidAndInRange(String timeout) {
		try {
			int timeInMin = Integer.parseInt(timeout);
			return timeInMin > 0 && timeInMin < 601;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private boolean passwordValid(String password) {
		return PasswordValidationFactory.getPasswordValidator().passwordValid(password);
	}

	private Login createLoginUser(UnifiedSystemUserForm form, String loginUserId, boolean loginUserNew,
			boolean passwordUpdated, String loggedOnUserId) {

		Login login = new Login();

		if (!loginUserNew) {
			login.setId(form.getLoginUserId());
			loginDAO.getData(login);
		}
		login.setAccountDisabled(form.getAccountDisabled());
		login.setAccountLocked(form.getAccountLocked());
		login.setLoginName(form.getUserLoginName());
		if (passwordUpdated) {
			login.setPassword(form.getUserPassword1());
		}
		login.setPasswordExpiredDateForDisplay(form.getExpirationDate());
		if (RESERVED_ADMIN_NAME.equals(form.getUserLoginName())) {
			login.setIsAdmin("Y");
		} else {
			login.setIsAdmin("N");
		}
		login.setUserTimeOut(form.getTimeout());
		login.setSysUserId(loggedOnUserId);

		return login;
	}

	private SystemUser createSystemUser(UnifiedSystemUserForm form, String systemUserId, boolean systemUserNew,
			String loggedOnUserId) {

		SystemUser systemUser = new SystemUser();

		if (!systemUserNew) {
			systemUser.setId(systemUserId);
		}

		systemUser.setFirstName(form.getUserFirstName());
		systemUser.setLastName(form.getUserLastName());
		systemUser.setLoginName(form.getUserLoginName());
		systemUser.setIsActive(form.getAccountActive());
		systemUser.setIsEmployee("Y");
		systemUser.setExternalId("1");
		String initial = systemUser.getFirstName().substring(0, 1) + systemUser.getLastName().substring(0, 1);
		systemUser.setInitials(initial);
		systemUser.setSysUserId(loggedOnUserId);
		systemUser.setLastupdated(form.getSystemUserLastupdated());

		return systemUser;
	}

	private void persisteError(HttpServletRequest request, ObjectError error) {
		Errors errors;
		errors = new BaseErrors();

		errors.reject(error.getCode(), error.getCode());
		saveErrors(errors);
		request.setAttribute(Globals.ERROR_KEY, errors);
	}

	private void disableNavigationButtons(HttpServletRequest request) {
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS_INSERT.equals(forward)) {
			return new ModelAndView("redirect:UnifiedSystemUser.do", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("unifiedSystemUserDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "systemuserrole.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "systemuserrole.browse.title";
	}
}
