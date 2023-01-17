package org.openelisglobal.testreflex.controller.rest;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.service.TestReflexService;
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

}
