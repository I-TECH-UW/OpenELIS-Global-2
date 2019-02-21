package spring.mine.systemuser.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.systemuser.form.UnifiedSystemUserForm;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
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

@Controller
public class UnifiedSystemUserController extends BaseController {

	private static final String MAINTENANCE_ADMIN = "Maintenance Admin";
	private static String MAINTENANCE_ADMIN_ID;
	public static final char DEFAULT_PASSWORD_FILLER = '?';

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

	@RequestMapping(value = "/UnifiedSystemUser", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView showUnifiedSystemUser(HttpServletRequest request,
			@ModelAttribute("form") UnifiedSystemUserForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new UnifiedSystemUserForm();
		}
		form.setFormAction("UpdateUnifiedSystemUser.do");
		form.setCancelAction("UnifiedSystemUserMenu.do");
		Errors errors = new BaseErrors();
		

		String id = request.getParameter(ID);
		boolean doFiltering = true;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);

		List<Role> roles = getAllRoles();

		setDefaultProperties(form);

		if (!isNew) {
			setPropertiesForExistingUser(form, id, doFiltering);
		}

		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		doFiltering &= !userModuleDAO.isUserAdmin(request);

		if (doFiltering) {
			roles = doRoleFiltering(roles, getSysUserId(request));
		}

		List<DisplayRole> displayRoles = convertToDisplayRoles(roles);
		displayRoles = sortAndGroupRoles(displayRoles);

		PropertyUtils.setProperty(form, "roles", displayRoles);

		return findForward(forward, form);
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
			PropertyUtils.setProperty(form, "userPassword1", proxyPassword);
			PropertyUtils.setProperty(form, "userPassword2", proxyPassword);
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
			PropertyUtils.setProperty(form, "selectedRoles", roleIds.toArray(new String[0]));

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

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("unifiedSystemUserDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("haitiMasterListsPageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
