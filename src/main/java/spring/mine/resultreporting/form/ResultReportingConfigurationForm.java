package spring.mine.resultreporting.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ReportingConfiguration;

public class ResultReportingConfigurationForm extends BaseForm {

	private List<ReportingConfiguration> reports;

	private List<IdValuePair> hourList;

	private List<IdValuePair> minList;

	public ResultReportingConfigurationForm() {
		setFormName("ResultReportingConfigurationForm");
	}

	public List<ReportingConfiguration> getReports() {
		return reports;
	}

	public void setReports(List<ReportingConfiguration> reports) {
		this.reports = reports;
	}

	public List<IdValuePair> getHourList() {
		return hourList;
	}

	public void setHourList(List<IdValuePair> hourList) {
		this.hourList = hourList;
	}

	public List<IdValuePair> getMinList() {
		return minList;
	}

	public void setMinList(List<IdValuePair> minList) {
		this.minList = minList;
	}
}
