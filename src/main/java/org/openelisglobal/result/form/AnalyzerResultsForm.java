package org.openelisglobal.result.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.analyzerresults.action.beanitems.AnalyzerResultItem;
import org.openelisglobal.common.paging.PagingBean;

public class AnalyzerResultsForm extends BaseForm {

    public interface AnalyzerResuts {
    }

    @Valid
    private PagingBean paging;

    @Valid
    private List<AnalyzerResultItem> resultList;

    @SafeHtml
    private String analyzerType = "";

    @NotNull
    private Boolean displayNotFoundMsg = false;

    @NotNull
    private Boolean displayMissingTestMsg = false;

    private boolean hideShowFlag = false;

    public AnalyzerResultsForm() {
        setFormName("AnalyzerResultsForm");
    }

    public PagingBean getPaging() {
        return paging;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public List<AnalyzerResultItem> getResultList() {
        return resultList;
    }

    public void setResultList(List<AnalyzerResultItem> resultList) {
        this.resultList = resultList;
    }

    public String getAnalyzerType() {
        return analyzerType;
    }

    public void setAnalyzerType(String analyzerType) {
        this.analyzerType = analyzerType;
    }

    public Boolean getDisplayNotFoundMsg() {
        return displayNotFoundMsg;
    }

    public void setDisplayNotFoundMsg(Boolean displayNotFoundMsg) {
        this.displayNotFoundMsg = displayNotFoundMsg;
    }

    public Boolean getDisplayMissingTestMsg() {
        return displayMissingTestMsg;
    }

    public void setDisplayMissingTestMsg(Boolean displayMissingTestMsg) {
        this.displayMissingTestMsg = displayMissingTestMsg;
    }

    public boolean isHideShowFlag() {
        return hideShowFlag;
    }

    public void setHideShowFlag(boolean hideShowFlag) {
        this.hideShowFlag = hideShowFlag;
    }

}
