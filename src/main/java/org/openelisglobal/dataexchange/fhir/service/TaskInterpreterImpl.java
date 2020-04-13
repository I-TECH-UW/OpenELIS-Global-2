package org.openelisglobal.dataexchange.fhir.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.ITestIdentityService;
import org.openelisglobal.common.services.TestIdentityService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.InterpreterResults;
import org.openelisglobal.dataexchange.order.action.IOrderInterpreter.OrderType;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v251.segment.OBR;
import ca.uhn.hl7v2.model.v251.segment.PID;

@Service
public class TaskInterpreterImpl implements TaskInterpreter {

    @Autowired
    private FhirContext fhirContext;

    public enum IdentityType {
        GUID("GU"), ST_NUMBER("ST"), NATIONAL_ID("NA"), OB_NUMBER("OB"), PC_NUMBER("PC");

        private String tag;

        private IdentityType(String tag) {
            this.tag = tag;
        }

        public String getIdentifier() {
            return tag;
        }
    }

    public enum Gender {
        MALE("M"), FEMALE("F");

        private String tag;

        private Gender(String tag) {
            this.tag = tag;
        }

        public String getIdentifier() {
            return tag;
        }

    }

    public enum ServiceIdentifier {
        PANEL("P"), TEST("T");

        private String tag;

        ServiceIdentifier(String tag) {
            this.tag = tag;
        }

        public String getIdentifier() {
            return tag;
        }
    }

    @Autowired
    TestService testService;

    private String labOrderNumber;
    private OrderType orderType;
    private String orderMessage;
    private Task task;
    private Patient patient;
    private List<ServiceRequest> serviceRequestList;
    private MessagePatient messagePatient;
    private Test test;
    private List<InterpreterResults> results = new ArrayList<>();
    private List<String> unsupportedTests = new ArrayList<>();
    private List<String> unsupportedPanels = new ArrayList<>();
    private ITestIdentityService testIdentityService;
    
    @Override
    public List<InterpreterResults> interpret(Task incomingTask, List<ServiceRequest> incomingServiceRequestList, Patient incomingPatient) {
        
        this.task = incomingTask;
        this.serviceRequestList = incomingServiceRequestList;
        this.patient = incomingPatient;
        
        this.orderMessage = fhirContext.newJsonParser().encodeResourceToString(task);

        System.out.println("TaskInterpreter:interpret: " + this.orderMessage);
        try {
            messagePatient = createPatientFromFHIR();
            test = createTestFromFHIR();
            extractOrderInformation();
        } catch (HL7Exception e) {
            LogEvent.logDebug(e);
            return buildResultList(true);
        }
        return buildResultList(false);
    }

    private void extractOrderInformation() throws HL7Exception {
        System.out.println("extractOrderInformation:");
        for (ServiceRequest serviceRequest : serviceRequestList) {
            labOrderNumber = serviceRequest.getId();
        }
        System.out.println("extractOrderInformation:labOrderNumber: " + labOrderNumber);
        //gnr: make electronic_order.external_id longer
        if (labOrderNumber.length() > 60) 
        {
            labOrderNumber = labOrderNumber.substring(labOrderNumber.length() - 60);
        } 
      
        orderType = OrderType.REQUEST;
    }

    private Test createTestFromFHIR() throws HL7Exception {
        System.out.println("TaskInterpreter:createTestFromFHIR:");
        
        String loincCode = "";
        for (ServiceRequest serviceRequest : serviceRequestList) {
//            System.out.println("BasedOn ServiceRequest: "
//                    + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
//            System.out.println("ServiceRequest LOINC: " 
//                    + serviceRequest.getCode().getCoding().get(0).getCodeElement());
            loincCode = serviceRequest.getCode().getCoding().get(0).getCodeElement().toString();
        }
        
        List<Test> tests = testService.getTestsByLoincCode(loincCode);
        if (tests.size() == 0) {
            return null;
        }
        return tests.get(0);
    }

    private MessagePatient createPatientFromFHIR() throws HL7Exception {
          System.out.println("TaskInterpreter:createPatientFromFHIR:");
        
//        System.out.println("Task: " + fhirContext.newJsonParser().encodeResourceToString(task));
//        System.out.println("Patient: " 
//                + fhirContext.newJsonParser().encodeResourceToString((IBaseResource) patient));
        
//        for (ServiceRequest serviceRequest : serviceRequestList) {
//            System.out.println("BasedOn ServiceRequest: "
//                    + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
//        }
        
        MessagePatient messagePatient = new MessagePatient();

//          System.out.println("Patient.getId(): " + patient.getId());
//          System.out.println("Patient.getIdBase(): " + patient.getIdBase());
//          System.out.println("Patient.getBirthDate(): " + patient.getBirthDate());
//          System.out.println("Patient.getGeneralPractitionerFirstRep(): " + patient.getGeneralPractitionerFirstRep());
//          System.out.println("Patient.getGender(): " + patient.getGender());
//          System.out.println("Patient.getIdentifierFirstRep().getUse(): " + patient.getIdentifierFirstRep().getUse());
//          System.out.println("Patient.getIdentifierFirstRep().getSystem(): " + patient.getIdentifierFirstRep().getSystem());
//          System.out.println("Patient.getIdentifierFirstRep().getValue(): " + patient.getIdentifierFirstRep().getValue());
//          System.out.println("Patient.getNameFirstRep().getGivenAsSingleString(): " + patient.getNameFirstRep().getGivenAsSingleString());
//          System.out.println("Patient.getNameFirstRep().getFamily(): " + patient.getNameFirstRep().getFamily());
          
         messagePatient.setExternalId(patient.getId());
         
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         String date = sdf.format(patient.getBirthDate()); 
         messagePatient.setDisplayDOB(date);
         
         if(patient.getGender().toString() == "MALE") {
             messagePatient.setGender("M");    
         } else {
             messagePatient.setGender("F");
         }
         
         messagePatient.setLastName(patient.getNameFirstRep().getFamily());
         messagePatient.setFirstName(patient.getNameFirstRep().getGivenAsSingleString());
         
          
//        patient.setExternalId(patientId.getIDNumber().getValue());
//        CX[] patientIdentities = pid.getPatientIdentifierList();
//        for (CX identity : patientIdentities) {
//            String value = identity.getCx1_IDNumber().getValue();
//            String code = identity.getIdentifierTypeCode().getValue();
//
//            if (IdentityType.GUID.getIdentifier().equals(code)) {
//                patient.setGuid(value);
//            } else if (IdentityType.ST_NUMBER.getIdentifier().equals(code)) {
//                patient.setStNumber(value);
//            } else if (IdentityType.NATIONAL_ID.getIdentifier().equals(code)) {
//                patient.setNationalId(value);
//            } else if (IdentityType.OB_NUMBER.getIdentifier().equals(code)) {
//                patient.setObNumber(value);
//            } else if (IdentityType.PC_NUMBER.getIdentifier().equals(code)) {
//                patient.setPcNumber(value);
//            }
//        }

//        if (Gender.MALE.getIdentifier().equals(pid.getAdministrativeSex().getValue())) {
//            messagePatient.setGender("M");
//        } else if (Gender.FEMALE.getIdentifier().equals(pid.getAdministrativeSex().getValue())) {
//            messagePatient.setGender("F");
//        }
//
//        setDOB(messagePatient, pid);
//
//        messagePatient.setLastName(pid.getPatientName(0).getFamilyName().getSurname().getValue());
//        messagePatient.setFirstName(pid.getPatientName(0).getGivenName().getValue());
//        messagePatient.setAddressStreet(pid.getPatientAddress(0).getStreetAddress().getStreetOrMailingAddress().getValue());
//        messagePatient.setAddressVillage(pid.getPatientAddress(0).getCity().getValue());
//        messagePatient.setAddressDepartment(pid.getPatientAddress(0).getStateOrProvince().getValue());

        return messagePatient;
    }

    private void setDOB(MessagePatient patient, PID pid) throws HL7Exception {
        String dob = pid.getDateTimeOfBirth().encode();

        if (dob.length() >= 4) {
            String year = null;
            String month = DateUtil.AMBIGUOUS_DATE_SEGMENT;
            String date = DateUtil.AMBIGUOUS_DATE_SEGMENT;

            year = dob.substring(0, 4);

            if (dob.length() >= 6) {
                month = dob.substring(4, 6);
            }

            if (dob.length() >= 8) {
                date = dob.substring(6, 8);
            }

            patient.setDisplayDOB(date + "/" + month + "/" + year);
        }
    }

    private List<InterpreterResults> buildResultList(boolean exceptionThrown) {
        System.out.println("buildResultList: " + exceptionThrown);
        results = new ArrayList<>();

        if (exceptionThrown) {
            results.add(InterpreterResults.INTERPRET_ERROR);
        } else {
            if (orderType == OrderType.UNKNOWN) {
                results.add(InterpreterResults.UNKNOWN_REQUEST_TYPE);
            }

            if (GenericValidator.isBlankOrNull(getReferringOrderNumber())) {
                results.add(InterpreterResults.MISSING_ORDER_NUMBER);
            }

            if (orderType == OrderType.REQUEST) {
                // a GUID is no longer being sent, so no longer requiring it, it is instead
                // generated upon receiving patient
                /*
                 * if(GenericValidator.isBlankOrNull(getMessagePatient().getGuid())){
                 * results.add(InterpreterResults.MISSING_PATIENT_GUID); }
                 */

//These are being commented out until we get confirmation on the desired policy.  Either the request should be rejected or the user should be required to
// fill the missing information in at the time of sample entry.  Commenting these out supports the latter
              if(GenericValidator.isBlankOrNull(getMessagePatient().getGender())){
                  results.add(InterpreterResults.MISSING_PATIENT_GENDER);
              }

//              if(getMessagePatient().getDob() == null){
//                  results.add(InterpreterResults.MISSING_PATIENT_DOB);
//              }

                if (getMessagePatient().getNationalId() == null && getMessagePatient().getObNumber() == null
                        && getMessagePatient().getPcNumber() == null && getMessagePatient().getStNumber() == null
                        && getMessagePatient().getExternalId() == null) {
                    results.add(InterpreterResults.MISSING_PATIENT_IDENTIFIER);
                }
                System.out.println("buildResultList:if test: " + test.getId());
                if (test == null || !getTestIdentityService().doesActiveTestExistForLoinc(test.getLoinc())) {
                    results.add(InterpreterResults.UNSUPPORTED_TESTS);
                }

//                try {
//                    OML_O21_OBSERVATION_REQUEST orderRequest = orderMessage.getORDERAll().get(0)
//                            .getOBSERVATION_REQUEST();
//                    checkOBR(orderRequest.getOBR());
//                    List<OML_O21_ORDER_PRIOR> priorOrders = orderRequest.getPRIOR_RESULT().getORDER_PRIORAll();
//                    for (OML_O21_ORDER_PRIOR priorOrder : priorOrders) {
//                        checkOBR(priorOrder.getOBR());
//                    }
//
//                } catch (HL7Exception e) {
//                    LogEvent.logDebug(e);
//                    results.add(InterpreterResults.INTERPRET_ERROR);
//                }
            }
        }

        if (results.isEmpty()) {
            results.add(InterpreterResults.OK);
        }

        return results;
    }

    private void checkOBR(OBR obr) throws HL7Exception {
        if (obr.isEmpty()) {
            results.add(InterpreterResults.MISSING_TESTS);
        }
        // moving away from name based testrequet to LOINC based test requests
        // test request no longer in obr, now appears in orc
        /*
         * else{ String name = obr.getUniversalServiceIdentifier().getText().getValue();
         * String identifier =
         * obr.getUniversalServiceIdentifier().getIdentifier().getValue(); if(
         * identifier.startsWith(ServiceIdentifier.TEST.getIdentifier() + "-")){
         * if(!getTestIdentityService().doesActiveTestExist(name)){ if(
         * !results.contains(InterpreterResults.UNSUPPORTED_TESTS)){
         * results.add(InterpreterResults.UNSUPPORTED_TESTS); } unsupportedTests.add(
         * name ); } }else if(
         * identifier.startsWith(ServiceIdentifier.PANEL.getIdentifier() + "-")){
         * if(!getTestIdentityService().doesPanelExist(name)){ if(
         * !results.contains(InterpreterResults.UNSUPPORTED_PANELS)){
         * results.add(InterpreterResults.UNSUPPORTED_PANELS); } unsupportedPanels.add(
         * name ); } }else{
         * results.add(InterpreterResults.OTHER_THAN_PANEL_OR_TEST_REQUESTED); } }
         */
    }

    @Override
    public String getReferringOrderNumber() {
        return labOrderNumber;
    }

    @Override
    public String getMessage() {
        if (task == null) {
            return null;
        }
        return (fhirContext.newJsonParser().encodeResourceToString(task));
    }

    @Override
    public MessagePatient getMessagePatient() {
        return messagePatient;
    }

    @Override
    public List<InterpreterResults> getResultStatus() {
        return results;
    }

    @Override
    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public List<String> getUnsupportedTests() {
        return unsupportedTests;
    }

    @Override
    public List<String> getUnsupportedPanels() {
        return unsupportedPanels;
    }

    private ITestIdentityService getTestIdentityService() {
        if (testIdentityService == null) {
            testIdentityService = TestIdentityService.getInstance();
        }

        return testIdentityService;
    }

    public void setTestIdentityService(ITestIdentityService testIdentityService) {
        this.testIdentityService = testIdentityService;
    }

    @Override
    public Test getTest() {
        return test;
    }
}
