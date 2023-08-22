package org.openelisglobal.testcalculated.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testcalculated.action.bean.TestDisplayBeanItem;
import org.openelisglobal.testcalculated.service.ResultCalculationService;
import org.openelisglobal.testcalculated.service.TestCalculationService;
import org.openelisglobal.testcalculated.valueholder.Calculation;
import org.openelisglobal.testcalculated.valueholder.Operation;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
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
    
    @Autowired
    private TestResultService testResultService;
    
    @Autowired
    DictionaryService dictionaryService;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    ResultService resultService;
    
    @Autowired
    ResultCalculationService resultCalculationService;
    
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
        return !calculations.isEmpty() ? calculations : Collections.<Calculation> emptyList();
    }
    
    @GetMapping(value = "test-display-beans", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDisplayBeanItem> getTestsBySample(@RequestParam(required = false) String sampleType) {
        List<TestDisplayBeanItem> testItems = new ArrayList<>();
        List<Test> testList = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleType)) {
            testList = typeOfSampleService.getActiveTestsBySampleTypeId(sampleType, false);
        } else {
            return testItems;
        }
        
        for (Test test : testList) {
            TestDisplayBeanItem testDisplayBean = new TestDisplayBeanItem(test.getId(),
                    TestServiceImpl.getLocalizedTestNameWithType(test), testService.getResultType(test));
            List<IdValuePair> resultList = new ArrayList<>();
            List<TestResult> results = testResultService.getActiveTestResultsByTest(test.getId());
            results.forEach(result -> {
                if (result.getValue() != null) {
                    Dictionary dict = dictionaryService.getDictionaryById(result.getValue());
                    resultList.add(new IdValuePair(dict.getId(), dict.getDictEntryDisplayValue()));
                }
            });
            testDisplayBean.setResultList(resultList);
            testItems.add(testDisplayBean);
            
            Collections.sort(testItems, new Comparator<TestDisplayBeanItem>() {
                
                @Override
                public int compare(TestDisplayBeanItem o1, TestDisplayBeanItem o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
        }
        return testItems;
    }
    
    @GetMapping(value = "math-functions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<IdValuePair> getMathFunctions() {
        return Operation.mathFunctions();
    }
    
}
