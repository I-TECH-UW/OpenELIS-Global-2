package org.openelisglobal.common.rest.provider;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IOperationUntypedWithInputAndPartialOutput;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.param.StringOrListParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.*;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.common.provider.query.PatientSearchResultsForm;
import org.openelisglobal.common.provider.query.workerObjects.PatientSearchLocalAndExternalWorker;
import org.openelisglobal.common.provider.query.workerObjects.PatientSearchLocalWorker;
import org.openelisglobal.common.provider.query.workerObjects.PatientSearchWorker;
import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.common.rest.util.PatientSearchResultsPaging;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.service.ObservationHistoryServiceImpl.ObservationType;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.search.service.SearchResultsService;
import org.openelisglobal.spring.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class PatientSearchRestController extends BaseRestController {

    @Autowired
    private FhirConfig fhirConfig;
    @Autowired
    private FhirUtil fhirUtil;
    @Autowired
    SampleService sampleService;
    @Autowired
    PatientService patientService;
    @Autowired
    PersonService personService;
    @Autowired
    ObservationHistoryService observationHistoryService;
    @Autowired
    SampleHumanService sampleHumanService;
    @Autowired
    SearchResultsService searchResultsService;

    StringOrListParam targetSystemsParam;

    private static final Logger log = LoggerFactory.getLogger(PatientSearchRestController.class);

    @GetMapping(value = "patient-search-results", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientSearchResultsForm getPatientResults(HttpServletRequest request,
            @RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String STNumber, @RequestParam(required = false) String subjectNumber,
            @RequestParam(required = false) String nationalID, @RequestParam(required = false) String guid,
            @RequestParam(required = false) String labNumber, @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String suppressExternalSearch)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PatientSearchResultsPaging paging = new PatientSearchResultsPaging();
        PatientSearchResultsForm form = new PatientSearchResultsForm();

        String requestedPage = request.getParameter("page");
        if (GenericValidator.isBlankOrNull(requestedPage)) {
            List<PatientSearchResults> results = new ArrayList<>();
            if (!GenericValidator.isBlankOrNull(labNumber)) {
                Patient patient = getPatientForLabNumber(labNumber);
                if (patient == null || GenericValidator.isBlankOrNull(patient.getId())) {
                    form.setPatientSearchResults(results);
                    return form;
                } else {
                    PatientSearchResults searchResult = getSearchResultsForPatient(patient, null);
                    searchResult.setDataSourceName(MessageUtil.getMessage("patient.local.source"));
                    results.add(searchResult);
                }
            } else {
                PatientSearchWorker worker = getAppropriateWorker(request, "true".equals(suppressExternalSearch));
                if (worker != null) {
                    results = worker.getPatientSearchResults(lastName, firstName, STNumber, subjectNumber, nationalID,
                            null, guid, dateOfBirth, gender);
                } else {
                    form.setPatientSearchResults(results);
                    return form;
                }
            }

            if (request.getParameter("crResult") != null && request.getParameter("crResult").contains("true")) {
                List<PatientSearchResults> fhirResults = searchPatientInClientRegistry(lastName, firstName, STNumber,
                        subjectNumber, nationalID, null, guid, dateOfBirth, gender);
                results.addAll(fhirResults);
            }

            paging.setDatabaseResults(request, form, results);
        } else {
            int requestedPageNumber = Integer.parseInt(requestedPage);
            paging.page(request, form, requestedPageNumber);
        }
        return form;
    }

    private Patient getPatientForLabNumber(String labNumber) {

        Sample sample = sampleService.getSampleByAccessionNumber(labNumber);

        if (sample != null && !GenericValidator.isBlankOrNull(sample.getId())) {
            return sampleHumanService.getPatientForSample(sample);
        }

        return new Patient();
    }

    private PatientSearchResults getSearchResultsForPatient(Patient patient, String referringSitePatientId) {
        personService.getData(patient.getPerson());
        return new PatientSearchResults(BigDecimal.valueOf(Long.parseLong(patient.getId())),
                patientService.getFirstName(patient), patientService.getLastName(patient),
                patientService.getGender(patient), patientService.getEnteredDOB(patient),
                patientService.getNationalId(patient), patient.getExternalId(), patientService.getSTNumber(patient),
                patientService.getSubjectNumber(patient), patientService.getGUID(patient),
                referringSitePatientId != null ? referringSitePatientId
                        : observationHistoryService.getMostRecentValueForPatient(ObservationType.REFERRERS_PATIENT_ID,
                                patientService.getPatientId(patient)));
    }

    private PatientSearchWorker getAppropriateWorker(HttpServletRequest request, boolean suppressExternalSearch) {

        if (ConfigurationProperties.getInstance().isCaseInsensitivePropertyValueEqual(Property.UseExternalPatientInfo,
                "false") || suppressExternalSearch) {
            return new PatientSearchLocalWorker();
        } else {
            return new PatientSearchLocalAndExternalWorker(getSysUserId(request));
        }
    }

    @GetMapping("/patient-search")
    public @ResponseBody List<PatientSearchResults> getSearchResults(@RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName, @RequestParam(required = false) String STNumber,
            @RequestParam(required = false) String subjectNumber, @RequestParam(required = false) String nationalID,
            @RequestParam(required = false) String externalID, @RequestParam(required = false) String patientID,
            @RequestParam(required = false) String guid, @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender) {
        return searchResultsService.getSearchResults(lastName, firstName, STNumber, subjectNumber, nationalID,
                externalID, patientID, guid, dateOfBirth, gender);
    }

    private List<org.hl7.fhir.r4.model.Patient> parseCRPatientSearchResults(Bundle patientBundle) {
        return patientBundle.getEntry().stream()
                .filter(entry -> entry.getResource() instanceof org.hl7.fhir.r4.model.Patient)
                .map(entry -> (org.hl7.fhir.r4.model.Patient) entry.getResource()).collect(Collectors.toList());
    }

    private List<PatientSearchResults> searchPatientInClientRegistry(String lastName, String firstName, String STNumber,
            String subjectNumber, String nationalID, String patientID, String guid, String dateOfBirth, String gender) {
        if (isClientRegistryConfigInvalid()) {
            return new ArrayList<>();
        }

        IGenericClient clientRegistry = fhirUtil.getFhirClient(fhirConfig.getClientRegistryServerUrl(),
                fhirConfig.getClientRegistryUserName(), fhirConfig.getClientRegistryPassword());

        List<PatientSearchResults> patientSearchResults = searchResultsService.getSearchResults(lastName, firstName,
                STNumber, subjectNumber, nationalID, null, patientID, guid, dateOfBirth, gender);

        List<PatientSearchResults> finalResults = new ArrayList<>();

        for (PatientSearchResults patientSearchResult : patientSearchResults) {
            List<String> crIdentifiers = fetchCRIdentifiers(clientRegistry, patientSearchResult);

            if (!crIdentifiers.isEmpty()) {
                List<org.hl7.fhir.r4.model.Patient> externalPatients = fetchExternalPatients(clientRegistry,
                        crIdentifiers);

                Patient openelis = patientService.getPatientByNationalId(patientSearchResult.getNationalId());
                for (org.hl7.fhir.r4.model.Patient externalPatient : externalPatients) {
                    Patient openElisPatient = SpringContext.getBean(FhirTransformService.class)
                            .transformToOpenElisPatient(openelis, externalPatient);
                    PatientSearchResults searchResult = getSearchResultsForPatient(openElisPatient, null);
                    searchResult.setDataSourceName(MessageUtil.getMessage("patient.cr.source"));
                    finalResults.add(searchResult);
                }
            }
        }

        return finalResults;
    }

    private List<String> fetchCRIdentifiers(IGenericClient clientRegistry, PatientSearchResults patientSearchResult) {
        List<String> targetSystems = targetSystemsParam == null ? Collections.emptyList()
                : targetSystemsParam.getValuesAsQueryTokens().stream().filter(Objects::nonNull)
                        .map(StringParam::getValue).collect(Collectors.toList());

        // Construct request to external FHIR client
        IOperationUntypedWithInputAndPartialOutput<Parameters> identifiersRequest = clientRegistry.operation()
                .onType("Patient").named("$ihe-pix").withSearchParameter(Parameters.class, "sourceIdentifier",
                        new TokenParam("http://openelis-global.org/pat_nationalId",
                                patientSearchResult.getNationalId()));

        if (!targetSystems.isEmpty()) {
            identifiersRequest.andSearchParameter("targetSystem", new StringParam(String.join(",", targetSystems)));
        }

        Parameters crMatchingParams = identifiersRequest.useHttpGet().execute();

        return crMatchingParams.getParameter().stream().filter(param -> Objects.equals(param.getName(), "targetId"))
                .map(param -> ((Reference) param.getValue()).getReference()).collect(Collectors.toList());
    }

    private List<org.hl7.fhir.r4.model.Patient> fetchExternalPatients(IGenericClient clientRegistry,
            List<String> crIdentifiers) {
        Bundle patientBundle = clientRegistry.search().forResource(org.hl7.fhir.r4.model.Patient.class)
                .where(new StringClientParam(org.hl7.fhir.r4.model.Patient.SP_RES_ID).matches().values(crIdentifiers))
                .returnBundle(Bundle.class).execute();

        return parseCRPatientSearchResults(patientBundle);
    }

    private boolean isClientRegistryConfigInvalid() {
        return GenericValidator.isBlankOrNull(fhirConfig.getClientRegistryServerUrl())
                || GenericValidator.isBlankOrNull(fhirConfig.getClientRegistryUserName())
                || GenericValidator.isBlankOrNull(fhirConfig.getClientRegistryPassword());
    }

}
