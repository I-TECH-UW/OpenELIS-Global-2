/*
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
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.jfree.util.Log;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateWorker;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.FollowupRequiredData;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class RetroCIFollowupRequiredByLocation extends RetroCIReport implements IReportParameterSetter, IReportCreator {
	
	private static String SAMPLE_TABLE_ID = null;
	private static String SAMPLE_QA_EVENT_TABLE_ID = null;
	private String lowDateStr;
	private String highDateStr;
	private DateRange dateRange;
	private List<FollowupRequiredData> reportItems;
	private SampleOrganizationDAO sampleOrganizationDAO = new SampleOrganizationDAOImpl();
	private ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();
	private NoteDAO noteDAO = new NoteDAOImpl();
	private SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();

	static {
		SAMPLE_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("sample").getId();
		SAMPLE_QA_EVENT_TABLE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("SAMPLE_QAEVENT").getId();
	}
	
	@Override
	protected String reportFileName(){
		return "RetroCI_FollowupRequired_ByLocation";
	}
	
	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.IReportParameterSetter#setRequestParameters(us.mn.state.health.lims.common.action.BaseActionForm)
	 */
	@Override
	public void setRequestParameters(BaseActionForm dynaForm) {
		try {
			PropertyUtils.setProperty(dynaForm, "reportName", getReportNameForParameterPage());
			PropertyUtils.setProperty(dynaForm, "useLowerDateRange", Boolean.TRUE);
			PropertyUtils.setProperty(dynaForm, "useUpperDateRange", Boolean.TRUE);
		} catch (Exception e) {
			Log.error("Error in FollowupRequired_ByLocation.setRequestParemeters: ", e);
			// throw e;
		}
	}

	/**
	 * @return
	 */
	private Object getReportNameForParameterPage() {
		return StringUtil.getMessageForKey("reports.label.followupRequired.title");
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.RetroCIReport#initializeReport(us.mn.state.health.lims.common.action.BaseActionForm)
	 */
	@Override
	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		errorFound = false;

		lowDateStr = dynaForm.getString("lowerDateRange");
		highDateStr = dynaForm.getString("upperDateRange");
		dateRange = new DateRange(lowDateStr, highDateStr);
		
		createReportParameters();
		
		errorFound = !validateSubmitParameters();
		if (errorFound) {
			return;
		}
	
		createReportItems();
		if (this.reportItems.size() == 0) {
			add1LineErrorMessage("report.error.message.noPrintableItems");
		}
	}

	@Override
	protected void createReportParameters() {
		super.createReportParameters();
		reportParameters.put("reportTitle", getReportNameForParameterPage() + "  -  " + dateRange.toString());
	}

	/**
	 * check everything
	 */
	private boolean validateSubmitParameters() {
		return (dateRange.validateHighLowDate("report.error.message.date.received.missing"));
	}

	/**
     * 
     */
	private void createReportItems() {
		reportItems = new ArrayList<FollowupRequiredData>();
		SampleDAO sampleDAO = new SampleDAOImpl();
		List<Sample> sampleList = sampleDAO.getSamplesReceivedInDateRange(DateUtil.convertSqlDateToStringDate(dateRange.getLowDate()),
				DateUtil.convertSqlDateToStringDate(dateRange.getHighDate()));

		for (Sample sample : sampleList) {
			if ( QAService.isOrderNonConforming( sample ) || isUnderInvestigation(sample)) {
				FollowupRequiredData item = new FollowupRequiredData();

				item.setCollectiondate(sample.getCollectionDateForDisplay() + " " + sample.getCollectionTimeForDisplay());
				item.setReceivedDate(sample.getReceivedDateForDisplay() + " " + sample.getReceivedTimeForDisplay( ));
				item.setLabNo(sample.getAccessionNumber());
				item.setDoctor(getOptionalObservationHistory(sample, OBSERVATION_DOCTOR_ID));
				item.setNonConformityNotes(getNonConformingNotes(sample));
				item.setUnderInvestigationNotes(getUnderInvestigationNotes(sample));
				item.setOrgname(getServiceName(sample));
				Patient patient = getPatient(sample);
				item.setSubjectNumber(patient.getNationalId());
				item.setSiteSubjectNumber(patient.getExternalId());
				reportItems.add(item);
			}
		}

		Collections.sort(reportItems, new FollowupRequiredData.OrderByOrgName());
	}

	protected boolean isUnderInvestigation(Sample sample) {
		String entryUnderInvestigationQuestion = getOptionalObservationHistory(sample, OBSERVATION_UNDER_INVESTIGATION_ID);
		return BaseProjectFormMapper.YES_ANSWERS.contains(entryUnderInvestigationQuestion);
	}
	
	private String getNonConformingNotes(Sample sample) {
		StringBuilder allNotes = new StringBuilder();

        String notes = new NoteService( sample ).getNotesAsString( StringUtil.getMessageForKey("report.followup.general.comment") + ": ","<br/>" );
        if( notes != null){
            allNotes.append( notes );
            allNotes.append( "<br/>" );
        }

		List<SampleQaEvent> qaEventList = sampleQaEventDAO.getSampleQaEventsBySample(sample);

		for( SampleQaEvent event : qaEventList){
			QAService qa = new QAService(event);
			if( qa.getSampleItem() == null){
				allNotes.append(StringUtil.getMessageForKey("report.followup.no.sampleType"));
			}else{	
				allNotes.append(qa.getSampleItem().getTypeOfSample().getLocalizedName());
			}
			allNotes.append(" : ");
			
			if( "0".equals(qa.getObservationValue( QAObservationType.SECTION ))){
				allNotes.append(StringUtil.getMessageForKey("report.followup.no.section"));
			}else{
				allNotes.append(qa.getObservationForDisplay( QAObservationType.SECTION ));
			}
			allNotes.append(" : ");
			
			if( GenericValidator.isBlankOrNull(qa.getObservationValue( QAObservationType.AUTHORIZER ))){
				allNotes.append(StringUtil.getMessageForKey("report.followup.no.authorizer"));
			}else{
				allNotes.append(qa.getObservationForDisplay( QAObservationType.AUTHORIZER ));
			}
			allNotes.append(" : ");
			
			List<Note> qaNotes = noteDAO.getNoteByRefIAndRefTableAndSubject(qa.getEventId(), SAMPLE_QA_EVENT_TABLE_ID, NonConformityUpdateWorker.NOTE_SUBJECT);
			
			if( qaNotes.isEmpty()){
				allNotes.append(StringUtil.getMessageForKey("report.followup.no.note"));
			}else{
				allNotes.append(qaNotes.get(0).getText());
			}
			
			allNotes.append("<br/>");
		}
		
		return allNotes.length() == 0 ? null : allNotes.toString();
	}

	/**
	 * Either the sample is fully defined and has an organization, or there
	 * might be some random string defining a service stored in observation
	 * history. This second scenerio comes from enter something in the system
	 * directly at on the Non Conformant page.
	 * 
	 * @param sample
	 * @return a displayable organization name
	 */
	private String getServiceName(Sample sample) {
		
		SampleOrganization so = sampleOrganizationDAO.getDataBySample(sample);
		String service;
		if (so == null) {
			return null;
		}
		service = so.getOrganization().getOrganizationName();
		if (service != null) {
			return service;
		}

		String serviceOH = getDictionaryValueForObservation(sample, OBSERVATION_SERVICE_ID);
		if (GenericValidator.isBlankOrNull(serviceOH)) {
			return null;
		}
		return service;
	}

	/**
	 * @param sample
	 * @return
	 */
	private Patient getPatient(Sample sample) {
		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		return sampleHumanDAO.getPatientForSample(sample);
	}

	private String getUnderInvestigationNotes(Sample sample) {
		String entryUnderInvestigationQuestion = getOptionalObservationHistory(sample, OBSERVATION_UNDER_INVESTIGATION_ID);

		if (BaseProjectFormMapper.YES_ANSWERS.contains(entryUnderInvestigationQuestion)) {
			List<Note> noteList = noteDAO.getNoteByRefIAndRefTableAndSubject(sample.getId(), SAMPLE_TABLE_ID, "UnderInvestigation");
			return noteList == null || noteList.isEmpty() ? null : noteList.get(0).getText();
		}

		return null;
	}

	private String getOptionalObservationHistory(Sample sample, String ohTypeId) {
		List<ObservationHistory> oh = observationHistoryDAO.getAll(null, sample, ohTypeId);
		if (oh == null || oh.size() == 0)
			return null;
		return oh.get(0).getValue();
	}

	private String getDictionaryValueForObservation(Sample sample, String ohTypeId) {
		String dictionaryId = getOptionalObservationHistory(sample, ohTypeId);
		if (dictionaryId == null) {
			return null;
		}
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Dictionary dictionary = null;
		try {
			dictionary = dictionaryDAO.getDictionaryById(dictionaryId);
		} catch (Exception e) {
			return dictionaryId; // I guess it wasn't really a dictionary ID
									// after all, so let's just return it.
		}
		if (dictionary == null) {
			return dictionaryId; // it was a number, but it wasn't in the
									// dictionary.
		}
		return dictionary.getLocalAbbreviation();
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.Report#getReportDataSource()
	 */
	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}
}
