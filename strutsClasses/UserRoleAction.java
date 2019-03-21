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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;

public class UserRoleAction extends BaseAction {

	
	@SuppressWarnings("unchecked")
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		Collection systemUsers = getAllSystemUsers();
		Collection<Role> roles = getAllRoles();
		
		PropertyUtils.setProperty(form, "users", systemUsers);
		PropertyUtils.setProperty(form, "roles", roles);
		

		return mapping.findForward(forward);
	}

	private Collection getAllSystemUsers() {
		SystemUserDAO usersDAO = new SystemUserDAOImpl();
		return usersDAO.getAllSystemUsers();
	}

	private Collection getAllRoles() {
		RoleDAO roleDAO = new RoleDAOImpl();
		return roleDAO.getAllRoles();
	}

	protected String getPageTitleKey() {
		return "systemuserrole.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "systemuserrole.browse.title";
	}

}
