package org.openelisglobal.dataexchange.fhir.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class FhirApiWorkFlowServiceImpl implements FhirApiWorkflowService {

    @Autowired
    private FhirContext fhirContext;

    @Value("${org.openelisglobal.fhirstore.uri}")
    private String fhirStorePath;

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
        Map<String, List<String>> searchParams = new HashMap<>();
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(fhirStorePath.toString());
        Bundle bundle = fhirClient.search()//
                .forResource(Task.class)//
                .include(Task.INCLUDE_PATIENT)//
                .include(Task.INCLUDE_BASED_ON)//
//                .whereMap(searchParams)//
                .returnBundle(Bundle.class)//
                .execute();

        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
                Task task = (Task) bundleComponent.getResource();
//                Patient forPatient = getForPatientFromTask(bundle, task);
                List<ServiceRequest> basedOn = getBasedOnServiceRequestFromTask(bundle, task);
                System.out.println("Task: " + fhirContext.newJsonParser().encodeResourceToString(task));
//                System.out.println("For Patient: " + fhirContext.newJsonParser().encodeResourceToString(forPatient));
                for (ServiceRequest serviceRequest : basedOn) {
                    System.out.println("BasedOn ServiceRequest: "
                            + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
                }

                task.setStatus(TaskStatus.ACCEPTED);
                fhirContext.newRestfulGenericClient(fhirStorePath).update().resource(task).execute();
            }
        }

    }

    private List<ServiceRequest> getBasedOnServiceRequestFromTask(Bundle bundle, Task task) {
        List<ServiceRequest> basedOn = new ArrayList<>();
        for (Reference reference : task.getBasedOn()) {
            basedOn.add((ServiceRequest) findResourceInBundle(bundle, reference.getReference()));
        }

        return basedOn;
    }

    private Patient getForPatientFromTask(Bundle bundle, Task task) {
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
