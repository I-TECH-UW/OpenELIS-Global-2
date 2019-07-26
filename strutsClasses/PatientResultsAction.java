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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.patient.dao.PatientDAO;
import org.openelisglobal.patient.daoimpl.PatientDAOImpl;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.test.beanItems.TestResultItem;

public class PatientResultsAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ResultsLoadUtility resultsUtility = new ResultsLoadUtility(currentUserId);
		String forward = FWD_SUCCESS;

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		DynaActionForm dynaForm = (DynaActionForm) form;
		PropertyUtils.setProperty(dynaForm, "displayTestKit", Boolean.FALSE);
		PropertyUtils.setProperty(dynaForm, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(dynaForm, "rejectReasons",
				DisplayListService.getInstance().getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));
		PatientSearch patientSearch = new PatientSearch();
		patientSearch.setLoadFromServerWithPatient(true);
		patientSearch.setSelectedPatientActionButtonText(StringUtil.getMessageForKey("resultsentry.patient.search"));
		PropertyUtils.setProperty(dynaForm, "patientSearch", patientSearch);

		ResultsPaging paging = new ResultsPaging();
		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {

			String patientID = request.getParameter("patientID");

			if (!GenericValidator.isBlankOrNull(patientID)) {

				PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.TRUE);
				Patient patient = getPatient(patientID);

				String statusRules = ConfigurationProperties.getInstance()
						.getPropertyValueUpperCase(Property.StatusRules);
				if (statusRules.equals(IActionConstants.STATUS_RULES_RETROCI)) {
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.TechnicalRejected);
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				} else if (statusRules.equals(IActionConstants.STATUS_RULES_HAITI)
						|| statusRules.equals(IActionConstants.STATUS_RULES_HAITI_LNSP)) {
					resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				}

				List<TestResultItem> results = resultsUtility.getGroupedTestsForPatient(patient);

				PropertyUtils.setProperty(dynaForm, "testResult", results);

				// move this out of results utility

				// commented out to allow maven compilation - CSL
				// resultsUtility.addIdentifingPatientInfo( patient, dynaForm );

				if (resultsUtility.inventoryNeeded()) {
					addInventory(dynaForm);
					PropertyUtils.setProperty(dynaForm, "displayTestKit", true);
				} else {
					addEmptyInventoryList(dynaForm);
				}

				// commented out to allow maven compilation - CSL
				// paging.setDatabaseResults(request, dynaForm, results);

			} else {
				PropertyUtils.setProperty(dynaForm, "testResult", new ArrayList<TestResultItem>());
				PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.FALSE);
			}
		} else {

			// commented out to allow maven compilation - CSL
			// paging.page(request, dynaForm, newPage);
		}

		return mapping.findForward(forward);
	}

	private void addInventory(DynaActionForm dynaForm)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(dynaForm, "inventoryItems", list);
	}

	private void addEmptyInventoryList(DynaActionForm dynaForm)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "inventoryItems", new ArrayList<InventoryKitItem>());
	}

	private Patient getPatient(String patientID) {
		PatientDAO patientDAO = new PatientDAOImpl();
		Patient patient = new Patient();
		patient.setId(patientID);
		patientDAO.getData(patient);

		return patient;
	}

	@Override
	protected String getPageTitleKey() {
		return "banner.menu.results";

	}

	@Override
	protected String getPageSubtitleKey() {
		return "banner.menu.results";
	}

}
