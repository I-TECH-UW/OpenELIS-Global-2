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
 * Copyright (C) I-TECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.resultvalidation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationPaging;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationRetroCIUtility;

public class ResultValidationRetroCIAction extends BaseResultValidationRetroCIAction {

    @Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(SAVE_DISABLED, "true");
		String testSectionName = (request.getParameter("type"));
		String testName = (request.getParameter("test"));

		ResultValidationPaging paging = new ResultValidationPaging();
		String newPage = request.getParameter("page");

		if (GenericValidator.isBlankOrNull(newPage)) {

			// Initialize the form.
			dynaForm.initialize(mapping);
			PropertyUtils.setProperty(dynaForm, "testSectionsByName", new ArrayList<IdValuePair>()); //required on jsp page
			PropertyUtils.setProperty(dynaForm, "displayTestSections", false);

            ResultsValidationRetroCIUtility resultsValidationUtility = new ResultsValidationRetroCIUtility();
			setRequestType(testSectionName);

			if (!GenericValidator.isBlankOrNull(testSectionName)) {
				String sectionName = Character.toUpperCase(testSectionName.charAt(0)) + testSectionName.substring(1);
				sectionName = getDBSectionName(sectionName);
                List<AnalysisItem> resultList = resultsValidationUtility.getResultValidationList( sectionName, testName, getValidationStatus( testSectionName ) );
				paging.setDatabaseResults(request, dynaForm, resultList);
			}
			
		} else {
			paging.page(request, dynaForm, newPage);
		}

		if (testSectionName.equals("serology")) {
			return mapping.findForward("elisaSuccess");
		} else {
			return mapping.findForward(FWD_SUCCESS);
		}
	}

	public List<Integer> getValidationStatus(String testSection) {
        List<Integer> validationStatus = new ArrayList<Integer>();

		if ("serology".equals(testSection)) {
			validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
			validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));
			// This next status determines if NonConformity analysis can still
			// be displayed on bio. validation page. We are awaiting feedback on
			// RetroCI
			// validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming)));
		} else {
            validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
            if( ConfigurationProperties.getInstance().isPropertyValueEqual( ConfigurationProperties.Property.VALIDATE_REJECTED_TESTS , "true")){
                validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
            }
		}

        return validationStatus;
	}
}
