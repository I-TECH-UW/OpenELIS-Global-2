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
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

public class UserRoleUpdateAction extends BaseAction {

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;

		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");

		forward = validateAndUpdateRoles(mapping, request, dynaForm);

		return getForward(mapping.findForward(forward), id, start, direction);

	}

	public String validateAndUpdateRoles(ActionMapping mapping, HttpServletRequest request, BaseActionForm dynaForm) {
		String forward = FWD_SUCCESS_INSERT;
		String userId = (String) dynaForm.get("userNameId");
		String[] selectedRoles = (String[]) dynaForm.get("selectedRoles");

		ActionMessages errors = new ActionMessages();

		validateUserAndRole(userId, selectedRoles, errors);

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return FWD_FAIL;
		}

		String sysUserId = getSysUserId(request);
		UserRoleDAO usrRoleDAO = new UserRoleDAOImpl();
		List<String> currentUserRoles = usrRoleDAO.getRoleIdsForUser(userId);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (int i = 0; i < selectedRoles.length; i++) {
				if (notDuplicate(currentUserRoles, selectedRoles[i])) {
					UserRole userRole = new UserRole();
					userRole.setSystemUserId(userId);
					userRole.setRoleId(selectedRoles[i]);
					userRole.setSysUserId(sysUserId);

					usrRoleDAO.insertData(userRole);
				}
			}
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null, null);
			} else {
				error = new ActionError("errors.UpdateException", null, null);
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

		return forward;
	}

	private boolean notDuplicate(List<String> currentUserRoles, String roleId) {
		
		for( String currentRoleId : currentUserRoles){
			if( roleId.equals(currentRoleId)){
				return false;
			}
		}
		
		return true;
	}

	private void persisteError(HttpServletRequest request, ActionError error) {
		ActionMessages errors;
		errors = new ActionMessages();

		errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		saveErrors(request, errors);
		request.setAttribute(Globals.ERROR_KEY, errors);
	}

	private void disableNavigationButtons(HttpServletRequest request) {
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);
	}

	private void validateUserAndRole(String userId, String[] selectedRoles, ActionMessages errors) {
		if (GenericValidator.isBlankOrNull(userId) || userId.equals("0")) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.role.name.required"));
		}

		if (selectedRoles.length < 1) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.role.roles.required"));
		}
	}

	protected String getPageTitleKey() {
		return "systemuserrole.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "systemuserrole.browse.title";
	}
}