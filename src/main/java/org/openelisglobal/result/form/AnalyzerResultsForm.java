package org.openelisglobal.result.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.analyzerresults.action.beanitems.AnalyzerResultItem;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.form.IPagingForm;
import org.openelisglobal.common.paging.PagingBean;

public class AnalyzerResultsForm extends BaseForm implements IPagingForm {

    public interface AnalyzerResuts {
    }

    @Valid
    private PagingBean paging;

    @Valid
    private List<AnalyzerResultItem> resultList;

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String type;

    @NotNull
    private Boolean displayNotFoundMsg = false;

    @NotNull
    private Boolean displayMissingTestMsg = false;

    public AnalyzerResultsForm() {
        setFormName("AnalyzerResultsForm");
    }

    @Override
    public PagingBean getPaging() {
        return paging;
    }

    @Override
    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public List<AnalyzerResultItem> getResultList() {
        return resultList;
    }

    public void setResultList(List<AnalyzerResultItem> resultList) {
        this.resultList = resultList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

}
