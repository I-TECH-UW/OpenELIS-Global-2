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

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.result.action.util.ResultsPaging;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;

public class AccessionResultsAction extends BaseAction {

	private String accessionNumber;
	private Sample sample;
	private InventoryUtility inventoryUtility = new InventoryUtility();
	private static SampleDAO sampleDAO = new SampleDAOImpl();
	private static UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
	private static String RESULT_EDIT_ROLE_ID;
	
	static{
		Role editRole = new RoleDAOImpl().getRoleByName("Results modifier");
		
		if( editRole != null){
			RESULT_EDIT_ROLE_ID = editRole.getId();
		}
	}

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		DynaActionForm dynaForm = (DynaActionForm) form;
		PropertyUtils.setProperty(dynaForm, "referralReasons", DisplayListService.getList( DisplayListService.ListType.REFERRAL_REASONS));
        PropertyUtils.setProperty( dynaForm, "rejectReasons", DisplayListService.getNumberedListWithLeadingBlank( DisplayListService.ListType.REJECTION_REASONS ) );

		ResultsPaging paging = new ResultsPaging();
		String newPage = request.getParameter("page");
		if (GenericValidator.isBlankOrNull(newPage)) {

			accessionNumber = request.getParameter("accessionNumber");
			PropertyUtils.setProperty(dynaForm, "displayTestKit", false);

			if (!GenericValidator.isBlankOrNull(accessionNumber)) {
				ResultsLoadUtility resultsUtility = new ResultsLoadUtility(currentUserId);
				//This is for Haiti_LNSP if it gets more complicated use the status set stuff
				resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Canceled);
				//resultsUtility.addExcludedAnalysisStatus(AnalysisStatus.Finalized);
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
					resultsUtility.addIdentifingPatientInfo(patient, dynaForm);

					List<TestResultItem> results = resultsUtility.getGroupedTestsForSample(sample, patient);

					if (resultsUtility.inventoryNeeded()) {
						addInventory(dynaForm);
						PropertyUtils.setProperty(dynaForm, "displayTestKit", true);
					} else {
						addEmptyInventoryList(dynaForm);
					}

					paging.setDatabaseResults(request, dynaForm, results);
				} else {
					setEmptyResults(dynaForm);
				}
			} else {
				PropertyUtils.setProperty(dynaForm, "testResult", new ArrayList<TestResultItem>());
				PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.FALSE);
			}
		} else {
			paging.page(request, dynaForm, newPage);
		}

		return mapping.findForward(forward);
	}

	private boolean modifyResultsRoleBased() {
		return "true".equals(ConfigurationProperties.getInstance().getPropertyValue(Property.roleRequiredForModifyResults));
	}

	private boolean userNotInRole(HttpServletRequest request) {
		if( userModuleDAO.isUserAdmin(request)){
			return false;
		}
		
		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
		
		List<String> roleIds = userRoleDAO.getRoleIdsForUser( currentUserId );
		
		return !roleIds.contains(RESULT_EDIT_ROLE_ID);
	}

	private void setEmptyResults(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "testResult", new ArrayList<TestResultItem>());
		PropertyUtils.setProperty(dynaForm, "displayTestKit", false);
		addEmptyInventoryList(dynaForm);
	}

	private void addInventory(DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		List<InventoryKitItem> list = inventoryUtility.getExistingActiveInventory();
		PropertyUtils.setProperty(dynaForm, "inventoryItems", list);
	}

	private void addEmptyInventoryList(DynaActionForm dynaForm) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
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

	protected String getPageTitleKey() {
		return "banner.menu.results";

	}

	protected String getPageSubtitleKey() {
		return "banner.menu.results";
	}

}
