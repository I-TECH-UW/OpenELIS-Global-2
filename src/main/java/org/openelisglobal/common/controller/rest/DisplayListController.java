package org.openelisglobal.common.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.IdValuePair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class DisplayListController {

    @GetMapping(value = "tests", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<IdValuePair> getTests(HttpServletRequest request) {
        return DisplayListService.getInstance().getList(DisplayListService.ListType.ALL_TESTS);
    }

    @GetMapping(value = "samples", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<IdValuePair> getSamples(HttpServletRequest request) {
        return DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE);
    }

    @GetMapping(value = "health-regions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<IdValuePair> getHealthRegions(HttpServletRequest request) {
        return DisplayListService.getInstance().getList(DisplayListService.ListType.PATIENT_HEALTH_REGIONS);
    }
}
