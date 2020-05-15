package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.List;

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

    public String CreateFhirFromOESample(PortableOrder pOrder) {

        System.out.println("CreateFhirFromOESample: ");

        org.openelisglobal.patient.valueholder.Patient patient = (pOrder.getPatient());
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        try {
            // check for existence
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
                Observation observation = new Observation();
                
                CodeableConcept codeableConcept = new CodeableConcept();
                Coding coding = new Coding();
                coding.setCode(pOrder.getLoinc());
                coding.setSystem("http://loinc.org");
                codeableConcept.addCoding(coding);
                observation.setCode(codeableConcept);
                
                observation.setSubject(subjectRef);
                
                Identifier identifier = new Identifier();
                List<Identifier> identifierList = new ArrayList<Identifier>();
                identifier.setSystem("OpenELIS-Global/Lab No"); 
                identifier.setId(pOrder.getExternalId());
                identifierList.add(identifier);
                observation.setIdentifier(identifierList);
                
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

                oOutcome = localFhirClient.create().resource(observation).execute();
            
                DiagnosticReport diagnosticReport = new DiagnosticReport();
//                diagnosticReport.setId(result.getTest().getCode());
//                diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
                
                diagnosticReport.setCode(codeableConcept);
               
                diagnosticReport.setIdentifier(identifierList);
                
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
            System.out.println("FhirTransformServiceImpl:Result update exception: " + e.toString());
            e.printStackTrace();
        }
        
        return fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                        oOutcome.toString() !=null ? "": oOutcome.toString() + 
                        drOutcome.toString() !=null ? "": drOutcome.toString();
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