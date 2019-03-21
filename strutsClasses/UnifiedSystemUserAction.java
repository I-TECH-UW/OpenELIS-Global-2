/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.systemuser.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
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

@SuppressWarnings("unchecked")
public class UnifiedSystemUserAction extends BaseAction {

	private boolean isNew = false;
	private static final String MAINTENANCE_ADMIN = "Maintenance Admin";
	private static String MAINTENANCE_ADMIN_ID;
	private boolean doFiltering = true;
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

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter(ID);
		doFiltering = true;

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);

		List<Role> roles = getAllRoles();

		setDefaultProperties(dynaForm);

		if (!isNew) {
			setPropertiesForExistingUser(dynaForm, id);
		}

		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		doFiltering &= !userModuleDAO.isUserAdmin(request);

		if (doFiltering) {
			roles = doRoleFiltering(roles, getSysUserId(request));
		}

		List<DisplayRole> displayRoles = convertToDisplayRoles( roles );
		displayRoles = sortAndGroupRoles(displayRoles);

		PropertyUtils.setProperty(dynaForm, "roles", displayRoles);

		return mapping.findForward(forward);
	}

	private List<DisplayRole> convertToDisplayRoles(List<Role> roles) {
		int elementCount = 0;

		List<DisplayRole> displayRoles = new ArrayList<DisplayRole>();

		for( Role role: roles){
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
		 * The sorting we want to end up with is first alphabetical and then by
		 * groups What makes things a little more difficult is that we may have
		 * roles which have parents which don't exist, we shouldn't but we
		 * might. So... First sweep is to find all the orphaned roles and set
		 * their parents to null Then move all the first generation groups to a
		 * new list. Then scan for all for all groups and move their members,
		 * repeat until the first list is empty, which is why we didn't want
		 * orphans.  Lastly we will add the role ID as a child to all of it's parents
		 */

		Collections.sort(roles, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				DisplayRole role1 = (DisplayRole) obj1;
				DisplayRole role2 = (DisplayRole) obj2;
				return role1.getRoleName().toUpperCase().compareTo(role2.getRoleName().toUpperCase());
			}
		});

		/*
		 * The reason we're not making a map is that we want to preserve the
		 * order during this whole process
		 */
		List<String> groupIds = new ArrayList<String>();

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

		List<DisplayRole> mergeList = new ArrayList<DisplayRole>();
		List<DisplayRole> currentWorkingList = new ArrayList<DisplayRole>();
		List<DisplayRole> unplacedList = new ArrayList<DisplayRole>();

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
					List<DisplayRole> removeList = new ArrayList<DisplayRole>();
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
			mergeList = new ArrayList<DisplayRole>();
		}

		/* For finding all parents we are going to iterate backwards since all parents are
		 * in front of children role
		 */
		for( int i = currentWorkingList.size() - 1; i > 0; i--){
			DisplayRole role = currentWorkingList.get(i);

			if( !GenericValidator.isBlankOrNull(role.getParentRole())){
				String roleID = role.getRoleId();
				String currentParentID = role.getParentRole();

				for( int parent = i - 1; parent >= 0; parent--){
					if( currentWorkingList.get(parent).getRoleId().equals(currentParentID)){
						DisplayRole parentRole = currentWorkingList.get(parent);

						parentRole.addChildID(roleID);

						if( GenericValidator.isBlankOrNull(parentRole.getParentRole())){
							break;
						}else{
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
			List<Role> tmpRoles = new ArrayList<Role>();

			for (Role role : roles) {
				if (!MAINTENANCE_ADMIN_ID.equals(role.getId())) {
					tmpRoles.add(role);
				}
			}

			roles = tmpRoles;
		}

		return roles;
	}

	private void setDefaultProperties(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String expireDate = getYearsFromNow(10);
		PropertyUtils.setProperty(dynaForm, "expirationDate", expireDate);
		PropertyUtils.setProperty(dynaForm, "timeout", "480");
		PropertyUtils.setProperty(dynaForm, "systemUserLastupdated", new Timestamp(System.currentTimeMillis()));
	}

	private void setPropertiesForExistingUser(DynaActionForm dynaForm, String id) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Login login = getLoginFromCombinedId(id);
		SystemUser systemUser = getSystemUserFromCombinedId(id);

		if (login != null) {
			String proxyPassword = getProxyPassword(login);
			PropertyUtils.setProperty(dynaForm, "loginUserId", login.getId());
			PropertyUtils.setProperty(dynaForm, "accountDisabled", login.getAccountDisabled());
			PropertyUtils.setProperty(dynaForm, "accountLocked", login.getAccountLocked());
			PropertyUtils.setProperty(dynaForm, "userLoginName", login.getLoginName());
			PropertyUtils.setProperty(dynaForm, "userPassword1", proxyPassword );
			PropertyUtils.setProperty(dynaForm, "userPassword2", proxyPassword);
			PropertyUtils.setProperty(dynaForm, "expirationDate", login.getPasswordExpiredDateForDisplay());
			PropertyUtils.setProperty(dynaForm, "timeout", login.getUserTimeOut());
		}

		if (systemUser != null) {
			PropertyUtils.setProperty(dynaForm, "systemUserId", systemUser.getId());
			PropertyUtils.setProperty(dynaForm, "userFirstName", systemUser.getFirstName());
			PropertyUtils.setProperty(dynaForm, "userLastName", systemUser.getLastName());
			PropertyUtils.setProperty(dynaForm, "accountActive", systemUser.getIsActive());
			PropertyUtils.setProperty(dynaForm, "systemUserLastupdated", systemUser.getLastupdated());

			UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
			List<String> roleIds = userRoleDAO.getRoleIdsForUser(systemUser.getId());
			PropertyUtils.setProperty(dynaForm, "selectedRoles", roleIds.toArray(new String[0]));

			doFiltering = !roleIds.contains(MAINTENANCE_ADMIN_ID);
		}

	}

	private String getProxyPassword(Login login) {
		char[] chars = new char[9];
		Arrays.fill(chars,  DEFAULT_PASSWORD_FILLER);
		return new String(chars);
		//return StringUtil.replaceAllChars(login.getPassword(), DEFAULT_PASSWORD_FILLER);
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

	protected String getPageTitleKey() {
		return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
	}

	protected String getPageSubtitleKey() {
		return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
	}

}
