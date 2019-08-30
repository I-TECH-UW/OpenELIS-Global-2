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
package org.openelisglobal.result.action;

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

import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.statusofsample.util.StatusRules;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.test.dao.TestSectionDAO;
import org.openelisglobal.test.daoimpl.TestSectionDAOImpl;
import org.openelisglobal.test.valueholder.TestSection;

public class ResultsLogbookEntryAction extends ResultsLogbookBaseAction {

	private InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);

	@Override
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
		PropertyUtils.setProperty(dynaForm, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(dynaForm, "rejectReasons",
				DisplayListService.getInstance().getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		// load testSections for drop down
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<IdValuePair> testSections = DisplayListService.getInstance().getList(ListType.TEST_SECTION);
		PropertyUtils.setProperty(dynaForm, "testSections", testSections);
		PropertyUtils.setProperty(dynaForm, "testSectionsByName",
				DisplayListService.getInstance().getList(ListType.TEST_SECTION_BY_NAME));

		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionDAO.getTestSectionById(testSectionId);
			PropertyUtils.setProperty(dynaForm, "testSectionId", "0");
		}

		setRequestType(ts == null ? StringUtil.getMessageForKey("workplan.unit.types") : ts.getLocalizedName());

		List<TestResultItem> tests;

		ResultsPaging paging = new ResultsPaging();
		List<InventoryKitItem> inventoryList = new ArrayList<>();
		ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility(currentUserId);

		if (GenericValidator.isBlankOrNull(requestedPage)) {

			new StatusRules().setAllowableStatusForLoadingResults(resultsLoadUtility);

			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				tests = resultsLoadUtility.getUnfinishedTestResultItemsInTestSection(testSectionId);
			} else {
				tests = new ArrayList<>();
			}

			if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.PATIENT_DATA_ON_RESULTS_BY_ROLE,
					"true") && !userHasPermissionForModule(request, "PatientResults")) {
				for (TestResultItem resultItem : tests) {
					resultItem.setPatientInfo("---");
				}

			}

			// commented out to allow maven compilation - CSL
			// paging.setDatabaseResults(request, dynaForm, tests);

		} else {

			// commented out to allow maven compilation - CSL
			// paging.page(request, dynaForm, requestedPage);
		}
		if (ts != null) {
			// this does not look right what happens after a new page!!!
			boolean isHaitiClinical = ConfigurationProperties.getInstance()
					.isPropertyValueEqual(Property.configurationName, "Haiti Clinical");
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
