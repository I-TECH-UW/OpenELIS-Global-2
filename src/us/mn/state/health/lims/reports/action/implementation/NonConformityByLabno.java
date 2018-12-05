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
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ARVReportData;
import us.mn.state.health.lims.reports.action.util.ReportUtil;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public abstract class NonConformityByLabno extends Report implements IReportCreator {
	
	ObservationHistoryTypeDAO observationTypeDAO = new ObservationHistoryTypeDAOImpl();
	ObservationHistoryType	observationType = observationTypeDAO.getByName("nameOfDoctor");
	private String OBSERVATION_DOCTOR_ID = observationType.getId();
	
	private String lowDateStr;
	private String highDateStr;
	private String lowerNumber;
	private String upperNumber;
	private DateRange dateRange;
	
	private ArrayList<ARVReportData> reportItems;

	private ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
	private SampleDAO sampleDAO = new SampleDAOImpl();
	private SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();

	private Sample sample;
	private Project project;
	private String service;
	private Patient patient;
	private QaEvent qaEvent;
	private List<SampleQaEvent> sampleQaEvents;

	@Override
	protected void createReportParameters() throws IllegalStateException {
		super.createReportParameters();
		String nonConformity = StringUtil.getContextualMessageForKey("banner.menu.nonconformity");
		reportParameters.put("status", nonConformity);
		reportParameters.put("reportTitle", nonConformity);
	//	reportParameters.put("reportPeriod", StringUtil.getContextualMessageForKey("banner.menu.nonconformity") + "  " + dateRange.toString());
	 //   reportParameters.put("supervisorSignature", ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SIGNATURES_ON_NONCONFORMITY_REPORTS, "true"));
		if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "CI_GENERAL")){
			reportParameters.put("headerName", "CILNSPHeader.jasper");	
		} else {
	//		reportParameters.put("headerName", getHeaderName());
		}
	}
	
	@Override
	public void initializeReport(BaseActionForm dynaForm) {
        super.initializeReport();
        lowerNumber = dynaForm.getString( "accessionDirect" );
        upperNumber = dynaForm.getString( "highAccessionDirect" );
        dateRange = new DateRange(lowDateStr, highDateStr);
        createReportParameters();
        errorFound = !validateAccessionNumbers();
        if ( errorFound ) {
            return;
        }
        reportItems = new ArrayList<ARVReportData>();
        createReportItems();
        if ( this.reportItems.size() == 0 ) {
            add1LineErrorMessage("report.error.message.noPrintableItems");
        }
        Collections.sort(reportItems, new ReportItemsComparator() );
   	}

    /**
     *
     */
    private void createReportItems() {
        List<Sample> samples = sampleDAO.getSamplesByAccessionRange(lowerNumber, upperNumber);
        for (Sample sample : samples) {
            this.sample = sample;
            patient = ReportUtil.findPatient( sample );
            project = ReportUtil.findProject(sample);
            service = findService();
            sampleQaEvents = findSampleQaEvents();
            
            ARVReportData data = new ARVReportData();
            SampleOrganizationDAO orgDAO = new SampleOrganizationDAOImpl();

    		String subjectNumber = patient.getNationalId();
    		if(GenericValidator.isBlankOrNull(subjectNumber)){
    			subjectNumber = patient.getExternalId();
    		}

    		data.setLabNo(sample.getAccessionNumber());
    		data.setSubjectNumber(subjectNumber);
    		data.setBirth_date(patient.getBirthDateForDisplay());
    		data.setAge(DateUtil.getCurrentAgeForDate(patient.getBirthDate(), sample.getCollectionDate()));
    		data.setGender(patient.getGender());
    		data.setCollectiondate(sample.getCollectionDateForDisplay() + " " + sample.getCollectionTimeForDisplay());
    		data.setReceptiondate(DateUtil.convertTimestampToStringDate(sample.getReceivedTimestamp()));

    		SampleOrganization sampleOrg = new SampleOrganization();
    		sampleOrg.setSample(sample);
    		orgDAO.getDataBySample(sampleOrg);
    		data.setOrgname(sampleOrg.getId() == null ? "" : sampleOrg.getOrganization().getOrganizationName());

    		data.setDoctor(getObservationValues(OBSERVATION_DOCTOR_ID));
    				
    		data.getSampleQaEventItems(sample);
    		reportItems.add(data); 
         /*   for (SampleQaEvent sampleQaEvent : sampleQaEvents) {
            	QAService qa = new QAService( sampleQaEvent);
                this.qaEvent = qa.getQAEvent();
                String sampleType  = ReportUtil.getSampleType(sampleQaEvent);
                String noteForSampleQaEvent = NonConformityAction.getNoteForSampleQaEvent(sampleQaEvent);
                String noteForSample = NonConformityAction.getNoteForSample(sample);

                ARVReportData data = new ARVReportData();
                data.setLabNo(sample.getAccessionNumber());
                data.setSubjectNumber(patient.getNationalId());
                data.setSiteSubjectNumber(patient.getExternalId());
                data.setStudy((project != null)?project.getLocalizedName():"");
                data.setService(service);
                data.setReceivedDate(sample.getReceivedDateForDisplay() + " " + sample.getReceivedTimeForDisplay( ));

                data.setNonConformityDate(DateUtil.convertTimestampToStringDate( qa.getLastupdated()));
                data.setSection(qa.getObservationForDisplay( QAObservationType.SECTION ));
                data.setNonConformityReason( qaEvent.getLocalizedName() );
                data.setSampleType( sampleType );
                data.setBiologist( qa.getObservationForDisplay( QAObservationType.AUTHORIZER ) );
                data.setQaNote(noteForSampleQaEvent);
                data.setSampleNote(noteForSample);
                
                
                data.getSampleQaEventItems(sample);

                reportItems.add(data);
            }  */
        }
      
    }

    /**
     * @return a displayable string describing the service.
     */
    private String findService() {
        String service = "";
        List<ObservationHistory> oh = observationDAO.getAll(null, sample, TableIdService.SERVICE_OBSERVATION_TYPE_ID);
        if (oh.size() > 0) {
            service = oh.get(0).getValue();
        }
        return service;
    }

    /**
     * @return
     */
    private List<SampleQaEvent> findSampleQaEvents() {
        SampleQaEvent sampleQaEvent = new SampleQaEvent();
        sampleQaEvent.setSample(sample);
        return sampleQaEventDAO.getSampleQaEventsBySample(sample);
    }

	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
        return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}

	   static class ReportItemsComparator implements Comparator<ARVReportData>{
        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         * left.get().compareTo(right.get());
         */
        @Override
        public int compare(ARVReportData left, ARVReportData right) {
            int compare = left.getLabNo().compareTo(right.getLabNo());
            if (compare != 0) return compare;
          /*  compare = StringUtil.compareWithNulls(left.getSubjectNumber(), right.getSubjectNumber());
            if (compare != 0) return compare;
            compare = StringUtil.compareWithNulls(left.getSiteSubjectNumber(), right.getSubjectNumber());
            if (compare != 0) return compare;
            compare = StringUtil.compareWithNulls(left.getSampleType(),right.getSampleType());*/
            return compare;
        }

    }

    @Override
	protected String reportFileName(){
		return "retroCINonConformityByLabno";
	}

    protected abstract String getHeaderName();

	protected String getObservationValues(String observationTypeId) {
		List<ObservationHistory> observationList = observationDAO.getAll(patient, sample, observationTypeId);
		return observationList.size() > 0 ? observationList.get(0).getValue() : "";
	}

	private boolean validateAccessionNumbers() {
	
		if (GenericValidator.isBlankOrNull(lowerNumber) && GenericValidator.isBlankOrNull(upperNumber)) {
		    add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}
	
		if (GenericValidator.isBlankOrNull(lowerNumber)) {
			lowerNumber = upperNumber;
		} else if (GenericValidator.isBlankOrNull(upperNumber)) {
			upperNumber = lowerNumber;
		}
	
		int lowIndex = findFirstNumber(lowerNumber);
		int highIndex = findFirstNumber(upperNumber);
	
		if (lowIndex == lowerNumber.length() || highIndex == upperNumber.length()) {
		    add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}
	
		String lowPrefix = (String) lowerNumber.subSequence(0, lowIndex);
		String highPrefix = (String) upperNumber.subSequence(0, highIndex);
	
		if (!lowPrefix.equals(highPrefix)) {
			add1LineErrorMessage("report.error.message.samePrefix");
			return false;
		}
	
		double lowBounds = Double.parseDouble(lowerNumber.substring(lowIndex));
		double highBounds = Double.parseDouble(upperNumber.substring(highIndex));
	
		if (highBounds < lowBounds) {
			String temp = upperNumber;
			upperNumber = lowerNumber;
			lowerNumber = temp;
		}
	
		return true;
	}

	private int findFirstNumber(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (Character.isDigit(number.charAt(i))) {
				return i;
			}
		}
		return number.length();
	}
    
}
