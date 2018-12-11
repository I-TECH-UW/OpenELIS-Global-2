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
package us.mn.state.health.lims.workplan.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.resultvalidation.action.util.ResultValidationItem;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.resultvalidation.util.ResultsValidationRetroCIUtility;

public class ElisaAlgorithmWorkplanAction extends BaseWorkplanAction {

	private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private ResultsValidationRetroCIUtility resultsValidationUtility = new ResultsValidationRetroCIUtility();
	private List<Integer> notValidStatus = new ArrayList<Integer>();


	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		// Initialize the form.
		dynaForm.initialize(mapping);
		String workplan = request.getParameter("type");
		setRequestType(workplan);

		setNotValidStatus();

		List<AnalysisItem> workplanTests;

		// workplan by department
		if (!GenericValidator.isBlankOrNull(workplan)) {

			// get tests based on test section
			workplanTests = getWorkplanByTestSection(workplan);
			PropertyUtils.setProperty(dynaForm, "resultList", workplanTests);

		} else {
			// set workplanTests as empty
			PropertyUtils.setProperty(dynaForm, "resultList", new ArrayList<AnalysisItem>());
		}

		PropertyUtils.setProperty(dynaForm, "workplanType", request.getParameter("type"));
		PropertyUtils.setProperty(dynaForm, "testName", "Serology");
		PropertyUtils.setProperty(dynaForm, "testSectionsByName" , new ArrayList<IdValuePair>());
		
		//this is needed because the jsp form is shared with the biologist validation
		PagingBean pagingBean = new PagingBean();
		pagingBean.setSearchTermToPage(new ArrayList<IdValuePair>());
		PropertyUtils.setProperty(dynaForm, "paging", pagingBean);

		return mapping.findForward(FWD_SUCCESS);
	}

	@SuppressWarnings("unchecked")
	private List<AnalysisItem> getWorkplanByTestSection(String testSection) {

		List<Analysis> testList;
		List<ResultValidationItem> testItemList;
		List<AnalysisItem> workplanTestList = new ArrayList<AnalysisItem>();

		if (!(GenericValidator.isBlankOrNull(testSection))) {

			String sectionId = getTestSectionId();
			testList = (List<Analysis>) analysisDAO.getAllAnalysisByTestSectionAndExcludedStatus(sectionId, notValidStatus);

			if (testList.isEmpty()) {
				return new ArrayList<AnalysisItem>();
			}

			testItemList = resultsValidationUtility.getGroupedTestsForAnalysisList(testList, true);
			workplanTestList = testResultListToELISAItemList(testItemList);

		}

		return workplanTestList;
	}


	public List<AnalysisItem> testResultListToELISAItemList(List<ResultValidationItem> testResultList) {
		List<AnalysisItem> analysisItemList = new ArrayList<AnalysisItem>();
		AnalysisItem analysisResultItem = new AnalysisItem();
		String currentAccessionNumber = "";
		boolean hasFinalResult = false;
		
		Iterator<ResultValidationItem> i = testResultList.iterator();

		while (i.hasNext()) {
			ResultValidationItem validationItem = i.next();

			// create new bean
			if (!validationItem.getAccessionNumber().equals(currentAccessionNumber)) {

				if (!GenericValidator.isBlankOrNull(currentAccessionNumber)
						&& GenericValidator.isBlankOrNull(analysisResultItem.getFinalResult())
						&& !GenericValidator.isBlankOrNull(analysisResultItem.getNextTest())) {
					analysisItemList.add(analysisResultItem);
				}

				analysisResultItem = resultsValidationUtility.testResultItemToELISAAnalysisItem(validationItem);
				String finalResult = resultsValidationUtility.checkIfFinalResult(validationItem.getAnalysis().getId());

				if (!GenericValidator.isBlankOrNull(finalResult)) {
					analysisResultItem.setFinalResult(finalResult);
				}
				if (GenericValidator.isBlankOrNull(analysisResultItem.getResult())) {
					setNextTest(analysisResultItem, analysisResultItem.getTestName());
				}

				currentAccessionNumber = analysisResultItem.getAccessionNumber();

				hasFinalResult = false;
				// or just add test result to elisaAlgorithm bean
			} else if(!hasFinalResult ) {
				String finalResult = resultsValidationUtility.checkIfFinalResult(validationItem.getAnalysis().getId());

				if (!GenericValidator.isBlankOrNull(finalResult)) {
					analysisResultItem.setFinalResult(finalResult);
					hasFinalResult = true;
				}
				if (!GenericValidator.isBlankOrNull(validationItem.getResultValue())) {
					analysisResultItem.setResult(validationItem.getResultValue());
				} else {
					setNextTest(analysisResultItem, validationItem.getTestName());
				}
				analysisResultItem = resultsValidationUtility.addTestResultToELISAAnalysisItem(validationItem, analysisResultItem);
			}

			// add if reached end of list
			if (!i.hasNext()) {
				if (GenericValidator.isBlankOrNull(analysisResultItem.getFinalResult())
						&& !GenericValidator.isBlankOrNull(analysisResultItem.getNextTest())) {
					analysisItemList.add(analysisResultItem);
				}
			}

		}
		
		return analysisItemList;
	}

	private void setNextTest(AnalysisItem analysisItem, String newTestName) {
		if (!GenericValidator.isBlankOrNull(analysisItem.getNextTest())) {
			newTestName = analysisItem.getNextTest() + ":" + newTestName;
			
			newTestName = sortTestNames(newTestName);
		}

		analysisItem.setNextTest(newTestName);
		analysisItem.setResult(null); // clear result, don't want to display

	}

	private String sortTestNames(String newTestName) {
		
		String[] nameList = newTestName.split(":");
		Arrays.sort(nameList);
		
		StringBuilder buffer = new StringBuilder();
		buffer.append(nameList[0]);
		for( int i = 1; i < nameList.length; i++ ){
			buffer.append(", ");
			buffer.append( nameList[i]);
		}
			
		return buffer.toString();
	}

	protected List<Integer> setNotValidStatus() {
		notValidStatus = new ArrayList<Integer>();
		notValidStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));
		notValidStatus.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));

		return notValidStatus;
	}

}
