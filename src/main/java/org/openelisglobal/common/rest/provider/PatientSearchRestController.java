package org.openelisglobal.common.rest.provider;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.service.ObservationHistoryServiceImpl.ObservationType;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.search.service.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class PatientSearchRestController {

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

    @GetMapping(value = "patient-search-results", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PatientSearchResults> getPatientResults(@RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName, 
            @RequestParam(required = false) String STNumber,
            @RequestParam(required = false) String subjectNumber,
            @RequestParam(required = false) String nationalID,
            @RequestParam(required = false) String guid,
            @RequestParam(required = false) String labNumber, 
            @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender) {

        List<PatientSearchResults> results = new ArrayList<>();
        if (!GenericValidator.isBlankOrNull(labNumber)) {
            Patient patient = getPatientForLabNumber(labNumber);
            if (patient == null || GenericValidator.isBlankOrNull(patient.getId())) {
                return Collections.<PatientSearchResults>emptyList();
            } else {
                PatientSearchResults searchResult = getSearchResultsForPatient(patient, null);
                searchResult.setDataSourceName(MessageUtil.getMessage("patient.local.source"));
                results.add(searchResult);

            }
        } else {
            if (GenericValidator.isBlankOrNull(lastName) && GenericValidator.isBlankOrNull(firstName)
                    && GenericValidator.isBlankOrNull(STNumber) && GenericValidator.isBlankOrNull(subjectNumber)
                    && GenericValidator.isBlankOrNull(nationalID) && GenericValidator.isBlankOrNull(guid) && GenericValidator.isBlankOrNull(dateOfBirth)
                    && GenericValidator.isBlankOrNull(gender)) {
                return Collections.<PatientSearchResults>emptyList();

            }

            results = searchResultsService.getSearchResults(lastName, firstName, STNumber,
                    subjectNumber, nationalID, nationalID, null, guid, dateOfBirth, gender);
            if (!GenericValidator.isBlankOrNull(nationalID)) {
                List<PatientSearchResults> observationResults = getObservationsByReferringPatientId(nationalID);
                results.addAll(observationResults);
            }
            sortPatients(results);
        }
        return results;
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
                patientService.getNationalId(patient), patient.getExternalId(),
                patientService.getSTNumber(patient), patientService.getSubjectNumber(patient),
                patientService.getGUID(patient),
                referringSitePatientId != null ? referringSitePatientId
                        : observationHistoryService.getMostRecentValueForPatient(
                                ObservationType.REFERRERS_PATIENT_ID, patientService.getPatientId(patient)));
    }

    private List<PatientSearchResults> getObservationsByReferringPatientId(String referringId) {
        List<PatientSearchResults> resultList = new ArrayList<>();
        List<ObservationHistory> observationList = observationHistoryService
                .getObservationsByTypeAndValue(ObservationType.REFERRERS_PATIENT_ID, referringId);

        if (observationList != null) {
            for (ObservationHistory observation : observationList) {
                Patient patient = patientService.getData(observation.getPatientId());
                if (patient != null) {
                    PatientSearchResults searchResult = getSearchResultsForPatient(patient, referringId);
                    searchResult.setDataSourceName(MessageUtil.getMessage("patient.local.source"));
                    resultList.add(searchResult);
                }

            }
        }

        return resultList;
    }

    private void sortPatients(List<PatientSearchResults> foundList) {
        Collections.sort(foundList, new FoundListComparator());
    }

    class FoundListComparator implements Comparator<PatientSearchResults> {

        @Override
        public int compare(PatientSearchResults o1, PatientSearchResults o2) {
            if (o1.getLastName() == null) {
                return o2.getLastName() == null ? 0 : 1;
            } else if (o2.getLastName() == null) {
                return -1;
            }

            int lastNameResults = o1.getLastName().compareToIgnoreCase(o2.getLastName());

            if (lastNameResults == 0) {
                if (GenericValidator.isBlankOrNull(o1.getFirstName())
                        && GenericValidator.isBlankOrNull(o2.getFirstName())) {
                    return 0;
                }

                String oneName = (o1.getFirstName() == null) ? " " : o1.getFirstName();
                String twoName = (o2.getFirstName() == null) ? " " : o2.getFirstName();
                return oneName.compareToIgnoreCase(twoName);
            } else {
                return lastNameResults;
            }
        }

    }

}
