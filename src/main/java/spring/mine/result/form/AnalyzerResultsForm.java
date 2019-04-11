package spring.mine.result.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.analyzerresults.action.beanitems.AnalyzerResultItem;
import us.mn.state.health.lims.common.paging.PagingBean;

public class AnalyzerResultsForm extends BaseForm {
	// for display
	private PagingBean paging;

	private Timestamp lastupdated;

	// TODO
	@Valid
	private List<AnalyzerResultItem> resultList;

	// for display
	private String analyzerType = "";

	@NotNull
	private Boolean displayNotFoundMsg = false;

	@NotNull
	private Boolean displayMissingTestMsg = false;

	public AnalyzerResultsForm() {
		setFormName("AnalyzerResultsForm");
	}

	public PagingBean getPaging() {
		return paging;
	}

	public void setPaging(PagingBean paging) {
		this.paging = paging;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
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

}
