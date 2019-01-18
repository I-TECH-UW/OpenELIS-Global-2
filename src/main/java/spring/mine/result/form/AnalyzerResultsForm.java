package spring.mine.result.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.analyzerresults.action.beanitems.AnalyzerResultItem;
import us.mn.state.health.lims.common.paging.PagingBean;

public class AnalyzerResultsForm extends BaseForm {
	private PagingBean paging;

	private Timestamp lastupdated;

	private List<AnalyzerResultItem> resultList;

	private String notFoundMsg = "";

	private String analyzerType = "";

	private Boolean missingTestMsg;

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

	public String getNotFoundMsg() {
		return notFoundMsg;
	}

	public void setNotFoundMsg(String notFoundMsg) {
		this.notFoundMsg = notFoundMsg;
	}

	public String getAnalyzerType() {
		return analyzerType;
	}

	public void setAnalyzerType(String analyzerType) {
		this.analyzerType = analyzerType;
	}

	public Boolean getMissingTestMsg() {
		return missingTestMsg;
	}

	public void setMissingTestMsg(Boolean missingTestMsg) {
		this.missingTestMsg = missingTestMsg;
	}
}
