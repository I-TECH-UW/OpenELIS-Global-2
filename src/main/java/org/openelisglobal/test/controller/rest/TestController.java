package org.openelisglobal.test.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openelisglobal.common.services.DisplayListService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class TestController {

    @GetMapping(value = "tests", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTests(HttpServletRequest request) {
        JSONArray testsArray = new JSONArray();
        DisplayListService.getInstance().getList(DisplayListService.ListType.ALL_TESTS).forEach(test -> {
            testsArray.put(test.getValue());
        });

        return testsArray.toString();
    }

    @GetMapping(value = "samples", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSeamples(HttpServletRequest request) {
        JSONArray sampleArray = new JSONArray();
        DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE).forEach(test -> {
            JSONObject sampleObj = new JSONObject();
            sampleObj.put("label", test.getValue());
            sampleObj.put("value", test.getId());
            sampleArray.put(sampleObj);
        });

        return sampleArray.toString();
    }


}
