package org.openelisglobal.testreflex.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.openelisglobal.common.util.LabelValuePair;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.test.beanItems.TestDisplayBean;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.action.bean.ReflexRuleOptionsDisplayItem;
import org.openelisglobal.testreflex.action.bean.ReflexRuleOptions;
import org.openelisglobal.testreflex.service.TestReflexService;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
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

@Controller
@RequestMapping(value = "/rest/")
public class TestReflexRuleRestController {

    @Autowired
    TestReflexService reflexService;
    @Autowired
    private TestService testService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    TypeOfSampleService typeOfSampleService;

    @PostMapping(value = "reflexrule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void saveReflexRule(HttpServletRequest request, @RequestBody ReflexRule reflexRule) {
        reflexService.saveOrUpdateReflexRule(reflexRule);
    }

    @PostMapping(value = "deactivate-reflexrule/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deactivateReflexRule(@PathVariable String id) {
        reflexService.deactivateReflexRule(id);
    }

    @GetMapping(value = "reflexrules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ReflexRule> getReflexRules(HttpServletRequest request) {
        List<ReflexRule> rules = reflexService.getAllReflexRules().stream()
                .filter(rule -> Boolean.TRUE.equals(rule.getActive())).collect(Collectors.toList());
        rules.forEach(rule -> rule.setToggled(false));
        return !rules.isEmpty() ? rules : Collections.<ReflexRule>emptyList();
    }

    @GetMapping(value = "reflexrule-options", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReflexRuleOptionsDisplayItem getReflexRuleOptions() {
        ReflexRuleOptionsDisplayItem options = new ReflexRuleOptionsDisplayItem();
        List<LabelValuePair> overallOptions = new ArrayList<>();
        ReflexRuleOptions.OverallOptions.stream()
                .forEach(option -> overallOptions.add(new LabelValuePair(option.getDisplayName(), option.name())));
        List<LabelValuePair> generalRelationOptions = new ArrayList<>();
        ReflexRuleOptions.GeneralRelationOptions.stream()
                .forEach(
                        option -> generalRelationOptions
                                .add(new LabelValuePair(option.getDisplayName(), option.name())));
        List<LabelValuePair> numericRelationOptions = new ArrayList<>();
        ReflexRuleOptions.NumericRelationOptions.stream().forEach(
                option -> numericRelationOptions
                        .add(new LabelValuePair(option.getDisplayName(), option.name())));
        options.setOverallOptions(overallOptions);
        options.setGeneralRelationOptions(generalRelationOptions);
        options.setNumericRelationOptions(numericRelationOptions);
        return options;
    }

    @GetMapping(value = "test-details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDisplayBean> getTestList(HttpServletRequest request, @RequestParam String sampleType) {
        ArrayList<TestDisplayBean> tests = new ArrayList<>();
        List<Test> testList = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleType)) {
            testList = typeOfSampleService.getActiveTestsBySampleTypeId(sampleType, false);
        } else {
            testList = testService.getAllActiveTests(false);
        }
        for (Test test : testList) {
            TestDisplayBean testObj = new TestDisplayBean();
            testObj.setLabel(TestServiceImpl.getLocalizedTestNameWithType(test));
            testObj.setValue(test.getId());
            testObj.setResultType(testService.getResultType(test));
            List<LabelValuePair> resultList = new ArrayList<>();
            List<TestResult> results = testResultService.getActiveTestResultsByTest(test.getId());
            results.forEach(result -> {
                if (result.getValue() != null) {
                    Dictionary dict = dictionaryService.getDictionaryById(result.getValue());
                    resultList.add(new LabelValuePair(dict.getDictEntryDisplayValue(), dict.getId()));
                }
            });
            testObj.setResultList(resultList);
            tests.add(testObj);

            Collections.sort(tests, new Comparator<TestDisplayBean>() {
                @Override
                public int compare(TestDisplayBean o1, TestDisplayBean o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
        }

        return tests;
    }

}
