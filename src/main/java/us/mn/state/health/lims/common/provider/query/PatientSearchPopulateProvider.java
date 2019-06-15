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
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;

import spring.mine.internationalization.MessageUtil;
import spring.service.address.AddressPartService;
import spring.service.address.PersonAddressService;
import spring.service.patient.PatientService;
import spring.service.patientidentity.PatientIdentityService;
import spring.service.patienttype.PatientPatientTypeService;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.util.XMLUtil;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.util.PatientIdentityTypeMap;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;
import us.mn.state.health.lims.person.valueholder.Person;

public class PatientSearchPopulateProvider extends BaseQueryProvider {

	protected PatientService patientService = SpringContext.getBean(PatientService.class);
	protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
	protected PatientPatientTypeService patientPatientTypeService = SpringContext
			.getBean(PatientPatientTypeService.class);
	protected AddressPartService addressPartService = SpringContext.getBean(AddressPartService.class);
	protected PersonAddressService personAddressService = SpringContext.getBean(PersonAddressService.class);

	private String ADDRESS_PART_VILLAGE_ID;
	private String ADDRESS_PART_COMMUNE_ID;
	private String ADDRESS_PART_DEPT_ID;

	public PatientSearchPopulateProvider() {
		List<AddressPart> partList = addressPartService.getAll();
		for (AddressPart addressPart : partList) {
			if ("department".equals(addressPart.getPartName())) {
				ADDRESS_PART_DEPT_ID = addressPart.getId();
			} else if ("commune".equals(addressPart.getPartName())) {
				ADDRESS_PART_COMMUNE_ID = addressPart.getId();
			} else if ("village".equals(addressPart.getPartName())) {
				ADDRESS_PART_VILLAGE_ID = addressPart.getId();
			}
		}
	}

	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nationalId = request.getParameter("nationalID");
		String externalId = request.getParameter("externalID");
		String patientKey = request.getParameter("personKey");
		StringBuilder xml = new StringBuilder();
		String result = null;
		if (nationalId != null) {
			result = createSearchResultXML(patientService.getPatientByNationalId(nationalId), xml);
		} else if (externalId != null) {
			result = createSearchResultXML(patientService.getPatientByExternalId(externalId), xml);
		} else {
			result = createSearchResultXML(getPatientForID(patientKey), xml);
		}
		if (!result.equals(VALID)) {
			result = MessageUtil.getMessage("patient.message.patientNotFound");
			xml.append("empty");
		}
		ajaxServlet.sendData(xml.toString(), result, request, response);
	}

	/**
	 * building the XML and the status return.
	 *
	 * @return
	 */
	private String createSearchResultXML(Patient patient, StringBuilder xml) {
		if (patient == null) {
			return INVALID;
		}
		createReturnXML(patient, xml);
		return VALID;
	}

	private Patient getPatientForID(String personKey) {

		Patient patient = new Patient();
		patient.setId(personKey);

		patientService.getData(patient);
		if (patient.getId() == null) {
			return null;
		} else {
			return patient;
		}
	}

	private void createReturnXML(Patient patient, StringBuilder xml) {

		Person person = patient.getPerson();

		PatientIdentityTypeMap identityMap = PatientIdentityTypeMap.getInstance();

		List<PatientIdentity> identityList = PatientUtil.getIdentityListForPatient(patient.getId());

		XMLUtil.appendKeyValue("ID", patient.getId(), xml);
		XMLUtil.appendKeyValue("nationalID", patient.getNationalId(), xml);
		XMLUtil.appendKeyValue("ST_ID", identityMap.getIdentityValue(identityList, "ST"), xml);
		XMLUtil.appendKeyValue("subjectNumber", identityMap.getIdentityValue(identityList, "SUBJECT"), xml);
		XMLUtil.appendKeyValue("lastName", getLastNameForResponse(person), xml);
		XMLUtil.appendKeyValue("firstName", person.getFirstName(), xml);
		XMLUtil.appendKeyValue("mother", identityMap.getIdentityValue(identityList, "MOTHER"), xml);
		XMLUtil.appendKeyValue("aka", identityMap.getIdentityValue(identityList, "AKA"), xml);
		XMLUtil.appendKeyValue("street", person.getStreetAddress(), xml);
		XMLUtil.appendKeyValue("city", getAddress(person, ADDRESS_PART_VILLAGE_ID), xml);
		XMLUtil.appendKeyValue("birthplace", patient.getBirthPlace(), xml);
		XMLUtil.appendKeyValue("faxNumber", person.getFax(), xml);
		XMLUtil.appendKeyValue("phoneNumber", person.getHomePhone(), xml);
		XMLUtil.appendKeyValue("email", person.getEmail(), xml);
		XMLUtil.appendKeyValue("gender", patient.getGender(), xml);
		XMLUtil.appendKeyValue("patientType", getPatientType(patient), xml);
		XMLUtil.appendKeyValue("insurance", identityMap.getIdentityValue(identityList, "INSURANCE"), xml);
		XMLUtil.appendKeyValue("occupation", identityMap.getIdentityValue(identityList, "OCCUPATION"), xml);
		XMLUtil.appendKeyValue("dob", patient.getBirthDateForDisplay(), xml);
		XMLUtil.appendKeyValue("commune", getAddress(person, ADDRESS_PART_COMMUNE_ID), xml);
		XMLUtil.appendKeyValue("addressDept", getAddress(person, ADDRESS_PART_DEPT_ID), xml);
		XMLUtil.appendKeyValue("motherInitial", identityMap.getIdentityValue(identityList, "MOTHERS_INITIAL"), xml);
		XMLUtil.appendKeyValue("externalID", patient.getExternalId(), xml);
		XMLUtil.appendKeyValue("education", identityMap.getIdentityValue(identityList, "EDUCATION"), xml);
		XMLUtil.appendKeyValue("maritialStatus", identityMap.getIdentityValue(identityList, "MARITIAL"), xml);
		XMLUtil.appendKeyValue("nationality", identityMap.getIdentityValue(identityList, "NATIONALITY"), xml);
		XMLUtil.appendKeyValue("otherNationality", identityMap.getIdentityValue(identityList, "OTHER NATIONALITY"),
				xml);
		XMLUtil.appendKeyValue("healthDistrict", identityMap.getIdentityValue(identityList, "HEALTH DISTRICT"), xml);
		XMLUtil.appendKeyValue("healthRegion", identityMap.getIdentityValue(identityList, "HEALTH REGION"), xml);
		XMLUtil.appendKeyValue("guid", identityMap.getIdentityValue(identityList, "GUID"), xml);

		if (patient.getLastupdated() != null) {
			String updateAsString = patient.getLastupdated().toString();
			XMLUtil.appendKeyValue("patientUpdated", updateAsString, xml);
		}

		if (person.getLastupdated() != null) {
			String updateAsString = person.getLastupdated().toString();
			XMLUtil.appendKeyValue("personUpdated", updateAsString, xml);
		}
	}

	private String getAddress(Person person, String addressPartId) {
		if (GenericValidator.isBlankOrNull(addressPartId)) {
			return "";
		}
		PersonAddress address = personAddressService.getByPersonIdAndPartId(person.getId(), addressPartId);

		return address != null ? address.getValue() : "";
	}

	/**
	 * Fake the unknown patient by never return whatever happens to be in last name
	 * field.
	 *
	 * @param person
	 * @return
	 */
	private String getLastNameForResponse(Person person) {
		if (PatientUtil.getUnknownPerson().getId().equals(person.getId())) {
			return null;
		} else {
			return person.getLastName();
		}
	}

	private String getPatientType(Patient patient) {
		PatientType patientType = patientPatientTypeService.getPatientTypeForPatient(patient.getId());
		return patientType != null ? patientType.getType() : null;
	}

}
