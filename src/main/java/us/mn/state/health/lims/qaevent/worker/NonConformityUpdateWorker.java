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
package us.mn.state.health.lims.qaevent.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.address.dao.PersonAddressDAO;
import us.mn.state.health.lims.address.daoimpl.PersonAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.NoteService;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.services.QAService.QAObservationValueType;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.note.dao.NoteDAO;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.dao.OrganizationOrganizationTypeDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationOrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.qaevent.dao.QaObservationDAO;
import us.mn.state.health.lims.qaevent.daoimpl.QaObservationDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.daoimpl.SampleProjectDAOImpl;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public class NonConformityUpdateWorker {

	private Sample sample;
	private SampleHuman sampleHuman;
	private Patient patient;
	private ObservationHistory doctorObservation;
	private ObservationHistory serviceObservation;
	private Map<String, SampleItem> sampleItemsByType = new HashMap<String, SampleItem>();

	private List<SampleQaEvent> sampleQAEventInsertList;
	private List<SampleQaEvent> sampleQAEventDeleteList;
	private Map<QaObservation, SampleQaEvent> qaObservationMap;
	private List<NoteSet> insertableNotes;
	private List<Note> updateableNotes;
	private List<Note> deleteableNotes;
    private SampleProject sampleProject;
	private List<PersonAddress> addressPartList;
	private PatientIdentity subjectNoPatientIdentity;
	private PatientIdentity STNoPatientIdentity;

	private boolean insertPatient = false;
	private boolean insertDoctorObservation = false;
	private boolean insertServiceObservation = false;
	private boolean insertSampleItems = false;
	private boolean insertSampleProject = false;
	private boolean insertSampleRequester = false;

	private boolean updatePatient = false;
	private boolean useFullProviderInfo;
	private boolean updateSampleHuman;

	public static final String NOTE_SUBJECT = "QaEvent Note";

	
	private static final ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
	private static final SampleDAO sampleDAO = new SampleDAOImpl();
	private static final PatientDAO patientDAO = new PatientDAOImpl();
	private static final SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private static final SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
	private static final NoteDAO noteDAO = new NoteDAOImpl();
	private static final SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();
	private static final SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
	private static final TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
	private static final QaObservationDAO qaObservationDAO = new QaObservationDAOImpl();
	private static final OrganizationDAO orgDAO = new OrganizationDAOImpl();
	private static final OrganizationOrganizationTypeDAO orgOrgTypeDAO = new OrganizationOrganizationTypeDAOImpl();
	private static final PersonAddressDAO personAddressDAO = new PersonAddressDAOImpl();
	private static final PatientIdentityDAO patientIdentityDAO = new PatientIdentityDAOImpl();
	private Provider provider;
	private Person providerPerson;
	private boolean insertProvider = false;
	private ActionMessages errors = null;
	private Organization newOrganization= null;
	private boolean insertNewOrganizaiton = false;
	private SampleRequester sampleRequester = null;

    private final NonConformityUpdateData webData;
	private final static boolean REJECT_IF_EMPTY = true;
	
	public NonConformityUpdateWorker(NonConformityUpdateData data) {
		webData = data;
	}

	public String update() {
		useFullProviderInfo = FormFields.getInstance().useField(FormFields.Field.QA_FULL_PROVIDER_INFO );

		createSystemUser();
		clearMembers();

		boolean isNewSample = GenericValidator.isBlankOrNull(webData.getSampleId());


		if (isNewSample) {
			createNewArtifacts();
		} else {
			updateArtifacts();
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			if (insertPatient) {
				patientDAO.insertData(patient);
			} else if (updatePatient) {
				patientDAO.updateData(patient);
			}

			if (insertProvider) {
				persistProviderData();
			}

			if( subjectNoPatientIdentity != null){
				subjectNoPatientIdentity.setPatientId(patient.getId());
				if(GenericValidator.isBlankOrNull(subjectNoPatientIdentity.getId() ) ){
					patientIdentityDAO.insertData(subjectNoPatientIdentity);
				}else{
					patientIdentityDAO.updateData(subjectNoPatientIdentity);
				}
			}
			
			if( STNoPatientIdentity != null){
				STNoPatientIdentity.setPatientId(patient.getId());
				if(GenericValidator.isBlankOrNull(STNoPatientIdentity.getId() ) ){
					patientIdentityDAO.insertData(STNoPatientIdentity);
				}else{
					patientIdentityDAO.updateData(STNoPatientIdentity);
				}
			}
			
			if (isNewSample) {
				sampleDAO.insertDataWithAccessionNumber(sample);

				sampleHuman.setPatientId(patient.getId());
				sampleHuman.setSampleId(sample.getId());
				sampleHuman.setProviderId((provider == null) ? null : provider.getId());
				sampleHumanDAO.insertData(sampleHuman);
			} else {
				sampleDAO.updateData(sample);
			}

			if (updateSampleHuman) {
				sampleHuman.setProviderId(provider.getId());
				sampleHuman.setSampleId(sample.getId());
				sampleHuman.setPatientId(patient.getId());
				sampleHuman.setSysUserId(webData.getCurrentSysUserId());
				sampleHumanDAO.updateData(sampleHuman);
			}

			if (insertSampleItems) {
				for (SampleItem si : sampleItemsByType.values()) {
					sampleItemDAO.insertData(si);
				}
			}

			if (insertSampleProject) {
				SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl();
				sampleProjectDAO.insertData(sampleProject);
			}

			if (insertDoctorObservation) {
				doctorObservation.setPatientId(patient.getId());
				doctorObservation.setSampleId(sample.getId());
				observationDAO.insertData(doctorObservation);
			}

			if (insertServiceObservation) {
				serviceObservation.setPatientId(patient.getId());
				serviceObservation.setSampleId(sample.getId());
				observationDAO.insertData(serviceObservation);
			}

			if(insertNewOrganizaiton){
				orgDAO.insertData(newOrganization);
				orgOrgTypeDAO.linkOrganizationAndType(newOrganization, TableIdService.REFERRING_ORG_TYPE_ID);
			}
			
			if (insertSampleRequester) {
				if(insertNewOrganizaiton){
					sampleRequester.setRequesterId(newOrganization.getId());
				}
				sampleRequester.setSampleId(Long.parseLong( sample.getId()));
				sampleRequesterDAO.insertData(sampleRequester);
			}

			for (SampleQaEvent event : sampleQAEventInsertList) {
				event.setSample(sample);
				sampleQaEventDAO.insertData(event);
			}

			for (NoteSet noteSet : insertableNotes) {
				if (noteSet.referencedEvent != null) {
					noteSet.note.setReferenceId(noteSet.referencedEvent.getId());
				} else if (noteSet.referencedSample != null) {
					noteSet.note.setReferenceId(noteSet.referencedSample.getId());
				} else {
					continue;
				}
				noteDAO.insertData(noteSet.note);
			}

			for (Note note : updateableNotes) {
				noteDAO.updateData(note);
			}

			noteDAO.deleteData(deleteableNotes);
			sampleQaEventDAO.deleteData(sampleQAEventDeleteList);

			for (QaObservation qa : qaObservationMap.keySet()) {
				if (qa.getId() == null) {
					qa.setObservedId( qaObservationMap.get( qa ).getId() );
					qaObservationDAO.insertData(qa);
				} else {
					qaObservationDAO.updateData(qa);
				}
			}

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			ActionMessage error;
			if (lre.getException() instanceof StaleObjectStateException) {
				error = new ActionMessage("errors.OptimisticLockException", null, null);
			} else {
				lre.printStackTrace();
				error = new ActionMessage("errors.UpdateException", null, null);
			}

			errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);

			return IActionConstants.FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}

		return IActionConstants.FWD_SUCCESS;
	}

	public ActionMessages getErrors() {
		return errors;
	}

	private void createSystemUser() {
        SystemUser systemUser = new SystemUser();
		systemUser.setId( webData.getCurrentSysUserId() );
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUserDAO.getData( systemUser );
	}

	private void clearMembers() {
		sample = null;
		sampleHuman = null;
		patient = null;
		doctorObservation = null;
		serviceObservation = null;
		sampleItemsByType = new HashMap<String, SampleItem>();
		sampleQAEventInsertList = new ArrayList<SampleQaEvent>();
		sampleQAEventDeleteList = new ArrayList<SampleQaEvent>();
		qaObservationMap = new HashMap<QaObservation, SampleQaEvent>();
		insertableNotes = new ArrayList<NoteSet>();
		updateableNotes = new ArrayList<Note>();
		deleteableNotes = new ArrayList<Note>();
		sampleProject = null;

		insertPatient = false;
		insertDoctorObservation = false;
		insertServiceObservation = false;
		insertSampleItems = false;
		insertSampleProject = false;
		insertSampleRequester = false;
		updateSampleHuman = false;
	}

	private void createNewArtifacts() {
		sample = new Sample();
		sample.setSysUserId(webData.getCurrentSysUserId());
		sample.setAccessionNumber(webData.getLabNo());
		sample.setDomain("H");
		sample.setEnteredDate(DateUtil.convertStringDateToSqlDate(getCompleteDateTime()));
		sample.setReceivedDate(DateUtil.convertStringDateToSqlDate(getCompleteDateTime()));
		sample.setStatusId(StatusService.getInstance().getStatusID(OrderStatus.Entered));
		sample.setReferringId(webData.getRequesterSpecimanID());
		
		sampleHuman = new SampleHuman();
		sampleHuman.setSysUserId(webData.getCurrentSysUserId());

		if (!(GenericValidator.isBlankOrNull(webData.getProjectId()) || webData.getProjectId().equals("0"))) {
			insertSampleProject = true;
			sampleProject = new SampleProject();

			Project project = new Project();
			project.setId(webData.getProjectId());

			ProjectDAO projectDAO = new ProjectDAOImpl();
			projectDAO.getData(project);
			sampleProject.setProject(project);
			sampleProject.setSample(sample);
			sampleProject.setSysUserId(webData.getCurrentSysUserId());
		}

		setPatient();
		addPatientIdentityIfNeeded();
		addDoctorIfNeeded();
		addServiceIfNeeded();
		addAllSampleQaEvents();
		addNoteToSampleIfNeeded();
	}

	private String getCompleteDateTime() {
		String receivedDateTime = webData.getReceivedDate();
		if (!GenericValidator.isBlankOrNull(webData.getReceivedTime())) {
			receivedDateTime += " " + webData.getReceivedTime();
		}else{
			receivedDateTime += " 00:00";
		}
		return receivedDateTime;
	}

	private void updateArtifacts() {
		sample = new Sample();
		sample.setId(webData.getSampleId());
		sampleDAO.getData(sample);
		sample.setSysUserId(webData.getCurrentSysUserId());
		sample.setReferringId(webData.getRequesterSpecimanID());

		sampleHuman = new SampleHuman();
		sampleHuman.setSampleId(sample.getId());
		sampleHumanDAO.getDataBySample(sampleHuman);

		String projectId = webData.getProjectId();
		if (!GenericValidator.isBlankOrNull(projectId)) {
			if (!(GenericValidator.isBlankOrNull(projectId) || projectId.equals("0"))) {
				insertSampleProject = true;
				sampleProject = new SampleProject();

				Project project = new Project();
				project.setId(projectId);

				ProjectDAO projectDAO = new ProjectDAOImpl();
				projectDAO.getData(project);
				sampleProject.setProject(project);
				sampleProject.setSample(sample);
				sampleProject.setSysUserId(webData.getCurrentSysUserId());
			}
		} else {
			insertSampleProject = false;
			sampleProject = null;
		}

		if (webData.getNewNationalId() && !GenericValidator.isBlankOrNull(webData.getNationalId())) { // number
			updatePatient = true;
			patient = sampleHumanDAO.getPatientForSample(sample);
			patient.setSysUserId(webData.getCurrentSysUserId());
			patient.setNationalId(webData.getNationalId());
		} 
		
		if (webData.getNewSubject() && !GenericValidator.isBlankOrNull(webData.getSubjectNo())) {
			if(patient == null){
				patient = sampleHumanDAO.getPatientForSample(sample);
			}
			
			subjectNoPatientIdentity = patientIdentityDAO.getPatitentIdentityForPatientAndType(patient.getId(), TableIdService.PATIENT_SUBJECT_IDENTITY);
			if( subjectNoPatientIdentity == null){
				subjectNoPatientIdentity = new PatientIdentity();
			}
			
			subjectNoPatientIdentity.setSysUserId(webData.getCurrentSysUserId());
			subjectNoPatientIdentity.setIdentityData(webData.getSubjectNo());
			subjectNoPatientIdentity.setIdentityTypeId(TableIdService.PATIENT_SUBJECT_IDENTITY);
		} 

		if (webData.getNewSTNumber() && !GenericValidator.isBlankOrNull(webData.getSTNumber())) {
			if(patient == null){
				patient = sampleHumanDAO.getPatientForSample(sample);
			}
			
			STNoPatientIdentity = patientIdentityDAO.getPatitentIdentityForPatientAndType(patient.getId(),TableIdService.PATIENT_ST_IDENTITY);
			if( STNoPatientIdentity == null){
				STNoPatientIdentity = new PatientIdentity();
			}
			
			STNoPatientIdentity.setSysUserId(webData.getCurrentSysUserId());
			STNoPatientIdentity.setIdentityData(webData.getSTNumber());
			STNoPatientIdentity.setIdentityTypeId(TableIdService.PATIENT_ST_IDENTITY);
		} 

		
		if(patient == null || GenericValidator.isBlankOrNull(patient.getId())){
			setPatient();
		}

		addDoctorIfNeeded();
		addServiceIfNeeded();
		addAllSampleQaEvents();
		addNoteToSampleIfNeeded();
	}

	private void persistProviderData() {
		if (providerPerson != null) {
			PersonDAO personDAO = new PersonDAOImpl();
			providerPerson.setSysUserId(webData.getCurrentSysUserId());
			personDAO.insertData(providerPerson);
		}
		if (provider != null) {
			ProviderDAO providerDAO = new ProviderDAOImpl();
			provider.setSysUserId(webData.getCurrentSysUserId());
			if (providerPerson != null) {
				provider.setPerson(providerPerson);
			}
			providerDAO.insertData(provider);
		}

        if( providerPerson != null){
            for( PersonAddress addressPart : addressPartList){
                addressPart.setPersonId( providerPerson.getId() );
                personAddressDAO.insert( addressPart );
            }
		}
	}


	/**
	 * This is for when patients can not be added through the form
	 */
	private void setPatient() {

		if (!GenericValidator.isBlankOrNull(webData.getPatientId())) {
			patient = patientDAO.readPatient(webData.getPatientId());
		} else if (!GenericValidator.isBlankOrNull(webData.getNationalId())) {
			patient = patientDAO.getPatientByNationalId(webData.getNationalId());
		}

		if (patient == null) {
			insertPatient = true;
			patient = new Patient();
			patient.setNationalId(webData.getNationalId());
			patient.setPerson(PatientUtil.getUnknownPerson());
		}

		patient.setSysUserId(webData.getCurrentSysUserId());
	}

	private void addPatientIdentityIfNeeded(){
		if (webData.getNewSubject() && !GenericValidator.isBlankOrNull(webData.getSubjectNo())) { 	
			subjectNoPatientIdentity = new PatientIdentity();
			subjectNoPatientIdentity.setSysUserId(webData.getCurrentSysUserId());
			subjectNoPatientIdentity.setIdentityData(webData.getSubjectNo());
			subjectNoPatientIdentity.setIdentityTypeId(TableIdService.PATIENT_SUBJECT_IDENTITY);
		} 

		if (webData.getNewSTNumber() && !GenericValidator.isBlankOrNull(webData.getSTNumber())) { 
			STNoPatientIdentity = new PatientIdentity();
			STNoPatientIdentity.setSysUserId(webData.getCurrentSysUserId());
			STNoPatientIdentity.setIdentityData(webData.getSTNumber());
			STNoPatientIdentity.setIdentityTypeId(TableIdService.PATIENT_ST_IDENTITY);
		} 
	}
	
	private void addDoctorIfNeeded() {

		if (useFullProviderInfo) {
			insertProvider = true;
			initProvider();
		} else {
			String doctor = newOrBlankFieldValue(webData.getNewDoctor(), webData.getDoctor());
			if (GenericValidator.isBlankOrNull(doctor)) {
				return;
			}
			insertDoctorObservation = true;
			doctorObservation = new ObservationHistory();
			doctorObservation.setValue(doctor);
			doctorObservation.setValueType(ValueType.LITERAL);
			doctorObservation.setSysUserId(webData.getCurrentSysUserId());
			doctorObservation.setObservationHistoryTypeId(TableIdService.DOCTOR_OBSERVATION_TYPE_ID);
		}
	}

	private void initProvider() {
		addressPartList = new ArrayList<PersonAddress>();
		
		if (webData.noRequesterInformation()) {
			Person person = PatientUtil.getUnknownPerson();
			provider = new Provider();
			provider.setPerson(person);
			providerPerson = null;
			updateSampleHuman = true;
			insertProvider = true;
		} else {
			providerPerson = new Person();
			provider = new Provider();

			providerPerson.setFirstName(webData.getRequesterFirstName());
			providerPerson.setLastName(webData.getRequesterLastName());
			providerPerson.setStreetAddress(webData.getRequesterStreetAddress());
			providerPerson.setWorkPhone(webData.getRequesterPhoneNumber());
			providerPerson.setSysUserId(webData.getCurrentSysUserId());

	//		provider.setExternalId(webData.getRequesterSpecimanID());
			provider.setSysUserId(webData.getCurrentSysUserId());
			updateSampleHuman = true;
			sampleHuman.setSysUserId(webData.getCurrentSysUserId());
			insertProvider = true;
			
			addAddressPart(webData.getRequesterCommune(), TableIdService.ADDRESS_COMMUNE_ID , addressPartList, "T");
			addAddressPart(webData.getRequesterVillage(), TableIdService.ADDRESS_VILLAGE_ID, addressPartList, "T");
			addAddressPart(webData.getRequesterDepartment(), TableIdService.ADDRESS_DEPARTMENT_ID, addressPartList, "D");
		}

	}

	private void addAddressPart(String value, String partId, List<PersonAddress> addressPartList, String type){
		if( !GenericValidator.isBlankOrNull(value) && !("D".equals(type) && "0".equals(value))){
			PersonAddress addressPart = new PersonAddress();
			addressPart.setAddressPartId(partId);
			addressPart.setValue(value);
			addressPart.setType(type);
			addressPart.setSysUserId(webData.getCurrentSysUserId());
			addressPartList.add(addressPart);
		}
	}

	private void addServiceIfNeeded() {
		
		if (!webData.getNewService()) {
			return;
		}
		
		String service = webData.getService();
		if( GenericValidator.isBlankOrNull(service)){
			service = webData.getNewServiceName();
			if( GenericValidator.isBlankOrNull(service)){
				return;
			}
			
			if (useFullProviderInfo) {
				newOrganization = new Organization();
				newOrganization.setIsActive("Y");
				newOrganization.setOrganizationName(service);
				newOrganization.setSysUserId(webData.getCurrentSysUserId());
				newOrganization.setMlsSentinelLabFlag("N");
				insertNewOrganizaiton = true;
			}
		}
		
		
		if (useFullProviderInfo) {
			sampleRequester = new SampleRequester();
			if( !insertNewOrganizaiton){
				sampleRequester.setRequesterId(service);
			}
			sampleRequester.setRequesterTypeId( TableIdService.ORGANIZATION_REQUESTER_TYPE_ID);
			sampleRequester.setSysUserId(webData.getCurrentSysUserId());
			insertSampleRequester = true;
		} else {
			serviceObservation = new ObservationHistory();
			serviceObservation.setValue(service);
			serviceObservation.setValueType(ValueType.LITERAL);
			serviceObservation.setSysUserId(webData.getCurrentSysUserId());
			serviceObservation.setObservationHistoryTypeId(TableIdService.SERVICE_OBSERVATION_TYPE_ID);
			insertServiceObservation = true;
		}
	}

	private void addAllSampleQaEvents() {

		for (QaEventItem item : webData.getQaEvents()) {
			// New event
			if (isNonBlankNewEvent(item)) {
				String sampleType = item.getSampleType();
                //All samples has an id of -1
				if (sampleType.equals("-1") ) {
					addSampleQaEvent(item, null);
				} else {
					insertSampleItems = true;
					SampleItem sampleItem = sampleItemsByType.get(sampleType);
					sampleItem = addSampleQaEventForItem(item, sampleItem, sampleType);
					sampleItemsByType.put(sampleType, sampleItem);
				}
				// Marked for removal
			} else if (isOldForRemoval(item)) {
                SampleQaEvent sampleQaEvent = new SampleQaEvent();
				sampleQaEvent.setId( item.getId() );
				sampleQaEvent.setSysUserId( webData.getCurrentSysUserId() );
				this.sampleQAEventDeleteList.add( sampleQaEvent );
				Note existingNote = findExistingQANote(item.getId());
				if (existingNote != null) {
					existingNote.setSysUserId(webData.getCurrentSysUserId());
					this.deleteableNotes.add(existingNote);
				}
				// Updated note
			} else {
                SampleQaEvent sampleQaEvent = new SampleQaEvent();
				sampleQaEvent.setId( item.getId() );
				Note existingNote = findExistingQANote(item.getId());
				if (existingNote == null) {
					addNoteIfNeeded(item.getNote(), sampleQaEvent );
				} else {
					if ( (existingNote.getText() != null && !existingNote.getText().equals(item.getNote())) || 
						 (existingNote.getText() == null && item.getNote() != null ) ) {
						existingNote.setText(item.getNote());
						existingNote.setSystemUserId(webData.getCurrentSysUserId());
						existingNote.setSysUserId(webData.getCurrentSysUserId());
						updateableNotes.add(existingNote);
					}
				}
			}
		}
	}

	private void addNoteToSampleIfNeeded() {
		String noteText = webData.getNoteText();// newOrBlankFieldValue(webData.getNewNoteText(),
												// webData.getNoteText());
		if (!GenericValidator.isBlankOrNull(noteText)) {
			Note note = findExistingSampleNote();
			if (note != null) {
				if (!note.getText().equals(noteText)) {
					note.setText(noteText);
					note.setSysUserId(webData.getCurrentSysUserId());
					note.setSystemUserId(webData.getCurrentSysUserId());
					updateableNotes.add(note);
				}
			} else {
				NoteSet noteSet = new NoteSet();
				noteSet.referencedSample = sample;
				noteSet.note = new NoteService( sample ).createSavableNote( NoteService.NoteType.NON_CONFORMITY, noteText, NOTE_SUBJECT, webData.getCurrentSysUserId() );
				insertableNotes.add(noteSet);
			}
		}

	}

	private SampleQaEvent addSampleQaEvent(QaEventItem item, SampleItem sampleItem) {
		QAService qaService = new QAService(new SampleQaEvent());			
		qaService.setCurrentUserId(webData.getCurrentSysUserId());
		qaService.setReportTime(getCompleteDateTime());
		qaService.setQaEventById(item.getQaEvent());
		qaService.setObservation(QAObservationType.SECTION, item.getSection(), QAObservationValueType.KEY, REJECT_IF_EMPTY);
		qaService.setObservation(QAObservationType.AUTHORIZER, item.getAuthorizer(), QAObservationValueType.LITERAL, REJECT_IF_EMPTY);
		qaService.setObservation(QAObservationType.DOC_NUMBER, item.getRecordNumber(), QAObservationValueType.LITERAL, REJECT_IF_EMPTY);
		qaService.setSampleItem(sampleItem);
		addNoteIfNeeded(item.getNote(), qaService.getSampleQaEvent());

		sampleQAEventInsertList.add(qaService.getSampleQaEvent());

		for (QaObservation observation : qaService.getUpdatedObservations()) {
			qaObservationMap.put( observation, qaService.getSampleQaEvent() );
		}

		return qaService.getSampleQaEvent();
	}

	/**
	 * 
	 * @param item  The Item to be evaluated
	 * @param sampleItem The item being checked for existence
	 * @param sampleTypeId The type of the sample item
	 * @return if the return is null, the there was already a sampleItem on the
	 *         Sample for the given sampleTypeId
	 */
	private SampleItem addSampleQaEventForItem(QaEventItem item, SampleItem sampleItem, String sampleTypeId) {
		List<SampleItem> sampleItemsOfType = new ArrayList<SampleItem>();
		if (sampleItem == null) {

			TypeOfSample typeOfSample = typeOfSampleDAO.getTypeOfSampleById(sampleTypeId);

			if (sample.getId() != null) {
				sampleItemsOfType = sampleItemDAO.getSampleItemsBySampleIdAndType(sample.getId(), typeOfSample);
			}
			if (sampleItemsOfType != null && sampleItemsOfType.size() > 0) {
				// ignoring any sample which actually has more than one
				// sampleItem of the same typeOfSample
				sampleItem = sampleItemsOfType.get(0);
			} else {
				sampleItem = new SampleItem();
				sampleItem.setSample(sample);
				sampleItem.setSysUserId(webData.getCurrentSysUserId());
				sampleItem.setTypeOfSample(typeOfSample);
				sampleItem.setSortOrder(getNextSampleItemSortOrder());
				sampleItem.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));
			}
		}
		addSampleQaEvent( item, sampleItem );
		// if the DB already has this sample type don't bother returning it to
		// the caller (who will want to save it later), because this update
		// action never updates sampleItems
		return (sampleItemsOfType != null && sampleItemsOfType.size() > 0) ? null : sampleItem;
	}



	private Note findExistingSampleNote() {
		if (sample.getId() == null) {
			return null;
		}

		List<Note> notes = noteDAO.getNoteByRefIAndRefTableAndSubject(sample.getId(),NoteService.getReferenceTableIdForNoteBinding( NoteService.BoundTo.SAMPLE ), NOTE_SUBJECT);
		return notes.isEmpty() ? null : notes.get(0);
	}

	private Note findExistingQANote(String sampleQAEventId ) {
		if (sampleQAEventId == null || !sampleQAEventId.matches("\\d+")) {
			return null;
		}

		List<Note> notes = noteDAO.getNoteByRefIAndRefTableAndSubject(sampleQAEventId, NoteService.getReferenceTableIdForNoteBinding( NoteService.BoundTo.QA_EVENT ), NOTE_SUBJECT);
		return notes.isEmpty() ? null : notes.get(0);
	}
	
	private void addNoteIfNeeded(String noteText, SampleQaEvent event) {
		if (!GenericValidator.isBlankOrNull(noteText)) {
			NoteSet noteSet = new NoteSet();
			noteSet.referencedEvent = event;
			noteSet.note = new NoteService( event ).createSavableNote( NoteService.NoteType.NON_CONFORMITY, noteText, NOTE_SUBJECT, webData.getCurrentSysUserId() );
			insertableNotes.add(noteSet);
		}
	}

	private String getNextSampleItemSortOrder() {
		String sampleId = sample.getId();
		int max = 0;
		if (!GenericValidator.isBlankOrNull(sampleId)) {
			List<SampleItem> sampleItems = sampleItemDAO.getSampleItemsBySampleId(sampleId);
			for (SampleItem sampleItem : sampleItems) {
				Integer curr = Integer.valueOf(sampleItem.getSortOrder());
				max = (curr > max) ? curr : max;
			}
		}
		max++;
		return String.valueOf(max);
	}

	private String newOrBlankFieldValue(Boolean newValue, String value) {
		if (newValue) {
			return value == null ? "" : value;
		}
		return null;
	}

	/**
	 * 
	 * @param item The Item to be evaluated
	 * @return TRUE if is new, contains some reason and isn't marked for delete
	 */
	private boolean isNonBlankNewEvent(QaEventItem item) {
		return "NEW".equals(item.getId()) && !item.isRemove() && !"0".equals(item.getQaEvent());
	}

	/**
	 * @param item The Item to be evaluated
	 * @return TRUE if Is not new and is marked for removal.
	 */
	private boolean isOldForRemoval(QaEventItem item) {
		return item.getId() != null && !"NEW".equals( item.getId() ) && item.isRemove();
	}

	private class NoteSet {
		public SampleQaEvent referencedEvent;
		public Sample referencedSample;
		public Note note;
	}
}
