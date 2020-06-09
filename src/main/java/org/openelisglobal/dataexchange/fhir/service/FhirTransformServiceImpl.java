package org.openelisglobal.dataexchange.fhir.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskOutputComponent;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.SampleAddService.SampleTestCollection;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.CodedValueXmit;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

@Service
public class FhirTransformServiceImpl implements FhirTransformService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    protected PatientService patientService = SpringContext.getBean(PatientService.class);
    protected FhirApiWorkflowService fhirApiWorkFlowService = SpringContext.getBean(FhirApiWorkflowService.class);
    protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
    protected ElectronicOrderService electronicOrderService = SpringContext.getBean(ElectronicOrderService.class);
    protected TestService testService = SpringContext.getBean(TestService.class);
    
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
        String accessionNumber = result.getAccessionNumber();
        accessionNumber = accessionNumber.substring(0,accessionNumber.indexOf('-')); // disregard test number within set
        org.openelisglobal.patient.valueholder.Patient patient = patientService.getPatientForGuid(patientGuid);
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        
        try {
            
            Reference basedOnRef = new Reference();
            Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(accessionNumber))
                    .prettyPrint()
                    .execute();
            
            if (srBundle.getEntry().size() != 0) {
                BundleEntryComponent bundleComponent = srBundle.getEntryFirstRep();
                ServiceRequest existingServiceRequest = (ServiceRequest) bundleComponent.getResource();
                basedOnRef.setReference(existingServiceRequest.getResourceType() + "/" + existingServiceRequest.getIdElement().getIdPart());
            }
            
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
                            + fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource())
                            + fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                }

                Observation observation = new Observation();
                Identifier identifier = new Identifier();
                identifier.setSystem(accessionNumber);
                observation.addIdentifier(identifier);
                Reference reference = new Reference();
                reference.setReference(accessionNumber);
//            observation.addBasedOn(reference);
                observation.setStatus(Observation.ObservationStatus.FINAL);
                CodeableConcept codeableConcept = new CodeableConcept();
                Coding coding = new Coding();
                List<Coding> codingList = new ArrayList<Coding>();
                coding.setCode(result.getTest().getCode());
                coding.setSystem("http://loinc.org");
                codingList.add(coding);

                Coding labCoding = new Coding();
                labCoding.setCode(accessionNumber);
                labCoding.setSystem("OpenELIS-Global/Lab No");
                codingList.add(labCoding);
                codeableConcept.setCoding(codingList);
                observation.setCode(codeableConcept);

                observation.setSubject(subjectRef);

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
                identifier.setSystem(accessionNumber);
                diagnosticReport.addIdentifier(identifier);
                reference.setReference(accessionNumber);
                diagnosticReport.addBasedOn(basedOnRef);
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);

                diagnosticReport.setCode(codeableConcept);
                diagnosticReport.setSubject(subjectRef);

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
        System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource()));
        System.out.println("diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));

        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource())
                + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));     
        
    }
    
    public String CreateFhirFromOESample(PortableOrder pOrder) {

        System.out.println("CreateFhirFromOESample:pOrder:externalId: " + pOrder.getExternalId());

        org.openelisglobal.patient.valueholder.Patient patient = (pOrder.getPatient());
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        Bundle oBundle = new Bundle();
        Bundle drBundle = new Bundle();
        Bundle srBundle = new Bundle();
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        MethodOutcome srOutcome = null;
        ServiceRequest serviceRequest = new ServiceRequest();
        
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
        
        try {
            // check for patient existence
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdElement().getIdPart()))
                    .prettyPrint().execute();

            Reference subjectRef = new Reference();
            
            if (pBundle.getEntry().size() == 0) {
                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                subjectRef.setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference(existingPatient.getResourceType() + "/" + existingPatient.getIdElement().getIdPart());
            }
            
            srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                    .prettyPrint()
                    .execute();
            
            if (srBundle.getEntry().size() == 0) {
                
                serviceRequest.setCode(codeableConcept);
                serviceRequest.setSubject(subjectRef);
                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
            }
            
            if (pOrder.getResultValue() != null && pOrder.getUomName() != null) {
                
                oBundle = (Bundle) localFhirClient.search().forResource(Observation.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                        .prettyPrint()
                        .execute();
                
                drBundle = (Bundle) localFhirClient.search().forResource(DiagnosticReport.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                        .prettyPrint()
                        .execute();
                
                if (oBundle.getEntry().size() != 0 && drBundle.getEntry().size() != 0) {
                    System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient)); 
                    System.out.println("existing observation: " + fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource())); 
                    System.out.println("existing diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                    System.out.println("existing serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(srBundle.getEntryFirstRep().getResource()));
                    
                    return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                            fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource()) +
                            fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()) +
                            fhirContext.newJsonParser().encodeResourceToString(srBundle.getEntryFirstRep().getResource()));
                }
                
                Observation observation = new Observation();
                
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
                
                Reference serviceRequestReference = new Reference();
                serviceRequestReference.setType(srOutcome.getId().getResourceType());
                serviceRequestReference
                        .setReference(srOutcome.getId().getResourceType() + "/" + srOutcome.getId().getIdPart());
                diagnosticReport.addBasedOn(serviceRequestReference);
                
                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                
                System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient)); 
                System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource())); 
                System.out.println("diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));
                System.out.println("serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(srOutcome.getResource()));
                
                return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                        fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource()) + 
                        fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()) +
                        fhirContext.newJsonParser().encodeResourceToString((srOutcome == null) ? srBundle.getEntryFirstRep().getResource() : srOutcome.getResource()));
            }
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }
        
        // check for no results therefore no ob or dr
        String returnString = new String();
        
        System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
        returnString += fhirContext.newJsonParser().encodeResourceToString(fhirPatient);
        
        if(oBundle.getEntry().size() != 0) {
            System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource());
        }
        if(drBundle.getEntry().size() != 0) {
            System.out.println("diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource());
        }
        if(srBundle.getEntry().size() != 0) {
            System.out.println("serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(srBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(srBundle.getEntryFirstRep().getResource());
        } else {
            System.out.println("serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(srOutcome.getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(srOutcome.getResource());
        }
        return returnString;
    }

    public String CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result) {

        
        MethodOutcome oOutcome = null;
        MethodOutcome drOutcome = null;
        
        String orderNumber = result.getReferringOrderNumber();
        List<ElectronicOrder> eOrders = electronicOrderService.getElectronicOrdersByExternalId(orderNumber);
        eOrder = eOrders.get(eOrders.size() - 1);
        ExternalOrderStatus eOrderStatus = SpringContext.getBean(IStatusService.class)
                .getExternalOrderStatusForID(eOrder.getStatusId());
        
        Task eTask = fhirContext.newJsonParser().parseResource(Task.class, eOrder.getData());
        Task task = new Task();
        
        // using UUID from getData which is idPart in original etask
        Bundle tsrBundle = (Bundle) localFhirClient.search().forResource(Task.class)
                .where(new TokenClientParam("identifier").exactly().code(eTask.getIdElement().getIdPart()))
                .include(new Include("Task:based-on"))
                .prettyPrint()
                .execute();
     
        task = null; 
        List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>();
        for (BundleEntryComponent bundleComponent : tsrBundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
                task = (Task) bundleComponent.getResource();
            }
            
            if (bundleComponent.hasResource()
                    && ResourceType.ServiceRequest.equals(bundleComponent.getResource().getResourceType())) {
                    
                ServiceRequest serviceRequest = (ServiceRequest) bundleComponent.getResource();
                for(Identifier identifier : serviceRequest.getIdentifier()) {
                    if(identifier.getValue().equals(orderNumber))
                        serviceRequestList.add((ServiceRequest) bundleComponent.getResource());
                }
            }
        }
        
        for (ServiceRequest serviceRequest : serviceRequestList) {
         // task has to be refreshed after each loop 
         // using UUID from getData which is idPart in original etask
            Bundle tBundle = (Bundle) localFhirClient.search().forResource(Task.class)
                    .where(new TokenClientParam("identifier").exactly().code(eTask.getIdElement().getIdPart()))
                    .prettyPrint()
                    .execute();
         
            task = null; 
            for (BundleEntryComponent bundleComponent : tBundle.getEntry()) {
                if (bundleComponent.hasResource()
                        && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
                    task = (Task) bundleComponent.getResource();
                }
            }
            
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("_id").exactly()
                            .code(serviceRequest.getSubject().getReferenceElement().getIdPart()))
                    .prettyPrint().execute();

            org.hl7.fhir.r4.model.Patient patient = null;
            for (BundleEntryComponent bundleComponent : pBundle.getEntry()) {
                if (bundleComponent.hasResource()
                        && ResourceType.Patient.equals(bundleComponent.getResource().getResourceType())) {
                    patient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                }
            }

            try {
                Observation observation = new Observation();
                observation.setIdentifier(serviceRequest.getIdentifier());
                observation.setBasedOn(serviceRequest.getBasedOn());
                observation.setStatus(Observation.ObservationStatus.FINAL);
                observation.setCode(serviceRequest.getCode());
                Reference subjectRef = new Reference();
                subjectRef.setReference(patient.getResourceType() + "/" + patient.getIdElement().getIdPart());
                observation.setSubject(subjectRef);
                // TODO: numeric, check for other result types
                Quantity quantity = new Quantity();
                quantity.setValue(new java.math.BigDecimal(result.getResults().get(0).getResult().getText()));
                quantity.setUnit(result.getUnits());
                observation.setValue(quantity);

                Annotation oNote = new Annotation();
                oNote.setText(result.getTestNotes());
                observation.addNote(oNote);

                oOutcome = localFhirClient.create().resource(observation).execute();

                DiagnosticReport diagnosticReport = new DiagnosticReport();
                diagnosticReport.setId(result.getTest().getCode());
                diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
                diagnosticReport.setBasedOn(serviceRequest.getBasedOn());
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
                diagnosticReport.setCode(serviceRequest.getCode());
                diagnosticReport.setSubject(subjectRef);

                Reference observationReference = new Reference();
                observationReference.setType(oOutcome.getId().getResourceType());
                observationReference
                        .setReference(oOutcome.getId().getResourceType() + "/" + oOutcome.getId().getIdPart());
                diagnosticReport.addResult(observationReference);
                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();

                Reference diagnosticReportReference = new Reference();
                diagnosticReportReference.setType(drOutcome.getId().getResourceType());
                diagnosticReportReference.setReference(
                        drOutcome.getId().getResourceType() + "/" + drOutcome.getId().getIdPart());
                
                System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient)); 
                System.out.println("observation: " + fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource())); 
                System.out.println("diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));

                TaskOutputComponent theOutputComponent = new TaskOutputComponent();
                theOutputComponent.setValue(diagnosticReportReference);
                task.addOutput(theOutputComponent);
                task.setStatus(TaskStatus.COMPLETED);

                localFhirClient.update().resource(task).execute();

            } catch (Exception e) {
                System.out.println("Result update exception: " + e.getStackTrace());
            }
        }
        
        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) + 
                fhirContext.newJsonParser().encodeResourceToString(oOutcome.getResource()) + 
                fhirContext.newJsonParser().encodeResourceToString(drOutcome.getResource()));
    }
    
    @Override
    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
            PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request) {
        
        System.out.println("CreateFhirFromOESample:add Order:accession#: " + updateData.getAccessionNumber());

        fhirPatient = CreateFhirPatientFromOEPatient(patientInfo);
        
        Bundle srBundle = new Bundle();
        MethodOutcome pOutcome = new MethodOutcome();
        MethodOutcome srOutcome = new MethodOutcome();
        
        ServiceRequest serviceRequest = new ServiceRequest();
        
        CodeableConcept codeableConcept = new CodeableConcept();
        List<Coding> codingList = new ArrayList<Coding>();
        
        List<SampleTestCollection> sampleTestCollectionList = updateData.getSampleItemsTests();
        for (SampleTestCollection sampleTestCollection : sampleTestCollectionList) {
            for (Test test : sampleTestCollection.tests) {
                test = testService.get(test.getId());
                Coding coding = new Coding();
                coding.setCode(test.getLoinc());
                coding.setSystem("http://loinc.org");
                codingList.add(coding);
            }
        }
        
        Coding labCoding = new Coding();
        labCoding.setCode(updateData.getAccessionNumber());
        labCoding.setSystem("OpenELIS-Global/Lab No");
        codingList.add(labCoding);
        codeableConcept.setCoding(codingList);
        
        try {
            // check for patient existence
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdElement().getIdPart()))
                    .prettyPrint().execute();

            Reference subjectRef = new Reference();
            
            if (pBundle.getEntry().size() == 0) {
                pOutcome = localFhirClient.create().resource(fhirPatient).execute();
                subjectRef.setReference(pOutcome.getId().getResourceType() + "/" + pOutcome.getId().getIdPart());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference(existingPatient.getResourceType() + "/" + existingPatient.getIdElement().getIdPart());
            }
            
            srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(updateData.getAccessionNumber()))
                    .prettyPrint()
                    .execute();

            if (srBundle.getEntry().size() == 0) {

                serviceRequest.setCode(codeableConcept);
                serviceRequest.setSubject(subjectRef);
                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
            }
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }

        return null;
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
    
    private org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();
        
        String subjectNumber = patientInfo.getSubjectNumber();
        
        Identifier identifier = new Identifier();
        identifier.setId(subjectNumber);
        identifier.setSystem("OpenELIS-Global/SubjectNumber"); // fix hardcode
        List<Identifier> identifierList = new ArrayList<Identifier>();
        identifierList.add(identifier);
        fhirPatient.setIdentifier(identifierList);
        
        HumanName humanName = new HumanName();
        List<HumanName> humanNameList = new ArrayList<HumanName>();
        humanName.setFamily(patientInfo.getLastName());
        humanName.addGiven(patientInfo.getFirstName());
        humanNameList.add(humanName);
        fhirPatient.setName(humanNameList);
        
        
        String strDate = patientInfo.getBirthDateForDisplay();
        Date fhirDate = new Date();
        try {
            fhirDate = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        fhirPatient.setBirthDate(fhirDate);
        
        if (patientInfo.getGender().equalsIgnoreCase("M")) {
            fhirPatient.setGender(AdministrativeGender.MALE);
        } else {
            fhirPatient.setGender(AdministrativeGender.FEMALE);
        }

        return fhirPatient;
    }

    

}

