package org.openelisglobal.testcalculated.action.bean;

import org.openelisglobal.common.util.IdValuePair;

public class TestBeanItem extends IdValuePair {

    String dataType ;

    public TestBeanItem(String id, String value , String dataType) {
        super(id, value);
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
