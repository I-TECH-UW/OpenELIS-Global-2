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
*/
package us.mn.state.health.lims.organization.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class OrganizationMenuAction extends BaseMenuAction {

	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//System.out.println("I am in OrganizationMenuAction createMenuList()");

		List organizations = new ArrayList();

		String stringStartingRecNo = (String) request
				.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);
		
		// bugzilla 2372
		String searchString=(String) request
       .getParameter("searchString");
		 
		String doingSearch=(String)request
       .getParameter("search");

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();
		
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES))
		    organizations= organizationDAO.getPagesOfSearchedOrganizations(startingRecNo, searchString);
	    else
		    organizations = organizationDAO.getPageOfOrganizations(startingRecNo);


		request.setAttribute("menuDefinition", "OrganizationMenuDefinition");

		// bugzilla 1411 set pagination variables 
        // bugzilla 2372 set pagination variables for searched results
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES))
			request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(organizationDAO
					.getTotalSearchedOrganizationCount(searchString)));
		
		else 
		    request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(organizationDAO
				   .getTotalOrganizationCount()));
		
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		int numOfRecs = 0;
		if (organizations != null) {
			if (organizations.size() > SystemConfiguration.getInstance()
					.getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance()
						.getDefaultPageSize();
			} else {
				numOfRecs = organizations.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		//end bugzilla 1411
		
		//bugzilla 2372
		request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "organization.organizationName");
		// bugzilla 2372 set up a seraching mode so the next and previous action will know 
		// what to do
			
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES) ) {
		 
		   request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");
		   
		   request.setAttribute(MENU_SELECT_LIST_HEADER_SEARCH_STRING, searchString );
		}
		
		return organizations;
	}

	protected String getPageTitleKey() {
		return "organization.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "organization.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
}
