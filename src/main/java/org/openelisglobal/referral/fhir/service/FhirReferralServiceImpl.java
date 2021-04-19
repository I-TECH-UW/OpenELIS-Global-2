package org.openelisglobal.referral.fhir.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.TaskStatus;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirApiWorkFlowServiceImpl.ReferralResultsImportObjects;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.result.action.util.ResultSet;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;
import org.openelisglobal.result.service.LogbookResultsPersistService;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FhirReferralServiceImpl implements FhirReferralService {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private SampleHumanService sampleHumanService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private FhirTransformService fhirTransformService;
    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private LogbookResultsPersistService logbookPersistService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private FhirConfig fhirConfig;
    @Value("${org.openelisglobal.remote.source.identifier:}#{T(java.util.Collections).emptyList()}")
    private List<String> remoteStoreIdentifier;

//    @Override
//    @Transactional
//    public Bundle cancelReferralToOrganization(String referralOrganizationId, String sampleId,
//            List<String> analysisIds) throws FhirLocalPersistingException {
//        org.openelisglobal.organization.valueholder.Organization referralOrganization = organizationService
//                .get(referralOrganizationId);
//        Organization fhirOrg = getFhirOrganization(referralOrganization);
//        if (fhirOrg == null) {
//            // organization doesn't exist as fhir organization, cannot cancel automatically
//            return new Bundle();
//        }
//        Sample sample = sampleService.get(sampleId);
//        List<Analysis> analysises = analysisService.get(analysisIds);
//
//        List<ServiceRequest> serviceRequests = new ArrayList<>();
//        for (Analysis analysis : analysises) {
//            serviceRequests.add(fhirPersistanceService.getServiceRequestByAnalysisUuid(analysis.getFhirUuidAsString())
//                    .orElseThrow());
//        }
//
//        Task task = this.fhirPersistanceService.getTaskBasedOnServiceRequests(serviceRequests).orElseThrow();
//        task.setStatus(TaskStatus.CANCELLED);
//        return fhirPersistanceService.updateFhirResourceInFhirStore(task);
//    }

    @Override
    @Transactional
    public Bundle referAnalysisesToOrganization(String referralOrganizationId, String sampleId,
            List<String> analysisIds) throws FhirLocalPersistingException {
        org.openelisglobal.organization.valueholder.Organization referralOrganization = organizationService
                .get(referralOrganizationId);
        Organization fhirOrg = getFhirOrganization(referralOrganization);
        if (fhirOrg == null) {
            // organization doesn't exist as fhir organization, cannot refer automatically
            return new Bundle();
        }
        Map<String, Resource> newResources = new HashMap<>();
        Sample sample = sampleService.get(sampleId);

        List<Analysis> analysises = analysisService.get(analysisIds);
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        for (Analysis analysis : analysises) {
            serviceRequests.add(fhirPersistanceService.getServiceRequestByAnalysisUuid(analysis.getFhirUuidAsString())
                    .orElseThrow());
        }
        Task task = createReferralTask(fhirOrg, fhirPersistanceService
                .getPatientByUuid(sampleHumanService.getPatientForSample(sample).getFhirUuidAsString()).orElseThrow(),
                serviceRequests);

        return fhirPersistanceService.createFhirResourceInFhirStore(task);
    }

    private Organization getFhirOrganization(org.openelisglobal.organization.valueholder.Organization organization) {
        return fhirPersistanceService.getFhirOrganizationByName(organization.getOrganizationName()).orElseThrow();
    }

    public Task createReferralTask(Organization referralOrganization, Patient patient,
            List<ServiceRequest> serviceRequests) {
        Bundle bundle = new Bundle();
        Task task = new Task();
//        task.setGroupIdentifier(
//                new Identifier().setValue(labNumber).setSystem(fhirConfig.getOeFhirSystem() + "/samp_labNumber"));
        // TODO put the referral reason into the code
        task.setReasonCode(new CodeableConcept()
                .addCoding(new Coding().setSystem(fhirConfig.getOeFhirSystem() + "/refer_reason")));
        task.setOwner(fhirTransformService.createReferenceFor(referralOrganization));
        if (!remoteStoreIdentifier.isEmpty()) {
            task.setRequester(new Reference(remoteStoreIdentifier.get(0)));
        }
        task.setStatus(TaskStatus.REQUESTED);
        task.setFor(fhirTransformService.createReferenceFor(patient));
        task.setBasedOn(serviceRequests.stream().map(e -> fhirTransformService.createReferenceFor(e))
                .collect(Collectors.toList()));

        bundle.addEntry(new BundleEntryComponent().setResource(task));
        return task;
    }

    @Override
    @Transactional
    public void setReferralResult(ReferralResultsImportObjects resultsImport) {
        Analysis analysis = analysisService
                .getMatch("fhirUuid",
                        UUID.fromString(
                                resultsImport.originalReferralObjects.serviceRequest.getIdElement().getIdPart()))
                .orElseThrow();
        List<Result> currentResults = resultService.getResultsByAnalysis(analysis);

        ResultsUpdateDataSet resultsUpdateDataSet = new ResultsUpdateDataSet("1");
        List<Analysis> modifiedAnalysis = resultsUpdateDataSet.getModifiedAnalysis();

        modifiedAnalysis.add(analysis);
        analysis.setEnteredDate(DateUtil.getNowAsTimestamp());
        analysis.setSysUserId("1");
        for (Observation observation : resultsImport.observations) {
            Result result = new Result();
            if (currentResults.size() == 1 && resultsImport.observations.size() == 1) {
                result = currentResults.get(0);
            } else {
                result = new Result();
                TestResult testResult = result.getTestResult();
                String testResultType = testResult.getTestResultType();
                result.setResultType(testResultType);
                result.setAnalysis(analysis);
                currentResults.stream().forEach(e -> {
                    resultService.delete(e);
                });
            }

            Sample sample = analysis.getSampleItem().getSample();
            if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(result.getResultType())
                    || TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
                String inferredValue = ((StringType) observation.getValue()).getValueAsString();
                List<TestResult> testResults = testResultService
                        .getAllActiveTestResultsPerTest(analysisService.getTest(analysis));
                dictionaryService.getMatch("dictEntry", inferredValue).orElseThrow();
                for (TestResult testResult : testResults) {
                    if (StringUtils.equals(inferredValue,
                            dictionaryService.get(testResult.getValue()).getDictEntry())) {
                        result.setValue(dictionaryService.get(testResult.getValue()).getId());
                    }
                }
            } else if (TypeOfTestResultServiceImpl.ResultType.isNumeric(result.getResultType())) {
                result.setValue(((Quantity) observation.getValue()).getValue().toPlainString());
            } else if (TypeOfTestResultServiceImpl.ResultType.isTextOnlyVariant(result.getResultType())) {
                result.setValue(((StringType) observation.getValue()).getValueAsString());
            }

            result.setSysUserId("1");

            if (GenericValidator.isBlankOrNull(result.getId())) {
                resultsUpdateDataSet.getNewResults()
                        .add(new ResultSet(result, null, null, sampleService.getPatient(sample),
                                analysis.getSampleItem().getSample(), new HashMap<>(),
                                resultsImport.observations.size() > 1));
            } else {
                resultsUpdateDataSet.getModifiedResults()
                        .add(new ResultSet(result, null, null, sampleService.getPatient(sample),
                                analysis.getSampleItem().getSample(), new HashMap<>(),
                                resultsImport.observations.size() > 1));
            }
        }
        resultsUpdateDataSet.setPreviousAnalysis(analysis);

        logbookPersistService.persistDataSet(resultsUpdateDataSet, new ArrayList<>(), "1");
        try {
            fhirTransformService.transformPersistResultsEntryFhirObjects(resultsUpdateDataSet);
            resultsImport.originalReferralObjects.task.setStatus(TaskStatus.COMPLETED);
            fhirPersistanceService.updateFhirResourceInFhirStore(resultsImport.originalReferralObjects.task);
        } catch (FhirTransformationException | FhirPersistanceException e) {
            LogEvent.logError(e);
        }
    }

}
