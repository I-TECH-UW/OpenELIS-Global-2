package org.openelisglobal.common.provider.query.rest;

import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.PersonAddressService;
import org.openelisglobal.address.valueholder.AddressPart;
import org.openelisglobal.address.valueholder.PersonAddress;
import org.openelisglobal.common.provider.query.rest.bean.PatientDetails;
import org.openelisglobal.patient.service.PatientContactService;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.util.PatientUtil;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patient.valueholder.PatientContact;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.patientidentitytype.util.PatientIdentityTypeMap;
import org.openelisglobal.patienttype.service.PatientPatientTypeService;
import org.openelisglobal.patienttype.valueholder.PatientType;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class PatientSearchPopulateRestController {

    @Autowired
    PatientService patientService;
    @Autowired
    PatientContactService patientContactService;
    @Autowired
    AddressPartService addressPartService;
    @Autowired
    PersonAddressService personAddressService;
    @Autowired
    PatientPatientTypeService patientPatientTypeService;

    private String ADDRESS_PART_VILLAGE_ID;
    private String ADDRESS_PART_COMMUNE_ID;
    private String ADDRESS_PART_DEPT_ID;

    @GetMapping(value = "patient-details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientDetails getPatientResults(@RequestParam String patientID) {

        if (!GenericValidator.isBlankOrNull(patientID)) {
            return getPatientDetails(getPatientForID(patientID));
        } else {
            return new PatientDetails();
        }
    }

    private Patient getPatientForID(String patientID) {

        Patient patient = new Patient();
        patient.setId(patientID);

        patientService.getData(patient);
        if (patient.getId() == null) {
            return null;
        } else {
            return patient;
        }
    }

    private PatientDetails getPatientDetails(Patient patient) {
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

        Person person = patient.getPerson();

        PatientIdentityTypeMap identityMap = PatientIdentityTypeMap.getInstance();

        List<PatientIdentity> identityList = PatientUtil.getIdentityListForPatient(patient.getId());
        List<PatientContact> patientContacts = patientContactService.getForPatient(patient.getId());

        String city = getAddress(person, ADDRESS_PART_VILLAGE_ID);
        if (GenericValidator.isBlankOrNull(city)) {
            city = person.getCity();
        }
        String commune = getAddress(person, ADDRESS_PART_COMMUNE_ID);
        String dept = getAddress(person, ADDRESS_PART_DEPT_ID);

        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setID(patient.getId());
        patientDetails.setFhirUuid(patient.getFhirUuidAsString());
        patientDetails.setNationalID(patient.getNationalId());
        patientDetails.setST_ID(identityMap.getIdentityValue(identityList, "ST"));
        patientDetails.setSubjectNumber(identityMap.getIdentityValue(identityList, "SUBJECT"));
        patientDetails.setLastName(getLastNameForResponse(person));
        patientDetails.setFirstName(person.getFirstName());
        patientDetails.setMother(identityMap.getIdentityValue(identityList, "MOTHER"));
        patientDetails.setAka(identityMap.getIdentityValue(identityList, "AKA"));
        patientDetails.setStreet(person.getStreetAddress());
        patientDetails.setCity(city);
        patientDetails.setBirthplace(patient.getBirthPlace());
        patientDetails.setFaxNumber(person.getFax());
        patientDetails.setPhoneNumber(person.getPrimaryPhone());
        patientDetails.setEmail(person.getEmail());
        patientDetails.setGender(patient.getGender());
        patientDetails.setPatientType(getPatientType(patient));
        patientDetails.setInsurance(identityMap.getIdentityValue(identityList, "INSURANCE"));
        patientDetails.setOccupation(identityMap.getIdentityValue(identityList, "OCCUPATION"));
        patientDetails.setDob(patient.getBirthDateForDisplay());
        patientDetails.setCommune(commune);
        patientDetails.setAddressDept(dept);
        ;
        patientDetails.setMotherInitial(identityMap.getIdentityValue(identityList, "MOTHERS_INITIAL"));
        patientDetails.setExternalID(patient.getExternalId());
        patientDetails.setEducation(identityMap.getIdentityValue(identityList, "EDUCATION"));
        patientDetails.setMaritialStatus(identityMap.getIdentityValue(identityList, "MARITIAL"));
        patientDetails.setNationality(identityMap.getIdentityValue(identityList, "NATIONALITY"));
        patientDetails.setOtherNationality(identityMap.getIdentityValue(identityList, "OTHER NATIONALITY"));
        patientDetails.setHealthDistrict(identityMap.getIdentityValue(identityList, "HEALTH DISTRICT"));
        patientDetails.setHealthRegion(identityMap.getIdentityValue(identityList, "HEALTH REGION"));
        patientDetails.setGuid(identityMap.getIdentityValue(identityList, "GUID"));
        if (patientContacts.size() >= 1) {
            PatientContact contact = patientContacts.get(0);
            patientDetails.setContactFirstName(contact.getPerson().getFirstName());
            patientDetails.setContactLastName(contact.getPerson().getLastName());
            patientDetails.setContactPhone(contact.getPerson().getPrimaryPhone());
            patientDetails.setContactEmail(contact.getPerson().getEmail());
            patientDetails.setContactPK(contact.getId());
        }

        if (patient.getLastupdated() != null) {
            String updateAsString = patient.getLastupdated().toString();
            patientDetails.setPatientUpdated(updateAsString);
        }

        if (person.getLastupdated() != null) {
            String updateAsString = person.getLastupdated().toString();
            patientDetails.setPersonUpdated(updateAsString);
        }
        return patientDetails;
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
