package org.openelisglobal.testcalculated.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testcalculated.action.bean.TestBeanItem;
import org.openelisglobal.testcalculated.service.TestCalculationService;
import org.openelisglobal.testcalculated.valueholder.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    TestCalculationService testCalculationService;
    
    @PostMapping(value = "test-calculation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void saveReflexRule(HttpServletRequest request, @RequestBody Calculation calculation) {
        if (calculation.getId() != null) {
            if (testCalculationService.get(calculation.getId()) != null) {
                testCalculationService.update(calculation);
            }
        } else {
            testCalculationService.save(calculation);
        }
    }
    
    @PostMapping(value = "deactivate-test-calculation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deactivateReflexRule(@PathVariable Integer id) {
        Calculation calculation = testCalculationService.get(id);
        calculation.setActive(false);
        testCalculationService.update(calculation);
    }

    @GetMapping(value = "test-calculations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Calculation> getReflexRules(HttpServletRequest request) {
        List<Calculation> calculations = testCalculationService.getAll().stream()
                .filter(c -> Boolean.TRUE.equals(c.getActive())).collect(Collectors.toList());
        calculations.forEach(c -> c.setToggled(false));
        return !calculations.isEmpty() ? calculations : Collections.<Calculation>emptyList();
    }
    
    @GetMapping(value = "test-beans-by-sample", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestBeanItem> getTestsBySample(@RequestParam(required = false) String sampleType) {
        List<TestBeanItem> tests = new ArrayList<>();
        List<Test> testList = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleType)) {
            testList = typeOfSampleService.getActiveTestsBySampleTypeId(sampleType, false);
        } else {
            return tests;
        }
        
        testList.forEach(test -> {
            tests.add(new TestBeanItem(test.getId(), TestServiceImpl.getLocalizedTestNameWithType(test),
                    testService.getResultType(test)));
        });
        return tests;
    }
    
}
