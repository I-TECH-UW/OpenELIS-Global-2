package org.openelisglobal.search.service;

import java.util.List;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchResultsController {

    @Autowired
    private SearchResultsService searchResultsService;

    @GetMapping("/search")
    public @ResponseBody List<PatientSearchResults> getSearchResults(@RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName, @RequestParam(required = false) String STNumber,
            @RequestParam(required = false) String subjectNumber, @RequestParam(required = false) String nationalID,
            @RequestParam(required = false) String externalID, @RequestParam(required = false) String patientID,
            @RequestParam(required = false) String guid, @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String gender) {
        return searchResultsService.getSearchResults(lastName, firstName, STNumber, subjectNumber, nationalID,
                externalID, patientID, guid, dateOfBirth, gender);
    }

    @GetMapping("/search/exact")
    public @ResponseBody List<PatientSearchResults> getSearchResultsExact(
            @RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String STNumber, @RequestParam(required = false) String subjectNumber,
            @RequestParam(required = false) String nationalID, @RequestParam(required = false) String externalID,
            @RequestParam(required = false) String patientID, @RequestParam(required = false) String guid,
            @RequestParam(required = false) String dateOfBirth, @RequestParam(required = false) String gender) {
        return searchResultsService.getSearchResultsExact(lastName, firstName, STNumber, subjectNumber, nationalID,
                externalID, patientID, guid, dateOfBirth, gender);
    }
}
