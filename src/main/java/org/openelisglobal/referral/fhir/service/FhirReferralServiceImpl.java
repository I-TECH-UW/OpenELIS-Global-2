package org.openelisglobal.referral.fhir.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class FhirReferralServiceImpl implements FhirReferralService {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private FhirTransformService fhirTransformService;
    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private FhirContext fhirContext;
    @Autowired
    private FhirConfig fhirConfig;

    @Override
    @Transactional
    public Bundle cancelReferralToOrganization(String referralOrganizationId, String sampleId,
            List<String> analysisIds) {
        IGenericClient localClient = fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());
        org.openelisglobal.organization.valueholder.Organization referralOrganization = organizationService
                .get(referralOrganizationId);
        Organization fhirOrg = getFhirOrganization(referralOrganization);
        if (fhirOrg == null) {
            // organization doesn't exist as fhir organization, cannot cancel automatically
            return new Bundle();
        }
        Sample sample = sampleService.get(sampleId);
        List<Analysis> analysises = analysisService.get(analysisIds);
        Bundle serviceRequestBundle = localClient.search().forResource(ServiceRequest.class).returnBundle(Bundle.class)
                .where(ServiceRequest.CODE.exactly().code(sample.getAccessionNumber())).execute();

        List<ServiceRequest> serviceRequests = getServiceRequestsBasedOnOriginalsForReferredTest(analysises, sample,
                serviceRequestBundle);

        Bundle cancelTaskBundle = localClient.search().forResource(Task.class).returnBundle(Bundle.class)
                .where(Task.BASED_ON
                        .hasAnyOfIds(serviceRequests.stream().map(e -> e.getId()).collect(Collectors.toList())))
                .execute();
        List<Resource> cancelTasks = new ArrayList<>();
        for (BundleEntryComponent entry : cancelTaskBundle.getEntry()) {
            if (entry.hasResource() && ResourceType.Task.equals(entry.getResource().getResourceType())) {
                Task cancelTask = ((Task) entry.getResource());
                cancelTask.setStatus(TaskStatus.CANCELLED);
                cancelTasks.add(cancelTask);
            }
        }
        return fhirPersistanceService.updateFhirResourcesInFhirStore(cancelTasks);
    }

    private List<ServiceRequest> getServiceRequestsBasedOnOriginalsForReferredTest(List<Analysis> analysises,
            Sample sample, Bundle serviceRequesttBundle) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        for (Analysis analysis : analysises) {
            for (BundleEntryComponent entry : serviceRequesttBundle.getEntry()) {
                if (entry.hasResource() && ResourceType.ServiceRequest.equals(entry.getResource().getResourceType())) {
                    ServiceRequest originalServiceRequest = (ServiceRequest) entry.getResource();
                    if (entryIsForAnalysis(originalServiceRequest, analysis)) {
                        ServiceRequest createdServiceRequest = getServiceRequestBasedOnOriginal(originalServiceRequest,
                                analysis);
                        if (createdServiceRequest != null) {
                            serviceRequests.add(getServiceRequestBasedOnOriginal(originalServiceRequest, analysis));
                        }
                        break;
                    }
                }
            }
        }

        return serviceRequests;
    }

    private ServiceRequest getServiceRequestBasedOnOriginal(ServiceRequest originalServiceRequest, Analysis analysis) {
        IGenericClient localClient = fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());
        Bundle createdServiceRequestBundle = localClient.search().forResource(ServiceRequest.class)
                .returnBundle(Bundle.class).where(ServiceRequest.BASED_ON.hasAnyOfIds(originalServiceRequest.getId()))
                .execute();

        for (BundleEntryComponent entry : createdServiceRequestBundle.getEntry()) {
            if (entry.hasResource() && ResourceType.ServiceRequest.equals(entry.getResource().getResourceType())) {
                return (ServiceRequest) entry.getResource();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Bundle referAnalysisesToOrganization(String referralOrganizationId, String sampleId,
            List<String> analysisIds) {
        IGenericClient localClient = fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());
        org.openelisglobal.organization.valueholder.Organization referralOrganization = organizationService
                .get(referralOrganizationId);
        Organization fhirOrg = getFhirOrganization(referralOrganization);
        if (fhirOrg == null) {
            // organization doesn't exist as fhir organization, cannot refer automatically
            return new Bundle();
        }
        List<Resource> newResources = new ArrayList<>();
        Sample sample = sampleService.get(sampleId);
        List<Analysis> analysises = analysisService.get(analysisIds);
        Bundle serviceRequestAndPatientBundle = localClient.search().forResource(ServiceRequest.class)
                .returnBundle(Bundle.class).where(ServiceRequest.CODE.exactly().code(sample.getAccessionNumber()))
                .include(ServiceRequest.INCLUDE_SUBJECT).execute();

        List<ServiceRequest> serviceRequests = createServiceRequestsForReferredTest(analysises, sample,
                serviceRequestAndPatientBundle);
        Task task = createReferralTask(fhirOrg,
                getFhirPatient(serviceRequestAndPatientBundle), serviceRequests, sample.getAccessionNumber());

        newResources.add(task);
        newResources.addAll(serviceRequests);
        return fhirPersistanceService.createFhirResourcesInFhirStore(newResources);
    }

    private Organization getFhirOrganization(org.openelisglobal.organization.valueholder.Organization organization) {
        return fhirTransformService.getFhirOrganization(organization);
    }

    private Patient getFhirPatient(Bundle serviceRequestAndPatientBundle) {
        for (BundleEntryComponent entry : serviceRequestAndPatientBundle.getEntry()) {
            if (entry.hasResource() && ResourceType.Patient.equals(entry.getResource().getResourceType())) {
                return (Patient) entry.getResource();
            }
        }
        return null;
    }

    private List<ServiceRequest> createServiceRequestsForReferredTest(List<Analysis> analysises, Sample sample,
            Bundle serviceRequestAndPatientBundle) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        for (Analysis analysis : analysises) {
            for (BundleEntryComponent entry : serviceRequestAndPatientBundle.getEntry()) {
                if (entry.hasResource() && ResourceType.ServiceRequest.equals(entry.getResource().getResourceType())) {
                    ServiceRequest basedOnServiceRequest = (ServiceRequest) entry.getResource();
                    if (entryIsForAnalysis(basedOnServiceRequest, analysis)) {
                        serviceRequests.add(createServiceRequestBasedOnOriginal(basedOnServiceRequest, analysis));
                        break;
                    }
                }
            }
        }

        return serviceRequests;
    }

    private boolean entryIsForAnalysis(ServiceRequest basedOnServiceRequest, Analysis analysis) {
        if (GenericValidator.isBlankOrNull(analysis.getTest().getLoinc())) {
            throw new TestNotFullyConfiguredException();
        }
        return basedOnServiceRequest.getCode().hasCoding("http://loinc.org", analysis.getTest().getLoinc());
    }

    private ServiceRequest createServiceRequestBasedOnOriginal(ServiceRequest originalServiceRequest,
            Analysis analysis) {
        ServiceRequest serviceRequest = new ServiceRequest();
        // a temp id is used to preserve the connection between objects in the bundle.
        // the id will be overwritten when the object is saved
        String tempId = UUID.randomUUID().toString();
        serviceRequest.setId(tempId);
        serviceRequest.setSubject(originalServiceRequest.getSubject());
        serviceRequest.addBasedOn(fhirTransformService.createReferenceFor(originalServiceRequest));
        serviceRequest.setCode(new CodeableConcept()
                .addCoding(new Coding().setCode(analysis.getTest().getLoinc()).setSystem("http://loinc.org")));
        return serviceRequest;
    }

    public Task createReferralTask(Organization referralOrganization, Patient patient,
            List<ServiceRequest> serviceRequests, String labNumber) {
        Bundle bundle = new Bundle();
        Task task = new Task();
        task.setGroupIdentifier(
                new Identifier().setValue(labNumber).setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber"));
        // TODO put the referral reason into the code
        task.setReasonCode(new CodeableConcept()
                .addCoding(new Coding().setSystem(fhirConfig.getOeFhirSystem() + "/refer_reason")));
        task.setOwner(fhirTransformService.createReferenceFor(referralOrganization));
        task.setStatus(TaskStatus.REQUESTED);
        task.setFor(fhirTransformService.createReferenceFor(patient));
        task.setBasedOn(serviceRequests.stream().map(e -> fhirTransformService.createReferenceFor(e))
                .collect(Collectors.toList()));

        bundle.addEntry(new BundleEntryComponent().setResource(task));
        return task;
    }

    @Override
    public Bundle referAnalysisesToOrganization(String referralOrganizationId, String analysisId) {
        IGenericClient localClient = fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());
        org.openelisglobal.organization.valueholder.Organization referralOrganization = organizationService
                .get(referralOrganizationId);
        List<Resource> newResources = new ArrayList<>();
        Analysis analysis = analysisService.get(analysisId);
        Sample sample = sampleService.getSampleByAccessionNumber(analysisService.getOrderAccessionNumber(analysis));
        Bundle serviceRequestAndPatientBundle = localClient.search().forResource(Patient.class)
                .returnBundle(Bundle.class).where(ServiceRequest.CODE.exactly().code(sample.getAccessionNumber()))
                .include(ServiceRequest.INCLUDE_SUBJECT).execute();

        List<ServiceRequest> serviceRequests = createServiceRequestsForReferredTest(Arrays.asList(analysis), sample,
                serviceRequestAndPatientBundle);
        Task task = createReferralTask(getFhirOrganization(referralOrganization),
                getFhirPatient(serviceRequestAndPatientBundle), serviceRequests, sample.getAccessionNumber());

        newResources.add(task);
        newResources.addAll(serviceRequests);
        return fhirPersistanceService.createFhirResourcesInFhirStore(newResources);
    }

}
