package org.openelisglobal.dataexchange.order.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder.SortOrder;

public class ElectronicOrderViewForm extends BaseForm {
    @NotNull
    private ElectronicOrder.SortOrder sortOrder = ElectronicOrder.SortOrder.STATUS_ID;

    private List<ExternalOrderStatus> excludedStatuses = new ArrayList<>();

    @Min(1)
    private int page = 1;

    private String searchValue;

    // for display
    private List<ElectronicOrder> eOrders;

    // for display
    private SortOrder[] sortOrderOptions = ElectronicOrder.SortOrder.values();

    public ElectronicOrderViewForm() {
        setFormName("ElectronicOrderViewForm");
    }

    public ElectronicOrder.SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(ElectronicOrder.SortOrder sortBy) {
        sortOrder = sortBy;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ElectronicOrder> getEOrders() {
        return eOrders;
    }

    public void setEOrders(List<ElectronicOrder> eOrders) {
        this.eOrders = eOrders;
    }

    public SortOrder[] getSortOrderOptions() {
        return sortOrderOptions;
    }

    public void setSortOrderOptions(SortOrder[] sortOrderOptions) {
        this.sortOrderOptions = sortOrderOptions;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<ExternalOrderStatus> getExcludedStatuses() {
        return excludedStatuses;
    }

    public void setExcludedStatuses(List<ExternalOrderStatus> excludedStatuses) {
        this.excludedStatuses = excludedStatuses;
    }
}
