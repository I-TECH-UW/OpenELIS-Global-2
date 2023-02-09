package org.openelisglobal.testreflex.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openelisglobal.common.util.LabelValuePair;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.test.beanItems.TestDisplayBean;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.action.bean.ReflexRuleOtions;
import org.openelisglobal.testreflex.service.TestReflexService;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    DictionaryService dictionaryService ;

    @PostMapping(value = "reflexrule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void postReflexRule(HttpServletRequest request, @RequestBody ReflexRule reflexRule) {
        reflexService.saveOrUpdateReflexRule(reflexRule);

    }

    @GetMapping(value = "reflexrules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ReflexRule> getReflexRules(HttpServletRequest request) {
        List<ReflexRule> rules = reflexService.getAllReflexRules();
        rules.forEach(rule -> rule.setToggled(false));
        return !rules.isEmpty() ? rules : Collections.<ReflexRule>emptyList();
    }


    @GetMapping(value = "reflexrule-options", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getReflexRuleOptions() {
        JSONObject options = new JSONObject();
        JSONArray overallOptions = new JSONArray();
        ReflexRuleOtions.OverallOptions.stream().forEach(option -> overallOptions.put(new JSONObject().put("label", option.getDisplayName()).put("value", option.name())));
        JSONArray generalRelationOptions = new JSONArray();
        ReflexRuleOtions.GeneralRelationOptions.stream().forEach(option -> generalRelationOptions.put(new JSONObject().put("label", option.getDisplayName()).put("value", option.name())));
        JSONArray numericRelationOptions = new JSONArray();
        ReflexRuleOtions.NumericRelationOptions.stream().forEach(option -> numericRelationOptions.put(new JSONObject().put("label", option.getDisplayName()).put("value", option.name())));
        JSONArray actionOptions = new JSONArray();
        ReflexRuleOtions.ActionOptions.stream().forEach(option -> actionOptions.put(new JSONObject().put("label", option.getDisplayName()).put("value", option.name())));
        options.put("overallOptions" , overallOptions);
        options.put("generalRelationOptions" , generalRelationOptions);
        options.put("numericRelationOptions" , numericRelationOptions);
        options.put("actionOptions" , actionOptions);
        
        return options.toString();
    }


    @GetMapping(value = "test-details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDisplayBean> createTestList() {
        ArrayList<TestDisplayBean> tests = new ArrayList<>();
        List<Test> testList = testService.getAllActiveTests(false);
        for (Test test : testList) {
            TestDisplayBean testObj = new TestDisplayBean();
            testObj.setLabel(TestServiceImpl.getLocalizedTestNameWithType(test));
            testObj.setValue(test.getId());
            testObj.setResultType(testService.getResultType(test));
            List<LabelValuePair> resultList = new ArrayList<>();
            List<TestResult> results = testResultService.getActiveTestResultsByTest(test.getId());
            results.forEach(result -> {
                if(result.getValue()!= null){
                    System.out.println(result.getValue());
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
