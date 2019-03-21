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

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;


public class UserRoleDeleteAction extends BaseAction {
	static private String FWD_CLOSE = "close";
	private static final int USER_ID = 0;
	private static final int ROLE_ID = 1;
	
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "success";

		DynaActionForm dynaForm = (DynaActionForm) form;

		String[] selectedIDs = (String[]) dynaForm.get("selectedIDs");

		String sysUserId = getSysUserId(request);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		
		for (int i = 0; i < selectedIDs.length; i++) {
			String[] ids = selectedIDs[i].split("-");
			UserRole userRole = new UserRole();
			userRole.setSystemUserId(ids[USER_ID]);
			userRole.setRoleId(ids[ROLE_ID]);
			userRole.setSysUserId(sysUserId);
			userRoles.add(userRole);
		}

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();	
		ActionMessages errors = null;
		try {
			
			UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
			userRoleDAO.deleteData(userRoles);
			
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			
			errors = new ActionMessages();
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null,	null);
			} else {
				error = new ActionError("errors.DeleteException", null, null);
			}
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			forward = FWD_FAIL;
			
		}  finally {
			HibernateUtil.closeSession();
        }							
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);
		
		if (TRUE.equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}
		
		request.setAttribute("menuDefinition", "RoleMenuDefinition");

		return mapping.findForward(forward);
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}