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
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class UnifiedSystemUserMenuAction extends BaseMenuAction {

	@SuppressWarnings("unchecked")
	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<SystemUser> systemUsers = new ArrayList<SystemUser>();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUsers = systemUserDAO.getPageOfSystemUsers(startingRecNo);

		List<UnifiedSystemUser> unifiedUsers = getUnifiedUsers(systemUsers);
		
		request.setAttribute("menuDefinition", "UnifiedSystemUserMenuDefinition");

		
		setDisplayPageBounds(request, systemUsers.size(), startingRecNo, systemUserDAO, SystemUser.class);
		
		return unifiedUsers;
	}

	@SuppressWarnings("unchecked")
	private List<UnifiedSystemUser> getUnifiedUsers(List<SystemUser> systemUsers) {
		
		List<UnifiedSystemUser> unifiedUsers = new ArrayList<UnifiedSystemUser>();
		
		LoginDAO loginDAO = new LoginDAOImpl();
		List<Login> loginUsers = loginDAO.getAllLoginUsers();
		
		HashMap<String, Login> loginMap = createLoginMap( loginUsers);
		
		for( SystemUser user : systemUsers){
			UnifiedSystemUser unifiedUser = createUnifiedSystemUser(loginMap,user);
			unifiedUsers.add(unifiedUser);
		}
		
		return unifiedUsers;
	}

	private UnifiedSystemUser createUnifiedSystemUser(	HashMap<String, Login> loginMap, SystemUser user) {
		
		UnifiedSystemUser unifiedUser = new UnifiedSystemUser();
		unifiedUser.setFirstName(user.getFirstName());
		unifiedUser.setLastName(user.getLastName());
		unifiedUser.setLoginName(user.getLoginName());
		unifiedUser.setSystemUserId(user.getId());
		unifiedUser.setActive(user.getIsActive());
		
		Login login = loginMap.get(user.getLoginName());

		if(login != null){
			unifiedUser.setExpDate( DateUtil.formatDateAsText(login.getPasswordExpiredDate()));
			unifiedUser.setDisabled(login.getAccountDisabled());
			unifiedUser.setLocked(login.getAccountLocked());
			unifiedUser.setTimeout(login.getUserTimeOut());
			unifiedUser.setLoginUserId(login.getId());
		}
		return unifiedUser;
	}

	private HashMap<String, Login> createLoginMap(List<Login> loginUsers) {
		HashMap<String, Login> loginMap = new HashMap<String, Login>();
		
		for(Login login :loginUsers){
			loginMap.put(login.getLoginName(), login);
		}
		
		return loginMap;
	}

	protected String getPageTitleKey() {
		return "unifiedSystemUser.browser.title";
	}

	protected String getPageSubtitleKey() {
		return "unifiedSystemUser.browser.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
}
