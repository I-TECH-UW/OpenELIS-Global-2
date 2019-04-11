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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
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
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;


public class UnifiedSystemUserDeleteAction extends BaseAction {
	static private String FWD_CLOSE = "close";

	
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "success";

		DynaActionForm dynaForm = (DynaActionForm) form;

		String[] selectedIDs = (String[]) dynaForm.get("selectedIDs");
		List<Login> loginUsers = new ArrayList<Login>(); 
		List<SystemUser> systemUsers = new ArrayList<SystemUser>(); 
		List<UserRole> userRoles = new ArrayList<UserRole>(); 
		
		
		String sysUserId = getSysUserId(request);
		
		for (int i = 0; i < selectedIDs.length; i++) {
			String systemUserId = UnifiedSystemUser.getSystemUserIDFromCombinedID(selectedIDs[i]);
			
			if(!GenericValidator.isBlankOrNull(systemUserId)){
				SystemUser systemUser = new SystemUser();
				systemUser.setId(systemUserId);
				systemUser.setSysUserId(sysUserId);
				systemUsers.add( systemUser);
			}
			
			String loginUserId = UnifiedSystemUser.getLoginUserIDFromCombinedID(selectedIDs[i]);
			
			if(!GenericValidator.isBlankOrNull(loginUserId)){
				Login loginUser = new Login();
				loginUser.setId(loginUserId);
				loginUser.setSysUserId(sysUserId);
				loginUsers.add( loginUser);
			}
		}

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

		for( SystemUser systemUser : systemUsers){
			List<String> roleIds = userRoleDAO.getRoleIdsForUser(systemUser.getId());
			
			for( String roleId : roleIds){
				UserRole userRole = new UserRole();
				userRole.setSystemUserId(systemUser.getId());
				userRole.setRoleId(roleId);
				userRole.setSysUserId(sysUserId);
				userRoles.add(userRole);
			}
		}
		
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		LoginDAO loginDAO = new LoginDAOImpl();
		
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();	
		ActionMessages errors = null;
		try {

			userRoleDAO.deleteData(userRoles);

			for( SystemUser systemUser : systemUsers){
				//we're not going to actually delete them to preserve auditing
				systemUserDAO.getData(systemUser);
				systemUser.setSysUserId(sysUserId);
				systemUser.setIsActive("N");
				systemUserDAO.updateData(systemUser);
			}
			
			loginDAO.deleteData(loginUsers);
		
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