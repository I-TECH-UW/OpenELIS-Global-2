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
package org.openelisglobal.workplan.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openelisglobal.analysis.dao.AnalysisDAO;
import org.openelisglobal.analysis.daoimpl.AnalysisDAOImpl;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.ObservationHistoryService;
import org.openelisglobal.common.services.ObservationHistoryService.ObservationType;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.common.services.QAService.QAObservationType;
import org.openelisglobal.common.services.TestService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.panel.dao.PanelDAO;
import org.openelisglobal.panel.daoimpl.PanelDAOImpl;
import org.openelisglobal.panelitem.dao.PanelItemDAO;
import org.openelisglobal.panelitem.daoimpl.PanelItemDAOImpl;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleqaevent.dao.SampleQaEventDAO;
import org.openelisglobal.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;
import org.openelisglobal.test.beanItems.TestResultItem;
//import org.openelisglobal.test.valueholder.TestSection;

public class WorkplanByPanelAction extends BaseWorkplanAction {

	private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private final PanelDAO panelDAO = new PanelDAOImpl();
	private final PanelItemDAO panelItemDAO = new PanelItemDAOImpl();

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		dynaForm.initialize(mapping);
		setRequestType( "panel");
		

		List<TestResultItem> workplanTests;

		String panelID = request.getParameter("selectedSearchID");

		if (!GenericValidator.isBlankOrNull(panelID)) {
			String panelName = getPanelName(panelID);
			workplanTests = getWorkplanByPanel(panelID);

			// resultsLoadUtility.sortByAccessionAndSequence(workplanTests);
			PropertyUtils.setProperty(dynaForm, "testTypeID", panelID);
			PropertyUtils.setProperty(dynaForm, "testName", panelName);
			PropertyUtils.setProperty(dynaForm, "workplanTests", workplanTests);
			PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.TRUE);
		} else {
			// no search done, set workplanTests as empty
			PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.FALSE);
			PropertyUtils.setProperty(dynaForm, "testName", null);
			PropertyUtils.setProperty(dynaForm, "workplanTests", new ArrayList<TestResultItem>());
		}

		PropertyUtils.setProperty(dynaForm, "workplanType", "panel");
		PropertyUtils.setProperty(dynaForm, "searchTypes", DisplayListService.getInstance().getList( DisplayListService.ListType.PANELS ));
		PropertyUtils.setProperty(dynaForm, "searchLabel", StringUtil.getMessageForKey("workplan.panel.types"));
		PropertyUtils.setProperty(dynaForm, "searchAction", "WorkPlanByPanel.do");

		return mapping.findForward(FWD_SUCCESS);
	}

	@SuppressWarnings("unchecked")
	private List<TestResultItem> getWorkplanByPanel(String panelId) {

		List<TestResultItem> workplanTestList = new ArrayList<TestResultItem>();
		// check for patient name addition 
		boolean addPatientName = isPatientNameAdded();
		
		if (!(GenericValidator.isBlankOrNull(panelId) || panelId.equals("0"))) {

			List<PanelItem> panelItems = panelItemDAO.getPanelItemsForPanel(panelId);
			
			for(PanelItem panelItem : panelItems){
				List<Analysis> analysisList = analysisDAO.getAllAnalysisByTestAndStatus(panelItem.getTest().getId(), statusList);
				
				for( Analysis analysis : analysisList){
						TestResultItem testResultItem = new TestResultItem();
						Sample sample = analysis.getSampleItem().getSample();
						testResultItem.setAccessionNumber(sample.getAccessionNumber());
						testResultItem.setPatientInfo(getSubjectNumber(analysis));
						testResultItem.setNextVisitDate(ObservationHistoryService.getValueForSample( ObservationType.NEXT_VISIT_DATE, sample.getId() ));
						testResultItem.setReceivedDate(getReceivedDateDisplay(sample));
						testResultItem.setTestName( TestServiceImpl.getUserLocalizedTestName( analysis.getTest() ));
						testResultItem.setNonconforming(QAService.isAnalysisParentNonConforming(analysis));
						if(FormFields.getInstance().useField(Field.QaEventsBySection) )
							testResultItem.setNonconforming(getQaEventByTestSection(analysis));

						if (addPatientName)
						    testResultItem.setPatientName(getPatientName(analysis));

						workplanTestList.add(testResultItem);
				}
			}
			
			Collections.sort(workplanTestList, new Comparator<TestResultItem>(){
				@Override
				public int compare(TestResultItem o1, TestResultItem o2) {
					return o1.getAccessionNumber().compareTo(o2.getAccessionNumber());
				}
				
			});
			
			String currentAccessionNumber = null;
			int sampleGroupingNumber = 0;
			
			int newIndex = 0;
			int newElementsAdded = 0;
			int workplanTestListOrigSize = workplanTestList.size();
			
			for (int i=0; newIndex < (workplanTestListOrigSize + newElementsAdded) ; i++) { 
			    
			    TestResultItem testResultItem = workplanTestList.get(newIndex);
			    
				if (!testResultItem.getAccessionNumber().equals(currentAccessionNumber)) {
					sampleGroupingNumber++;
					if (addPatientName) {
					    addPatientNameToList(testResultItem, workplanTestList, newIndex, sampleGroupingNumber);
					    newIndex++; newElementsAdded++;
					}
					
					currentAccessionNumber = testResultItem.getAccessionNumber();
				}
				testResultItem.setSampleGroupingNumber(sampleGroupingNumber);	
				newIndex++;
			}

		}

		return workplanTestList;
	}
	
    private void addPatientNameToList(TestResultItem firstTestResultItem, List<TestResultItem> workplanTestList, int insertPosition, int sampleGroupingNumber) {
            TestResultItem testResultItem = new TestResultItem();
            testResultItem.setAccessionNumber(firstTestResultItem.getAccessionNumber());
            testResultItem.setPatientInfo(firstTestResultItem.getPatientInfo());
            testResultItem.setReceivedDate(firstTestResultItem.getReceivedDate());
            // Add Patient Name to top of test list
            testResultItem.setTestName(firstTestResultItem.getPatientName());
            testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
            testResultItem.setServingAsTestGroupIdentifier(true);
            workplanTestList.add(insertPosition, testResultItem);
            
    }
	
    private boolean isPatientNameAdded() {
        return ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP");
    }
	
	private String getPanelName(String panelId) {
		return panelDAO.getNameForPanelId(panelId);
	}

	private boolean getQaEventByTestSection(Analysis analysis){
		
		if (analysis.getTestSection()!=null && analysis.getSampleItem().getSample()!=null) {
			Sample sample=analysis.getSampleItem().getSample();
			List<SampleQaEvent> sampleQaEventsList=getSampleQaEvents(sample);
			for(SampleQaEvent event : sampleQaEventsList){
				QAService qa = new QAService(event);
				if(!GenericValidator.isBlankOrNull(qa.getObservationValue( QAObservationType.SECTION )) && qa.getObservationValue( QAObservationType.SECTION ).equals(analysis.getTestSection().getNameKey()))
					 return true;				
			}
		}
		return false;
	}

	public List<SampleQaEvent> getSampleQaEvents(Sample sample){
		SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();
		return sampleQaEventDAO.getSampleQaEventsBySample(sample);
	}
	

}
