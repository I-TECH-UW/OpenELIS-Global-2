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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.daoimpl.StatusOfSampleDAOImpl;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

public class StatusService{
	public enum OrderStatus{
		Entered,
		Started,
		Finished,
		NonConforming_depricated
	}

	public enum AnalysisStatus{
		NotStarted,
		Canceled,
		TechnicalAcceptance,
		TechnicalRejected,
		BiologistRejected,
		NonConforming_depricated,
		Finalized
	}

	public enum RecordStatus{
		NotRegistered,
		InitialRegistration,
		ValidationRegistration
	}

	public enum SampleStatus{
		Entered,
		Canceled
	}

	public enum StatusType{
		Analysis,
		Sample,
		Order,
		SampleEntry,
		PatientEntry
	}

	public enum ExternalOrderStatus{
		Entered,
		Cancelled,
		Realized
	}

	private static Map<String, OrderStatus> idToOrderStatusMap = null;
	private static Map<String, SampleStatus> idToSampleStatusMap = null;
	private static Map<String, AnalysisStatus> idToAnalysisStatusMap = null;
	private static Map<String, RecordStatus> idToRecordStatusMap = null;
	private static Map<String, ExternalOrderStatus> idToExternalOrderStatusMap = null;

	private static Map<OrderStatus, StatusOfSample> orderStatusToObjectMap = null;
	private static Map<SampleStatus, StatusOfSample> sampleStatusToObjectMap = null;
	private static Map<AnalysisStatus, StatusOfSample> analysisStatusToObjectMap = null;
	private static Map<RecordStatus, Dictionary> recordStatusToObjectMap = null;
	private static Map<ExternalOrderStatus, StatusOfSample> externalOrderStatusToObjectMap = null;

	private static String orderRecordStatusID;
	private static String patientRecordStatusID;
	private static ObservationHistoryDAO observationHistoryDAO = new ObservationHistoryDAOImpl();

	private static boolean mapsSet = false;

	private StatusService(){
	}

	private static class SingletonHolder{
		public static final StatusService INSTANCE = new StatusService();
	}

	public static StatusService getInstance(){
		return SingletonHolder.INSTANCE;
	}

    public boolean matches(String id, SampleStatus sampleStatus){
        insureMapsAreBuilt();
        return getStatusID( sampleStatus ).equals( id );
    }

    public boolean matches(String id, AnalysisStatus analysisStatus){
        insureMapsAreBuilt();
        return getStatusID( analysisStatus ).equals( id );
    }

    public boolean matches(String id, OrderStatus orderStatus){
        insureMapsAreBuilt();
        return getStatusID( orderStatus ).equals( id );
    }

    public boolean matches(String id, ExternalOrderStatus externalOrderStatus){
        insureMapsAreBuilt();
        return getStatusID( externalOrderStatus ).equals( id );
    }

    public String getStatusID(OrderStatus statusType){
        insureMapsAreBuilt();
		StatusOfSample status = orderStatusToObjectMap.get(statusType);
		return status == null ? "-1" : status.getId();
	}

	public String getStatusID(SampleStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = sampleStatusToObjectMap.get(statusType);
		return status == null ? "-1" : status.getId();
	}

	public String getStatusID(AnalysisStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = analysisStatusToObjectMap.get(statusType);
		return status == null ? "-1" : status.getId();
	}

	public String getStatusID(ExternalOrderStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = externalOrderStatusToObjectMap.get(statusType);
		return status == null ? "-1" : status.getId();
	}

	public String getStatusName(RecordStatus statusType){
		insureMapsAreBuilt();
		Dictionary dictionary = recordStatusToObjectMap.get(statusType);
		return dictionary == null ? "unknown" : dictionary.getLocalizedName();
	}

	public String getStatusName(OrderStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = orderStatusToObjectMap.get(statusType);
		return status == null ? "unknown" : status.getLocalizedName();
	}

	public String getStatusName(SampleStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = sampleStatusToObjectMap.get(statusType);
		return status == null ? "unknown" : status.getLocalizedName();
	}

	public String getStatusName(AnalysisStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = analysisStatusToObjectMap.get(statusType);
		return status == null ? "unknown" : status.getLocalizedName();
	}

	public String getStatusName(ExternalOrderStatus statusType){
		insureMapsAreBuilt();
		StatusOfSample status = externalOrderStatusToObjectMap.get(statusType);
		return status == null ? "unknown" : status.getLocalizedName();
	}

	public String getDictionaryID(RecordStatus statusType){
		insureMapsAreBuilt();
		Dictionary dictionary = recordStatusToObjectMap.get(statusType);
		return dictionary == null ? "-1" : dictionary.getId();
	}

	public OrderStatus getOrderStatusForID(String id){
		insureMapsAreBuilt();
		return idToOrderStatusMap.get(id);
	}

	public SampleStatus getSampleStatusForID(String id){
		insureMapsAreBuilt();
		return idToSampleStatusMap.get(id);
	}

	public AnalysisStatus getAnalysisStatusForID(String id){
		insureMapsAreBuilt();
		return idToAnalysisStatusMap.get(id);
	}

	public ExternalOrderStatus getExternalOrderStatusForID(String id){
		insureMapsAreBuilt();
		return idToExternalOrderStatusMap.get(id);
	}

	public RecordStatus getRecordStatusForID(String id){
		insureMapsAreBuilt();
		return idToRecordStatusMap.get(id);
	}

	public StatusSet getStatusSetForSampleId(String sampleId){
		Sample sample = new Sample();
		sample.setId(sampleId);

		SampleDAO sampleDAO = new SampleDAOImpl();
		sampleDAO.getData(sample);

		return buildStatusSet(sample);
	}

	public StatusSet getStatusSetForAccessionNumber(String accessionNumber){
		if(GenericValidator.isBlankOrNull(accessionNumber)){
			return new StatusSet();
		}

		SampleDAO sampleDAO = new SampleDAOImpl();

		Sample sample = sampleDAO.getSampleByAccessionNumber(accessionNumber);

		return buildStatusSet(sample);
	}

	/*
	 * Preconditions: It is called within a transaction Both the patient and
	 * sample ids are valid
	 * 
	 * For now it will fail silently Either sampleStatus or patient status may
	 * be null
	 */
	public void persistRecordStatusForSample(Sample sample, RecordStatus recordStatus, Patient patient, RecordStatus patientStatus, String sysUserId){
		insureMapsAreBuilt();

		if(sample == null || patient == null){
			return;
		}

		List<ObservationHistory> observationList = observationHistoryDAO.getAll(patient, sample);

		ObservationHistory sampleRecord = null;
		ObservationHistory patientRecord = null;

		for(ObservationHistory currentHistory : observationList){
			if(currentHistory.getObservationHistoryTypeId().equals(orderRecordStatusID)){
				sampleRecord = currentHistory;
			}else if(currentHistory.getObservationHistoryTypeId().equals(patientRecordStatusID)){
				patientRecord = currentHistory;
			}
		}

		if(recordStatus != null){
			insertOrUpdateStatus(sample, patient, recordStatus, sysUserId, sampleRecord, orderRecordStatusID);
		}

		if(patientStatus != null){
			insertOrUpdateStatus(sample, patient, patientStatus, sysUserId, patientRecord, patientRecordStatusID);
		}
	}

	private void insertOrUpdateStatus(Sample sample, Patient patient, RecordStatus status, String sysUserId, ObservationHistory record, String historyTypeId) {

		if( record == null){
			record = new ObservationHistory();
			record.setObservationHistoryTypeId(historyTypeId);
			record.setPatientId(patient.getId());
			record.setSampleId(sample.getId());
			record.setSysUserId(sysUserId);
			record.setValue(getDictionaryID(status));
			record.setValueType(ValueType.DICTIONARY);
			observationHistoryDAO.insertData(record);
		}else{
			record.setSysUserId(sysUserId);
			record.setValue(getDictionaryID(status));
			observationHistoryDAO.updateData(record);
		}
	}
	
	public void deleteRecordStatus(Sample sample, Patient patient, String sysUserId){
		insureMapsAreBuilt();

		if(sample == null || patient == null){
			return;
		}

		List<ObservationHistory> observations = observationHistoryDAO.getAll(patient, sample);

		List<ObservationHistory> records = new ArrayList<ObservationHistory>();

		for(ObservationHistory observation : observations){
			if(observation.getObservationHistoryTypeId().equals(orderRecordStatusID) ||
					observation.getObservationHistoryTypeId().equals(patientRecordStatusID)){
				observation.setSysUserId(sysUserId);
				records.add(observation);
			}
		}

		observationHistoryDAO.delete(records);
	}

	public String getStatusNameFromId(String id){
		insureMapsAreBuilt();
		if(idToAnalysisStatusMap.get(id) != null){
			return getStatusName(idToAnalysisStatusMap.get(id));
		}else if(idToOrderStatusMap.get(id) != null){
			return getStatusName(idToOrderStatusMap.get(id));
		}else if(idToSampleStatusMap.get(id) != null){
			return getStatusName(idToSampleStatusMap.get(id));
		}else if(idToRecordStatusMap.get(id) != null){
			return getStatusName(idToRecordStatusMap.get(id));
		}else if(idToExternalOrderStatusMap.get(id) != null){
			return getStatusName(idToExternalOrderStatusMap.get(id));
		}
		return null;
	}

	private void insureMapsAreBuilt(){
		synchronized(StatusService.class){
			if(!mapsSet){
				buildMap();
				mapsSet = true;
			}
		}
	}

	private void buildMap(){
		orderStatusToObjectMap = new HashMap<OrderStatus, StatusOfSample>();
		sampleStatusToObjectMap = new HashMap<SampleStatus, StatusOfSample>();
		analysisStatusToObjectMap = new HashMap<AnalysisStatus, StatusOfSample>();
		recordStatusToObjectMap = new HashMap<RecordStatus, Dictionary>();
		externalOrderStatusToObjectMap = new HashMap<ExternalOrderStatus, StatusOfSample>();
		idToOrderStatusMap = new HashMap<String, OrderStatus>();
		idToSampleStatusMap = new HashMap<String, SampleStatus>();
		idToAnalysisStatusMap = new HashMap<String, AnalysisStatus>();
		idToRecordStatusMap = new HashMap<String, RecordStatus>();
		idToExternalOrderStatusMap = new HashMap<String, ExternalOrderStatus>();

		buildStatusToIdMaps();

		// now put everything in the reverse map
		buildIdToStatusMapsFromStatusToIdMaps();

		getObservationHistoryTypeIDs();
	}

	@SuppressWarnings("unchecked")
	private void buildStatusToIdMaps(){
		StatusOfSampleDAO statusOfSampleDAO = new StatusOfSampleDAOImpl();

		List<StatusOfSample> statusList = statusOfSampleDAO.getAllStatusOfSamples();

		// sorry about this but it is only done once and until Java 7 we have to
		// use if..else
		for(StatusOfSample status : statusList){
			if(status.getStatusType().equals("ORDER")){
				addToOrderMap(status);
			}else if(status.getStatusType().equals("ANALYSIS")){
				addToAnalysisMap(status);
			}else if(status.getStatusType().equals("SAMPLE")){
				addToSampleMap(status);
			}else if(status.getStatusType().equals("EXTERNAL_ORDER")){
				addToExternalOrderMap(status);
			}
		}

		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		List<Dictionary> dictionaryList = dictionaryDAO.getDictionaryEntrysByCategoryNameLocalizedSort("REC_STATUS");

		for(Dictionary dictionary : dictionaryList){
			addToRecordMap(dictionary);
		}
	}

	private void addToOrderMap(StatusOfSample status){
		String name = status.getStatusOfSampleName();

		if(name.equals("Test Entered")){
			orderStatusToObjectMap.put(OrderStatus.Entered, status);
		}else if(name.equals("Testing Started")){
			orderStatusToObjectMap.put(OrderStatus.Started, status);
		}else if(name.equals("Testing finished")){
			orderStatusToObjectMap.put(OrderStatus.Finished, status);
		}else if(name.equals("NonConforming")){
			orderStatusToObjectMap.put(OrderStatus.NonConforming_depricated, status);
		}
	}

	private void addToAnalysisMap(StatusOfSample status){
		String name = status.getStatusOfSampleName();

		if(name.equals("Not Tested")){
			analysisStatusToObjectMap.put(AnalysisStatus.NotStarted, status);
		}else if(name.equals("Test Canceled")){
			analysisStatusToObjectMap.put(AnalysisStatus.Canceled, status);
		}else if(name.equals("Technical Acceptance")){
			analysisStatusToObjectMap.put(AnalysisStatus.TechnicalAcceptance, status);
		}else if(name.equals("Technical Rejected")){
			analysisStatusToObjectMap.put(AnalysisStatus.TechnicalRejected, status);
		}else if(name.equals("Biologist Rejection")){
			analysisStatusToObjectMap.put(AnalysisStatus.BiologistRejected, status);
		}else if(name.equals("Finalized")){
			analysisStatusToObjectMap.put(AnalysisStatus.Finalized, status);
		}else if(name.equals("NonConforming")){
			analysisStatusToObjectMap.put(AnalysisStatus.NonConforming_depricated, status);
		}
	}

	private void addToExternalOrderMap(StatusOfSample status){
		String name = status.getStatusOfSampleName();

		if(name.equals("Entered")){
			externalOrderStatusToObjectMap.put(ExternalOrderStatus.Entered, status);
		}else if(name.equals("Cancelled")){
			externalOrderStatusToObjectMap.put(ExternalOrderStatus.Cancelled, status);
		}else if(name.equals("Realized")){
			externalOrderStatusToObjectMap.put(ExternalOrderStatus.Realized, status);
		}
	}

	private void addToSampleMap(StatusOfSample status){
		String name = status.getStatusOfSampleName();

		if(name.equals("SampleEntered")){
			sampleStatusToObjectMap.put(SampleStatus.Entered, status);
		}else if(name.equals("SampleCanceled")){
			sampleStatusToObjectMap.put(SampleStatus.Canceled, status);
		}
	}

	private void addToRecordMap(Dictionary dictionary) {
		String name = dictionary.getLocalAbbreviation();

		if( name.equals("Not Start")){
			recordStatusToObjectMap.put(RecordStatus.NotRegistered, dictionary);
		}else if( name.equals("Init Ent")){
			recordStatusToObjectMap.put(RecordStatus.InitialRegistration, dictionary);
		}else if( name.equals("Valid Ent")){
			recordStatusToObjectMap.put(RecordStatus.ValidationRegistration, dictionary);
		}
	}

	private  void buildIdToStatusMapsFromStatusToIdMaps() {
		for( Entry<OrderStatus, StatusOfSample> status : orderStatusToObjectMap.entrySet()){
			idToOrderStatusMap.put(status.getValue().getId(), status.getKey());
		}
		for( Entry<SampleStatus, StatusOfSample> status : sampleStatusToObjectMap.entrySet()){
			idToSampleStatusMap.put(status.getValue().getId(), status.getKey());
		}
		
		for( Entry<AnalysisStatus, StatusOfSample> status : analysisStatusToObjectMap.entrySet()){
			idToAnalysisStatusMap.put(status.getValue().getId(), status.getKey());
		}
		for( Entry<RecordStatus, Dictionary> status : recordStatusToObjectMap.entrySet()){
			idToRecordStatusMap.put(status.getValue().getId(), status.getKey());
		}
        for( Entry<ExternalOrderStatus, StatusOfSample> status : externalOrderStatusToObjectMap.entrySet()){
            idToExternalOrderStatusMap.put(status.getValue().getId(), status.getKey());
        }

	}

	private void getObservationHistoryTypeIDs() {
		ObservationHistoryTypeDAO observationTypeDAO = new ObservationHistoryTypeDAOImpl();
		List<ObservationHistoryType> obsrvationTypeList = observationTypeDAO.getAll();

		for( ObservationHistoryType observationType : obsrvationTypeList){
			if( "SampleRecordStatus".equals(observationType.getTypeName())){
				orderRecordStatusID = observationType.getId();
			}else if( "PatientRecordStatus".equals(observationType.getTypeName())){
				patientRecordStatusID = observationType.getId();
			}
		}
	}
	
	private StatusSet buildStatusSet(Sample sample){
		StatusSet statusSet = new StatusSet();
		if(sample == null || sample.getId() == null){
			statusSet.setPatientRecordStatus(null);
			statusSet.setSampleRecordStatus(null);
		}else{

			statusSet.setSampleStatus(getOrderStatusForID(sample.getStatusId()));

			setAnalysisStatus(statusSet, sample);

			setRecordStatus(statusSet, sample);
		}

		return statusSet;
	}
	
	private void setAnalysisStatus(StatusSet statusSet, Sample sample) {
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());

		Map<Analysis, AnalysisStatus> analysisStatusMap = new HashMap<Analysis, AnalysisStatus>();

		for( Analysis analysis : analysisList ){
			analysisStatusMap.put(analysis, getAnalysisStatusForID(analysis.getStatusId()));
		}

		statusSet.setAnalysisStatus(analysisStatusMap);
	}
	
	private void setRecordStatus(StatusSet statusSet, Sample sample) {
		if( "H".equals(sample.getDomain())){
			SampleHuman sampleHuman = new SampleHuman();
			sampleHuman.setSampleId(sample.getId());
			SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
			sampleHumanDAO.getDataBySample(sampleHuman);

			String patientId = sampleHuman.getPatientId();

			statusSet.setSampleId(sample.getId());
			statusSet.setPatientId(patientId);

			if( patientId != null ){
				Patient patient = new Patient();
				patient.setId(patientId);

				List<ObservationHistory> observations = observationHistoryDAO.getAll(patient, sample);

				for( ObservationHistory observation : observations){
					if( observation.getObservationHistoryTypeId().equals( orderRecordStatusID)){
						statusSet.setSampleRecordStatus(getRecordStatusForID(observation.getValue()));
					}else if( observation.getObservationHistoryTypeId().equals( patientRecordStatusID)){
						statusSet.setPatientRecordStatus(getRecordStatusForID(observation.getValue()));
					}
				}
			}
		}
	}
}
