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
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.ObservationHistoryService;
import us.mn.state.health.lims.common.services.ObservationHistoryService.ObservationType;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
//import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
//import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

public class WorkplanByTestSectionAction extends BaseWorkplanAction {

	private final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static boolean HAS_NFS_PANEL = false;


	static {
		HAS_NFS_PANEL = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.CONDENSE_NFS_PANEL, "true");
	}
	
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		String testSectionId = (request.getParameter("testSectionId"));

		// Initialize the form.
		dynaForm.initialize(mapping);
		String workplan = request.getParameter("type");
		
		// load testSections for drop down
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		PropertyUtils.setProperty(dynaForm, "testSections", DisplayListService.getList(ListType.TEST_SECTION));
		PropertyUtils.setProperty(dynaForm, "testSectionsByName", DisplayListService.getList(ListType.TEST_SECTION_BY_NAME));
		
		TestSection ts = null;
		
		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			ts = testSectionDAO.getTestSectionById(testSectionId);
			PropertyUtils.setProperty(dynaForm, "testSectionId", "0");
		}
		
		
		
		List<TestResultItem> workplanTests = new ArrayList<TestResultItem>();

		nfsTestIdList = new ArrayList<String>();
		if( HAS_NFS_PANEL){
			setNFSTestIdList();
		}

		// workplan by department
		setRequestType(ts == null ? StringUtil.getMessageForKey("workplan.unit.types") : ts.getLocalizedName());
		if (!GenericValidator.isBlankOrNull(testSectionId)) {
			// get tests based on test section
			workplanTests = getWorkplanByTestSection(testSectionId);
			PropertyUtils.setProperty(dynaForm, "workplanTests", workplanTests);
			PropertyUtils.setProperty(dynaForm, "searchFinished", Boolean.TRUE);
			PropertyUtils.setProperty(dynaForm, "testName", ts.getLocalizedName());

		} else {
			// set workplanTests as empty
			PropertyUtils.setProperty(dynaForm, "workplanTests", new ArrayList<TestResultItem>());
		}
		resultsLoadUtility.sortByAccessionAndSequence(workplanTests);
		// add Patient Name to test table 
		if (isPatientNameAdded())
		    addPatientNamesToList(workplanTests);
		PropertyUtils.setProperty(dynaForm, "workplanType", workplan);
		PropertyUtils.setProperty(dynaForm, "searchLabel", StringUtil.getMessageForKey("workplan.unit.types"));
		return mapping.findForward(FWD_SUCCESS);
	}

	@SuppressWarnings("unchecked")
	private List<TestResultItem> getWorkplanByTestSection(String testSectionId) {
		
		List<Analysis> testList = new ArrayList<Analysis>();
		List<TestResultItem> workplanTestList = new ArrayList<TestResultItem>();
		String currentAccessionNumber = new String();
		String subjectNumber = new String();
		String patientName = new String();
		String nextVisit = new String();
		int sampleGroupingNumber = 0;
		List<String> testIdList = new ArrayList<String>();
		List<TestResultItem> nfsTestItemList = new ArrayList<TestResultItem>();
		boolean isNFSTest = false;
		TestResultItem testResultItem = new TestResultItem();

		if (!(GenericValidator.isBlankOrNull(testSectionId))) {

			String sectionId = testSectionId; 
			testList = (List<Analysis>) analysisDAO.getAllAnalysisByTestSectionAndStatus(sectionId, statusList, true);

			if (testList.isEmpty()) {
				return new ArrayList<TestResultItem>();
			}
			
			
			for (Analysis analysis : testList) {
				Sample sample = analysis.getSampleItem().getSample();
				String analysisAccessionNumber = sample.getAccessionNumber();

				if (!analysisAccessionNumber.equals(currentAccessionNumber)) {

					if (isNFSTest) {
						if (!allNFSTestsRequested(testIdList)) {
							// add nfs subtests
							for (TestResultItem nfsTestItem : nfsTestItemList) {
								workplanTestList.add(nfsTestItem);
							}
						}
					}

					sampleGroupingNumber++;
					
					currentAccessionNumber = analysisAccessionNumber;
					testIdList = new ArrayList<String>();
					nfsTestItemList = new ArrayList<TestResultItem>();
					isNFSTest = false;
					
					subjectNumber = getSubjectNumber(analysis);
					patientName = getPatientName(analysis);
					nextVisit = ObservationHistoryService.getValueForSample(ObservationType.NEXT_VISIT_DATE, sample.getId());
				}

				AnalysisService analysisService = new AnalysisService(analysis);
				testResultItem = new TestResultItem();
				testResultItem.setTestName(analysisService.getTestDisplayName( ))  ;
				testResultItem.setAccessionNumber(currentAccessionNumber);
				testResultItem.setReceivedDate(getReceivedDateDisplay(sample) );
				testResultItem.setSampleGroupingNumber(sampleGroupingNumber);
				testResultItem.setTestId(analysis.getTest().getId());
				testResultItem.setNonconforming(QAService.isAnalysisParentNonConforming(analysis));
				
				if(FormFields.getInstance().useField(Field.QaEventsBySection) )
					testResultItem.setNonconforming(getQaEventByTestSection(analysis));
				
				testResultItem.setPatientInfo(subjectNumber);
				testResultItem.setNextVisitDate( nextVisit );
				if (isPatientNameAdded())
				    testResultItem.setPatientName(patientName);
				testIdList.add(testResultItem.getTestId());

				if (nfsTestIdList.contains(testResultItem.getTestId())) {
					isNFSTest = true;
					nfsTestItemList.add(testResultItem);
				}
				if (isNFSTest) {

					if (allNFSTestsRequested(testIdList)) {
						testResultItem.setTestName("NFS");
						workplanTestList.add(testResultItem);
					} else if (!nfsTestItemList.isEmpty() && (testList.size() - 1) == testList.indexOf(analysis)) {
						// add nfs subtests
						for (TestResultItem nfsTestItem : nfsTestItemList) {
							workplanTestList.add(nfsTestItem);
						}
					}
				} else {
					workplanTestList.add(testResultItem);
				}
			}

		}

		return workplanTestList;
	}

	private void addPatientNamesToList(List<TestResultItem> workplanTestList) {
        String currentAccessionNumber = new String();
        int sampleGroupingNumber = 0;
        
        int newIndex = 0;
        int newElementsAdded = 0;
        int workplanTestListOrigSize = workplanTestList.size();
        
        for (int i=0; newIndex < (workplanTestListOrigSize + newElementsAdded) ; i++) { 
            
            TestResultItem testResultItem = (TestResultItem) workplanTestList.get(newIndex);
            
            if (!testResultItem.getAccessionNumber().equals(currentAccessionNumber)) {
                sampleGroupingNumber++;
                if (isPatientNameAdded()) {
                    addPatientNameToList(testResultItem, workplanTestList, newIndex, sampleGroupingNumber);
                    newIndex++; newElementsAdded++;
                }
                
                currentAccessionNumber = testResultItem.getAccessionNumber();
            }
            testResultItem.setSampleGroupingNumber(sampleGroupingNumber);   
            newIndex++;
        }

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
