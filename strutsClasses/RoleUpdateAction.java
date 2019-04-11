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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;

public class RoleUpdateAction extends BaseAction {

	private boolean isNew = false;
	private String currentUser;

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);
		isNew = id == null || id.equals("0");

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;

		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");
		currentUser = getSysUserId(request);

		forward = validateAndUpdateRole(mapping, request, dynaForm, isNew);

		return getForward(mapping.findForward(forward), id, start, direction);

	}

	public String validateAndUpdateRole(ActionMapping mapping, HttpServletRequest request,
			BaseActionForm dynaForm, boolean newRole) {
		String forward = FWD_SUCCESS_INSERT;
		String name = dynaForm.getString("roleName");

		ActionMessages errors = new ActionMessages();
		if (GenericValidator.isBlankOrNull(name)) {
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionError("error.role.name.required"));

			saveErrors(request, errors);

		 return FWD_FAIL;
		 }

		String[] checkedBoxes = (String[])dynaForm.get("isParentRole");

		Role role = new Role();
		role.setName(name);
		role.setDescription(dynaForm.getString("description"));
		role.setSysUserId(currentUser);
		role.setDisplayKey(dynaForm.getString("displayKey"));
		role.setGroupingParent(dynaForm.getString("parentRole"));
		role.setGroupingRole(checkedBoxes.length > 0 && "isChecked".equals(checkedBoxes[0]));

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			RoleDAO roleDAO = new RoleDAOImpl();

			if( newRole){
				roleDAO.insertData(role);
			}else{
				role.setId(request.getParameter(ID));
				roleDAO.updateData(role);
				if( !role.getGroupingRole()){
					dropReferencingRoles(role, roleDAO);
				}
			}

			tx.commit();

			forward = FWD_SUCCESS_INSERT;
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			errors = new ActionMessages();
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {

				error = new ActionError("errors.OptimisticLockException", null, null);

			} else {
				error = new ActionError("errors.UpdateException", null, null);
			}

			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}
		return forward;
	}


	private void dropReferencingRoles(Role role, RoleDAO roleDAO) {
		List<Role> referencingRoleList = roleDAO.getReferencingRoles(role);

		for( Role referencingRole : referencingRoleList){
			referencingRole.setGroupingParent(null);
			referencingRole.setSysUserId(currentUser);
			roleDAO.updateData(referencingRole);
		}
	}

	protected String getPageTitleKey() {
		return isNew ? "role.add.title" : "role.edit.title";
	}

	protected String getPageSubtitleKey() {
		return isNew ? "role.add.title" : "role.edit.title";
	}
}