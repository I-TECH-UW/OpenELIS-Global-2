package org.openelisglobal.dataexchange.fhir.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Endpoint;
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
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.SampleAddService.SampleTestCollection;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.service.TaskWorker.TaskResult;
import org.openelisglobal.dataexchange.order.action.DBOrderExistanceChecker;
import org.openelisglobal.dataexchange.order.action.IOrderPersister;
import org.openelisglobal.dataexchange.order.action.MessagePatient;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.resultreporting.beans.CodedValueXmit;
import org.openelisglobal.dataexchange.resultreporting.beans.TestResultsXmit;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.organization.valueholder.OrganizationType;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ReferenceClientParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

@Service
public class FhirTransformServiceImpl implements FhirTransformService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    protected FhirApiWorkflowService fhirApiWorkFlowService = SpringContext.getBean(FhirApiWorkflowService.class);
    protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
    protected ElectronicOrderService electronicOrderService = SpringContext.getBean(ElectronicOrderService.class);
    protected TestService testService = SpringContext.getBean(TestService.class);

    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private FhirUtil fhirUtil;
    private FhirConfig fhirConfig = SpringContext.getBean(FhirConfig.class);
    IGenericClient localFhirClient = fhirUtil.getFhirClient(fhirConfig.getLocalFhirStorePath());

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
//        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
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
                    .where(new TokenClientParam("_id").exactly().code(srId)).prettyPrint().execute();

            if (srBundle.getEntry().size() != 0) {
                serviceRequest = (ServiceRequest) srBundle.getEntryFirstRep().getResource();

                Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                        .where(new TokenClientParam("_id").exactly()
                                .code(serviceRequest.getSubject().getReference().toString()))
                        .prettyPrint().execute();

                fhirPatient = new org.hl7.fhir.r4.model.Patient();
                if (pBundle.getEntry().size() != 0) {
                    fhirPatient = (org.hl7.fhir.r4.model.Patient) pBundle.getEntryFirstRep().getResource();
                }

                Bundle tBundle = (Bundle) localFhirClient.search().forResource(Task.class).where(
                        new ReferenceClientParam("based-on").hasId(serviceRequest.getResourceType() + "/" + srId))
                        .prettyPrint().execute();

                if (tBundle.getEntry().size() != 0) {
                    task = (Task) tBundle.getEntryFirstRep().getResource();
                }

                LogEvent.logDebug(this.getClass().getName(), "getFhirOrdersById",
                        "FhirTransformServiceImpl:getFhirOrdersById:sr: " + serviceRequest.getIdElement().getIdPart()
                                + " patient: " + fhirPatient.getIdElement().getIdPart() + " task: "
                                + task.getIdElement().getIdPart());

            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            LogEvent.logDebug(this.getClass().getName(), "getFhirOrdersById",
                    "FhirTransformServiceImpl:Transform exception: " + e.toString());
        }

        TaskWorker worker = new TaskWorker(task, fhirContext.newJsonParser().encodeResourceToString(task),
                serviceRequest, fhirPatient);

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
        LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample", "CreateFhirFromOESample:result ");

        String patientGuid = result.getPatientGUID();
        String accessionNumber = result.getAccessionNumber();
        accessionNumber = accessionNumber.substring(0, accessionNumber.indexOf('-')); // disregard test number within
                                                                                      // set
//        org.openelisglobal.patient.valueholder.Patient patient = patientService.getPatientForGuid(patientGuid);
        org.hl7.fhir.r4.model.Patient fhirPatient = CreateFhirPatientFromOEPatient(patient);
        Bundle oResp = null;
        Bundle drResp = null;

        try {

            Reference basedOnRef = new Reference();
            Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(accessionNumber)).prettyPrint().execute();

            if (srBundle.getEntry().size() != 0) {
                BundleEntryComponent bundleComponent = srBundle.getEntryFirstRep();
                ServiceRequest existingServiceRequest = (ServiceRequest) bundleComponent.getResource();
                basedOnRef.setReference(existingServiceRequest.getResourceType() + "/"
                        + existingServiceRequest.getIdElement().getIdPart());
            }

            // check for patient existence
            Bundle pBundle = (Bundle) localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .where(new TokenClientParam("identifier").exactly().code(fhirPatient.getIdElement().getIdPart()))
                    .prettyPrint().execute();

            Reference subjectRef = new Reference();

            if (pBundle.getEntry().size() == 0) {
//                oOutcome = localFhirClient.create().resource(fhirPatient).execute();
                Bundle resp = fhirPersistanceService.createFhirResourceInFhirStore(fhirPatient);
                subjectRef.setReference(resp.getEntryFirstRep().getResponse().getLocation());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent
                        .getResource();
                subjectRef = createReferenceFor(existingPatient);
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
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "existing observation: " + fhirContext.newJsonParser()
                                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource()));
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "existing diagnosticReport: " + fhirContext.newJsonParser()
                                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource()));

                    return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource())
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
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
                labCoding.setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber");
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

                LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                        "observation: " + fhirContext.newJsonParser().encodeResourceToString(observation));

//                oOutcome = localFhirClient.create().resource(observation).execute();
                oResp = fhirPersistanceService.createFhirResourceInFhirStore(observation);
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
                observationReference.setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);

                LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                        "diagnosticReport: " + fhirContext.newJsonParser().encodeResourceToString(diagnosticReport));
//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = fhirPersistanceService.createFhirResourceInFhirStore(diagnosticReport);
            }
        } catch (Exception e) {
            LogEvent.logError(e);
        }

        return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                + fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource())
                + fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource()));
    }

    @Override
    public String CreateFhirFromOESample(PortableOrder pOrder) {

        LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                "CreateFhirFromOESample:pOrder:externalId: " + pOrder.getExternalId());

        org.openelisglobal.patient.valueholder.Patient patient = (pOrder.getPatient());
        org.hl7.fhir.r4.model.Patient fhirPatient = CreateFhirPatientFromOEPatient(patient);
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

//        Identifier identifier = new Identifier();
//        identifier.setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber");
//        identifier.setValue(pOrder.getExternalId());

        Coding labCoding = new Coding();
        labCoding.setCode(pOrder.getExternalId());
        labCoding.setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber");
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
                oResp = fhirPersistanceService.createFhirResourceInFhirStore(fhirPatient);
//                subjectRef.setReference("Patient/" + oResp.getEntryFirstRep().getId());
                subjectRef.setReference(oResp.getEntryFirstRep().getResponse().getLocation());
            } else {
                BundleEntryComponent bundleComponent = pBundle.getEntryFirstRep();
                org.hl7.fhir.r4.model.Patient existingPatient = (org.hl7.fhir.r4.model.Patient) bundleComponent
                        .getResource();
                subjectRef = this.createReferenceFor(existingPatient);
            }

            srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId())).prettyPrint().execute();

            if (srBundle.getEntry().size() == 0) {

                serviceRequest.setCode(codeableConcept);
                serviceRequest.setSubject(subjectRef);
//                serviceRequest.addIdentifier(identifier);
//                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
                srResp = fhirPersistanceService.createFhirResourceInFhirStore(serviceRequest);
            }

            if (pOrder.getResultValue() != null && pOrder.getUomName() != null) {

                oBundle = (Bundle) localFhirClient.search().forResource(Observation.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId())).prettyPrint()
                        .execute();

                drBundle = (Bundle) localFhirClient.search().forResource(DiagnosticReport.class)
                        .where(new TokenClientParam("code").exactly().code(pOrder.getExternalId())).prettyPrint()
                        .execute();

                if (oBundle.getEntry().size() != 0 && drBundle.getEntry().size() != 0) {
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "existing observation: " + fhirContext.newJsonParser()
                                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource()));
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "existing diagnosticReport: " + fhirContext.newJsonParser()
                                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
                    LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                            "existing serviceRequest: " + fhirContext.newJsonParser()
                                    .encodeResourceToString(srBundle.getEntryFirstRep().getResource()));

                    return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource())
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource())
                            + fhirContext.newJsonParser()
                                    .encodeResourceToString(srBundle.getEntryFirstRep().getResource()));
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
                oResp = fhirPersistanceService.createFhirResourceInFhirStore(observation);

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
                observationReference.setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);

                Reference serviceRequestReference = new Reference();
//                serviceRequestReference.setType(srOutcome.getId().getResourceType());
                serviceRequestReference.setReference("ServiceRequest/" + srResp.getEntryFirstRep().getId());
                diagnosticReport.addBasedOn(serviceRequestReference);

//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = fhirPersistanceService.createFhirResourceInFhirStore(diagnosticReport);

                return (fhirContext.newJsonParser().encodeResourceToString(fhirPatient)
                        + fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource())
                        + fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource())
                        + fhirContext.newJsonParser()
                                .encodeResourceToString((srResp == null) ? srBundle.getEntryFirstRep().getResource()
                                        : srResp.getEntryFirstRep().getResource()));
            }
        } catch (Exception e) {
            LogEvent.logError(e);
        }

        // check for no results therefore no ob or dr
        String returnString = new String();

        LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                "patient: " + fhirContext.newJsonParser().encodeResourceToString(fhirPatient));
        returnString += fhirContext.newJsonParser().encodeResourceToString(fhirPatient);

        if (oBundle.getEntry().size() != 0) {
            LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample", "observation: "
                    + fhirContext.newJsonParser().encodeResourceToString(oBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser()
                    .encodeResourceToString(oBundle.getEntryFirstRep().getResource());
        }
        if (drBundle.getEntry().size() != 0) {
            LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample", "diagnosticReport: "
                    + fhirContext.newJsonParser().encodeResourceToString(drBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser()
                    .encodeResourceToString(drBundle.getEntryFirstRep().getResource());
        }
        if (srBundle.getEntry().size() != 0) {
            LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample", "serviceRequest: "
                    + fhirContext.newJsonParser().encodeResourceToString(srBundle.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser()
                    .encodeResourceToString(srBundle.getEntryFirstRep().getResource());
        } else {
            LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample", "serviceRequest: "
                    + fhirContext.newJsonParser().encodeResourceToString(srResp.getEntryFirstRep().getResource()));
            returnString += fhirContext.newJsonParser().encodeResourceToString(srResp.getEntryFirstRep().getResource());
        }
        return returnString;
    }

    @Override
    public void CreateFhirFromOESample(ElectronicOrder eOrder, TestResultsXmit result) {
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
                .include(new Include("Task:based-on")).prettyPrint().execute();

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
                for (Identifier identifier : serviceRequest.getIdentifier()) {
                    if (identifier.getValue().equals(orderNumber)) {
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
                    .prettyPrint().execute();

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
                Reference subjectRef = this.createReferenceFor(patient);
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
                oResp = fhirPersistanceService.createFhirResourceInFhirStore(observation);

                DiagnosticReport diagnosticReport = new DiagnosticReport();
                diagnosticReport.setId(result.getTest().getCode());
                diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
                diagnosticReport.setBasedOn(serviceRequest.getBasedOn());
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
                diagnosticReport.setCode(serviceRequest.getCode());
                diagnosticReport.setSubject(subjectRef);

                Reference observationReference = new Reference();
//                observationReference.setType(oOutcome.getId().getResourceType());
                observationReference.setReference("Observation/" + oResp.getEntryFirstRep().getId());
                diagnosticReport.addResult(observationReference);
//                drOutcome = localFhirClient.create().resource(diagnosticReport).execute();
                drResp = fhirPersistanceService.createFhirResourceInFhirStore(diagnosticReport);

                Reference diagnosticReportReference = new Reference();
//                diagnosticReportReference.setType(drOutcome.getId().getResourceType());
                diagnosticReportReference.setReference("DiagnosticReport/" + drResp.getEntryFirstRep().getId());

                TaskOutputComponent theOutputComponent = new TaskOutputComponent();
                theOutputComponent.setValue(diagnosticReportReference);
                task.addOutput(theOutputComponent);
                task.setStatus(TaskStatus.COMPLETED);

                localFhirClient.update().resource(task).execute();

            } catch (Exception e) {
                LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                        "Result update exception: " + e.getStackTrace());
            }
        }

//        return (fhirContext.newJsonParser().encodeResourceToString(patient)
//                + fhirContext.newJsonParser().encodeResourceToString(oResp.getEntryFirstRep().getResource())
//                + fhirContext.newJsonParser().encodeResourceToString(drResp.getEntryFirstRep().getResource()));
    }

    @Override
    public String CreateFhirFromOESample(SamplePatientUpdateData updateData, PatientManagementInfo patientInfo) {

        LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                "CreateFhirFromOESample:add Order:accession#: " + updateData.getAccessionNumber());
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
        labCoding.setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber");
        codingList.add(labCoding);
        codeableConcept.setCoding(codingList);

        try {
            Reference subjectRef = new Reference();
            org.hl7.fhir.r4.model.Patient fhirPatient = getFhirPatient(patientInfo);
            // check for patient existence
            if (fhirPatient == null) {
                fhirPatient = CreateFhirPatientFromOEPatient(patientInfo);
//                pOutcome = localFhirClient.create().resource(fhirPatient).execute();
                pResp = fhirPersistanceService.createFhirResourceInFhirStore(fhirPatient);
                patientInfo.setGuid(getIdFromLocation(pResp.getEntryFirstRep().getResponse().getLocation()));
                LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                        "pResp:" + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(pResp));
//                subjectRef.setReference("Patient/" + pResp.getEntryFirstRep().getResponse().getLocation());
                subjectRef.setReference(pResp.getEntryFirstRep().getResponse().getLocation());
            } else {
                subjectRef = createReferenceFor(fhirPatient);
                patientInfo.setGuid(fhirPatient.getIdElement().getId());
            }

            srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("code").exactly().code(updateData.getAccessionNumber())).prettyPrint()
                    .execute();

            if (srBundle.getEntry().size() == 0) {
                serviceRequest.setCode(codeableConcept);
                serviceRequest.setSubject(subjectRef);
//                srOutcome = localFhirClient.create().resource(serviceRequest).execute();
                srResp = fhirPersistanceService.createFhirResourceInFhirStore(serviceRequest);
                LogEvent.logDebug(this.getClass().getName(), "CreateFhirFromOESample",
                        "srResp:" + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(srResp));
            }
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return null;
    }

    @Override
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(Patient patient) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();
//        List<PatientIdentity> patientIdentityList = patientIdentityService
//                .getPatientIdentitiesForPatient(patient.getId());
        // TODO restructure these beans so we no longer have a circular dependency.
        // workaround is to retreive bean at runtime.
        String subjectNumber = SpringContext.getBean(PatientService.class).getSubjectNumber(patient);
        String nationalId = SpringContext.getBean(PatientService.class).getNationalId(patient);
        String guid = SpringContext.getBean(PatientService.class).getGUID(patient);
        String stNumber = SpringContext.getBean(PatientService.class).getSTNumber(patient);

//        for (PatientIdentity patientIdentity : patientIdentityList) {
//            if (patientIdentity.getIdentityTypeId().equalsIgnoreCase("9")) { // fix hardcode Subject Number
//                subjectNumber = patientIdentity.getIdentityData();
//            }
//        }

//        identifier.setId(subjectNumber);
//        identifier.setSystem("OpenELIS-Global/SubjectNumber"); // fix hardcode
        fhirPatient.setIdentifier(createIdentifiers(subjectNumber, nationalId, stNumber, guid));

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

    private List<Identifier> createIdentifiers(String subjectNumber, String nationalId, String stNumber, String guid) {
        List<Identifier> identifierList = new ArrayList<>();
        if (!GenericValidator.isBlankOrNull(subjectNumber)) {
            identifierList.add(createIdentifier(fhirConfig.getOeFhirSystem() + "/pat_subjectNumber", subjectNumber));
        }
        if (!GenericValidator.isBlankOrNull(nationalId)) {
            identifierList.add(createIdentifier(fhirConfig.getOeFhirSystem() + "/pat_nationalId", nationalId));
        }
        if (!GenericValidator.isBlankOrNull(stNumber)) {
            identifierList.add(createIdentifier(fhirConfig.getOeFhirSystem() + "/pat_stNumber", stNumber));
        }
        if (!GenericValidator.isBlankOrNull(guid)) {
            identifierList.add(createIdentifier(fhirConfig.getOeFhirSystem() + "/pat_guid", guid));
        }
        return identifierList;
    }

    private Identifier createIdentifier(String system, String value) {
        Identifier identifier = new Identifier();
        identifier.setSystem(system);
        identifier.setValue(value);
        return identifier;
    }

    @Override
    public org.hl7.fhir.r4.model.Patient CreateFhirPatientFromOEPatient(PatientManagementInfo patientInfo) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();

//        String subjectNumber = patientInfo.getSubjectNumber();
//
//        Identifier identifier = new Identifier();
//        identifier.setValue(subjectNumber);
//        identifier.setSystem("OpenELIS-Global/SubjectNumber"); // fix hardcode
//        List<Identifier> identifierList = new ArrayList<>();
//        identifierList.add(identifier);
        fhirPatient.setIdentifier(createIdentifiers(patientInfo.getSubjectNumber(), patientInfo.getNationalId(),
                patientInfo.getSTnumber(), patientInfo.getGuid()));

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

    @Override
    public org.hl7.fhir.r4.model.Organization organizationToFhirOrganization(Organization organization) {
        org.hl7.fhir.r4.model.Organization fhirOrganization = new org.hl7.fhir.r4.model.Organization();
        fhirOrganization.setId(organization.getId());
        fhirOrganization.setName(organization.getOrganizationName());
        this.setFhirIdentifiers(fhirOrganization, organization);
        this.setFhirAddressInfo(fhirOrganization, organization);
        this.setFhirOrganizationTypes(fhirOrganization, organization);

//        if (!GenericValidator.isBlankOrNull(organization.getInternetAddress())) {
//            Endpoint endpoint = this.setFhirConnectionInfo(fhirOrganization, organization);
//        }
//        if (organization.getOrganization() != null) {
//            org.hl7.fhir.r4.model.Organization parentFhirOrganization = this.setFhirParentOrg(fhirOrganization,
//                    organization);
//        }
        return fhirOrganization;
    }

    @Override
    public Organization fhirOrganizationToOrganization(org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client) {
        Organization organization = new Organization();
        organization.setOrganizationName(fhirOrganization.getName());
        organization.setIsActive(IActionConstants.YES);
        setIdentifiers(organization, fhirOrganization);
        setAddressInfo(organization, fhirOrganization);
        setOrganizationTypes(organization, fhirOrganization, client);
        if (fhirOrganization.getEndpoint() != null && fhirOrganization.getEndpoint().size() > 0) {
            setConnectionInfo(organization, fhirOrganization, client);
        }
        if (fhirOrganization.getPartOf() != null
                && !GenericValidator.isBlankOrNull(fhirOrganization.getPartOf().getId())) {
            setParentOrg(organization, fhirOrganization, client);
        }
        organization.setMlsLabFlag(IActionConstants.NO);
        organization.setMlsSentinelLabFlag(IActionConstants.NO);

        return organization;
    }

    private void setParentOrg(Organization organization, org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client) {
        org.hl7.fhir.r4.model.Organization parentOrg = client.read().resource(org.hl7.fhir.r4.model.Organization.class)
                .withId(fhirOrganization.getPartOf().getReferenceElement().getIdPart()).execute();
        if (parentOrg != null) {
            organization.setOrganization(fhirOrganizationToOrganization(parentOrg, client));
        }
    }

    private void setIdentifiers(Organization organization, org.hl7.fhir.r4.model.Organization fhirOrganization) {
        for (Identifier identifier : fhirOrganization.getIdentifier()) {
            if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_cliaNum")) {
                organization.setCliaNum(identifier.getValue());
            } else if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_shortName")) {
                organization.setShortName(identifier.getValue());
            } else if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_code")) {
                organization.setCode(identifier.getValue());
            }
        }
    }

    private void setFhirIdentifiers(org.hl7.fhir.r4.model.Organization fhirOrganization, Organization organization) {
        if (!GenericValidator.isBlankOrNull(organization.getCliaNum())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_cliaNum")
                    .setValue(organization.getCliaNum()));
        }
        if (!GenericValidator.isBlankOrNull(organization.getShortName())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_shortName")
                    .setValue(organization.getShortName()));
        }
        if (!GenericValidator.isBlankOrNull(organization.getCode())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_code")
                    .setValue(organization.getCode()));
        }
    }

    private void setOrganizationTypes(Organization organization, org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client) {
        Set<OrganizationType> orgTypes = new HashSet<>();
        OrganizationType orgType = null;
        for (CodeableConcept type : fhirOrganization.getType()) {
            for (Coding coding : type.getCoding()) {
                if (coding.getSystem() != null
                        && coding.getSystem().equals(fhirConfig.getOeFhirSystem() + "/orgType")) {
                    orgType = new OrganizationType();
                    orgType.setName(coding.getCode());
                    orgType.setDescription(type.getText());
                    orgType.setOrganizations(new HashSet<>());
                    orgType.getOrganizations().add(organization);
                    orgTypes.add(orgType);
                }
            }
        }
        organization.setOrganizationTypes(orgTypes);
    }

    private void setFhirOrganizationTypes(org.hl7.fhir.r4.model.Organization fhirOrganization,
            Organization organization) {
        Set<OrganizationType> orgTypes = organization.getOrganizationTypes();
        for (OrganizationType orgType : orgTypes) {
            fhirOrganization.addType(new CodeableConcept() //
                    .setText(orgType.getDescription()) //
                    .addCoding(new Coding() //
                            .setSystem(fhirConfig.getOeFhirSystem() + "/orgType") //
                            .setCode(orgType.getName())));
        }
    }

    private void setConnectionInfo(Organization organization, org.hl7.fhir.r4.model.Organization fhirOrganization,
            IGenericClient client) {
        Endpoint endpoint = client.read().resource(Endpoint.class)
                .withId(fhirOrganization.getEndpointFirstRep().getReferenceElement().getIdPart()).execute();
        if (endpoint != null) {
            organization.setInternetAddress(endpoint.getAddress());
        }

    }

    private void setAddressInfo(Organization organization, org.hl7.fhir.r4.model.Organization fhirOrganization) {
        organization.setStreetAddress(fhirOrganization.getAddressFirstRep().getLine().stream()
                .map(e -> e.asStringValue()).collect(Collectors.joining("\\n")));
        organization.setCity(fhirOrganization.getAddressFirstRep().getCity());
        organization.setState(fhirOrganization.getAddressFirstRep().getState());
        organization.setZipCode(fhirOrganization.getAddressFirstRep().getPostalCode());
    }

    private void setFhirAddressInfo(org.hl7.fhir.r4.model.Organization fhirOrganization, Organization organization) {
        if (!GenericValidator.isBlankOrNull(organization.getStreetAddress())) {
            fhirOrganization.getAddressFirstRep().addLine(organization.getStreetAddress());
        }
        if (!GenericValidator.isBlankOrNull(organization.getCity())) {
            fhirOrganization.getAddressFirstRep().setCity(organization.getCity());
        }
        if (!GenericValidator.isBlankOrNull(organization.getState())) {
            fhirOrganization.getAddressFirstRep().setState(organization.getState());
        }
        if (!GenericValidator.isBlankOrNull(organization.getZipCode())) {
            fhirOrganization.getAddressFirstRep().setPostalCode(organization.getZipCode());
        }
    }

    @Override
    public Reference createReferenceFor(Resource resource) {
        Reference reference = new Reference(resource);
        reference.setReference(resource.getResourceType() + "/" + resource.getIdElement().getIdPart());
        return reference;
    }

    public org.hl7.fhir.r4.model.Patient getFhirPatient(Map<String, String> patientIds) {
        Bundle bundle;
        if (patientIds.containsKey("GUID")) {
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class)
                    .where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly()
                            .systemAndCode(fhirConfig.getOeFhirSystem() + "/pat_guid", patientIds.get("GUID")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
            // legacy check for previous identifier system that was used
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class).where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly()
                            .systemAndCode("OpenELIS-Global/SubjectNumber", patientIds.get("SubjectNumber")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
        }
        if (patientIds.containsKey("SubjectNumber")) {
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class)
                    .where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly().systemAndCode(
                            fhirConfig.getOeFhirSystem() + "/pat_subjectNumber", patientIds.get("SubjectNumber")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
            // legacy check for previous identifier system that was used
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class).where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly()
                            .systemAndCode("OpenELIS-Global/SubjectNumber", patientIds.get("SubjectNumber")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
        }
        if (patientIds.containsKey("NationalId")) {
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class).where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly().systemAndCode(
                            fhirConfig.getOeFhirSystem() + "/pat_nationalId", patientIds.get("NationalId")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
            // legacy check for previous identifier system that was used
            bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                    .returnBundle(Bundle.class).where(org.hl7.fhir.r4.model.Patient.IDENTIFIER.exactly()
                            .systemAndCode("OpenELIS-Global/NationalId", patientIds.get("NationalId")))
                    .execute();
            if (bundle.hasEntry()) {
                return (org.hl7.fhir.r4.model.Patient) bundle.getEntryFirstRep().getResource();
            }
        }
        return null;
    }

    @Override
    public org.hl7.fhir.r4.model.Organization getFhirOrganization(Organization organization) {
        Bundle bundle = localFhirClient.search().forResource(org.hl7.fhir.r4.model.Organization.class)
                .returnBundle(Bundle.class).where(org.hl7.fhir.r4.model.Organization.NAME.matchesExactly()
                        .value(organization.getOrganizationName()))
                .execute();
        if (bundle.hasEntry()) {
            return (org.hl7.fhir.r4.model.Organization) bundle.getEntryFirstRep().getResource();
        }
        return null;
    }

    @Override
    public org.hl7.fhir.r4.model.Patient getFhirPatient(PatientManagementInfo patientInfo) {
        Map<String, String> patientIds = new HashMap<>();
        // these two ids are not always unique, so it is best to ignore them
//        if (!GenericValidator.isBlankOrNull(patientInfo.getSubjectNumber())) {
//            patientIds.put("SubjectNumber", patientInfo.getSubjectNumber());
//        }
//        if (!GenericValidator.isBlankOrNull(patientInfo.getNationalId())) {
//            patientIds.put("NationalId", patientInfo.getNationalId());
//        }
        if (!GenericValidator.isBlankOrNull(patientInfo.getGuid())) {
            patientIds.put("GUID", patientInfo.getGuid());
        }
        return getFhirPatient(patientIds);
    }

    @Override
    public org.hl7.fhir.r4.model.Patient getFhirPatientOrCreate(PatientManagementInfo patientInfo) {
        org.hl7.fhir.r4.model.Patient fhirPatient = getFhirPatient(patientInfo);
        if (fhirPatient == null) {
            fhirPatient = this.CreateFhirPatientFromOEPatient(patientInfo);
            fhirPatient.setId(UUID.randomUUID().toString());
        }
        return fhirPatient;
    }

    @Override
    public String getIdFromLocation(String location) {
        String id = location.substring(location.indexOf("/") + 1);
        while (id.lastIndexOf("/") > 0) {
            id = id.substring(0, id.lastIndexOf("/"));
        }
        return id;
    }
}
