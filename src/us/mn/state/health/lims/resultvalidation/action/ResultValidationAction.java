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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
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
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationPaging;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationUtility;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

public class ResultValidationAction extends BaseResultValidationAction {

    @Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(SAVE_DISABLED, "true");
		String testSectionId = (request.getParameter("testSectionId"));
		
		

		ResultValidationPaging paging = new ResultValidationPaging();
		String newPage = request.getParameter("page");

		TestSection ts = null;
		
		
		

		if (GenericValidator.isBlankOrNull(newPage)) {

			// Initialize the form.
			dynaForm.initialize(mapping);

		
			
			// load testSections for drop down
			TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
			PropertyUtils.setProperty(dynaForm, "testSections", DisplayListService.getList(ListType.TEST_SECTION));
			PropertyUtils.setProperty(dynaForm, "testSectionsByName", DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));
			
			if (!GenericValidator.isBlankOrNull(testSectionId)) {
				ts = testSectionDAO.getTestSectionById(testSectionId);
				PropertyUtils.setProperty(dynaForm, "testSectionId", "0");
			}
			
			
			List<AnalysisItem> resultList;
			ResultsValidationUtility resultsValidationUtility = new ResultsValidationUtility();
			setRequestType(ts == null ? StringUtil.getMessageForKey("workplan.unit.types") : ts.getLocalizedName());
			if (!GenericValidator.isBlankOrNull(testSectionId)) {
               resultList = resultsValidationUtility.getResultValidationList( getValidationStatus(), testSectionId );
				
			} else {
				resultList = new ArrayList<AnalysisItem>();
			}
			paging.setDatabaseResults(request, dynaForm, resultList);
			
		} else {
			paging.page(request, dynaForm, newPage);
		}

		return mapping.findForward(FWD_SUCCESS);
	}

	public List<Integer> getValidationStatus() {
        List<Integer> validationStatus = new ArrayList<Integer>();
        validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance)));
        if( ConfigurationProperties.getInstance().isPropertyValueEqual( ConfigurationProperties.Property.VALIDATE_REJECTED_TESTS , "true")){
            validationStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
        }

        return validationStatus;
	}
	

}
