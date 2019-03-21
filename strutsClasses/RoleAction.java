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
package us.mn.state.health.lims.role.action;

import java.util.ArrayList;
import java.util.List;

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

public class RoleAction extends BaseAction {

	private boolean isNew = false;

	@SuppressWarnings("unchecked")
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new TestResult.
		// If there is a parameter present, we should bring up an existing
		// TestResult to edit.
		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		List<Role> groupRoles = getGroupingRoles();
		PropertyUtils.setProperty(dynaForm, "parentRoles", groupRoles);

		isNew = id == null || "0".equals(id);

		if (!isNew) {

			Role role = new Role();
			role.setId(id);
			RoleDAO roleDAO = new RoleDAOImpl();
			roleDAO.getData(role);
			request.setAttribute(ID, role.getId());

			List roles = roleDAO.getNextRoleRecord(role.getId());
			if (roles.size() > 0) {
				request.setAttribute(NEXT_DISABLED, FALSE);
			}

			roles = roleDAO.getPreviousRoleRecord(role.getId());
			if (roles.size() > 0) {
				request.setAttribute(PREVIOUS_DISABLED, FALSE);
			}

			String[] checkboxes = new String[]{role.getGroupingRole() ? "isChecked" : ""};
			PropertyUtils.setProperty(dynaForm, "roleName", role.getName());
			PropertyUtils.setProperty(dynaForm, "description", role.getDescription());
			PropertyUtils.setProperty(dynaForm, "displayKey", role.getDisplayKey());
			PropertyUtils.setProperty(dynaForm, "isParentRole", checkboxes);
			PropertyUtils.setProperty(dynaForm, "parentRole", role.getGroupingParent());
		}


		return mapping.findForward(forward);
	}

	private List<Role> getGroupingRoles() {
		List<Role> groupingRoleList = new ArrayList<Role>();

		RoleDAO roleDAO = new RoleDAOImpl();
		List<Role> roles = roleDAO.getAllRoles();

		for( Role role : roles){
			if( role.getGroupingRole()){
				groupingRoleList.add(role);
			}
		}

		return groupingRoleList;
	}

	protected String getPageTitleKey() {
		return isNew ? "role.add.title" : "role.edit.title";
	}

	protected String getPageSubtitleKey() {
		return isNew ? "role.add.title" : "role.edit.title";
	}

}
