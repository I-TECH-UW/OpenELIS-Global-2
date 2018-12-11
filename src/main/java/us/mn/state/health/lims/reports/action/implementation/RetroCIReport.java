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
package us.mn.state.health.lims.reports.action.implementation;


import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;

public abstract class RetroCIReport extends Report implements IReportCreator {

	protected static final String ANTIRETROVIRAL_STUDY = "Antiretroviral Study";
	protected static final String ANTIRETROVIRAL_FOLLOW_UP_STUDY = "Antiretroviral Followup Study";
	protected static final String VL_STUDY = "Viral Load Results";
	protected static final String EID_STUDY = "Early Infant Diagnosis for HIV Study";
	protected static final String INDETERMINATE_STUDY = "Indeterminate Results";
	protected static final String SPECIAL_REQUEST_STUDY = "Special Request";
	protected static String ANTIRETROVIRAL_STUDY_ID;
	protected static String ANTIRETROVIRAL_FOLLOW_UP_STUDY_ID;
	protected static String EID_STUDY_ID;
	protected static String VL_STUDY_ID;
	protected static String SPECIAL_REQUEST_STUDY_ID;
	protected static String INDETERMINATE_STUDY_ID;
	protected static String OBSERVATION_DOCTOR_ID;
	protected static String OBSERVATION_REQUESTOR_ID;
	protected static String OBSERVATION_HOSPITAL_ID;
	protected static String OBSERVATION_SERVICE_ID;
	protected static String OBSERVATION_PROJECT_ID;
	protected static String OBSERVATION_WHICH_PCR_ID;
	protected static String OBSERVATION_UNDER_INVESTIGATION_ID;
	protected static List<Integer> ANTIRETROVIRAL_ID= new ArrayList<Integer>();


	protected static String CONCLUSION_ID;
	protected static String CD4_CNT_CONCLUSION;

	protected static ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();


	static{
		ObservationHistoryTypeDAO observationTypeDAO = new ObservationHistoryTypeDAOImpl();
		ObservationHistoryType
		observationType = observationTypeDAO.getByName("nameOfDoctor");
		OBSERVATION_DOCTOR_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("nameOfRequestor");
		OBSERVATION_REQUESTOR_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("hospital");
		OBSERVATION_HOSPITAL_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("service");
		OBSERVATION_SERVICE_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("projectFormName");
		OBSERVATION_PROJECT_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("whichPCR");
		OBSERVATION_WHICH_PCR_ID = observationType.getId();
		observationType = observationTypeDAO.getByName("underInvestigation");
		OBSERVATION_UNDER_INVESTIGATION_ID = observationType.getId(); 
		
		AnalyteDAO analyteDAO = new AnalyteDAOImpl();
		Analyte analyte = new Analyte();
		analyte.setAnalyteName("Conclusion");
		analyte = analyteDAO.getAnalyteByName( analyte, false);
		CONCLUSION_ID = analyte.getId();
		analyte.setAnalyteName("generated CD4 Count");
		analyte = analyteDAO.getAnalyteByName( analyte, false);
		CD4_CNT_CONCLUSION = analyte.getId();

		ProjectDAO projectDAO = new ProjectDAOImpl();
		List<Project> projectList = projectDAO.getAllProjects();

		for(Project project : projectList){
			if( ANTIRETROVIRAL_STUDY.equals(project.getProjectName())){
				ANTIRETROVIRAL_STUDY_ID = project.getId();
			}else if(ANTIRETROVIRAL_FOLLOW_UP_STUDY.equals(project.getProjectName())){
				ANTIRETROVIRAL_FOLLOW_UP_STUDY_ID = project.getId();
			}else if( VL_STUDY.equals(project.getProjectName())){
				VL_STUDY_ID = project.getId();
			}else if( EID_STUDY.equals(project.getProjectName())){
				EID_STUDY_ID = project.getId();
			}else if( INDETERMINATE_STUDY.equals(project.getProjectName())){
				INDETERMINATE_STUDY_ID = project.getId();
			}else if( SPECIAL_REQUEST_STUDY.equals(project.getProjectName())){
				SPECIAL_REQUEST_STUDY_ID = project.getId();
			}
		}
		ANTIRETROVIRAL_ID.add(Integer.parseInt(ANTIRETROVIRAL_STUDY_ID));
		ANTIRETROVIRAL_ID.add(Integer.parseInt(ANTIRETROVIRAL_FOLLOW_UP_STUDY_ID));
		ANTIRETROVIRAL_ID.add(Integer.parseInt(VL_STUDY_ID));
	}

    /**
     * @see us.mn.state.health.lims.reports.action.implementation.IReportCreator#initializeReport(us.mn.state.health.lims.common.action.BaseActionForm)
     */
    @Override
    abstract public void initializeReport(BaseActionForm dynaForm);
}
