package spring.mine.dataexchange.order.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public class ElectronicOrderViewForm extends BaseForm {
	private String sortOrder = "lastupdated";

	private int page = 1;

	private List<ElectronicOrder> eOrders;

	public ElectronicOrderViewForm() {
		setFormName("ElectronicOrderViewForm");
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortBy) {
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
}
