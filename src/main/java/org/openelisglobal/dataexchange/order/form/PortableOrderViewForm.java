package org.openelisglobal.dataexchange.order.form;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;
import org.openelisglobal.dataexchange.order.valueholder.PortableOrder.SortOrder;

public class PortableOrderViewForm extends BaseForm {
    @NotNull
    private PortableOrder.SortOrder sortOrder = PortableOrder.SortOrder.LAST_UPDATED;

    @Min(1)
    private int page = 1;

    // for display
    private List<PortableOrder> pOrders;

    // for display
    private SortOrder[] sortOrderOptions = PortableOrder.SortOrder.values();

    public PortableOrderViewForm() {
        setFormName("PortableOrderViewForm");
    }

    public PortableOrder.SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(PortableOrder.SortOrder sortBy) {
        sortOrder = sortBy;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PortableOrder> getPOrders() {
        return pOrders;
    }

    public void setPOrders(List<PortableOrder> pOrders) {
        this.pOrders = pOrders;
    }

    public SortOrder[] getSortOrderOptions() {
        return sortOrderOptions;
    }

    public void setSortOrderOptions(SortOrder[] sortOrderOptions) {
        this.sortOrderOptions = sortOrderOptions;
    }
}