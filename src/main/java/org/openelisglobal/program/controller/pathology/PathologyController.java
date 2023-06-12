package org.openelisglobal.program.controller.pathology;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.program.service.PathologyDisplayService;
import org.openelisglobal.program.service.PathologySampleService;
import org.openelisglobal.program.valueholder.pathology.PathologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologyDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologySample.PathologyStatus;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathologyController extends BaseRestController {

    @Autowired
    private PathologySampleService pathologySampleService;
    @Autowired
    private PathologyDisplayService pathologyDisplayService;
    @Autowired
    private SystemUserService systemUserService;

    @GetMapping(value = "/rest/pathology/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PathologyDisplayItem> getFilteredPathologyEntries(@RequestParam(required = false) String searchTerm,
            @RequestParam PathologyStatus... statuses) {
        return pathologySampleService.getWithStatus(Arrays.asList(statuses)).stream()
                .map(e -> pathologyDisplayService.convertToDisplayItem(e.getId()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/rest/pathology/dashboard/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Long> getFilteredPathologyEntries(@RequestParam PathologyStatus... statuses) {
        return ResponseEntity.ok(pathologySampleService.getCountWithStatus(Arrays.asList(statuses)));
    }

    @PostMapping(value = "/rest/pathology/assignTechnician", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> assignTechnician(@RequestParam Integer pathologySampleId,
            HttpServletRequest request) {
        String currentUserId = getSysUserId(request);
        pathologySampleService.assignTechnician(pathologySampleId, systemUserService.get(currentUserId));
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/rest/pathology/assignPathologist", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> assignPathologist(@RequestParam Integer pathologySampleId,
            HttpServletRequest request) {
        String currentUserId = getSysUserId(request);
        pathologySampleService.assignPathologist(pathologySampleId, systemUserService.get(currentUserId));
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/rest/pathology/caseView/{pathologySampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PathologyCaseViewDisplayItem getFilteredPathologyEntries(
            @PathVariable("pathologySampleId") Integer pathologySampleId) {
        return pathologyDisplayService.convertToCaseDisplayItem(pathologySampleId);
    }

    @PostMapping(value = "/rest/pathology/caseView/{pathologySampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PathologySampleForm getFilteredPathologyEntries(@PathVariable("pathologySampleId") Integer pathologySampleId,
            @RequestBody PathologySampleForm form) {
        pathologySampleService.updateWithFormValues(pathologySampleId, form);

        return form;
    }
}
