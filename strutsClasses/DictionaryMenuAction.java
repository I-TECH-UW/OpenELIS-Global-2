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
package us.mn.state.health.lims.dictionary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DictionaryMenuAction extends BaseMenuAction {

	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List dictionarys = new ArrayList();

		String stringStartingRecNo = (String) request
				.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);
		// bugzilla 1413
		 String searchString=(String) request
        .getParameter("searchString");
		 
		String doingSearch=(String)request
        .getParameter("search");
	
		
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES))
		   dictionarys = dictionaryDAO.getPagesOfSearchedDictionarys(startingRecNo, searchString);
		else
		   dictionarys = dictionaryDAO.getPageOfDictionarys(startingRecNo);
	    // end of bugzilla 1413
		
		request.setAttribute("menuDefinition", "DictionaryMenuDefinition");

		// bugzilla 1411 set pagination variables 
		// bugzilla 1413 set pagination variables for searched results
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES))
			request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(dictionaryDAO
					.getTotalSearchedDictionaryCount(searchString)));
		
		  
		else
		    request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(dictionaryDAO
				.getTotalDictionaryCount()));
			request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		int numOfRecs = 0;
		if (dictionarys != null) {
			if (dictionarys.size() > SystemConfiguration.getInstance()
					.getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance()
						.getDefaultPageSize();
			} else {
				numOfRecs = dictionarys.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		//end bugzilla 1411
		
		//bugzilla 1413
		request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "dictionary.dictEntry");
		// bugzilla 1413 set up a seraching mode so the next and previous action will know 
		// what to do
			
		if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES) ) {
		 
		   request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");
		   
		   request.setAttribute(MENU_SELECT_LIST_HEADER_SEARCH_STRING, searchString );
		}
		
		
		return dictionarys;
	}

	protected String getPageTitleKey() {
		return "dictionary.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "dictionary.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
}
