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

    @Scheduled(initialDelay = 10 * 1000, fixedRate = 60 * 1000)
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
//        remoteSearchParams.put("owner", Arrays.asList("Practitioner/f9badd80-ab76-11e2-9e96-0800200c9a66"));
        remoteSearchParams.put("owner", Arrays.asList("f9badd80-ab76-11e2-9e96-0800200c9a66"));

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
                // System.out.println("Task: " +
                // fhirContext.newJsonParser().encodeResourceToString(remoteTask));
                // should contain the Patient, the ServiceRequest, and the Task
                Bundle transactionResponseBundle = saveRemoteTaskAsLocalTask(sourceFhirClient, remoteTask, bundle);
                if (useBasedOn && !basedOnExistsLocallyForTask(remoteTask)) {
                    Task taskBasedOnRemoteTask = saveTaskBasedOnRemoteTask(sourceFhirClient, remoteTask, bundle);
                }
                
                IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
                Map<String, List<String>> localSearchParams = new HashMap<>();
                Task taskWithSameIdentifier = getTaskWithSameIdentifier(remoteTask);
                localSearchParams.put("_id", Arrays.asList(taskWithSameIdentifier.getId()));
                Bundle localBundle = localFhirClient.search()//
                        .forResource(Task.class)//
                        // TODO use include
                        .include(Task.INCLUDE_PATIENT)//
                        .include(Task.INCLUDE_BASED_ON)//
                        .whereMap(localSearchParams)//
                        .returnBundle(Bundle.class)//
                        .execute();
                
                List<ServiceRequest> serviceRequestList = getBasedOnServiceRequestFromBundle(localBundle, taskWithSameIdentifier);
                Patient forPatient = getForPatientFromBundle(localBundle, taskWithSameIdentifier);
                System.out.println("localBundle: " + fhirContext.newJsonParser().encodeResourceToString(localBundle));

                TaskWorker worker = new TaskWorker(remoteTask,
                        fhirContext.newJsonParser().encodeResourceToString(remoteTask), serviceRequestList, forPatient);
                worker.setInterpreter(SpringContext.getBean(TaskInterpreter.class));
                worker.setExistanceChecker(SpringContext.getBean(DBOrderExistanceChecker.class));

                worker.setPersister(SpringContext.getBean(IOrderPersister.class));

                TaskResult taskResult = worker.handleOrderRequest();

//                remoteTask.setStatus(TaskStatus.ACCEPTED);
//                fhirContext.newRestfulGenericClient(fhirStorePath).update().resource(remoteTask).execute();
            }
        }
    }

    private boolean basedOnExistsLocallyForTask(Task remoteTask) {
        Map<String, List<String>> localSearchParams = new HashMap<>();
        localSearchParams.put(Task.SP_BASED_ON, Arrays.asList(remoteStorePath + remoteTask.getId()));

        IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);
        Bundle localBundle = localFhirClient.search().forResource(Task.class).whereMap(localSearchParams)
                .returnBundle(Bundle.class).execute();
        return !localBundle.getEntry().isEmpty();
    }

    private Task saveTaskBasedOnRemoteTask(IGenericClient fhirClient, Task remoteTask, Bundle bundle) {
        Task taskBasedOnRemoteTask = new Task();
        Reference reference = new Reference();
        reference.setReference(remoteStorePath + remoteTask.getId());
        taskBasedOnRemoteTask.addBasedOn(reference);
        taskBasedOnRemoteTask.setStatus(TaskStatus.ACCEPTED);

        MethodOutcome outcome = fhirContext.newRestfulGenericClient(localFhirStorePath).create()
                .resource(taskBasedOnRemoteTask).execute();

        return (Task) outcome.getResource();
    }

    private Bundle saveRemoteTaskAsLocalTask(IGenericClient sourceFhirClient, Task remoteTask, Bundle bundle) {
        List<Resource> createResources = new ArrayList<>();
        List<Resource> updateResources = new ArrayList<>();
        // Task
        Task localTask = remoteTask.addIdentifier(createIdentifierToRemoteResource(remoteTask));
        localTask.setStatus(TaskStatus.ACCEPTED);
        Task taskWithSameIdentifier = getTaskWithSameIdentifier(remoteTask);
        if (taskWithSameIdentifier == null) {
            createResources.add(localTask);
        } else {
//            updateResources.add(localTask.setId(taskWithSameIdentifier.getIdElement().getValue()));
        }

        // ServiceRequests
        List<ServiceRequest> basedOnServiceRequests = getBasedOnServiceRequestsFromServer(sourceFhirClient, remoteTask);
//      List<ServiceRequest> basedOnServiceRequests = getBasedOnServiceRequestFromBundle(bundle, remoteTask);
        for (ServiceRequest basedOn : basedOnServiceRequests) {
            basedOn = basedOn.addIdentifier(createIdentifierToRemoteResource(basedOn));
            ServiceRequest serviceRequestWithSameIdentifier = getServiceRequestWithSameIdentifier(basedOn);
            if (serviceRequestWithSameIdentifier == null) {
                createResources.add(basedOn);
            } else {
//                updateResources.add(basedOn.setId(serviceRequestWithSameIdentifier.getIdElement().getValue()));
            }
        }

        // Patient
        Patient forPatient = getForPatientFromServer(sourceFhirClient, remoteTask);
//      Patient forPatient = getForPatientFromBundle(bundle, remoteTask);
        forPatient = forPatient.addIdentifier(createIdentifierToRemoteResource(forPatient));
        Patient patientWithSameIdentifier = getPatientWithSameServiceIdentifier(forPatient);
        if (patientWithSameIdentifier == null) {
            createResources.add(forPatient);
        } else {
//            updateResources.add(forPatient.setId(patientWithSameIdentifier.getIdElement().getValue()));
        }

        // Run the transaction
        return fhirContext.newRestfulGenericClient(localFhirStorePath).transaction()
                .withBundle(createBundleFromResources(createResources, updateResources)).execute();
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
