package org.openelisglobal.test.beanItems;

import java.util.List;

import org.openelisglobal.common.util.LabelValuePair;

public class TestDisplayBean {
    String label;
    String value;
    String resultType;
    List<LabelValuePair> resultList;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<LabelValuePair> getResultList() {
        return resultList;
    }

    public void setResultList(List<LabelValuePair> resultList) {
        this.resultList = resultList;
    }

}
