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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.common.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.RequesterTypeDAOImpl;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;


public class SampleService {
    private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
    private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
    private static final SampleQaEventDAO sampleQaEventDAO  = new SampleQaEventDAOImpl();
    private static final SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
    private static final PersonDAO personDAO = new PersonDAOImpl();
    public static final String TABLE_REFERENCE_ID;
    private static Long PERSON_REQUESTER_TYPE_ID;
    private static Long ORGANIZATION_REQUESTER_TYPE_ID;

    static{
        ReferenceTablesDAO refTableDAO = new ReferenceTablesDAOImpl();
        TABLE_REFERENCE_ID = refTableDAO.getReferenceTableByName( "SAMPLE" ).getId();

        RequesterTypeDAO requesterTypeDAO = new RequesterTypeDAOImpl();
        RequesterType type = requesterTypeDAO.getRequesterTypeByName( "provider" );
        PERSON_REQUESTER_TYPE_ID  = type != null ? Long.parseLong( type.getId() ) : Long.MIN_VALUE;
        type = requesterTypeDAO.getRequesterTypeByName( "organization" );
        ORGANIZATION_REQUESTER_TYPE_ID = type != null ? Long.parseLong( type.getId() ) : Long.MIN_VALUE;
    }

	private Sample sample;


    public SampleService( Sample sample){
		this.sample = sample;
	}

    public SampleService( String accessionNumber){
        this.sample = sampleDAO.getSampleByAccessionNumber( accessionNumber );
    }

	/**
	 * Gets the date of when the order was completed
	 * @return The date of when it was completed, null if it was not yet completed
	 */
	public Date getCompletedDate(){
		Date date = null;
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

        for( Analysis analysis : analysisList ){
            if( !isCanceled( analysis ) ){
                if( analysis.getCompletedDate() == null ){
                    return null;
                }else if( date == null ){
                    date = analysis.getCompletedDate();
                }else if( analysis.getCompletedDate().after( date ) ){
                    date = analysis.getCompletedDate();
                }
            }
        }
        return date;
	}

    private boolean isCanceled( Analysis analysis ){
        return StatusService.getInstance().getStatusID( StatusService.AnalysisStatus.Canceled ).equals( analysis.getStatusId() );
    }

    public Timestamp getOrderedDate(){
        if( sample == null){
            return null;
        }
        ObservationHistory observation =  ObservationHistoryService.getObservationForSample( ObservationHistoryService.ObservationType.REQUEST_DATE, sample.getId() );
		if( observation != null && observation.getValue() != null){
            return DateUtil.convertStringDateToTruncatedTimestamp( observation.getValue() );
        }else{ //If ordered date is not given then use received date
            return sample.getReceivedTimestamp();
        }
	}

    public String getAccessionNumber(){
        return sample.getAccessionNumber();
    }

    public String getReceivedDateForDisplay(){
        return sample.getReceivedDateForDisplay();
    }

    public String getTwoYearReceivedDateForDisplay(){
        String fourYearDate = getReceivedDateForDisplay();
        int lastSlash = fourYearDate.lastIndexOf( "/" );
        return fourYearDate.substring( 0, lastSlash + 1 ) + fourYearDate.substring( lastSlash + 3 );
    }
    public String getReceivedDateWithTwoYearDisplay(){ return DateUtil.convertTimestampToTwoYearStringDate( sample.getReceivedTimestamp() ); }

    public String getReceivedTimeForDisplay(){
        return sample.getReceivedTimeForDisplay();
    }

    public String getReceived24HourTimeForDisplay(){
        return sample.getReceived24HourTimeForDisplay();
    }
    public boolean isConfirmationSample(){
        return sample != null && sample.getIsConfirmation();
    }

    public Sample getSample(){
        return sample;
    }

    public String getId(){
        return sample.getId();
    }

    public Patient getPatient(){
        return sampleHumanDAO.getPatientForSample( sample );
    }

    public List<Analysis> getAnalysis(){
        return sample == null ? new ArrayList<Analysis>(  ) : analysisDAO.getAnalysesBySampleId( sample.getId() );
    }

    public List<SampleQaEvent> getSampleQAEventList(){
        return sample == null ? new ArrayList<SampleQaEvent>(  ) : sampleQaEventDAO.getSampleQaEventsBySample(sample);
    }

    public Person getPersonRequester(){
        if( sample == null ){
            return null;
        }

        List<SampleRequester> requesters = sampleRequesterDAO.getRequestersForSampleId( sample.getId() );

        for( SampleRequester requester : requesters ){
            if( PERSON_REQUESTER_TYPE_ID == requester.getRequesterTypeId() ){
                Person person = new Person();
                person.setId( String.valueOf( requester.getRequesterId() ) );
                personDAO.getData( person );
                return person.getId() != null ? person : null;
            }
        }

        return null;
    }

    public Organization getOrganizationRequester(){
        if( sample == null ){
            return null;
        }

        List<SampleRequester> requesters = sampleRequesterDAO.getRequestersForSampleId( sample.getId() );

        for( SampleRequester requester : requesters ){
            if( ORGANIZATION_REQUESTER_TYPE_ID == requester.getRequesterTypeId() ){
                OrganizationDAO organizationDAO = new OrganizationDAOImpl();
                Organization org = organizationDAO.getOrganizationById( String.valueOf( requester.getRequesterId() ) );
                return org != null ? org : null;
            }
        }

        return null;
    }

	public Sample getPatientPreviousSampleForTestName(Patient patient,String testName){
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		List<Sample> sampList=sampleHumanDAO.getSamplesForPatient(patient.getId());
		Sample previousSample=null;
		List<Integer> sampIDList= new ArrayList<Integer>();
		List<Integer> testIDList= new ArrayList<Integer>();
		
		TestDAO testDAO=new TestDAOImpl();
		testIDList.add(Integer.parseInt(testDAO.getTestByName(testName).getId()));
		
		if (sampList.isEmpty()) return previousSample;
		
		for(Sample sample : sampList){
			sampIDList.add(Integer.parseInt(sample.getId()));
		}	
		
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized)));
	
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleIdTestIdAndStatusId(sampIDList,testIDList, statusList);
		
		if (analysisList.isEmpty()) return previousSample;
		
		
		for(int j=0;j<analysisList.size();j++){
			if(j<analysisList.size() && sample.getAccessionNumber().equals(analysisList.get(j).getSampleItem().getSample().getAccessionNumber()))
				previousSample=analysisList.get(j+1).getSampleItem().getSample();
			
		}
		
	/*	for(int j=0;j<analysisList.size();j++){
					
			if(j<analysisList.size() && sample.getAccessionNumber().equals(analysisList.get(j).getSampleItem().getSample().getAccessionNumber()))
				return analysisList.get(j+1).getSampleItem().getSample();	  
		
		}*/
		return previousSample;
		
	}

}
