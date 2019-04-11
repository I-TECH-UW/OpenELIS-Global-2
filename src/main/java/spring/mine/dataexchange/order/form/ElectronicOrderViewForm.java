package spring.mine.dataexchange.order.form;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public class ElectronicOrderViewForm extends BaseForm {
	@NotBlank
	@Pattern(regexp = "^$|^lastupdated$|^externalId$|^statusId$")
	private String sortOrder = "lastupdated";

	@Min(1)
	private int page = 1;

	// for display
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
