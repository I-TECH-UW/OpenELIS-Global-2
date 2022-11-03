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
            JSONObject testObj = new JSONObject();
            testObj.put("label", test.getValue());
            testObj.put("value", test.getId());
            testsArray.put(testObj);
        });
        return testsArray.toString();
    }

    @GetMapping(value = "samples", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSeamples(HttpServletRequest request) {
        JSONArray sampleArray = new JSONArray();
        DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE).forEach(sample -> {
            JSONObject sampleObj = new JSONObject();
            sampleObj.put("label", sample.getValue());
            sampleObj.put("value", sample.getId());
            sampleArray.put(sampleObj);
        });
        return sampleArray.toString();
    }
}
