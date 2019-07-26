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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.validator.ActionError;
import org.openelisglobal.inventory.action.InventoryUtility;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.login.dao.userModuleService;
import org.openelisglobal.login.daoimpl.userModuleServiceImpl;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.action.util.ResultsLoadUtility;
import org.openelisglobal.result.action.util.ResultsPaging;
import org.openelisglobal.role.daoimpl.RoleDAOImpl;
import org.openelisglobal.role.valueholder.Role;
import org.openelisglobal.sample.dao.SampleDAO;
import org.openelisglobal.sample.daoimpl.SampleDAOImpl;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.dao.SampleHumanDAO;
import org.openelisglobal.samplehuman.daoimpl.SampleHumanDAOImpl;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.userrole.dao.UserRoleDAO;
import org.openelisglobal.userrole.daoimpl.UserRoleDAOImpl;

public class AccessionResultsAction extends BaseAction {

	private String accessionNumber;
	private Sample sample;
	private InventoryUtility inventoryUtility = SpringContext.getBean(InventoryUtility.class);
	private static SampleDAO sampleDAO = new SampleDAOImpl();
	private static userModuleService userModuleService = new userModuleServiceImpl();
	private static String RESULT_EDIT_ROLE_ID;

	static {
		Role editRole = new RoleDAOImpl().getRoleByName("Results modifier");

		if (editRole != null) {
			RESULT_EDIT_ROLE_ID = editRole.getId();
		}
	}

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		DynaActionForm dynaForm = (DynaActionForm) form;
		PropertyUtils.setProperty(dynaForm, "referralReasons",
				DisplayListService.getInstance().getList(DisplayListService.ListType.REFERRAL_REASONS));
		PropertyUtils.setProperty(dynaForm, "rejectReasons",
				DisplayListService.getInstance().getNumberedListWithLeadingBlank(DisplayListService.ListType.REJECTION_REASONS));

		ResultsPaging paging = new ResultsPaging();
		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {

			accessionNumber = request.getParameter("accessionNumber");
			PropertyUtils.setProperty(dynaForm, "displayTestKit", false);

			if (!GenericValidator.isBlankOrNull(accessionNumber)) {
				ResultsLoadUtility resultsUtility = new ResultsLoadUtility(currentUserId);
				// This is for Haiti_LNSP if it gets more complicated use the status set stuff
				resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				// resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Finalized);
				resultsUtility.setLockCurrentResults(modifyResultsRoleBased() && userNotInRole(request));
				ActionMessages errors = new ActionMessages();
				errors = validateAll(request, errors, dynaForm);

				if (errors != null && errors.size() > 0) {
					saveErrors(request, errors);
					request.setAttribute(ALLOW_EDITS_KEY, "false");

					setEmptyResults(dynaForm);

					return mapping.findForward(FWD_FAIL);
				}

				PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.TRUE);

				getSample();

				if (!GenericValidator.isBlankOrNull(sample.getId())) {
					Patient patient = getPatient();

					// commented out to allow maven compilation - CSL
					// resultsUtility.addIdentifingPatientInfo(patient, dynaForm);

					List<TestResultItem> results = resultsUtility.getGroupedTestsForSample(sample, patient);

					if (resultsUtility.inventoryNeeded()) {
						addInventory(dynaForm);
						PropertyUtils.setProperty(dynaForm, "displayTestKit", true);
					} else {
						addEmptyInventoryList(dynaForm);
					}

					// commented out to allow maven compilation - CSL
					// paging.setDatabaseResults(request, dynaForm, results);
				} else {
					setEmptyResults(dynaForm);
				}
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

	private boolean modifyResultsRoleBased() {
		return "true"
				.equals(ConfigurationProperties.getInstance().getPropertyValue(Property.roleRequiredForModifyResults));
	}

	private boolean userNotInRole(HttpServletRequest request) {
		if (userModuleService.isUserAdmin(request)) {
			return false;
		}

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

		List<String> roleIds = userRoleDAO.getRoleIdsForUser(currentUserId);

		return !roleIds.contains(RESULT_EDIT_ROLE_ID);
	}

	private void setEmptyResults(DynaActionForm dynaForm)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "testResult", new ArrayList<TestResultItem>());
		PropertyUtils.setProperty(dynaForm, "displayTestKit", false);
		addEmptyInventoryList(dynaForm);
	}

	private void addInventory(DynaActionForm dynaForm)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(dynaForm, "inventoryItems", list);
	}

	private void addEmptyInventoryList(DynaActionForm dynaForm)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "inventoryItems", new ArrayList<InventoryKitItem>());
	}

	private ActionMessages validateAll(HttpServletRequest request, ActionMessages errors, DynaActionForm dynaForm) {

		Sample sample = sampleDAO.getSampleByAccessionNumber(accessionNumber);

		if (sample == null) {
			ActionError error = new ActionError("sample.edit.sample.notFound", accessionNumber, null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		return errors;
	}

	private Patient getPatient() {
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		return sampleHumanDAO.getPatientForSample(sample);
	}

	private void getSample() {
		sample = sampleDAO.getSampleByAccessionNumber(accessionNumber);
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
