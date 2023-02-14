package org.openelisglobal.testreflex.action.bean;

import java.util.List;

import org.openelisglobal.common.util.LabelValuePair;

public class ReflexRuleOptionsDisplayItem {
    List<LabelValuePair> overallOptions;
    List<LabelValuePair> generalRelationOptions;
    List<LabelValuePair> numericRelationOptions;
    List<LabelValuePair> actionOptions;
    public List<LabelValuePair> getOverallOptions() {
        return overallOptions;
    }
    public void setOverallOptions(List<LabelValuePair> overallOptions) {
        this.overallOptions = overallOptions;
    }
    public List<LabelValuePair> getGeneralRelationOptions() {
        return generalRelationOptions;
    }
    public void setGeneralRelationOptions(List<LabelValuePair> generalRelationOptions) {
        this.generalRelationOptions = generalRelationOptions;
    }
    public List<LabelValuePair> getNumericRelationOptions() {
        return numericRelationOptions;
    }
    public void setNumericRelationOptions(List<LabelValuePair> numericRelationOptions) {
        this.numericRelationOptions = numericRelationOptions;
    }
    public List<LabelValuePair> getActionOptions() {
        return actionOptions;
    }
    public void setActionOptions(List<LabelValuePair> actionOptions) {
        this.actionOptions = actionOptions;
    }

}
