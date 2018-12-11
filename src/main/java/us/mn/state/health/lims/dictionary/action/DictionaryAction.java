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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.daoimpl.DictionaryCategoryDAOImpl;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DictionaryAction extends BaseAction {

	private boolean isNew = false;

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new Dictionary.
		// If there is a parameter present, we should bring up an existing
		// Dictionary to edit.
		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		//bugzilla 2062
		request.setAttribute(RECORD_FROZEN_EDIT_DISABLED_KEY, "false");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DynaActionForm dynaForm = (DynaActionForm) form;

		// initialize the form
		dynaForm.initialize(mapping);
		
		Dictionary dictionary = new Dictionary();
		
		if ((id != null) && (!"0".equals(id))) { // this is an existing
			// dictionary

			dictionary.setId(id);
			DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
			dictionaryDAO.getData(dictionary);

			isNew = false; // this is to set correct page title

			// do we need to enable next or previous?
			List dictionarys = dictionaryDAO.getNextDictionaryRecord(dictionary
					.getId());
			if (dictionarys.size() > 0) {
				// enable next button
				request.setAttribute(NEXT_DISABLED, "false");
			}
			dictionarys = dictionaryDAO.getPreviousDictionaryRecord(dictionary
					.getId());
			if (dictionarys.size() > 0) {
				// enable next button
				request.setAttribute(PREVIOUS_DISABLED, "false");
			}
			// end of logic to enable next or previous button
		} else { // this is a new dictionary

			// default isActive to 'Y'
			dictionary.setIsActive(YES);
			isNew = true; // this is to set correct page title
		}

		if (dictionary.getId() != null && !dictionary.getId().equals("0")) {
			request.setAttribute(ID, dictionary.getId());
			// bugzilla 2062 initialize selectedDictionaryCategoryId
			if (dictionary.getDictionaryCategory() != null) {
				dictionary.setSelectedDictionaryCategoryId(dictionary.getDictionaryCategory()
						.getId());
			}
		}

		// populate form from valueholder
		PropertyUtils.copyProperties(form, dictionary);
		
		
		DictionaryCategoryDAO dictCategorygDAO = new DictionaryCategoryDAOImpl();
		List dictCats = dictCategorygDAO.getAllDictionaryCategorys();
		
		PropertyUtils.setProperty(form, "categories", dictCats);

		return mapping.findForward(forward);
	}

	protected String getPageTitleKey() {
		if (isNew) {
			return "dictionary.add.title";
		} else {
			return "dictionary.edit.title";
		}
	}

	protected String getPageSubtitleKey() {
		if (isNew) {
			return "dictionary.add.title";
		} else {
			return "dictionary.edit.title";
		}
	}

}
