package spring.mine.systemuser.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.controller.BaseController;
import spring.mine.common.validator.BaseErrors;
import spring.mine.systemuser.form.UnifiedSystemUserForm;
import spring.mine.systemuser.validator.UnifiedSystemUserFormValidator;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.daoimpl.UserModuleServiceImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.role.action.bean.DisplayRole;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

@Controller
public class UnifiedSystemUserController extends BaseController {

	@Autowired
	UnifiedSystemUserFormValidator formValidator;

	private static LoginDAO loginDAO = new LoginDAOImpl();
	private static final String RESERVED_ADMIN_NAME = "admin";

	private static final String MAINTENANCE_ADMIN = "Maintenance Admin";
	private static String MAINTENANCE_ADMIN_ID;
	public static final char DEFAULT_PASSWORD_FILLER = '@';

	{
		RoleDAO roleDAO = new RoleDAOImpl();
		List<Role> roles = roleDAO.getAllRoles();
		for (Role role : roles) {
			if (MAINTENANCE_ADMIN.equals(role.getName().trim())) {
				MAINTENANCE_ADMIN_ID = role.getId();
				break;
			}
		}
	}

	@RequestMapping(value = "/UnifiedSystemUser", method = RequestMethod.GET)
	public ModelAndView showUnifiedSystemUser(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		UnifiedSystemUserForm form = new UnifiedSystemUserForm();
		form.setFormAction("UnifiedSystemUser.do");
		form.setCancelAction("UnifiedSystemUserMenu.do");

		String id = request.getParameter(ID);
		boolean doFiltering = true;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);

		setDefaultProperties(form);
		if (!isNew) {
			setPropertiesForExistingUser(form, id, doFiltering);
		}
		setupRoles(form, request, doFiltering);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private void setupRoles(UnifiedSystemUserForm form, HttpServletRequest request, boolean doFiltering) {
		List<Role> roles = getAllRoles();
		UserModuleService userModuleService = new UserModuleServiceImpl();
		doFiltering &= !userModuleService.isUserAdmin(request);

		if (doFiltering) {
			roles = doRoleFiltering(roles, getSysUserId(request));
		}

		List<DisplayRole> displayRoles = convertToDisplayRoles(roles);
		displayRoles = sortAndGroupRoles(displayRoles);

		try {
			PropertyUtils.setProperty(form, "roles", displayRoles);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private List<DisplayRole> convertToDisplayRoles(List<Role> roles) {
		int elementCount = 0;

		List<DisplayRole> displayRoles = new ArrayList<>();

		for (Role role : roles) {
			elementCount++;
			displayRoles.add(convertToDisplayRole(role, elementCount));
		}

		return displayRoles;
	}

	private DisplayRole convertToDisplayRole(Role role, int count) {
		DisplayRole displayRole = new DisplayRole();

		displayRole.setRoleName(role.getLocalizedName());
		displayRole.setElementID(String.valueOf(count));
		displayRole.setRoleId(role.getId());
		displayRole.setGroupingRole(role.getGroupingRole());
		displayRole.setParentRole(role.getGroupingParent());

		return displayRole;
	}

	private List<DisplayRole> sortAndGroupRoles(List<DisplayRole> roles) {
		/*
		 * The sorting we want to end up with is first alphabetical and then by groups
		 * What makes things a little more difficult is that we may have roles which
		 * have parents which don't exist, we shouldn't but we might. So... First sweep
		 * is to find all the orphaned roles and set their parents to null Then move all
		 * the first generation groups to a new list. Then scan for all for all groups
		 * and move their members, repeat until the first list is empty, which is why we
		 * didn't want orphans. Lastly we will add the role ID as a child to all of it's
		 * parents
		 */

		Collections.sort(roles, new Comparator() {
			@Override
			public int compare(Object obj1, Object obj2) {
				DisplayRole role1 = (DisplayRole) obj1;
				DisplayRole role2 = (DisplayRole) obj2;
				return role1.getRoleName().toUpperCase().compareTo(role2.getRoleName().toUpperCase());
			}
		});

		/*
		 * The reason we're not making a map is that we want to preserve the order
		 * during this whole process
		 */
		List<String> groupIds = new ArrayList<>();

		for (DisplayRole role : roles) {
			if (role.isGroupingRole()) {
				groupIds.add(role.getRoleId());
			}
		}

		for (DisplayRole role : roles) {
			if (!GenericValidator.isBlankOrNull(role.getParentRole()) && !groupIds.contains(role.getParentRole())) {
				role.setParentRole(null);
			}
		}

		List<DisplayRole> mergeList = new ArrayList<>();
		List<DisplayRole> currentWorkingList = new ArrayList<>();
		List<DisplayRole> unplacedList = new ArrayList<>();

		for (DisplayRole role : roles) {
			if (GenericValidator.isBlankOrNull(role.getParentRole())) {
				role.setNestingLevel(0);
				currentWorkingList.add(role);
			} else {
				unplacedList.add(role);
			}
		}

		int indentCount = 0;
		while (unplacedList.size() > 0) {
			indentCount++;
			for (DisplayRole placedRole : currentWorkingList) {
				mergeList.add(placedRole);

				if (placedRole.isGroupingRole()) {
					List<DisplayRole> removeList = new ArrayList<>();
					for (DisplayRole unplacedRole : unplacedList) {
						if (unplacedRole.getParentRole().equals(placedRole.getRoleId())) {
							unplacedRole.setNestingLevel(indentCount);
							mergeList.add(unplacedRole);
							removeList.add(unplacedRole);
							placedRole.addChildID(unplacedRole.getRoleId());
						}
					}
					unplacedList.removeAll(removeList);
				}
			}

			currentWorkingList = mergeList;
			mergeList = new ArrayList<>();
		}

		/*
		 * For finding all parents we are going to iterate backwards since all parents
		 * are in front of children role
		 */
		for (int i = currentWorkingList.size() - 1; i > 0; i--) {
			DisplayRole role = currentWorkingList.get(i);

			if (!GenericValidator.isBlankOrNull(role.getParentRole())) {
				String roleID = role.getRoleId();
				String currentParentID = role.getParentRole();

				for (int parent = i - 1; parent >= 0; parent--) {
					if (currentWorkingList.get(parent).getRoleId().equals(currentParentID)) {
						DisplayRole parentRole = currentWorkingList.get(parent);

						parentRole.addChildID(roleID);

						if (GenericValidator.isBlankOrNull(parentRole.getParentRole())) {
							break;
						} else {
							currentParentID = parentRole.getParentRole();
						}
					}
				}
			}
		}

		return currentWorkingList;
	}

	private List<Role> doRoleFiltering(List<Role> roles, String loggedInUserId) {

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

		List<String> rolesForLoggedInUser = userRoleDAO.getRoleIdsForUser(loggedInUserId);

		if (!rolesForLoggedInUser.contains(MAINTENANCE_ADMIN_ID)) {
			List<Role> tmpRoles = new ArrayList<>();

			for (Role role : roles) {
				if (!MAINTENANCE_ADMIN_ID.equals(role.getId())) {
					tmpRoles.add(role);
				}
			}

			roles = tmpRoles;
		}

		return roles;
	}

	private void setDefaultProperties(UnifiedSystemUserForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String expireDate = getYearsFromNow(10);
		PropertyUtils.setProperty(form, "expirationDate", expireDate);
		PropertyUtils.setProperty(form, "timeout", "480");
		PropertyUtils.setProperty(form, "systemUserLastupdated", new Timestamp(System.currentTimeMillis()));
	}

	private void setPropertiesForExistingUser(UnifiedSystemUserForm form, String id, boolean doFiltering)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Login login = getLoginFromCombinedId(id);
		SystemUser systemUser = getSystemUserFromCombinedId(id);

		if (login != null) {
			String proxyPassword = getProxyPassword(login);
			PropertyUtils.setProperty(form, "loginUserId", login.getId());
			PropertyUtils.setProperty(form, "accountDisabled", login.getAccountDisabled());
			PropertyUtils.setProperty(form, "accountLocked", login.getAccountLocked());
			PropertyUtils.setProperty(form, "userLoginName", login.getLoginName());
			PropertyUtils.setProperty(form, "userPassword", proxyPassword);
			PropertyUtils.setProperty(form, "confirmPassword", proxyPassword);
			PropertyUtils.setProperty(form, "expirationDate", login.getPasswordExpiredDateForDisplay());
			PropertyUtils.setProperty(form, "timeout", login.getUserTimeOut());
		}

		if (systemUser != null) {
			PropertyUtils.setProperty(form, "systemUserId", systemUser.getId());
			PropertyUtils.setProperty(form, "userFirstName", systemUser.getFirstName());
			PropertyUtils.setProperty(form, "userLastName", systemUser.getLastName());
			PropertyUtils.setProperty(form, "accountActive", systemUser.getIsActive());
			PropertyUtils.setProperty(form, "systemUserLastupdated", systemUser.getLastupdated());

			UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
			List<String> roleIds = userRoleDAO.getRoleIdsForUser(systemUser.getId());
			PropertyUtils.setProperty(form, "selectedRoles", roleIds);

			doFiltering = !roleIds.contains(MAINTENANCE_ADMIN_ID);
		}

	}

	private String getProxyPassword(Login login) {
		char[] chars = new char[9];
		Arrays.fill(chars, DEFAULT_PASSWORD_FILLER);
		return new String(chars);
		// return StringUtil.replaceAllChars(login.getPassword(),
		// DEFAULT_PASSWORD_FILLER);
	}

	private Login getLoginFromCombinedId(String id) {
		Login login = null;
		String loginId = UnifiedSystemUser.getLoginUserIDFromCombinedID(id);

		if (!GenericValidator.isBlankOrNull(loginId)) {
			LoginDAO loginDAO = new LoginDAOImpl();

			login = new Login();
			login.setId(loginId);

			loginDAO.getData(login);
		}

		return login;
	}

	private SystemUser getSystemUserFromCombinedId(String id) {
		SystemUser systemUser = null;
		String systemUserId = UnifiedSystemUser.getSystemUserIDFromCombinedID(id);

		if (!GenericValidator.isBlankOrNull(systemUserId)) {
			SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
			systemUser = new SystemUser();
			systemUser.setId(systemUserId);
			systemUserDAO.getData(systemUser);
		}

		return systemUser;
	}

	private String getYearsFromNow(int years) {
		Calendar today = Calendar.getInstance();

		today.add(Calendar.YEAR, years);

		return DateUtil.formatDateAsText(today.getTime());
	}

	private List<Role> getAllRoles() {
		RoleDAO roleDAO = new RoleDAOImpl();
		return roleDAO.getAllActiveRoles();
	}

	@RequestMapping(value = "/UnifiedSystemUser", method = RequestMethod.POST)
	public ModelAndView showUpdateUnifiedSystemUser(HttpServletRequest request,
			@ModelAttribute("form") @Valid UnifiedSystemUserForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		boolean doFiltering = true;
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			setupRoles(form, request, doFiltering);
			return findForward(FWD_FAIL_INSERT, form);
		}

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

		String forward = validateAndUpdateSystemUser(request, form);

		if (forward.equals(FWD_SUCCESS)) {
			return getForward(findForward(forward, form), id, start, direction);
		} else if (forward.equals(FWD_SUCCESS_INSERT)) {
			redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
			Map<String, String> params = new HashMap<>();
			params.put("forward", FWD_SUCCESS);
			return getForwardWithParameters(findForward(forward, form), params);
		} else {
			setupRoles(form, request, doFiltering);
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
			return FWD_FAIL_INSERT;
		}

		String loggedOnUserId = getSysUserId(request);

		Login loginUser = createLoginUser(form, loginUserId, loginUserNew, passwordUpdated, loggedOnUserId);
		SystemUser systemUser = createSystemUser(form, systemUserId, systemUserNew, loggedOnUserId);

		List<String> selectedRoles = form.getSelectedRoles();

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

			for (int i = 0; i < selectedRoles.size(); i++) {
				if (!currentUserRoles.contains(selectedRoles.get(i))) {
					UserRole userRole = new UserRole();
					userRole.setSystemUserId(systemUser.getId());
					userRole.setRoleId(selectedRoles.get(i));
					userRole.setSysUserId(loggedOnUserId);
					usrRoleDAO.insertData(userRole);
				} else {
					currentUserRoles.remove(selectedRoles.get(i));
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
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errors.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			} else if (lre.getException() instanceof LIMSDuplicateRecordException) {
				errors.reject("errors.DuplicateRecordException", "errors.DuplicateRecordException");
			} else {
				errors.reject("errors.UpdateException", "errors.UpdateException");
			}

			saveErrors(errors);
			disableNavigationButtons(request);
			forward = FWD_FAIL_INSERT;
		} finally {
			if (!tx.wasRolledBack()) {
				tx.commit();
			}
			HibernateUtil.closeSession();
		}

		selectedRoles = new ArrayList<>();

		return forward;
	}

	private boolean passwordHasBeenUpdated(boolean loginUserNew, UnifiedSystemUserForm form) {
		if (loginUserNew) {
			return true;
		}

		String password = form.getUserPassword();

		return !StringUtil.containsOnly(password, DEFAULT_PASSWORD_FILLER);
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
			if (GenericValidator.isBlankOrNull(form.getUserPassword())
					|| !form.getUserPassword().equals(form.getConfirmPassword())) {
				errors.reject("errors.password.match", "errors.password.match");
			} else if (!passwordValid(form.getUserPassword())) { // validity
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
			login.setPassword(form.getUserPassword());
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

	private void disableNavigationButtons(HttpServletRequest request) {
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "unifiedSystemUserDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/MasterListsPage.do";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/UnifiedSystemUser.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "unifiedSystemUserDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		String id = request.getParameter(ID);
		boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);
		return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		String id = request.getParameter(ID);
		boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);
		return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
	}
}
