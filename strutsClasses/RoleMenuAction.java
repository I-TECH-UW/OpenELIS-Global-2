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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;


public class RoleMenuAction extends BaseMenuAction {

	
	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List roleList = new ArrayList();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		RoleDAO roleDAO = new RoleDAOImpl();
		request.setAttribute("menuDefinition", "RoleMenuDefinition");
		
		roleList = roleDAO.getPageOfRoles(startingRecNo);

		setDisplayPageBounds(request, roleList == null ? 0 : roleList.size(), startingRecNo, roleDAO, Role.class);

		return roleList;
	}


	protected String getPageTitleKey() {
		return "role.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "role.browse.title";
	}


	protected String getDeactivateDisabled() {
		return "false";
	}
	
}
