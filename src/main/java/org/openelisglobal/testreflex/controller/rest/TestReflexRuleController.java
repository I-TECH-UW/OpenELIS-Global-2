package org.openelisglobal.testreflex.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.action.bean.ReflexRuleAction;
import org.openelisglobal.testreflex.action.bean.ReflexRuleCondition;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class TestReflexRuleController {

    @PostMapping(value = "reflexrule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void postReflexRule(HttpServletRequest request, @RequestBody List<ReflexRule> rules) {
        for (ReflexRule rule : rules) {
            System.out.println("<<<<< rule >>>>>>");
            System.out.println(rule.getId());
            System.out.println(rule.getRuleName());
            System.out.println(rule.getOverall());
            System.out.println(rule.getToggled());

            if (!rule.getConditions().isEmpty()) {
                for (ReflexRuleCondition condition : rule.getConditions()) {
                    System.out.println("*** condition ***");
                    System.out.println(condition.getId());
                    System.out.println(condition.getRelation());
                    System.out.println(condition.getSample());
                    System.out.println(condition.getTest());
                    System.out.println(condition.getTestId());
                    System.out.println(condition.getValue());
                }
            }
            if (!rule.getActions().isEmpty()) {
                for (ReflexRuleAction action : rule.getActions()) {
                    System.out.println("### action ##");
                    System.out.println(action.getId());
                    System.out.println(action.getAction());
                    System.out.println(action.getReflexResult());
                    System.out.println(action.getReflexResultTestId());
                }
            }
        }

    }

}
