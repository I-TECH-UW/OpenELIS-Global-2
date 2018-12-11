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
package us.mn.state.health.lims.result.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.statusofsample.util.StatusRules;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

public class ResultsLogbookEntryAction extends ResultsLogbookBaseAction {

	private InventoryUtility inventoryUtility = new InventoryUtility();

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		String requestedPage = request.getParameter("page");
		
		String testSectionId = request.getParameter("testSectionId");
		
		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		TestSection ts = null;		
		
		String currentDate = getCurrentDate();
		PropertyUtils.setProperty(dynaForm, "currentDate", currentDate);
		PropertyUtils.setProperty(dynaForm, "logbookType", request.getParameter("type"));
		PropertyUtils.setProperty(dynaForm, "referralReasons", DisplayListService.getList( DisplayListService.ListType.REFERRAL_REASONS));
        PropertyUtils.setProperty( dynaForm, "rejectReasons", DisplayListService.getNumberedListWithLeadingBlank( DisplayListService.ListType.REJECTION_REASONS ) );
        
		// load testSections for drop down
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<IdValuePair> testSections = DisplayListService.getList(ListType.TEST_SECTION);
		PropertyUtils.setProperty(dynaForm, "testSections", testSections);
		PropertyUtils.setProperty(dynaForm, "testSectionsByName", DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));
		
		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionDAO.getTestSectionById(testSectionId);
			PropertyUtils.setProperty(dynaForm, "testSectionId", "0");
		} 
		
		
		
		setRequestType(ts == null ? StringUtil.getMessageForKey("workplan.unit.types") : ts.getLocalizedName());
		
		List<TestResultItem> tests;

		ResultsPaging paging = new ResultsPaging();
		List<InventoryKitItem> inventoryList = new ArrayList<InventoryKitItem>();
		ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility(currentUserId);

		if (GenericValidator.isBlankOrNull(requestedPage)) {
			
			new StatusRules().setAllowableStatusForLoadingResults(resultsLoadUtility);
			
			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				tests = resultsLoadUtility.getUnfinishedTestResultItemsInTestSection(testSectionId);
			} else {
				tests = new ArrayList<TestResultItem>();
			}

			if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE, "true") &&   
					!userHasPermissionForModule(request, "PatientResults") ){
				for( TestResultItem resultItem : tests){
					resultItem.setPatientInfo("---");
				}
				
			}
			
			paging.setDatabaseResults(request, dynaForm, tests);

		} else {
			paging.page(request, dynaForm, requestedPage);
		}
		if (ts != null) {
			//this does not look right what happens after a new page!!!
			boolean isHaitiClinical = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti Clinical");
			if (resultsLoadUtility.inventoryNeeded() || (isHaitiClinical && ("VCT").equals(ts.getTestSectionName()))) { 
				inventoryList = inventoryUtility.getExistingActiveInventory();
				PropertyUtils.setProperty(dynaForm, "displayTestKit", true);
			} else {
				PropertyUtils.setProperty(dynaForm, "displayTestKit", false);
			}
		}
		PropertyUtils.setProperty(dynaForm, "inventoryItems", inventoryList);

		return mapping.findForward(forward);
	}

	private String getCurrentDate() {
		Date today = Calendar.getInstance().getTime();
		return DateUtil.formatDateAsText(today);

	}

}
