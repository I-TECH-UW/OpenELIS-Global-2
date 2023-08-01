package org.openelisglobal.program.controller.immunohistochemistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.program.service.ImmunohistochemistryDisplayService;
import org.openelisglobal.program.service.ImmunohistochemistrySampleService;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistryCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistryDisplayItem;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample.ImmunohistochemistryStatus;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImmunohistochemistryController extends BaseRestController {

    @Autowired
    private ImmunohistochemistrySampleService immunohistochemistrySampleService;
    @Autowired
    private ImmunohistochemistryDisplayService immunohistochemistryDisplayService;
    @Autowired
    private SystemUserService systemUserService;

    @GetMapping(value = "/rest/immunohistochemistry/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ImmunohistochemistryDisplayItem> getFilteredImmunohistochemistryEntries(
            @RequestParam(required = false) String searchTerm, @RequestParam ImmunohistochemistryStatus... statuses) {
        return immunohistochemistrySampleService.getWithStatus(Arrays.asList(statuses)).stream()
                .map(e -> immunohistochemistryDisplayService.convertToDisplayItem(e.getId()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/rest/immunohistochemistry/dashboard/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Long> getFilteredImmunohistochemistryEntries(
            @RequestParam ImmunohistochemistryStatus... statuses) {
        return ResponseEntity.ok(immunohistochemistrySampleService.getCountWithStatus(Arrays.asList(statuses)));
    }

    @PostMapping(value = "/rest/immunohistochemistry/assignTechnician", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> assignTechnician(@RequestParam Integer immunohistochemistrySampleId,
            HttpServletRequest request) {
        String currentUserId = getSysUserId(request);
        immunohistochemistrySampleService.assignTechnician(immunohistochemistrySampleId,
                systemUserService.get(currentUserId));
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/rest/immunohistochemistry/assignPathologist", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> assignPathologist(@RequestParam Integer immunohistochemistrySampleId,
            HttpServletRequest request) {
        String currentUserId = getSysUserId(request);
        immunohistochemistrySampleService.assignPathologist(immunohistochemistrySampleId,
                systemUserService.get(currentUserId));
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/rest/immunohistochemistry/caseView/{immunohistochemistrySampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ImmunohistochemistryCaseViewDisplayItem getFilteredImmunohistochemistryEntries(
            @PathVariable("immunohistochemistrySampleId") Integer immunohistochemistrySampleId) {
        return immunohistochemistryDisplayService.convertToCaseDisplayItem(immunohistochemistrySampleId);
    }

//    @PostMapping(value = "/rest/immunohistochemistry/caseView/{immunohistochemistrySampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ImmunohistochemistrySampleForm getFilteredImmunohistochemistryEntries(@PathVariable("immunohistochemistrySampleId") Integer immunohistochemistrySampleId,
//            @RequestBody ImmunohistochemistrySampleForm form, HttpServletRequest request) {
//        form.setSystemUserId(this.getSysUserId(request));
//        immunohistochemistrySampleService.updateWithFormValues(immunohistochemistrySampleId, form);
//
//        return form;
//    }
}
