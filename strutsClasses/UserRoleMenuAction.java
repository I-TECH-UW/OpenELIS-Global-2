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
package us.mn.state.health.lims.userrole.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;


public class UserRoleMenuAction extends BaseMenuAction {

	
	@SuppressWarnings("unchecked")
	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<UserRole> roleList = new ArrayList<UserRole>();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
		request.setAttribute("menuDefinition", "UserRoleMenuDefinition");
		
		roleList = userRoleDAO.getPageOfUserRoles(startingRecNo);
		
		addNamesToRoles( roleList );

		setDisplayPageBounds(request, roleList == null ? 0 : roleList.size(), startingRecNo, userRoleDAO, UserRole.class);

		return roleList;
	}


	private void addNamesToRoles(List<UserRole> roleList) {
		RoleDAO roleDAO = new RoleDAOImpl();
		SystemUserDAO userDAO = new SystemUserDAOImpl();
		Role role;
		SystemUser user;
		
		for( UserRole userRole : roleList ){
			role = new Role();
			role.setId(userRole.getRoleId());
			roleDAO.getData(role);
			userRole.setRoleName(role.getName());
			
			user = new SystemUser();
			user.setId(userRole.getSystemUserId());
			userDAO.getData(user);
			userRole.setUserName(user.getLoginName());
			
		}
		
	}


	protected String getPageTitleKey() {
		return "systemuserrole.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "systemuserrole.browse.title";
	}


	protected String getDeactivateDisabled() {
		return "false";
	}
	
	protected String getEditDisabled() {
		return "true";
	}
}
