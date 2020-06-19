package org.openelisglobal.dataexchange.order;

import java.beans.PropertyEditorSupport;

import org.openelisglobal.dataexchange.order.valueholder.PortableOrder;

public class PortableOrderSortOrderCategoryConvertor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        setValue(PortableOrder.SortOrder.fromString(text));
    }
}
