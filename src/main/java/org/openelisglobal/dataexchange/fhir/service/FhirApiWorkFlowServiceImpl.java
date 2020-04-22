package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String remoteStorePath;
    @Value("${org.openelisglobal.task.useBasedOn}")
    private Boolean useBasedOn;

    @Scheduled(initialDelay = 100000 * 1000, fixedRate = 60 * 1000)
    @Override
    public void pollForRemoteTasks() {
        processWorkflow(ResourceType.Task);
    }

    @Override
    @Async
    public void processWorkflow(ResourceType resourceType) {
        switch (resourceType) {
        case Task:
            beginTaskPath();
        default:
        }
    }

    private void beginTaskPath() {

        Map<String, List<String>> remoteSearchParams = new HashMap<>();
        remoteSearchParams.put("status", Arrays.asList("REQUESTED"));
        // TODO make this configurable instead of hardcoded
        remoteSearchParams.put("owner", Arrays.asList("Practitioner/f9badd80-ab76-11e2-9e96-0800200c9a66"));
//        remoteSearchParams.put("owner", Arrays.asList("f9badd80-ab76-11e2-9e96-0800200c9a66"));

        System.out.println("searching for Tasks");

        IGenericClient sourceFhirClient = fhirContext.newRestfulGenericClient(remoteStorePath);
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
                        .whereMap(localSearchParams)//
                        .returnBundle(Bundle.class)//
                        .execute();

                List<ServiceRequest> serviceRequestList = getBasedOnServiceRequestFromBundle(localBundle, localTask);
                Patient forPatient = getForPatientFromBundle(localBundle, localTask);
                System.out.println("localBundle: " + fhirContext.newJsonParser().encodeResourceToString(localBundle));


                if (!localTask.getStatus().equals(TaskStatus.ACCEPTED)) {
                    TaskWorker worker = new TaskWorker(remoteTask,
                            fhirContext.newJsonParser().encodeResourceToString(remoteTask), serviceRequestList,
                            forPatient);



                    worker.setInterpreter(SpringContext.getBean(TaskInterpreter.class));
                    worker.setExistanceChecker(SpringContext.getBean(DBOrderExistanceChecker.class));

                    worker.setPersister(SpringContext.getBean(IOrderPersister.class));

                    TaskResult taskResult = worker.handleOrderRequest();
                    TaskStatus taskStatus = TaskResult.OK == taskResult ? TaskStatus.ACCEPTED : TaskStatus.REJECTED;
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

    private Task getLocalTaskBasedOnTask(Task remoteTask) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_BASED_ON, Arrays.asList(remoteStorePath + remoteTask.getId()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private Task saveTaskBasedOnRemoteTask(IGenericClient fhirClient, Task remoteTask, Bundle bundle) {
        Task taskBasedOnRemoteTask = new Task();
        Reference reference = new Reference();
        reference.setReference(remoteStorePath + remoteTask.getId());
        taskBasedOnRemoteTask.addBasedOn(reference);


        MethodOutcome outcome = fhirContext.newRestfulGenericClient(localFhirStorePath).create()
                .resource(taskBasedOnRemoteTask).execute();

        return (Task) outcome.getResource();
    }

    private Bundle saveRemoteTaskAsLocalTask(IGenericClient sourceFhirClient, Task remoteTask, Bundle bundle) {
        List<Resource> createResources = new ArrayList<>();
        List<Resource> updateResources = new ArrayList<>();

        Reference patientReference = new Reference();
        // Task

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
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath, localTask.getEncounter());
        }
        if (localTask.hasOwner()) {
            replaceLocalReferenceWithAbsoluteReference(remoteStorePath, localTask.getOwner());
        }

        // Patient
        Patient remoteForPatient = getForPatientFromServer(sourceFhirClient, remoteTask);
//      Patient forPatient = getForPatientFromBundle(bundle, remoteTask);
        Patient localForPatient = getPatientWithSameServiceIdentifier(remoteForPatient);
        if (localForPatient == null) {
            localForPatient = remoteForPatient.addIdentifier(createIdentifierToRemoteResource(remoteForPatient));
            createResources.add(localForPatient);
        } else {
            // patient already exists so we should update the reference to ours
            patientReference = new Reference();
            patientReference
                    .setReference(localForPatient.getResourceType() + "/" + localForPatient.getIdElement().getIdPart());
//            updateResources.add(forPatient.setId(patientWithSameIdentifier.getIdElement().getValue()));
        }

        // ServiceRequests
        List<ServiceRequest> remoteBasedOnServiceRequests = getBasedOnServiceRequestsFromServer(sourceFhirClient,
                remoteTask);
//      List<ServiceRequest> basedOnServiceRequests = getBasedOnServiceRequestFromBundle(bundle, remoteTask);
        for (ServiceRequest remoteBasedOn : remoteBasedOnServiceRequests) {
            ServiceRequest localBasedOn = getServiceRequestWithSameIdentifier(remoteBasedOn);
            if (localBasedOn == null) {
                localBasedOn = remoteBasedOn.addIdentifier(createIdentifierToRemoteResource(remoteBasedOn));
                createResources.add(localBasedOn);
                if (remoteBasedOn.getSubject() != null && remoteBasedOn.getSubject().equals(remoteTask.getFor())) {
                    localBasedOn.setSubject(patientReference);
                }
            } else {
//                updateResources.add(basedOn.setId(serviceRequestWithSameIdentifier.getIdElement().getValue()));
            }
        }

        localTask.setFor(patientReference);

        // Run the transaction
        return fhirContext.newRestfulGenericClient(localFhirStorePath).transaction()
                .withBundle(createBundleFromResources(createResources, updateResources)).execute();
    }

    private void replaceLocalReferenceWithAbsoluteReference(String fhirStorePath, Reference reference) {
        reference.setReference(fhirStorePath + reference.getReference());

    }

    private Identifier createIdentifierToRemoteResource(IDomainResource remoteResource) {
        Identifier identifier = new Identifier();
        identifier.setSystem(remoteStorePath);
        identifier.setValue(remoteResource.getIdElement().getIdPart());
        return identifier;
    }

    private Task getTaskWithSameIdentifier(Task remoteTask) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + remoteTask.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Task) localBundle.getEntryFirstRep().getResource();
    }

    private ServiceRequest getServiceRequestWithSameIdentifier(ServiceRequest basedOn) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(ServiceRequest.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + basedOn.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(ServiceRequest.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (ServiceRequest) localBundle.getEntryFirstRep().getResource();
    }

    private Patient getPatientWithSameServiceIdentifier(Patient remotePatient) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Patient.SP_IDENTIFIER,
                Arrays.asList(remoteStorePath + "|" + remotePatient.getIdElement().getIdPart()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Patient.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return (Patient) localBundle.getEntryFirstRep().getResource();
    }

    private Bundle createBundleFromResources(List<Resource> createResources, List<Resource> updateResources) {
        Bundle transactionBundle = new Bundle();
        transactionBundle.setType(BundleType.TRANSACTION);
        for (Resource createResource : createResources) {
            transactionBundle.addEntry(createTransactionComponentFromResource(createResource, HTTPVerb.POST));
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
        transactionComponent.getRequest().setUrl(resource.getResourceType() + "/" + sourceResourceId);

        return transactionComponent;
    }

    private List<ServiceRequest> getBasedOnServiceRequestsFromServer(IGenericClient fhirClient, Task remoteTask) {
        List<ServiceRequest> basedOn = new ArrayList<>();
        for (Reference basedOnElement : remoteTask.getBasedOn()) {
            basedOn.add(
                    fhirClient.read().resource(ServiceRequest.class).withId(basedOnElement.getReference()).execute());
        }

        for (ServiceRequest serviceRequest : basedOn) {
            System.out.println(
                    "BasedOn ServiceRequest: " + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
//                fhirContext.newRestfulGenericClient(localFhirStorePath).update().resource(serviceRequest)
//                        .execute();
        }
        return basedOn;
    }

    private Patient getForPatientFromServer(IGenericClient fhirClient, Task remoteTask) {
        Patient forPatient = fhirClient.read().resource(Patient.class).withId(remoteTask.getFor().getReference())
                .execute();
        System.out.println("For Patient: " + fhirContext.newJsonParser().encodeResourceToString(forPatient));
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
        return (Patient) findResourceInBundle(bundle, task.getFor().getReference());
    }

    private IBaseResource findResourceInBundle(Bundle bundle, String reference) {
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
            if (bundleComponent.hasResource() && bundleComponent.getFullUrl().endsWith(reference)) {
                return bundleComponent.getResource();
            }
        }
        return null;

    }

}
