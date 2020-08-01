package org.openelisglobal.dataexchange.fhir.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
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
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskOutputComponent;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.SampleAddService.SampleTestCollection;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.fhir.service.TaskWorker.TaskResult;
import org.openelisglobal.dataexchange.order.action.DBOrderExistanceChecker;
import org.openelisglobal.dataexchange.order.action.IOrderPersister;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.CodedValueXmit;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
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
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ReferenceClientParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

@Service
public class FhirTransformServiceImpl implements FhirTransformService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
//    protected PatientService patientService = SpringContext.getBean(PatientService.class);
    protected FhirApiWorkflowService fhirApiWorkFlowService = SpringContext.getBean(FhirApiWorkflowService.class);
    protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
    protected ElectronicOrderService electronicOrderService = SpringContext.getBean(ElectronicOrderService.class);
    protected TestService testService = SpringContext.getBean(TestService.class);

    IGenericClient localFhirClient = fhirContext
            .newRestfulGenericClient(fhirApiWorkFlowService.getLocalFhirStorePath());
    org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();
    
    private IStatusService statusService;
    
    private IStatusService getStatusService() {
        if (statusService == null) {
            statusService = SpringContext.getBean(IStatusService.class);
        }
        return statusService;
    }

//    private org.hl7.fhir.r4.model.Patient getPatientWithSameSubjectNumber(Patient remotePatient) {
//        Map<String, List<String>> localSearchParams = new HashMap<>();
//        localSearchParams.put(Patient.SUBJECT_NUMBER,
//                Arrays.asList(remoteStorePath + "|" + remotePatient.getIdElement().getIdPart()));
//
//        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
//        Bundle localBundle = localFhirClient.search()
//                .forResource(org.hl7.fhir.r4.model.Patient.class).whereMap(localSearchParams)
//                .returnBundle(Bundle.class).execute();
//        return (org.hl7.fhir.r4.model.Patient) localBundle.getEntryFirstRep().getResource();
//    }

    @Override
    public List<ElectronicOrder> getFhirOrdersById(String srId) {
        List<ElectronicOrder> eOrders = new ArrayList<>();
        ServiceRequest serviceRequest = new ServiceRequest();
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();
        Task task = new Task();
        
        try {
            Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("_id").exactly().code(srId))
                    .prettyPrint()
                    .execute();

            if (srBundle.getEntry().size() != 0) {
                serviceRequest = (ServiceRequest) srBundle.getEntryFirstRep().getResource();
                
                Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                        .where(new TokenClientParam("_id").exactly().code(serviceRequest.getSubject().getReference().toString()))
                        .prettyPrint().execute();
                
                fhirPatient = new org.hl7.fhir.r4.model.Patient();
                if (pBundle.getEntry().size() != 0) {
                    fhirPatient = (org.hl7.fhir.r4.model.Patient) pBundle.getEntryFirstRep().getResource();
                }
                
                Bundle tBundle = (Bundle) localFhirClient.search().forResource(Task.class)
                        .where(new ReferenceClientParam("based-on").hasId(serviceRequest.getResourceType() + "/" + srId))
                        .prettyPrint().execute();
                
                if (tBundle.getEntry().size() != 0) {
                    task = (Task) tBundle.getEntryFirstRep().getResource();
                }
                
                System.out.println("FhirTransformServiceImpl:getFhirOrdersById:sr: " +
                        serviceRequest.getIdElement().getIdPart() + " patient: " +
                        fhirPatient.getIdElement().getIdPart() + " task: " +
                        task.getIdElement().getIdPart() );
               
            }
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        } 
        
        TaskWorker worker = new TaskWorker(task,
                fhirContext.newJsonParser().encodeResourceToString(task), serviceRequest,
                fhirPatient);

        TaskInterpreter interpreter = SpringContext.getBean(TaskInterpreter.class);
        worker.setInterpreter(interpreter);
        
        worker.setExistanceChecker(SpringContext.getBean(DBOrderExistanceChecker.class));
        
        IOrderPersister persister = SpringContext.getBean(IOrderPersister.class);
        worker.setPersister(persister);
        
        TaskResult taskResult = null;
        taskResult = worker.handleOrderRequest();
        
        
        
        if (taskResult == TaskResult.OK) {
            task.setStatus(TaskStatus.ACCEPTED);
            localFhirClient.update().resource(task).execute();
            
            MessagePatient messagePatient = interpreter.getMessagePatient();
            messagePatient.setExternalId(fhirPatient.getIdElement().getIdPart());
            
            ElectronicOrder eOrder = new ElectronicOrder();
            eOrder.setExternalId(srId);
            eOrder.setData(fhirContext.newJsonParser().encodeResourceToString(task));
            eOrder.setStatusId(getStatusService().getStatusID(ExternalOrderStatus.Entered));
            eOrder.setOrderTimestamp(DateUtil.getNowAsTimestamp());
            eOrder.setSysUserId(persister.getServiceUserId());

            persister.persist(messagePatient, eOrder);
            eOrders.add(eOrder);
        }
        return eOrders;
    }
    
    @Override
    public String CreateFhirFromOESample(TestResultsXmit result, Patient patient) {
        System.out.println("CreateFhirFromOESample:result ");

        String patientGuid = result.getPatientGUID();
        String accessionNumber = result.getAccessionNumber();
        accessionNumber = accessionNumber.substring(0,accessionNumber.indexOf('-')); // disregard test number within set
//        org.openelisglobal.patient.valueholder.Patient patient = patientService.getPatientForGuid(patientGuid);
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        Bundle oResp = null;
        Bundle drResp = null;

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

            Reference subjectRef = new Reference();

            if (pBundle.getEntry().size() == 0) {
//                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                Bundle resp = CreateFhirResource(fhirPatient);
                subjectRef.setReference("Patient/" + resp.getEntryFirstRep().getId());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference("Patient/" + existingPatient.getIdElement().getIdPart());
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
                List<Coding> codingList = new ArrayList<>();
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

//                oOutcome = localFhirClient.create().resource(observation).execute();
                oResp = CreateFhirResource(observation);
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
//                observationReference.setType(oResp.getId().getResourceType());
                observationReference
                        .setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);

                System.out.println(
                        "diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(diagnosticReport));
//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = CreateFhirResource(diagnosticReport);
            }
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }

        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                + fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource())
                + fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource()));
    }

    @Override
    public String CreateFhirFromOESample(PortableOrder pOrder) {

        System.out.println("CreateFhirFromOESample:pOrder:externalId: " + pOrder.getExternalId());

        org.openelisglobal.patient.valueholder.Patient patient = (pOrder.getPatient());
        fhirPatient = CreateFhirPatientFromOEPatient(patient);
        Bundle oBundle = new Bundle();
        Bundle drBundle = new Bundle();
        Bundle srBundle = new Bundle();
        Bundle oResp = null;
        Bundle drResp = null;
        Bundle srResp = null;
        ServiceRequest serviceRequest = new ServiceRequest();

        CodeableConcept codeableConcept = new CodeableConcept();
        Coding coding = new Coding();
        List<Coding> codingList = new ArrayList<>();
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
//                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                oResp = CreateFhirResource(fhirPatient);
                subjectRef.setReference( "Patient/" + oResp.getEntryFirstRep().getId());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent.getResource();
                subjectRef.setReference("Patient/" + existingPatient.getIdElement().getIdPart());
            }

            srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId()))
                    .prettyPrint()
                    .execute();

            if (srBundle.getEntry().size() == 0) {

                serviceRequest.setCode(codeableConcept);
                serviceRequest.setSubject(subjectRef);
//                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
                srResp = CreateFhirResource(serviceRequest);
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

//                    oOutcome = localFhirClient.create().resource(observation).execute();
                oResp = CreateFhirResource(observation);

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
                observationReference
                        .setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);

                Reference serviceRequestReference = new Reference();
//                serviceRequestReference.setType(srOutcome.getId().getResourceType());
                serviceRequestReference
                        .setReference("ServiceRequest/" + srResp.getEntryFirstRep().getId());
                diagnosticReport.addBasedOn(serviceRequestReference);

//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = CreateFhirResource(diagnosticReport);

                return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient) +
                        fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource()) +
                        fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource()) +
                        fhirContext.newJsonParser().encodeResourceToString((srResp == null) ? srBundle.getEntryFirstRep().getResource() : srResp.getEntryFirstRep().getResource()));
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
            System.out.println("serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(srResp.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(srResp.getEntryFirstRep().getResource());
        }
        return returnString;
    }

    @Override
    public String CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result) {
        Bundle oResp = null;
        Bundle drResp = null;

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
        List<ServiceRequest> serviceRequestList = new ArrayList<>();
        for (BundleEntryComponent bundleComponent : tsrBundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
                task = (Task) bundleComponent.getResource();
            }

            if (bundleComponent.hasResource()
                    && ResourceType.ServiceRequest.equals(bundleComponent.getResource().getResourceType())) {

                ServiceRequest serviceRequest = (ServiceRequest) bundleComponent.getResource();
                for(Identifier identifier : serviceRequest.getIdentifier()) {
                    if(identifier.getValue().equals(orderNumber)) {
                        serviceRequestList.add((ServiceRequest) bundleComponent.getResource());
                    }
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

//                oOutcome = localFhirClient.create().resource(observation).execute();
                oResp = CreateFhirResource(observation);

                DiagnosticReport diagnosticReport = new DiagnosticReport();
                diagnosticReport.setId(result.getTest().getCode());
                diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
                diagnosticReport.setBasedOn(serviceRequest.getBasedOn());
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
                diagnosticReport.setCode(serviceRequest.getCode());
                diagnosticReport.setSubject(subjectRef);

                Reference observationReference = new Reference();
//                observationReference.setType(oOutcome.getId().getResourceType());
                observationReference
                        .setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);
//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = CreateFhirResource(diagnosticReport);

                Reference diagnosticReportReference = new Reference();
//                diagnosticReportReference.setType(drOutcome.getId().getResourceType());
                diagnosticReportReference.setReference("DiagnosticReport/" + drResp.getEntryFirstRep().getId());

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
                fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource()) +
                fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource()));
    }

    @Override
    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
            PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request) {

        System.out.println("CreateFhirFromOESample:add Order:accession#: " + updateData.getAccessionNumber());

        fhirPatient = CreateFhirPatientFromOEPatient(patientInfo);

        Bundle srBundle = new Bundle();
        Bundle pResp = new Bundle();
        Bundle srResp = new Bundle();
        ServiceRequest serviceRequest = new ServiceRequest();
        CodeableConcept codeableConcept = new CodeableConcept();
        List<Coding> codingList = new ArrayList<>();

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
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdentifierFirstRep().getValue()))
                    .prettyPrint().execute();

            Reference subjectRef = new Reference();

            if (pBundle.getEntry().size() == 0) {
//                pOutcome = localFhirClient.create().resource(fhirPatient).execute();
                pResp = CreateFhirResource(fhirPatient);
                System.out.println("pResp:" + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(pResp));
                subjectRef.setReference("Patient/" + pResp.getEntryFirstRep().getResponse().getLocation());
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
//                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
                srResp = CreateFhirResource(serviceRequest);
                System.out.println("srResp:" + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(srResp));
            }
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bundle CreateFhirResource(Resource resource) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        Identifier identifier = new Identifier();
        String resourceType = resource.getResourceType().toString();
//        resource.setId(IdType.newRandomUuid());
//        resource.setIdElement(IdType.newRandomUuid());
        identifier.setSystem(resource.getId());
        resource.castToIdentifier(identifier);

        try {

            bundle.addEntry()
                    .setFullUrl(resource.getIdElement().getValue())
                    .setResource(resource).getRequest().setUrl(resourceType + "/" + UUID.randomUUID()).setMethod(Bundle.HTTPVerb.PUT);

            System.out.println("CreateFhirResource: " + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

            resp = localFhirClient.transaction().withBundle(bundle).execute();
        } catch (Exception e) {
            System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public Bundle UpdateFhirResource(Resource resource) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        Identifier identifier = new Identifier();

        String resourceType = resource.getResourceType().toString();

        switch (resourceType) {
        case "Patient":
            org.hl7.fhir.r4.model.Patient fhirPatient = (org.hl7.fhir.r4.model.Patient) resource;
//        resource.setId(IdType.newRandomUuid());
//        resource.setIdElement(IdType.newRandomUuid());
//        identifier.setSystem(resource.getId());
//        resource.castToIdentifier(identifier);

            try {
                // check for patient existence
                System.out.println("UpdateFhirResource:resourceId: "
                        + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(fhirPatient));
                System.out.println("UpdateFhirResource:resourceId: "
                        + fhirPatient.getIdentifier().get(0).getValue()
                        );
                Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                        .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdentifier().get(0).getValue()))
                        .prettyPrint().execute();

                System.out.println("UpdateFhirResource:pBundle: "
                        + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(pBundle));

                if (pBundle.getEntry().size() == 0) {
                    bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                            .setUrl("Patient/" + UUID.randomUUID()).setMethod(Bundle.HTTPVerb.PUT);
                    System.out.println("Update<Create>FhirResource: "
                            + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
                    resp = localFhirClient.transaction().withBundle(bundle).execute();
                } else {
                    bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                            .setUrl("Patient/" + pBundle.getEntryFirstRep().getResource().getIdElement().getIdPart()).setMethod(Bundle.HTTPVerb.PUT);
                    System.out.println("UpdateFhirResource: "
                            + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
                    resp = localFhirClient.transaction().withBundle(bundle).execute();
                }
            } catch (Exception e) {
                System.out.println("FhirTransformServiceImpl:Transform exception: " + e.toString());
                e.printStackTrace();
            }
            break;
        default:
            System.out.println("UpdateFhirResource:Unknown FHIR resource:");
        }
        return resp;
    }

    @Override
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient) {
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
        List<Identifier> identifierList = new ArrayList<>();
        identifierList.add(identifier);
        fhirPatient.setIdentifier(identifierList);

        HumanName humanName = new HumanName();
        List<HumanName> humanNameList = new ArrayList<>();
        humanName.setFamily(patient.getPerson().getLastName());
        humanName.addGiven(patient.getPerson().getFirstName());
        humanNameList.add(humanName);
        fhirPatient.setName(humanNameList);

        fhirPatient.setBirthDate(patient.getBirthDate());
        if (GenericValidator.isBlankOrNull(patient.getGender())) {
            fhirPatient.setGender(AdministrativeGender.UNKNOWN);
        } else if (patient.getGender().equalsIgnoreCase("M")) {
            fhirPatient.setGender(AdministrativeGender.MALE);
        } else {
            fhirPatient.setGender(AdministrativeGender.FEMALE);
        }

        return fhirPatient;
    }

    @Override
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();

        String subjectNumber = patientInfo.getSubjectNumber();

        Identifier identifier = new Identifier();
        identifier.setValue(subjectNumber);
        identifier.setSystem("OpenELIS-Global/SubjectNumber"); // fix hardcode
        List<Identifier> identifierList = new ArrayList<>();
        identifierList.add(identifier);
        fhirPatient.setIdentifier(identifierList);

        HumanName humanName = new HumanName();
        List<HumanName> humanNameList = new ArrayList<>();
        humanName.setFamily(patientInfo.getLastName());
        humanName.addGiven(patientInfo.getFirstName());
        humanNameList.add(humanName);
        fhirPatient.setName(humanNameList);


        String strDate = patientInfo.getBirthDateForDisplay();
        Date fhirDate = new Date();
        try {
            fhirDate = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
            fhirPatient.setBirthDate(fhirDate);
        } catch (ParseException e) {
            LogEvent.logError("patient date unparseable", e);
        }

        if (GenericValidator.isBlankOrNull(patientInfo.getGender())) {
            fhirPatient.setGender(AdministrativeGender.UNKNOWN);
        } else if (patientInfo.getGender().equalsIgnoreCase("M")) {
            fhirPatient.setGender(AdministrativeGender.MALE);
        } else {
            fhirPatient.setGender(AdministrativeGender.FEMALE);
        }

        return fhirPatient;
    }
}

