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
package us.mn.state.health.lims.dataexchange.order.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.ExternalOrderStatus;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.daoimpl.PatientIdentityDAOImpl;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.daoimpl.PatientIdentityTypeDAOImpl;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public class DBOrderPersister implements IOrderPersister{

	private static final String SERVICE_USER_ID;
	private static final String IDENTITY_GUID_ID;
	private static final String IDENTITY_STNUMBER_ID;
	private static final String IDENTITY_OBNUMBER_ID;
	private static final String IDENTITY_PCNUMBER_ID;

	private ElectronicOrderDAO eOrderDAO = new ElectronicOrderDAOImpl();
	private Patient patient;

	static{
		SystemUser serviceUser = new SystemUserDAOImpl().getDataForLoginUser("serviceUser");
		SERVICE_USER_ID = serviceUser == null ? null : serviceUser.getId();

		PatientIdentityTypeDAO identityTypeDAO = new PatientIdentityTypeDAOImpl();
		IDENTITY_GUID_ID = getIdentityType(identityTypeDAO, "GUID");
		IDENTITY_STNUMBER_ID = getIdentityType(identityTypeDAO, "ST");
		IDENTITY_OBNUMBER_ID = getIdentityType(identityTypeDAO, "OB_NUMBER");
		IDENTITY_PCNUMBER_ID = getIdentityType(identityTypeDAO, "PC_NUMBER");
	}

	private static String getIdentityType(PatientIdentityTypeDAO identityTypeDAO, String name){
		PatientIdentityType type = identityTypeDAO.getNamedIdentityType(name);
		return type == null ? null : type.getId();
	}

	private void persist(MessagePatient orderPatient){
		PatientService patientService = new PatientService(orderPatient);
	    patient = patientService.getPatient();
		if(patient == null){
			createNewPatient(orderPatient);
		}else{
			updatePatient(orderPatient, patientService);
		}
	}

	private void createNewPatient(MessagePatient orderPatient){
		Person person = new Person();
		person.setFirstName(orderPatient.getFirstName());
		person.setLastName(orderPatient.getLastName());
		person.setStreetAddress(orderPatient.getAddressStreet());
		person.setCity(orderPatient.getAddressVillage());
		person.setSysUserId(SERVICE_USER_ID);

		patient = new Patient();
		patient.setBirthDateForDisplay(orderPatient.getDisplayDOB());
		patient.setGender(orderPatient.getGender());
		patient.setNationalId(orderPatient.getNationalId());
		patient.setPerson(person);
		patient.setSysUserId(SERVICE_USER_ID);
		patient.setExternalId(orderPatient.getExternalId());
		if (GenericValidator.isBlankOrNull(orderPatient.getGuid())) {
			orderPatient.setGuid(java.util.UUID.randomUUID().toString());
		}

		List<PatientIdentity> identities = new ArrayList<PatientIdentity>();
		addIdentityIfAppropriate(IDENTITY_GUID_ID, orderPatient.getGuid(), identities);
		addIdentityIfAppropriate(IDENTITY_STNUMBER_ID, orderPatient.getStNumber(), identities);
		addIdentityIfAppropriate(IDENTITY_OBNUMBER_ID, orderPatient.getObNumber(), identities);
		addIdentityIfAppropriate(IDENTITY_PCNUMBER_ID, orderPatient.getPcNumber(), identities);

		new PersonDAOImpl().insertData(person);
		new PatientDAOImpl().insertData(patient);

		PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
		for(PatientIdentity identity : identities){
			identity.setPatientId(patient.getId());
			identityDAO.insertData(identity);
		}
	}

	private void addIdentityIfAppropriate(String typeId, String value, List<PatientIdentity> identities){
		if(typeId != null && value != null){
			PatientIdentity identity = new PatientIdentity();
			identity.setIdentityData(value);
			identity.setIdentityTypeId(typeId);
			identity.setSysUserId(SERVICE_USER_ID);
			identities.add(identity);
		}
	}

	private void updatePatient(MessagePatient orderPatient, PatientService patientService){
		Patient patient = patientService.getPatient();
		Person person = patientService.getPerson();

		updatePersonIfNeeded(orderPatient, patientService, person);
		updatePatientIfNeeded(orderPatient, patientService, patient);

		List<PatientIdentity> identityList = patientService.getIdentityList();
		PatientIdentityDAO identityDAO = new PatientIdentityDAOImpl();
		updateIdentityIfNeeded(IDENTITY_OBNUMBER_ID, orderPatient.getObNumber(), patient.getId(), identityList, identityDAO);
		updateIdentityIfNeeded(IDENTITY_STNUMBER_ID, orderPatient.getStNumber(), patient.getId(), identityList, identityDAO);
		updateIdentityIfNeeded(IDENTITY_PCNUMBER_ID, orderPatient.getPcNumber(), patient.getId(), identityList, identityDAO);
	}

	private void updateIdentityIfNeeded(String identityTypeId, String newIdentityValue,  String patientId, List<PatientIdentity> identityList, PatientIdentityDAO identityDAO){
		
		if(!GenericValidator.isBlankOrNull(newIdentityValue)){
			boolean assigned = false;
			for(PatientIdentity identity : identityList){
				if(identity.getIdentityTypeId().equals(identityTypeId)){
					if(!newIdentityValue.equals(identity.getIdentityData())){
						identity.setIdentityData(newIdentityValue);
						identity.setSysUserId(SERVICE_USER_ID);
						identityDAO.updateData(identity);
					}
					assigned = true;
					break;
				}
			}

			if(!assigned){
				PatientIdentity identity = new PatientIdentity();
				identity.setIdentityTypeId(identityTypeId);
				identity.setIdentityData(newIdentityValue);
				identity.setPatientId(patientId);
				identity.setSysUserId(SERVICE_USER_ID);
				identityDAO.insertData(identity);
			}
		}
	}

	private void updatePatientIfNeeded(MessagePatient orderPatient, PatientService patientService, Patient patient){
		boolean updatePatient = false;

		if(needsUpdating(orderPatient.getDisplayDOB() , patientService.getBirthdayForDisplay() )){
			patient.setBirthDateForDisplay(orderPatient.getDisplayDOB());
			updatePatient = true;
		}

		if(needsUpdating(orderPatient.getGender(), patientService.getGender())){
			patient.setGender(orderPatient.getGender());
			updatePatient = true;
		}

		if(needsUpdating(orderPatient.getNationalId(), patientService.getNationalId())){
			patient.setNationalId(orderPatient.getNationalId());
			updatePatient = true;
		}

		if(updatePatient){
			patient.setSysUserId(SERVICE_USER_ID);
			new PatientDAOImpl().updateData(patient);
		}
	}

	private void updatePersonIfNeeded(MessagePatient orderPatient, PatientService patientService, Person person){
		boolean updatePerson = false;

		if(needsUpdating(orderPatient.getFirstName(), patientService.getFirstName())){
			person.setFirstName(orderPatient.getFirstName());
			updatePerson = true;
		}

		if(needsUpdating(orderPatient.getLastName(), patientService.getLastName())){
			person.setLastName(orderPatient.getLastName());
			updatePerson = true;
		}
		if(needsUpdating(orderPatient.getAddressStreet(), patientService.getPerson().getStreetAddress())){
			person.setStreetAddress(orderPatient.getAddressStreet());
			updatePerson = true;
		}
		if(needsUpdating(orderPatient.getAddressVillage(), patientService.getPerson().getCity())){
			person.setCity(orderPatient.getAddressVillage());
			updatePerson = true;
		}

		if(updatePerson){
			person.setSysUserId(SERVICE_USER_ID);
			new PersonDAOImpl().updateData(person);
		}
	}

	private boolean needsUpdating(String orderPatientValue, String currentPatientValue){
		return !GenericValidator.isBlankOrNull(orderPatientValue) && StringUtil.compareWithNulls(currentPatientValue, orderPatientValue) != 0;
	}

	@Override
	public void persist(MessagePatient orderPatient, ElectronicOrder eOrder){
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try{
			persist(orderPatient);
			eOrder.setPatient(patient);
			eOrderDAO.insertData(eOrder);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}
	}

	@Override
	public String getServiceUserId(){
		return SERVICE_USER_ID;
	}

	@Override
	public void cancelOrder(String referringOrderNumber){
		if(!GenericValidator.isBlankOrNull(referringOrderNumber)){
			List<ElectronicOrder> eOrders = eOrderDAO.getElectronicOrdersByExternalId(referringOrderNumber);

			if(eOrders != null && !eOrders.isEmpty()){
				ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
				eOrder.setStatusId(StatusService.getInstance().getStatusID(ExternalOrderStatus.Cancelled));
				eOrder.setSysUserId(SERVICE_USER_ID);
				Transaction tx = HibernateUtil.getSession().beginTransaction();

				try{
					eOrderDAO.updateData(eOrder);
					tx.commit();
				}catch(Exception e){
					tx.rollback();
				}

			}
		}
	}

}
