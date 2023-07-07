package org.openelisglobal.testcalculated.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testcalculated.action.bean.TestBeanItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;

@Controller
@RequestMapping(value = "/rest/")
public class CalculatedValueRestController {
    
    @Autowired
    TypeOfSampleService typeOfSampleService;
    
    @Autowired
    private TestService testService;
    
    @GetMapping(value = "test-beans-by-sample", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestBeanItem> getTestsBySample(@RequestParam String sampleType) {
        List<TestBeanItem> tests = new ArrayList<>();
        List<Test> testList = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleType)) {
            testList = typeOfSampleService.getActiveTestsBySampleTypeId(sampleType, false);
        } else {
            return tests;
        }
        
        testList.forEach(test -> {
            tests.add(new TestBeanItem(test.getId(), TestServiceImpl.getLocalizedTestNameWithType(test) ,testService.getResultType(test)));
        });
        return tests;
    }
    
}
