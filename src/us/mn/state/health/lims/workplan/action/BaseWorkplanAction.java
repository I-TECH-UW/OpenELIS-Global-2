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
package us.mn.state.health.lims.workplan.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.services.ObservationHistoryService;
import us.mn.state.health.lims.common.services.ObservationHistoryService.ObservationType;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.result.action.util.ResultsLoadUtility;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class BaseWorkplanAction extends BaseAction {

	public enum WorkplanType {
		UNKNOWN, 
		TEST, 
		PANEL,
		IMMUNOLOGY, 
		HEMATO_IMMUNOLOGY,
		HEMATOLOGY, 
		BIOCHEMISTRY, 
		SEROLOGY, 
		VIROLOGY,
		CHEM, 
		BACTERIOLOGY, 
		PARASITOLOGY, 
		IMMUNO, 
		ECBU, 
		HIV, 
		MYCROBACTERIOLOGY, 
		MOLECULAR_BIOLOGY, 
		LIQUID_BIOLOGY, 
		ENDOCRINOLOGY, 
		CYTOBACTERIOLOGY,
		MYCOLOGY,
		SEROLOGY_IMMUNOLOGY,
        MALARIA
	}

	protected WorkplanType workplanType = WorkplanType.UNKNOWN;	
	protected final TestDAO testDAO = new TestDAOImpl();

	protected ResultsLoadUtility resultsLoadUtility = new ResultsLoadUtility();

	protected static List<Integer> statusList;
	protected static boolean useReceptionTime = FormFields.getInstance().useField(Field.SampleEntryUseReceptionHour);
	protected List<String> nfsTestIdList = new ArrayList<String>();
	protected String testSectionId = "";

	static{
		
		statusList = new ArrayList<Integer>();
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted)));
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected)));
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated)));
		
	}



	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = FWD_SUCCESS;

		return mapping.findForward(forward);
	}

	private String titleKey = "";
	@Override
	protected String getPageTitleKey() {
		return titleKey;
	}

	@Override
	protected String getPageSubtitleKey() {
		return titleKey;
	}
	
	protected String getMessageForKey(String messageKey) throws Exception {
		return StringUtil.getMessageForKey("workplan.page.title", messageKey);
	}
	
	protected void setRequestType(String section) {
		if(!GenericValidator.isBlankOrNull(section)){
			titleKey = section;
		}
	}	
	
	protected void setNFSTestIdList() {
		nfsTestIdList = new ArrayList<String>();
		nfsTestIdList.add(getTestId("GB"));
		nfsTestIdList.add(getTestId("Neut %"));
		nfsTestIdList.add(getTestId("Lymph %"));
		nfsTestIdList.add(getTestId("Mono %"));
		nfsTestIdList.add(getTestId("Eo %"));
		nfsTestIdList.add(getTestId("Baso %"));
		nfsTestIdList.add(getTestId("GR"));
		nfsTestIdList.add(getTestId("Hb"));
		nfsTestIdList.add(getTestId("HCT"));
		nfsTestIdList.add(getTestId("VGM"));
		nfsTestIdList.add(getTestId("TCMH"));
		nfsTestIdList.add(getTestId("CCMH"));
		nfsTestIdList.add(getTestId("PLQ"));
	}

	protected boolean allNFSTestsRequested(List<String> testIdList) {
		return (testIdList.containsAll(nfsTestIdList));

	}

	protected String getTestId(String testName) {
		Test test = testDAO.getTestByName(testName);
		return test.getId();

	}

	protected String getSubjectNumber( Analysis analysis){
		if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SUBJECT_ON_WORKPLAN, "true")){
		  IPatientService patientService = new PatientService(analysis.getSampleItem().getSample());
		  return patientService.getSubjectNumber();
		}else{
			return "";
		}
	}
	
    protected String getPatientName(Analysis analysis){
        if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")){
            Sample sample = analysis.getSampleItem().getSample();
            IPatientService patientService = new PatientService(sample);
            List<String> values = new ArrayList<String>();
            values.add(patientService.getLastName() == null ? "" : patientService.getLastName().toUpperCase());
            values.add(patientService.getNationalId());
            
            String referringPatientId = ObservationHistoryService.getValueForSample( ObservationType.REFERRERS_PATIENT_ID, sample.getId() );
            values.add( referringPatientId == null ? "" : referringPatientId);
            return StringUtil.buildDelimitedStringFromList(values, " / ", true);
                        
        } else 
            return ""; 
    }
    
	protected String getReceivedDateDisplay(Sample sample){
		String receptionTime = useReceptionTime ? " " + sample.getReceivedTimeForDisplay( ) : "";
		return sample.getReceivedDateForDisplay() + receptionTime;
	}
	
	protected void setTestSectionId(String testSectionId) {
		this.testSectionId = testSectionId;
	}
	
	protected String getTestSectionId() {
		return testSectionId;
	}
	
	
	static class TypeNameGroup{
		private String name;
		private String key;
		private WorkplanType workplanType;
		
		TypeNameGroup( String name, String key, WorkplanType workplanType){
			this.name = name;
			this.key = key;
			this.workplanType = workplanType;
		}

		public String getName() {
			return name;
		}


		public String getKey() {
			return key;
		}

		public WorkplanType getWorkplanType() {
			return workplanType;
		}

	}
}
