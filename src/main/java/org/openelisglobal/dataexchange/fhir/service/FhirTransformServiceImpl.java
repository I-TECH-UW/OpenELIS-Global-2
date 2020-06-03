package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.CodedValueXmit;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

@Service
public class FhirTransformServiceImpl implements FhirTransformService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    protected PatientService patientService = SpringContext.getBean(PatientService.class);
    protected FhirApiWorkflowService fhirApiWorkFlowService = SpringContext.getBean(FhirApiWorkflowService.class);
    protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
    
    IGenericClient localFhirClient = fhirContext
            .newRestfulGenericClient(fhirApiWorkFlowService.getLocalFhirStorePath());
    org.hl7.fhir.r4.model.Patient fhirPatient = null;
    
//    private Patient getPatientWithSameSubjectNumber(Patient remotePatient) {
//        Map<String, List<String>> localSearchParams = new HashMap<>();
//        localSearchParams.put(Patient.SUBJECT_NUMBER,
//                Arrays.asList(remoteStorePath + "|" + remotePatient.getIdElement().getIdPart()));
//
//        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
//        Bundle localBundle = localFhirClient.search().forResource(Patient.class).whereMap(localSearchParams)
//                .returnBundle(Bundle.class).execute();
//        return (Patient) localBundle.getEntryFirstRep().getResource();
//    }

    public String CreateFhirFromOESample(TestResultsXmit result) {
        System.out.println("CreateFhirFromOESample:result ");
        
        String patientGuid = result.getPatientGUID();
        org.openelisglobal.patient.valueholder.Patient patient = patientService.getPatientForGuid(patientGuid);
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        try {
            // check for patient existence
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdElement().getIdPart()))
                    .prettyPrint().execute();

//            System.out.println("CreateFhirFromOESample: " + fhirContext.newJsonParser().encodeResourceToString(pBundle));
            
            Reference subjectRef = new Reference();
            
            if (pBundle.getEntry().size() == 0) {
                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                subjectRef.setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference(existingPatient.getResourceType() + "/" + existingPatient.getIdElement().getIdPart());
            }
            
            CodedValueXmit codedValueXmit = new CodedValueXmit();
            codedValueXmit = result.getTest();
          
            
            if (result.getResults() != null && result.getUnits() != null) {

                Bundle oBundle = (Bundle) localFhirClient.search().forResource(Observation.class)
                        .where(new TokenClientParam("code").exactly().code(result.getAccessionNumber())).prettyPrint()
                        .execute();

                Bundle drBundle = (Bundle) localFhirClient.search().forResource(DiagnosticReport.class)
                        .where(new TokenClientParam("code").exactly().code(result.getAccessionNumber())).prettyPrint()
                        .execute();

                if (oBundle.getEntry().size() != 0 && drBundle.getEntry().size() != 0) {
                    System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
                    System.out.println("existing observation: " + fhirContext.newJsonParser()
                            .encodeResourceToString(oBundle.getEntryFirstRep().getResource()));
                    System.out.println("existing diagnosticReport: " + fhirContext.newJsonParser()
                            .encodeResourceToString(drBundle.getEntryFirstRep().getResource()));

                    return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource())
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                }

                Observation observation = new Observation();
                Identifier identifier = new Identifier();
                identifier.setSystem(result.getAccessionNumber());
                observation.addIdentifier(identifier);
                Reference reference = new Reference();
                reference.setReference(result.getAccessionNumber());
//            observation.addBasedOn(reference);
                observation.setStatus(Observation.ObservationStatus.FINAL);
                CodeableConcept codeableConcept = new CodeableConcept();
                Coding coding = new Coding();
                List<Coding> codingList = new ArrayList<Coding>();
                coding.setCode(result.getTest().getCode());
                coding.setSystem("http://loinc.org");
                codingList.add(coding);

                Coding labCoding = new Coding();
                labCoding.setCode(result.getAccessionNumber());
                labCoding.setSystem("OpenELIS-Global/Lab No");
                codingList.add(labCoding);
                codeableConcept.setCoding(codingList);
                observation.setCode(codeableConcept);

//            Reference subjectRef = new Reference();
//            subjectRef.setReference("Patient/" + result.getPatientSTID());
//            observation.setSubject(subjectRef);

                // TODO: numeric, check for other result types
                Quantity quantity = new Quantity();
                quantity.setValue(new java.math.BigDecimal(result.getResults().get(0).getResult().getText()));
                quantity.setUnit(result.getUnits());
                observation.setValue(quantity);

                Annotation oNote = new Annotation();
                oNote.setText(result.getTestNotes());
                observation.addNote(oNote);

                System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(observation));

                oOutcome = localFhirClient.create().resource(observation).execute();

                DiagnosticReport diagnosticReport = new DiagnosticReport();
                diagnosticReport.setId(result.getTest().getCode());
                identifier.setSystem(result.getAccessionNumber());
                diagnosticReport.addIdentifier(identifier);
                reference.setReference(result.getAccessionNumber());
//            diagnosticReport.addBasedOn(reference);
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);

                diagnosticReport.setCode(codeableConcept);
//            diagnosticReport.setSubject(subjectRef);

                Reference observationReference = new Reference();
                observationReference.setType(oOutcome.getId().getResourceType());
                observationReference
                        .setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
                diagnosticReport.addResult(observationReference);

                System.out.println(
                        "diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(diagnosticReport));
                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();

            }
        } catch (Exception e) {
//      System.out.println("Result update exception: " + e.getStackTrace());
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }

        System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
        System.out
                .println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource()));
        System.out.println(
                "diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));

        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource())
                + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));     
        
    }
    
    public String CreateFhirFromOESample(PortableOrder pOrder) {

        System.out.println("CreateFhirFromOESample:pOrder ");

        org.openelisglobal.patient.valueholder.Patient patient = (pOrder.getPatient());
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        try {
            // check for patient existence
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdElement().getIdPart()))
                    .prettyPrint().execute();

//            System.out.println("CreateFhirFromOESample: " + fhirContext.newJsonParser().encodeResourceToString(pBundle));
            
            Reference subjectRef = new Reference();
            
            if (pBundle.getEntry().size() == 0) {
                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                subjectRef.setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference(existingPatient.getResourceType() + "/" + existingPatient.getIdElement().getIdPart());
            }
            
            if (pOrder.getResultValue() != null && pOrder.getUomName() != null) {
                
                Bundle oBundle = (Bundle) localFhirClient.search().forResource(Observation.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                        .prettyPrint()
                        .execute();
                
                Bundle drBundle = (Bundle) localFhirClient.search().forResource(DiagnosticReport.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                        .prettyPrint()
                        .execute();
                
                if (oBundle.getEntry().size() != 0 && drBundle.getEntry().size() != 0) {
                    System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient)); 
                    System.out.println("existing observation: " + fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource())); 
                    System.out.println("existing diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                    
                    return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                            fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource()) +
                            fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                }
                
                Observation observation = new Observation();
                
                CodeableConcept codeableConcept = new CodeableConcept();
                Coding coding = new Coding();
                List<Coding> codingList = new ArrayList<Coding>();
                coding.setCode(pOrder.getLoinc());
                coding.setSystem("http://loinc.org");
                codingList.add(coding);
                
                Coding labCoding = new Coding();
                labCoding.setCode(pOrder.getExternalId());
                labCoding.setSystem("OpenELIS-Global/Lab No");
                codingList.add(labCoding);
                codeableConcept.setCoding(codingList);
                
                observation.setCode(codeableConcept);
                
                observation.setSubject(subjectRef);
                
//        observation.setBasedOn(serviceRequest.getBasedOn());
                observation.setStatus(Observation.ObservationStatus.FINAL);
//        observation.setCode(serviceRequest.getCode());

                // TODO: numeric, check for other result types, check for null
                Quantity quantity = new Quantity();

                quantity.setValue(new java.math.BigDecimal(pOrder.getResultValue()));
                quantity.setUnit(pOrder.getUomName());
                observation.setValue(quantity);
                
                // TODO Note
//                Annotation oNote = new Annotation();
//        oNote.setText(pOrder.getNotes());
//            observation.addNote(oNote);
                
//                ca.uhn.fhir.rest.server.exceptions.InvalidRequestException
             
                    oOutcome = localFhirClient.create().resource(observation).execute();
            
                DiagnosticReport diagnosticReport = new DiagnosticReport();
//                diagnosticReport.setId(result.getTest().getCode());
//                diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
                
                diagnosticReport.setCode(codeableConcept);
                
//                diagnosticReport.setBasedOn(serviceRequest.getBasedOn());
                // TODO: status
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
//                diagnosticReport.setCode(serviceRequest.getCode());
                diagnosticReport.setSubject(subjectRef);

                Reference observationReference = new Reference();
                observationReference.setType(oOutcome.getId().getResourceType());
                observationReference
                        .setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
                diagnosticReport.addResult(observationReference);
                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                
                System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient)); 
                System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource())); 
                System.out.println("diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));
             
                /* task ?
             Reference diagnosticReportReference = new Reference();
             diagnosticReportReference.setType(drOutcome.getId().getResourceType());
             diagnosticReportReference.setReference( drOutcome.getId().getResourceType() + "/" + drOutcome.getId().getIdPart());
             
           
             * TaskOutputComponent theOutputComponent = new TaskOutputComponent();
             * theOutputComponent.setValue(diagnosticReportReference);
             * task.addOutput(theOutputComponent); task.setStatus(TaskStatus.COMPLETED);
             * 
             * localFhirClient.update().resource(task).execute();
             */
            }
        } catch (Exception e) {
//        System.out.println("Result update exception: " + e.getStackTrace());
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }
        
        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource()) + 
                fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));
    }

    private org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();
        
        List<PatientIdentity> patientIdentityList = patientIdentityService.getPatientIdentitiesForPatient(patient.getId());
        String subjectNumber = null;
        for (PatientIdentity patientIdentity : patientIdentityList ) {
            if(patientIdentity.getIdentityTypeId().equalsIgnoreCase("9")) { // fix hardcode Subject Number
                subjectNumber = patientIdentity.getIdentityData();
            }
        }
        
        Identifier identifier = new Identifier();
        identifier.setId(subjectNumber);
        identifier.setSystem("OpenELIS-Global/SubjectNumber"); // fix hardcode
        List<Identifier> identifierList = new ArrayList<Identifier>();
        identifierList.add(identifier);
        fhirPatient.setIdentifier(identifierList);
        
        HumanName humanName = new HumanName();
        List<HumanName> humanNameList = new ArrayList<HumanName>();
        humanName.setFamily(patient.getPerson().getLastName());
        humanName.addGiven(patient.getPerson().getFirstName());
        humanNameList.add(humanName);
        fhirPatient.setName(humanNameList);
        
        fhirPatient.setBirthDate(patient.getBirthDate());
        if (patient.getGender().equalsIgnoreCase("M")) {
            fhirPatient.setGender(AdministrativeGender.MALE);
        } else {
            fhirPatient.setGender(AdministrativeGender.FEMALE);
        }

        return fhirPatient;
    }
}