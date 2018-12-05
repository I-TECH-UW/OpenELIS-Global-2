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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSFrozenRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.daoimpl.DictionaryCategoryDAOImpl;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DictionaryUpdateAction extends BaseAction {

	private boolean isNew = false;

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// The first job is to determine if we are coming to this action with an
		// ID parameter in the request. If there is no parameter, we are
		// creating a new Dictionary.
		// If there is a parameter present, we should bring up an existing
		// Dictionary to edit.

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		if (StringUtil.isNullorNill(id) || "0".equals(id)) {
			isNew = true;
		} else {
			isNew = false;
		}

		BaseActionForm dynaForm = (BaseActionForm) form;
		
		// server-side validation (validation.xml)
		ActionMessages errors = dynaForm.validate(mapping, request);		
		if (errors != null && errors.size() > 0) {
			// System.out.println("Server side validation errors "
			// + errors.toString());
			saveErrors(request, errors);
			// since we forward to jsp - not Action we don't need to repopulate
			// the lists here
			return mapping.findForward(FWD_FAIL);
		}

		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");

		// System.out.println("This is ID from request " + id);
		
		Dictionary dictionary = new Dictionary();
		//get sysUserId from login module
		UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);
		String sysUserId = String.valueOf(usd.getSystemUserId());	
		dictionary.setSysUserId(sysUserId);				
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		
	
		// populate valueholder from form
		PropertyUtils.copyProperties(dictionary, dynaForm);
		
		//bugzilla 2062
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		DictionaryCategoryDAO dictionaryCategoryDAO = new DictionaryCategoryDAOImpl();
		
		String selectedCategoryId = (String)dynaForm.get("selectedDictionaryCategoryId");
		//bugzilla 2108
		DictionaryCategory dictionaryCategory = null;
		if (!StringUtil.isNullorNill(selectedCategoryId)) {
			dictionaryCategory = new DictionaryCategory();
			dictionaryCategory.setId(selectedCategoryId);
			dictionaryCategoryDAO.getData(dictionaryCategory);
		}
		
		dictionary.setDictionaryCategory(dictionaryCategory);

		try {

			if (!isNew) {
				// UPDATE

                //bugzilla 2062
				boolean isDictionaryFrozenCheckRequired = false;
				
				//there is an exception to rule of checking whether dictionary record
				//is frozen (can no longer be updated): 
				//currenly if only isActive has changed and
				//the current value is 'Y'
				//OR
				//bugzilla 1847: also the local abbreviation can be deleted/updated/inserted at anytime
				String dirtyFormFields = (String)dynaForm.get("dirtyFormFields");
				String isActiveValue = (String)dynaForm.get("isActive");
				
				String[] dirtyFields = dirtyFormFields.split(SystemConfiguration
						.getInstance().getDefaultIdSeparator(), -1);
				List listOfDirtyFields = new ArrayList();
				
				for (int i = 0; i < dirtyFields.length; i++) {
					String dirtyField = dirtyFields[i];
					if (!StringUtil.isNullorNill(dirtyField)) {
					 listOfDirtyFields.add(dirtyField);
					}
				}
				
				List listOfDirtyFieldsNoFrozenCheckRequired = new ArrayList();
				listOfDirtyFieldsNoFrozenCheckRequired.add("isActive");
				listOfDirtyFieldsNoFrozenCheckRequired.add("localAbbreviation");
				

				//bugzilla 1847 : added to exception for frozen check required
				// isActive changed to Y (no frozen check required) 
				//localAbbreviation has changed (no frozen check required)
				if (!listOfDirtyFields.isEmpty()) {
					for (int i = 0; i < listOfDirtyFields.size(); i++) {
						String dirtyField = (String)listOfDirtyFields.get(i);
						if (!listOfDirtyFieldsNoFrozenCheckRequired.contains(dirtyField)) {
							isDictionaryFrozenCheckRequired = true;
						} else {
							//in case of isActive: need to make sure it changed to YES to be able
							// to skip isFrozenCheck
							if (dirtyField.equals("isActive") && !isActiveValue.equals(YES)) {
								isDictionaryFrozenCheckRequired = true;
							}
						}
					}
				} else {
					isDictionaryFrozenCheckRequired = false;
				}

	   
				dictionaryDAO.updateData(dictionary, isDictionaryFrozenCheckRequired);

		} else {
				// INSERT

				dictionaryDAO.insertData(dictionary);
			}
			tx.commit();
		} catch (LIMSRuntimeException lre) {
            //bugzilla 2154
            LogEvent.logError("DictionaryUpdateAction","performAction()",lre.toString());    		
			tx.rollback();
			errors = new ActionMessages();
			//1482
			java.util.Locale locale = (java.util.Locale) request.getSession()
			.getAttribute("org.apache.struts.action.LOCALE");
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				// how can I get popup instead of struts error at the top of
				// page?
				// ActionMessages errors = dynaForm.validate(mapping, request);
				error = new ActionError("errors.OptimisticLockException", null,
						null);

			} else {
				//bugzilla 1386
				if (lre.getException() instanceof LIMSDuplicateRecordException) {
					String messageKey = "dictionary.dictEntryByCategory";
					String msg =  ResourceLocator.getInstance().getMessageResources().getMessage(
							locale, messageKey);
					error = new ActionError("errors.DuplicateRecord.activate", msg,
							null);

				} else if (lre.getException() instanceof LIMSFrozenRecordException){
					String messageKey = "dictionary.dictEntry";
					String msg =  ResourceLocator.getInstance().getMessageResources().getMessage(
							locale, messageKey);
					error = new ActionError("errors.FrozenRecord", msg,
							null);
					//Now disallow further edits RECORD_FROZEN_EDIT_DISABLED_KEY
					//in this case User needs to Exit and come back to refresh form
					//for further updates (this is to restore isDirty() functionality
					//that relies on defaultValues of form
					// --this is needed to determine whether frozen check is required
					request.setAttribute(RECORD_FROZEN_EDIT_DISABLED_KEY, "true");
				} else {
				error = new ActionError("errors.UpdateException", null, null);
				}
			}

			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			//bugzilla 1485: allow change and try updating again (enable save button)
			//request.setAttribute(IActionConstants.ALLOW_EDITS_KEY, "false");
			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, "true");
			request.setAttribute(NEXT_DISABLED, "true");
			forward = FWD_FAIL;
		
			
		} finally {
            HibernateUtil.closeSession();
        }
		if (forward.equals(FWD_FAIL))
			return mapping.findForward(forward);
		

		// initialize the form
		dynaForm.initialize(mapping);
		// repopulate the form from valueholder
		PropertyUtils.copyProperties(dynaForm, dictionary);

		if ("true".equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		if (dictionary.getId() != null && !dictionary.getId().equals("0")) {
			request.setAttribute(ID, dictionary.getId());

		}

		//bugzilla 1400
		if (isNew) forward = FWD_SUCCESS_INSERT;
		//bugzilla 1467 added direction for redirect to NextPreviousAction
		return getForward(mapping.findForward(forward), id, start, direction);

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