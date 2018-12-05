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

import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;

public class NonConformityUpdateData {

	private String sampleId;
	private String labNo;
	private String currentSysUserId;
	private String patientId;
	private Boolean newSTNumber;
	private String STNumber;
	private Boolean newSubject;
	private String subjectNo;
	private Boolean newNationalId;
	private String nationalId;
	private Boolean newDoctor = false;
	private String doctor;
	private String requesterSpecimanID;
	private String requesterFirstName;
	private String requesterLastName;
	private String requesterPhoneNumber;
	private String requesterStreetAddress;
	private String requesterVillage;
	private String requesterCommune;
	private String requesterDepartment;
	private Boolean newService = false;
	private String service;
	private String newServiceName;
	private String receivedDate;
	private String receivedTime;
	private String projectId;
	private List<QaEventItem> qaEvents;
	private Boolean newNoteText;
	private String noteText;
	
	@SuppressWarnings("unchecked")
	public NonConformityUpdateData(BaseActionForm dynaForm, String currentUserId) {

		currentSysUserId = currentUserId;
		sampleId = dynaForm.getString("sampleId");
		labNo = dynaForm.getString("labNo");
		patientId = dynaForm.getString("patientId");
		newSubject = (Boolean) dynaForm.get("subjectNew");
		subjectNo = dynaForm.getString("subjectNo");
		newSTNumber = (Boolean)dynaForm.get("newSTNumber");
		STNumber = dynaForm.getString("STNumber");
		newNationalId = (Boolean) dynaForm.get("nationalIdNew");
		nationalId = dynaForm.getString("nationalId");
		newDoctor = (Boolean) dynaForm.get("doctorNew");
		doctor = dynaForm.getString("doctor");
		requesterSpecimanID = dynaForm.getString("requesterSampleID");
		requesterFirstName = dynaForm.getString("providerFirstName");
		requesterLastName = dynaForm.getString("providerLastName");
		requesterPhoneNumber = dynaForm.getString("providerWorkPhone");
		requesterStreetAddress = dynaForm.getString("providerStreetAddress");
		requesterCommune = dynaForm.getString("providerCommune");
		requesterVillage = dynaForm.getString("providerCity");
		requesterDepartment = dynaForm.getString("providerDepartment");
		newService = (Boolean)dynaForm.get("serviceNew");
		service = dynaForm.getString("service");
		newServiceName = dynaForm.getString("newServiceName");
		receivedDate = dynaForm.getString("date");
		receivedTime = dynaForm.getString("time");
		projectId = dynaForm.getString("projectId");
		qaEvents = (List<QaEventItem>) dynaForm.get("qaEvents");
		newNoteText = (Boolean)dynaForm.get("commentNew");
		noteText = dynaForm.getString( "comment");
	}

	public Boolean getNewNationalId(){
		return newNationalId;
	}

	public String getNationalId(){
		return nationalId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public String getLabNo() {
		return labNo;
	}

	public String getCurrentSysUserId() {
		return currentSysUserId;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public Boolean getNewDoctor() {
		return newDoctor;
	}

	public String getDoctor() {
		return doctor;
	}

	public String getRequesterSpecimanID() {
		return requesterSpecimanID;
	}

	public String getRequesterFirstName() {
		return requesterFirstName;
	}

	public String getRequesterLastName() {
		return requesterLastName;
	}

	public String getRequesterPhoneNumber() {
		return requesterPhoneNumber;
	}

	public boolean noRequesterInformation() {
		return (GenericValidator.isBlankOrNull(requesterFirstName) && GenericValidator.isBlankOrNull(requesterPhoneNumber)
				&& GenericValidator.isBlankOrNull(requesterLastName) && GenericValidator.isBlankOrNull(requesterSpecimanID));
	}

	public Boolean getNewService() {
		return newService;
	}

	public String getService() {
		return service;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public String getProjectId() {
		return projectId;
	}

	public List<QaEventItem> getQaEvents() {
		return qaEvents;
	}

	public Boolean getNewNoteText() {
		return newNoteText;
	}

	public String getNoteText() {
		return noteText;
	}

	public Boolean getNewSubject() {
		return newSubject;
	}

	public String getReceivedTime() {
		return receivedTime;
	}
	
	public String getNewServiceName() {
		return newServiceName;
	}
	
	public String getRequesterStreetAddress(){
		return requesterStreetAddress;
	}

	public String getRequesterVillage(){
		return requesterVillage;
	}

	public String getRequesterCommune(){
		return requesterCommune;
	}

	public String getRequesterDepartment(){
		return requesterDepartment;
	}

	public Boolean getNewSTNumber(){
		return newSTNumber;
	}

	public String getSTNumber(){
		return STNumber;
	}

}
