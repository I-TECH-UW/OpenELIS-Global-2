package org.openelisglobal.testcalculated.action.bean;

import java.util.List;
import org.openelisglobal.common.util.IdValuePair;

public class TestDisplayBeanItem extends IdValuePair {

    String resultType ;
    List<IdValuePair> resultList;

    public TestDisplayBeanItem(String id, String value , String resultType) {
        super(id, value);
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<IdValuePair> getResultList() {
        return resultList;
    }
 
    public void setResultList(List<IdValuePair> resultList) {
        this.resultList = resultList;
    }
}
