package us.mn.state.health.lims.dataexchange.order;

import java.beans.PropertyEditorSupport;

import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public class ElectronicOrderSortOrderCategoryConvertor extends PropertyEditorSupport {

	@Override
	public void setAsText(final String text) {
		setValue(ElectronicOrder.SortOrder.fromString(text));
	}
}
