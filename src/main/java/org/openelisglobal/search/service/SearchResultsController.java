package org.openelisglobal.search.service;

import java.util.List;
import java.util.stream.Stream;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.search.service.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/search")
public class SearchResultsController {

    @Autowired
    private SearchResultsService searchResultsService;

    @GetMapping("/results")
    public ResponseEntity<List<PatientSearchResults>> getSearchResults(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String STNumber,
            @RequestParam(required = false) String subjectNumber,
            @RequestParam(required = false) String nationalID,
            @RequestParam(required = false) String externalID,
            @RequestParam(required = false) String patientID,
            @RequestParam(required = false) String guid,
            @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender) {

        if (areAllParamsEmpty(lastName, firstName, STNumber, subjectNumber, nationalID, externalID, patientID, guid, dateOfBirth, gender)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<PatientSearchResults> results = searchResultsService.getSearchResults(
                lastName, firstName, STNumber, subjectNumber, nationalID, externalID, patientID, guid, dateOfBirth, gender);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/results/exact")
    public ResponseEntity<List<PatientSearchResults>> getSearchResultsExact(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String STNumber,
            @RequestParam(required = false) String subjectNumber,
            @RequestParam(required = false) String nationalID,
            @RequestParam(required = false) String externalID,
            @RequestParam(required = false) String patientID,
            @RequestParam(required = false) String guid,
            @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender) {

        if (areAllParamsEmpty(lastName, firstName, STNumber, subjectNumber, nationalID, externalID, patientID, guid, dateOfBirth, gender)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<PatientSearchResults> results = searchResultsService.getSearchResultsExact(
                lastName, firstName, STNumber, subjectNumber, nationalID, externalID, patientID, guid, dateOfBirth, gender);

        return ResponseEntity.ok(results);
    }

    private boolean areAllParamsEmpty(String... params) {
        return Stream.of(params).allMatch(param -> param == null || param.isEmpty());
    }
}
