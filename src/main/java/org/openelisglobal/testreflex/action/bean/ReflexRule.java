package org.openelisglobal.testreflex.action.bean;

import java.util.List;

public class ReflexRule {

    private String id;
    private String ruleName;
    private String overall;
    private Boolean toggled;
    List<ReflexRuleCondition> conditions;
    List<ReflexRuleAction> actions;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getOverall() {
        return overall;
    }
    public void setOverall(String overall) {
        this.overall = overall;
    }
    public Boolean getToggled() {
        return toggled;
    }
    public void setToggled(Boolean toggled) {
        this.toggled = toggled;
    }
    public List<ReflexRuleCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<ReflexRuleCondition> conditions) {
        this.conditions = conditions;
    }
    public List<ReflexRuleAction> getActions() {
        return actions;
    }
    public void setActions(List<ReflexRuleAction> actions) {
        this.actions = actions;
    }

}
