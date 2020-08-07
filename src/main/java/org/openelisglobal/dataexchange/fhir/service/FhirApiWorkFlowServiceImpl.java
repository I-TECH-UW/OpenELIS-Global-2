package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IDomainResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Bundle.HTTPVerb;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.dataexchange.fhir.service.TaskWorker.TaskResult;
import org.openelisglobal.dataexchange.order.action.DBOrderExistanceChecker;
import org.openelisglobal.dataexchange.order.action.IOrderPersister;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;

@Service
public class FhirApiWorkFlowServiceImpl implements FhirApiWorkflowService {

    @Autowired
    private FhirContext fhirContext;

    @Value("${org.openelisglobal.fhirstore.uri}")
    private String localFhirStorePath;
    @Value("${org.openelisglobal.remote.source.uri}")
    private Optional<String> remoteStorePath;
    @Value("${org.openelisglobal.remote.source.identifier}")
    private Optional<String> remoteStoreIdentifier;
    @Value("${org.openelisglobal.task.useBasedOn}")
    private Boolean useBasedOn;

    @Scheduled(initialDelay = 10 * 1000, fixedRate = 60000 * 1000)

    @Override
    public void pollForRemoteTasks() {
        processWorkflow(ResourceType.Task);
    }

    @Override
    @Async
    public void processWorkflow(ResourceType resourceType) {
        if (remoteStorePath.isPresent() && !GenericValidator.isBlankOrNull(remoteStorePath.get())) {
            switch (resourceType) {
            case Task:
                beginTaskPath();
            default:
            }
        }
    }

    @Override
    public String getLocalFhirStorePath() {
        return localFhirStorePath;
    }

    private void beginTaskPath() {

        Map<String, List<String>> remoteSearchParams = new HashMap<>();
        if (remoteStoreIdentifier.isPresent() && !GenericValidator.isBlankOrNull(remoteStoreIdentifier.get())) {
            remoteSearchParams.put("status", Arrays.asList("REQUESTED"));
            remoteSearchParams.put("owner", Arrays.asList(remoteStoreIdentifier.get()));
//            remoteSearchParams.put("owner", Arrays.asList("Practitioner/f9badd80-ab76-11e2-9e96-0800200c9a66"));
        }

        System.out.println("searching for Tasks");

        IGenericClient sourceFhirClient = fhirContext.newRestfulGenericClient(remoteStorePath.get());
        IClientInterceptor authInterceptor = new BasicAuthInterceptor("admin", "Admin123");
        sourceFhirClient.registerInterceptor(authInterceptor);
        Bundle bundle = sourceFhirClient.search()//
                .forResource(Task.class)//
                // TODO use include
//                .include(Task.INCLUDE_PATIENT)//
//                .include(Task.INCLUDE_BASED_ON)//
                .whereMap(remoteSearchParams)//
                .returnBundle(Bundle.class)//
                .execute();

        if (bundle.hasEntry()) {
            System.out.println("received bundle with " + bundle.getEntry().size() + " entries");
        } else {
            System.out.println("received bundle with 0 entries");
        }
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {

            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {

                Task remoteTask = (Task) bundleComponent.getResource();
                // should contain the Patient, the ServiceRequest, and the Task
                Bundle transactionResponseBundle = saveRemoteTaskAsLocalTask(sourceFhirClient, remoteTask, bundle);
                Task taskBasedOnRemoteTask = getLocalTaskBasedOnTask(remoteTask);
                if (useBasedOn && taskBasedOnRemoteTask == null) {
                    taskBasedOnRemoteTask = saveTaskBasedOnRemoteTask(sourceFhirClient, remoteTask, bundle);
                }

                IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
                Map<String, List<String>> localSearchParams = new HashMap<>();
                Task localTask = getTaskWithSameIdentifier(remoteTask);
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
//                if(true) {
                if (localTask.getStatus() == null || !(localTask.getStatus().equals(TaskStatus.ACCEPTED)
                        || localTask.getStatus().equals(TaskStatus.COMPLETED))) {
                    Boolean taskOrderAcceptedFlag = false;
                    for (ServiceRequest serviceRequest : serviceRequestList) {

                        Patient patient = getPatientForTaskOrServiceRequest(remoteTask, serviceRequest, patients);
                        if (patient == null) {
                            throw new IllegalStateException("could not find a patient for task or service request");
                        }
                        TaskWorker worker = new TaskWorker(remoteTask,
                                fhirContext.newJsonParser().encodeResourceToString(remoteTask), serviceRequest,
                                patient);

                        worker.setInterpreter(SpringContext.getBean(TaskInterpreter.class));
                        worker.setExistanceChecker(SpringContext.getBean(DBOrderExistanceChecker.class));
                        worker.setPersister(SpringContext.getBean(IOrderPersister.class));

                        taskResult = worker.handleOrderRequest();
                        if (taskResult == TaskResult.OK) {
                            taskOrderAcceptedFlag = true; // at least one order was accepted per Piotr 5/14/2020
                        }
                    }

                    TaskStatus taskStatus = taskOrderAcceptedFlag ? TaskStatus.ACCEPTED : TaskStatus.REJECTED;
                    localTask.setStatus(taskStatus);
                    localFhirClient.update().resource(localTask).execute();
                    if (useBasedOn) {
                        taskBasedOnRemoteTask.setStatus(taskStatus);
                        localFhirClient.update().resource(taskBasedOnRemoteTask).execute();
                    }
                }
            }
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

    private Task getLocalTaskBasedOnTask(Task remoteTask) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_BASED_ON,
                Arrays.asList(remoteStorePath.get() + ResourceType.Task.toString() + "/"
                        + remoteTask.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private Task saveTaskBasedOnRemoteTask(IGenericClient fhirClient, Task remoteTask, Bundle bundle) {
        Task taskBasedOnRemoteTask = new Task();
        taskBasedOnRemoteTask.setId(UUID.randomUUID().toString());
        Reference reference = new Reference();
        String referenceString = remoteStorePath.get();
        if (!referenceString.endsWith("/")) {
            referenceString = referenceString + "/";
        }
        referenceString = referenceString + ResourceType.Task.toString() + "/" + remoteTask.getIdElement().getIdPart();
        reference.setReference(referenceString);
        taskBasedOnRemoteTask.addBasedOn(reference);

        MethodOutcome outcome = fhirContext.newRestfulGenericClient(localFhirStorePath).update()
                .resource(taskBasedOnRemoteTask).execute();

        return (Task) outcome.getResource();
    }

    private Bundle saveRemoteTaskAsLocalTask(IGenericClient sourceFhirClient, Task remoteTask, Bundle bundle) {
        List<Resource> createResources = new ArrayList<>();
        List<Resource> updateResources = new ArrayList<>();

        Reference patientReference = new Reference();

        Task localTask = getTaskWithSameIdentifier(remoteTask);
        if (localTask == null) {
            localTask = remoteTask.addIdentifier(createIdentifierToRemoteResource(remoteTask));
            patientReference = localTask.getFor();
//            localTask.setStatus(TaskStatus.ACCEPTED);
            createResources.add(localTask);
        } else {
//            updateResources.add(localTask.setId(taskWithSameIdentifier.getIdElement().getValue()));
        }

        if (localTask.hasEncounter()) {
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath.get(), localTask.getEncounter());
        }
        if (localTask.hasOwner()) {
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath.get(), localTask.getOwner());
        }

        // ServiceRequests
        List<ServiceRequest> remoteBasedOnServiceRequests = getBasedOnServiceRequestsFromServer(sourceFhirClient,
                remoteTask);
//      List<ServiceRequest> basedOnServiceRequests = getBasedOnServiceRequestFromBundle(bundle, remoteTask);
        ServiceRequest localBasedOn = null;
        for (ServiceRequest remoteBasedOn : remoteBasedOnServiceRequests) {
            localBasedOn = getServiceRequestWithSameIdentifier(remoteBasedOn);
            if (localBasedOn == null) {
                localBasedOn = remoteBasedOn.addIdentifier(createIdentifierToRemoteResource(remoteBasedOn));
                createResources.add(localBasedOn);
//                localBasedOn.setSubject(patientReference);
                if (remoteBasedOn.getSubject() != null && remoteBasedOn.getSubject().equals(remoteTask.getFor())) {
                    localBasedOn.setSubject(patientReference);
                }
            } else {
//                updateResources.add(basedOn.setId(serviceRequestWithSameIdentifier.getIdElement().getValue()));
            }
        }

        // Patient
        List<Patient> remotePatients = new ArrayList<>();
        Patient remoteForPatient = getForPatientFromServer(sourceFhirClient, remoteTask);
        if (remoteForPatient == null) {
            remotePatients = getForPatientFromServer(sourceFhirClient, remoteBasedOnServiceRequests);
        } else {
            remotePatients.add(remoteForPatient);
            localTask.setFor(patientReference);
        }
//      Patient forPatient = getForPatientFromBundle(bundle, remoteTask);
        for (Patient patient : remotePatients) {
            Patient localForPatient = getPatientWithSameServiceIdentifier(patient);
            if (localForPatient == null) {
                localForPatient = patient.addIdentifier(createIdentifierToRemoteResource(patient));
                createResources.add(localForPatient);
            } else {
                // patient already exists so we should update the reference to ours
                patientReference = new Reference();
                patientReference.setReference(
                        localForPatient.getResourceType() + "/" + localForPatient.getIdElement().getIdPart());
//                updateResources.add(forPatient.setId(patientWithSameIdentifier.getIdElement().getValue()));
            }
        }

        // Run the transaction
        return fhirContext.newRestfulGenericClient(localFhirStorePath).transaction()
                .withBundle(createBundleFromResources(createResources, updateResources)).execute();
    }

    private List<Patient> getForPatientFromServer(IGenericClient fhirClient, List<ServiceRequest> serviceRequests) {
        List<Patient> patients = new ArrayList<>();
        for (ServiceRequest serviceRequest : serviceRequests) {
            if (serviceRequest.getSubject() != null && serviceRequest.getSubject().getReference() != null) {
                patients.add(fhirClient.read().resource(Patient.class)
                        .withId(serviceRequest.getSubject().getReference()).execute());
            }
//            System.out.println("For Patient: " + fhirContext.newJsonParser().encodeResourceToString(forPatient));
//          fhirContext.newRestfulGenericClient(localFhirStorePath).update().resource(forPatient).execute();
        }
        return patients;
    }

    private void replaceLocalReferenceWithAbsoluteReference(String fhirStorePath, Reference reference) {
        reference.setReference(fhirStorePath + reference.getReference());

    }

    private Identifier createIdentifierToRemoteResource(IDomainResource remoteResource) {
        Identifier identifier = new Identifier();
        identifier.setSystem(remoteStorePath.get());
        identifier.setValue(remoteResource.getIdElement().getIdPart());
        return identifier;
    }

    private Task getTaskWithSameIdentifier(Task remoteTask) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath.get() + "|" + remoteTask.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private ServiceRequest getServiceRequestWithSameIdentifier(ServiceRequest basedOn) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(ServiceRequest.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath.get() + "|" + basedOn.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(ServiceRequest.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (ServiceRequest) localBundle.getEntryFirstRep().getResource();
    }

    private Patient getPatientWithSameServiceIdentifier(Patient remotePatient) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Patient.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath.get() + "|" + remotePatient.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Patient.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
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
        System.out.println(fhirContext.newJsonParser().encodeResourceToString(transactionBundle));
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

        for (ServiceRequest serviceRequest : basedOn) {
//            System.out.println(
//                    "BasedOn ServiceRequest: " + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
//                fhirContext.newRestfulGenericClient(localFhirStorePath).update().resource(serviceRequest)
//                        .execute();
        }
        return basedOn;
    }

    private Patient getForPatientFromServer(IGenericClient fhirClient, Task remoteTask) {
        if (remoteTask.getFor() == null || remoteTask.getFor().getReference() == null) {
            return null;
        }
        Patient forPatient = fhirClient.read().resource(Patient.class).withId(remoteTask.getFor().getReference())
                .execute();
//        System.out.println("For Patient: " + fhirContext.newJsonParser().encodeResourceToString(forPatient));
//      fhirContext.newRestfulGenericClient(localFhirStorePath).update().resource(forPatient).execute();
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

}
