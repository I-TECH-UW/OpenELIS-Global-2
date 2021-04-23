package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IDomainResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Bundle.HTTPVerb;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestStatus;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceServiceImpl.FhirOperations;
import org.openelisglobal.dataexchange.fhir.service.TaskWorker.TaskResult;
import org.openelisglobal.dataexchange.order.action.DBOrderExistanceChecker;
import org.openelisglobal.dataexchange.order.action.IOrderPersister;
import org.openelisglobal.referral.fhir.service.FhirReferralService;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;

@Service
public class FhirApiWorkFlowServiceImpl implements FhirApiWorkflowService {

    @Autowired
    private FhirContext fhirContext;
    @Autowired
    private FhirConfig fhirConfig;
    @Autowired
    private FhirUtil fhirUtil;
    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private FhirReferralService fhirReferralService;

    @Value("${org.openelisglobal.fhirstore.uri}")
    private String localFhirStorePath;
    @Value("${org.openelisglobal.remote.source.uri}")
    private String[] remoteStorePaths;
    @Value("${org.openelisglobal.remote.source.updateStatus}")
    private Optional<Boolean> remoteStoreUpdateStatus;
    @Value("${org.openelisglobal.remote.source.identifier:}#{T(java.util.Collections).emptyList()}")
    private List<String> remoteStoreIdentifier;

    @Scheduled(initialDelay = 10 * 1000, fixedRate = 2 * 60 * 1000)
    @Override
    public void pollForRemoteTasks() {
        processWorkflow(ResourceType.Task);
    }

    @Override
    @Async
    public void processWorkflow(ResourceType resourceType) {
        for (String remoteStorePath : remoteStorePaths) {
            switch (resourceType) {
            case Task:
                try {
                    beginTaskImportOrderPath(remoteStorePath);
                } catch (RuntimeException e) {
                    LogEvent.logError(this.getClass().getName(), "processWorkflow",
                            "could not process Task workflow using remote address: " + remoteStorePath);
                    LogEvent.logError(this.getClass().getName(), "processWorkflow", e.getMessage());
                }
                try {
                    beginTaskCheckIfAcceptedPath(remoteStorePath);
                } catch (RuntimeException e) {
                    LogEvent.logError(this.getClass().getName(), "processWorkflow",
                            "could not process Task workflow using remote address: " + remoteStorePath);
                    LogEvent.logError(this.getClass().getName(), "processWorkflow", e.getMessage());
                } catch (FhirLocalPersistingException e) {
                    LogEvent.logError(this.getClass().getName(), "processWorkflow",
                            "could not process Task workflow using remote address: " + remoteStorePath);
                    LogEvent.logError(this.getClass().getName(), "processWorkflow", e.getMessage());
                }
                try {
                    beginTaskImportResultsPath(remoteStorePath);
                } catch (RuntimeException e) {
                    LogEvent.logError(this.getClass().getName(), "processWorkflow",
                            "could not process Task workflow using remote address: " + remoteStorePath);
                    LogEvent.logError(this.getClass().getName(), "processWorkflow", e.getMessage());
                }
            default:
            }
        }
    }

    private void beginTaskCheckIfAcceptedPath(String remoteStorePath) throws FhirLocalPersistingException {
        if (remoteStoreIdentifier.isEmpty()) {
            return;
        }

        Map<String, Resource> updateResources = new HashMap<>();

        IGenericClient sourceFhirClient = fhirUtil.getFhirClient(remoteStorePath);
        Bundle originalTasksBundle = sourceFhirClient.search()//
                .forResource(Task.class)//
                .returnBundle(Bundle.class)//
//                .revInclude(Task.INCLUDE_BASED_ON)//
                .where(Task.STATUS.exactly().code(TaskStatus.REQUESTED.toCode()))//
                .where(Task.REQUESTER.hasAnyOfIds(remoteStoreIdentifier))//
                .execute();
        Map<String, Task> originalTasksById = new HashMap<>();
        for (BundleEntryComponent bundleEntry : originalTasksBundle.getEntry()) {
            if (bundleEntry.hasResource() && bundleEntry.getResource().getResourceType().equals(ResourceType.Task)) {
                Task originalTask = (Task) bundleEntry.getResource();
                originalTasksById.put(originalTask.getIdElement().getIdPart(), originalTask);
            }
        }
        if (originalTasksById.size() <= 0) {
            return;
        }

        Bundle tasksBasedOnOriginalTasksBundle = sourceFhirClient.search()//
                .forResource(Task.class)//
                .returnBundle(Bundle.class)//
//                .revInclude(Task.INCLUDE_BASED_ON)//
                .where(Task.BASED_ON.hasAnyOfIds(originalTasksById.keySet()))
                .where(Task.STATUS.exactly().code(TaskStatus.ACCEPTED.toCode()))//
                .execute();
        List<Task> acceptedTasks = new ArrayList<>();
        for (BundleEntryComponent bundleEntry : tasksBasedOnOriginalTasksBundle.getEntry()) {
            if (bundleEntry.hasResource() && bundleEntry.getResource().getResourceType().equals(ResourceType.Task)) {
                Task taskBasedOnOrginalTask = (Task) bundleEntry.getResource();
                Task originalTask = originalTasksById
                        .get(taskBasedOnOrginalTask.getBasedOnFirstRep().getReferenceElement().getIdPart());
                originalTask.setStatus(TaskStatus.ACCEPTED);
                updateResources.put(originalTask.getIdElement().getIdPart(), originalTask);
                acceptedTasks
                        .add(originalTasksById.get(taskBasedOnOrginalTask.getBasedOnFirstRep().getReferenceElement().getIdPart()));
            }
        }

        tasksBasedOnOriginalTasksBundle = sourceFhirClient.search()//
                .forResource(Task.class)//
                .returnBundle(Bundle.class)//
//                .revInclude(Task.INCLUDE_BASED_ON)//
                .where(Task.BASED_ON.hasAnyOfIds(originalTasksById.keySet()))
                .where(Task.STATUS.exactly().code(TaskStatus.REJECTED.toCode()))//
                .execute();
        List<Task> rejectedTasks = new ArrayList<>();
        for (BundleEntryComponent bundleEntry : tasksBasedOnOriginalTasksBundle.getEntry()) {
            if (bundleEntry.hasResource() && bundleEntry.getResource().getResourceType().equals(ResourceType.Task)) {
                Task taskBasedOnOrginalTask = (Task) bundleEntry.getResource();
                Task originalTask = originalTasksById
                        .get(taskBasedOnOrginalTask.getBasedOnFirstRep().getReferenceElement().getIdPart());
                originalTask.setStatus(TaskStatus.REJECTED);
                updateResources.put(originalTask.getIdElement().getIdPart(), originalTask);
                rejectedTasks.add(originalTask);
            }
        }

        fhirPersistanceService.updateFhirResourcesInFhirStore(updateResources);
    }

    private void beginTaskImportResultsPath(String remoteStorePath) {
        if (remoteStoreIdentifier.isEmpty()) {
            return;
        }

        IGenericClient sourceFhirClient = fhirUtil.getFhirClient(remoteStorePath);
        IQuery<Bundle> searchQuery = sourceFhirClient.search()//
                .forResource(Task.class)//
                .returnBundle(Bundle.class)//
//                .revInclude(Task.INCLUDE_BASED_ON)//
                .include(Task.INCLUDE_BASED_ON) // serviceRequest
                .where(Task.STATUS.exactly().code(TaskStatus.ACCEPTED.toCode()))//
                .where(Task.REQUESTER.hasAnyOfIds(remoteStoreIdentifier));
        Bundle originalTasksBundle = searchQuery.execute();
        Map<String, OriginalReferralObjects> originalReferralObjectsByServiceRequest = new HashMap<>();
        for (BundleEntryComponent bundleEntry : originalTasksBundle.getEntry()) {
            if (bundleEntry.hasResource()) {
                try {
                    addOriginalReferralObject(bundleEntry, originalReferralObjectsByServiceRequest);
                } catch (RuntimeException e) {
                    LogEvent.logError(e);
                    LogEvent.logError("could not import result for: " + bundleEntry.getResource().getId(), e);
                }
            }
        }
        if (originalReferralObjectsByServiceRequest.size() <= 0) {
            return;
        }

        searchQuery = sourceFhirClient.search()//
                .forResource(ServiceRequest.class)//
                .returnBundle(Bundle.class)//
//                .revInclude(Task.INCLUDE_BASED_ON)//
//                .include(ServiceRequest.INCLUDE_SPECIMEN) // specimen
                .revInclude(Observation.INCLUDE_BASED_ON.asRecursive())//
                .revInclude(DiagnosticReport.INCLUDE_BASED_ON.asRecursive())//
                .where(ServiceRequest.STATUS.exactly().code(ServiceRequestStatus.COMPLETED.toCode()))
                .where(ServiceRequest.BASED_ON.hasAnyOfIds(originalReferralObjectsByServiceRequest.keySet()));
        originalTasksBundle = searchQuery.execute();
        Map<String, ReferralResultsImportObjects> resultImportByServiceRequest = new HashMap<>();
        for (BundleEntryComponent bundleEntry : originalTasksBundle.getEntry()) {
            if (bundleEntry.hasResource()) {
                try {
                    addResultImportObject(bundleEntry, resultImportByServiceRequest,
                            originalReferralObjectsByServiceRequest);
                } catch (RuntimeException e) {
                    LogEvent.logError(e);
                    LogEvent.logError("could not import result for: " + bundleEntry.getResource().getId(), e);
                }
            }
        }

        for (Entry<String, ReferralResultsImportObjects> resultsImportEntry : resultImportByServiceRequest.entrySet()) {
            try {
                fhirReferralService.setReferralResult(resultsImportEntry.getValue());
            } catch (RuntimeException e) {
                LogEvent.logError(e);
                LogEvent.logError("could not import result for ServiceRequest: " + resultsImportEntry.getKey(), e);
            }
        }

    }

    private void addOriginalReferralObject(BundleEntryComponent bundleEntry,
            Map<String, OriginalReferralObjects> originalReferralObjectsByServiceRequest) {
        if (bundleEntry.getResource().getResourceType().equals(ResourceType.ServiceRequest)) {
            ServiceRequest originalServiceRequest = (ServiceRequest) bundleEntry.getResource();
            String serviceRequestId = originalServiceRequest.getIdElement().getIdPart();
            OriginalReferralObjects referralObjects = originalReferralObjectsByServiceRequest
                    .getOrDefault(serviceRequestId, new OriginalReferralObjects());
            referralObjects.serviceRequest = originalServiceRequest;
            originalReferralObjectsByServiceRequest.put(serviceRequestId, referralObjects);
        }
        if (bundleEntry.getResource().getResourceType().equals(ResourceType.Task)) {
            Task referralTask = (Task) bundleEntry.getResource();
            String serviceRequestId = referralTask.getBasedOnFirstRep().getReferenceElement().getIdPart();
            OriginalReferralObjects referralObjects = originalReferralObjectsByServiceRequest
                    .getOrDefault(serviceRequestId, new OriginalReferralObjects());
            referralObjects.task = referralTask;
            originalReferralObjectsByServiceRequest.put(serviceRequestId, referralObjects);
        }

    }

    private void addResultImportObject(BundleEntryComponent bundleEntry,
            Map<String, ReferralResultsImportObjects> resultImportByServiceRequest,
            Map<String, OriginalReferralObjects> originalReferralObjectsByServiceRequest) {
        if (bundleEntry.getResource().getResourceType().equals(ResourceType.ServiceRequest)) {
            ServiceRequest srBasedOnOriginalSR = (ServiceRequest) bundleEntry.getResource();
            String srId = srBasedOnOriginalSR.getIdElement().getIdPart();
            ReferralResultsImportObjects importObjects = resultImportByServiceRequest.getOrDefault(srId,
                    new ReferralResultsImportObjects());
            importObjects.serviceRequest = srBasedOnOriginalSR;
            importObjects.originalReferralObjects = originalReferralObjectsByServiceRequest
                    .get(srBasedOnOriginalSR.getBasedOnFirstRep().getReferenceElement().getIdPart());

            resultImportByServiceRequest.putIfAbsent(srId, importObjects);
        }
        if (bundleEntry.getResource().getResourceType().equals(ResourceType.Observation)) {
            Observation observation = (Observation) bundleEntry.getResource();

            String srId = observation.getBasedOnFirstRep().getReferenceElement().getIdPart();
            ReferralResultsImportObjects importObjects = resultImportByServiceRequest.getOrDefault(srId,
                    new ReferralResultsImportObjects());
            importObjects.observations.add(observation);
            resultImportByServiceRequest.putIfAbsent(srId, importObjects);
        }
        if (bundleEntry.getResource().getResourceType().equals(ResourceType.DiagnosticReport)) {
            DiagnosticReport diagnosticReport = (DiagnosticReport) bundleEntry.getResource();

            String srId = diagnosticReport.getBasedOnFirstRep().getReferenceElement().getIdPart();
            ReferralResultsImportObjects importObjects = resultImportByServiceRequest.getOrDefault(srId,
                    new ReferralResultsImportObjects());
            importObjects.diagnosticReport = diagnosticReport;
            resultImportByServiceRequest.putIfAbsent(srId, importObjects);
        }
    }

    private void beginTaskImportOrderPath(String remoteStorePath) {
        if (remoteStoreIdentifier.isEmpty()) {
            return;
        }
        LogEvent.logDebug(this.getClass().getName(), "beginTaskPath", "searching for Tasks");
        IGenericClient sourceFhirClient = fhirUtil.getFhirClient(remoteStorePath);
        IQuery<Bundle> searchQuery = sourceFhirClient.search()//
                .forResource(Task.class)//
                .returnBundle(Bundle.class)//
                // TODO use include
//                .include(Task.INCLUDE_PATIENT)//
//                .include(Task.INCLUDE_BASED_ON)//
                .where(Task.STATUS.exactly().code(TaskStatus.REQUESTED.toCode()))//
                .where(Task.OWNER.hasAnyOfIds(remoteStoreIdentifier));
        Bundle bundle = searchQuery.execute();

        if (bundle.hasEntry()) {
            LogEvent.logDebug(this.getClass().getName(), "beginTaskPath",
                    "received bundle with " + bundle.getEntry().size() + " entries");
        } else {
            LogEvent.logDebug(this.getClass().getName(), "beginTaskPath", "received bundle with 0 entries");
        }
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {

            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {

                Task remoteTask = (Task) bundleComponent.getResource();
                try {
                    processTaskImportOrder(remoteTask, remoteStorePath, sourceFhirClient, bundle);
                } catch (RuntimeException | FhirLocalPersistingException e) {
                    LogEvent.logError(e);
                    LogEvent.logError(this.getClass().getName(), "beginTaskPath",
                            "could not process Task with identifier : " + remoteTask.getId());
                }

            }
        }
    }

    private void processTaskImportOrder(Task remoteTask, String remoteStorePath, IGenericClient sourceFhirClient,
            Bundle bundle) throws FhirLocalPersistingException {
        // TODO use fhirPersistenceService
        // should contain the Patient, the ServiceRequest, and the Task
        Bundle transactionResponseBundle = saveRemoteTaskAsLocalTask(sourceFhirClient, remoteTask, bundle,
                remoteStorePath);

        Patient remotePatientForTask = getForPatientFromServer(sourceFhirClient, remoteTask);
        List<ServiceRequest> remoteServiceRequests = getBasedOnServiceRequestsFromServer(sourceFhirClient, remoteTask);

        Task taskBasedOnRemoteTask = getLocalTaskBasedOnTask(remoteTask, remoteStorePath);
        if (taskBasedOnRemoteTask == null) {
            taskBasedOnRemoteTask = saveTaskBasedOnRemoteTask(sourceFhirClient, remoteTask, bundle, remoteStorePath);
        }

        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Map<String, List<String>> localSearchParams = new HashMap<>();
        Task localTask = getTaskWithSameIdentifier(remoteTask, remoteStorePath);
        localSearchParams.put("_id", Arrays.asList(localTask.getId()));
        Bundle localBundle = localFhirClient.search()//
                .forResource(Task.class)//
                // TODO use include
                .include(Task.INCLUDE_PATIENT)//
                .include(Task.INCLUDE_BASED_ON)//
                .include(ServiceRequest.INCLUDE_PATIENT.asRecursive())//
                .whereMap(localSearchParams)//
                .returnBundle(Bundle.class)//
                .execute();

        List<ServiceRequest> serviceRequestList = getBasedOnServiceRequestFromBundle(localBundle, localTask);
        List<Patient> patients = new ArrayList<>();
        Patient forPatient = getForPatientFromBundle(localBundle, localTask);
        if (forPatient == null) {
            patients = getForPatientFromBundle(localBundle, serviceRequestList);
        } else {
            patients.add(forPatient);
        }
        TaskResult taskResult = null;
//            if (localTask.getStatus() == null || !(localTask.getStatus().equals(TaskStatus.ACCEPTED)
//                    || localTask.getStatus().equals(TaskStatus.COMPLETED))) {
        if (localTask.getStatus() == null || localTask.getStatus().equals(TaskStatus.REQUESTED)) {
            Boolean taskOrderAcceptedFlag = false;
            for (ServiceRequest serviceRequest : serviceRequestList) {

                Patient patient = getPatientForTaskOrServiceRequest(remoteTask, serviceRequest, patients);
                if (patient == null) {
                    throw new IllegalStateException("could not find a patient for task or service request");
                }
                TaskWorker worker = new TaskWorker(remoteTask,
                        fhirContext.newJsonParser().encodeResourceToString(remoteTask), serviceRequest, patient);

                worker.setInterpreter(SpringContext.getBean(TaskInterpreter.class));
                worker.setExistanceChecker(SpringContext.getBean(DBOrderExistanceChecker.class));
                worker.setPersister(SpringContext.getBean(IOrderPersister.class));

                taskResult = worker.handleOrderRequest();
                if (taskResult == TaskResult.OK) {
                    taskOrderAcceptedFlag = true; // at least one order was accepted per Piotr 5/14/2020
                }
            }

            TaskStatus taskStatus = taskOrderAcceptedFlag ? TaskStatus.RECEIVED : TaskStatus.REJECTED;
            localTask.setStatus(taskStatus);
            if (remoteStoreUpdateStatus.isPresent() && remoteStoreUpdateStatus.get()) {
                LogEvent.logDebug(this.getClass().getName(), "beginTaskPath",
                        "updating remote status to " + taskStatus);
                remoteTask.setStatus(taskStatus);
                sourceFhirClient.update().resource(remoteTask).execute();
            }
            localFhirClient.update().resource(localTask).execute();
            taskBasedOnRemoteTask.setStatus(taskStatus);
            localFhirClient.update().resource(taskBasedOnRemoteTask).execute();
        }
    }

    private Patient getPatientForTaskOrServiceRequest(Task task, ServiceRequest serviceRequest,
            List<Patient> patients) {
        for (Patient patient : patients) {
            if (taskForPatient(task, patient)) {
                return patient;
            } else if (serviceRequestForPatient(serviceRequest, patient)) {
                return patient;
            }
        }
        return null;
    }

    private boolean taskForPatient(Task task, Patient patient) {
        if (task.getFor() != null && task.getFor().getReference() != null) {
            return task.getFor().getReference()
                    .equals(ResourceType.Patient.toString() + "/" + patient.getIdElement().getIdPart());
        }
        return false;
    }

    private boolean serviceRequestForPatient(ServiceRequest serviceRequest, Patient patient) {
        if (serviceRequest.getSubject() != null && serviceRequest.getSubject().getReference() != null) {
            return serviceRequest.getSubject().getReference()
                    .equals(ResourceType.Patient.toString() + "/" + patient.getIdElement().getIdPart());
        }
        return false;
    }

    private Task getLocalTaskBasedOnTask(Task remoteTask, String remoteStorePath) {
        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class)
                .where(Task.BASED_ON
                        .hasAnyOfIds(ResourceType.Task.toString() + "/" + remoteTask.getIdElement().getIdPart()))
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private Task saveTaskBasedOnRemoteTask(IGenericClient fhirClient, Task remoteTask, Bundle bundle,
            String remoteStorePath) {
        Task taskBasedOnRemoteTask = new Task();
        taskBasedOnRemoteTask.setId(UUID.randomUUID().toString());
        Reference reference = new Reference();
        String referenceString = remoteStorePath;
        if (!referenceString.endsWith("/")) {
            referenceString = referenceString + "/";
        }
        referenceString = referenceString + ResourceType.Task.toString() + "/" + remoteTask.getIdElement().getIdPart();
        reference.setReference(referenceString);
        taskBasedOnRemoteTask.addBasedOn(reference);

        MethodOutcome outcome = fhirUtil.getFhirClient(localFhirStorePath).update().resource(taskBasedOnRemoteTask)
                .execute();

        return (Task) outcome.getResource();
    }

    private Bundle saveRemoteTaskAsLocalTask(IGenericClient sourceFhirClient, Task remoteTask, Bundle bundle,
            String remoteStorePath) throws FhirLocalPersistingException {
        FhirOperations fhirOperations = new FhirOperations();
        TempIdGenerator idGenerator = new CountingTempIdGenerator();

        List<ServiceRequest> remoteServiceRequests = getBasedOnServiceRequestsFromServer(sourceFhirClient, remoteTask);
        List<Patient> remotePatients = new ArrayList<>();
        Patient remotePatientForTask = getForPatientFromServer(sourceFhirClient, remoteTask);
        if (remotePatientForTask == null) {
            remotePatients = getForPatientFromServer(sourceFhirClient, remoteServiceRequests);
        } else {
            remotePatients.add(remotePatientForTask);
        }

        Task localTask = getTaskWithSameIdentifier(remoteTask, remoteStorePath);
        if (localTask == null) {
            localTask = remoteTask.addIdentifier(createIdentifierToRemoteResource(remoteTask, remoteStorePath));
//            localTask.setStatus(TaskStatus.ACCEPTED);
            fhirOperations.createResources.put(idGenerator.getNextId(), localTask);
        } else {
//            updateResources.add(localTask.setId(taskWithSameIdentifier.getIdElement().getValue()));
        }

        if (localTask.hasEncounter()) {
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath, localTask.getEncounter());
        }
        if (localTask.hasOwner()) {
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath, localTask.getOwner());
        }

        // ServiceRequests
//      List<ServiceRequest> basedOnServiceRequests = getBasedOnServiceRequestFromBundle(bundle, remoteTask);
        List<ServiceRequest> localServiceRequests = new ArrayList<>();
        ServiceRequest localBasedOn = null;
        for (ServiceRequest remoteBasedOn : remoteServiceRequests) {
            localBasedOn = getServiceRequestWithSameIdentifier(remoteBasedOn, remoteStorePath);
            if (localBasedOn == null) {
                localBasedOn = remoteBasedOn
                        .addIdentifier(createIdentifierToRemoteResource(remoteBasedOn, remoteStorePath));
                fhirOperations.createResources.put(idGenerator.getNextId(), localBasedOn);
                localServiceRequests.add(localBasedOn);
            } else {
//                updateResources.add(basedOn.setId(serviceRequestWithSameIdentifier.getIdElement().getValue()));
            }
        }

        // Patient
//      Patient forPatient = getForPatientFromBundle(bundle, remoteTask);
        List<Patient> patients = new ArrayList<>();
        for (Patient patient : remotePatients) {
            Patient localForPatient = getPatientWithSameServiceIdentifier(patient, remoteStorePath);
            if (localForPatient == null) {
                localForPatient = patient.addIdentifier(createIdentifierToRemoteResource(patient, remoteStorePath));
//                localForPatient.addLink(new PatientLinkComponent().setType(LinkType.SEEALSO).setOther(patient));
                fhirOperations.createResources.put(idGenerator.getNextId(), localForPatient);
                patients.add(localForPatient);
            } else {
                // patient already exists so we should update the reference to ours
//                updateResources.add(forPatient.setId(patientWithSameIdentifier.getIdElement().getValue()));
            }
        }

        LogEvent.logDebug(this.getClass().getName(), "",
                "creating local copies of the remote fhir objects relating to Task: "
                        + remoteTask.getIdElement().getIdPart());
        // Run the transaction
        return fhirPersistanceService.createUpdateFhirResourcesInFhirStore(fhirOperations);
    }

    private List<Patient> getForPatientFromServer(IGenericClient fhirClient, List<ServiceRequest> serviceRequests) {
        List<Patient> patients = new ArrayList<>();
        for (ServiceRequest serviceRequest : serviceRequests) {
            if (serviceRequest.getSubject() != null && serviceRequest.getSubject().getReference() != null) {
                patients.add(fhirClient.read().resource(Patient.class)
                        .withId(serviceRequest.getSubject().getReference()).execute());
            }
        }
        return patients;
    }

    private void replaceLocalReferenceWithAbsoluteReference(String fhirStorePath, Reference reference) {
        reference.setReference(fhirStorePath + reference.getReference());

    }

    private Identifier createIdentifierToRemoteResource(IDomainResource remoteResource, String remoteStorePath) {
        Identifier identifier = new Identifier();
        identifier.setSystem(remoteStorePath);
        identifier.setType(new CodeableConcept()
                .addCoding(new Coding().setCode("externalId").setSystem(fhirConfig.getOeFhirSystem() + "/genIdType")));
        identifier.setValue(remoteResource.getIdElement().getIdPart());
        return identifier;
    }

    private Task getTaskWithSameIdentifier(Task remoteTask, String remoteStorePath) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + remoteTask.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private ServiceRequest getServiceRequestWithSameIdentifier(ServiceRequest basedOn, String remoteStorePath) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(ServiceRequest.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + basedOn.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(ServiceRequest.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (ServiceRequest) localBundle.getEntryFirstRep().getResource();
    }

    private Patient getPatientWithSameServiceIdentifier(Patient remotePatient, String remoteStorePath) {
        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Patient.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + remotePatient.getIdElement().getIdPart()));

        Bundle localBundle = localFhirClient.search().forResource(Patient.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Patient) localBundle.getEntryFirstRep().getResource();
    }

    private Patient getPatientWithSameIdIfExists(Patient remotePatient, String remoteStorePath) {
        IGenericClient localFhirClient = fhirUtil.getFhirClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Patient.class)
                .where(Patient.RES_ID.exactly().code(remotePatient.getIdElement().getId())).returnBundle(Bundle.class)
                .execute();
        return (Patient) localBundle.getEntryFirstRep().getResource();
    }

    private Bundle createBundleFromResources(List<Resource> createResources, List<Resource> updateResources) {
        Bundle transactionBundle = new Bundle();
        transactionBundle.setType(BundleType.TRANSACTION);
        for (Resource createResource : createResources) {
            transactionBundle.addEntry(createTransactionComponentFromResource(createResource, HTTPVerb.PUT));
//            transactionBundle.addEntry(createTransactionComponentFromResource(createResource, HTTPVerb.POST));
            transactionBundle.setTotal(transactionBundle.getTotal() + 1);
        }
        for (Resource updateResource : updateResources) {
            transactionBundle.addEntry(createTransactionComponentFromResource(updateResource, HTTPVerb.PUT));
            transactionBundle.setTotal(transactionBundle.getTotal() + 1);
        }
        LogEvent.logDebug(this.getClass().getName(), "createBundleFromResources",
                fhirContext.newJsonParser().encodeResourceToString(transactionBundle));
        return transactionBundle;
    }

    private BundleEntryComponent createTransactionComponentFromResource(Resource resource, HTTPVerb method) {
        String sourceResourceId = resource.getIdElement().getIdPart();

        BundleEntryComponent transactionComponent = new BundleEntryComponent();
        transactionComponent.setResource(resource);
        transactionComponent.getRequest().setMethod(method);
        transactionComponent.getRequest().setUrl(resource.getResourceType() + "/" + UUID.randomUUID());
//        transactionComponent.getRequest().setUrl(resource.getResourceType() + "/" + sourceResourceId);

        return transactionComponent;
    }

    private List<ServiceRequest> getBasedOnServiceRequestsFromServer(IGenericClient fhirClient, Task remoteTask) {
        List<ServiceRequest> basedOn = new ArrayList<>();
        for (Reference basedOnElement : remoteTask.getBasedOn()) {
            basedOn.add(
                    fhirClient.read().resource(ServiceRequest.class).withId(basedOnElement.getReference()).execute());
        }
        return basedOn;
    }

    private Patient getForPatientFromServer(IGenericClient fhirClient, Task remoteTask) {
        if (remoteTask.getFor() == null || remoteTask.getFor().getReference() == null) {
            return null;
        }
        Patient forPatient = fhirClient.read().resource(Patient.class).withId(remoteTask.getFor().getReference())
                .execute();
        return forPatient;
    }

    // these methods can find the results in the bundle when include is used instead
    // of reaching back to the server
    private List<ServiceRequest> getBasedOnServiceRequestFromBundle(Bundle bundle, Task task) {
        List<ServiceRequest> basedOn = new ArrayList<>();
        for (Reference reference : task.getBasedOn()) {
            basedOn.add((ServiceRequest) findResourceInBundle(bundle, reference.getReference()));
        }
        return basedOn;
    }

    private Patient getForPatientFromBundle(Bundle bundle, Task task) {
        if (task.getFor() != null && task.getFor().getReference() != null) {
            return (Patient) findResourceInBundle(bundle, task.getFor().getReference());
        } else {
            return null;
        }
    }

    private IBaseResource findResourceInBundle(Bundle bundle, String reference) {
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
            if (bundleComponent.hasResource() && bundleComponent.getFullUrl().endsWith(reference)) {
                return bundleComponent.getResource();
            }
        }
        return null;

    }

    private List<Patient> getForPatientFromBundle(Bundle bundle, List<ServiceRequest> serviceRequestList) {
        List<Patient> patients = new ArrayList<>();

        for (ServiceRequest serviceRequest : serviceRequestList) {
            if (serviceRequest.getSubject() != null && serviceRequest.getSubject().getReference() != null) {
                patients.add((Patient) findResourceInBundle(bundle, serviceRequest.getSubject().getReference()));
            }
        }
        return patients;
    }

    public class OriginalReferralObjects {
        public Task task;
        public ServiceRequest serviceRequest;
    }

    public class ReferralResultsImportObjects {
        public OriginalReferralObjects originalReferralObjects = new OriginalReferralObjects();
        public ServiceRequest serviceRequest;
        public DiagnosticReport diagnosticReport;
        public List<Observation> observations = new ArrayList<>();
    }
}
