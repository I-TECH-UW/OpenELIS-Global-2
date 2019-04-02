package spring.mine.datasubmission.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class DataSubmissionForm extends BaseForm {
	private SiteInformation dataSubUrl;

	private List<DataIndicator> indicators;

	private int month = DateUtil.getCurrentMonth();

	private int year = DateUtil.getCurrentYear();

	private String siteId;

	public DataSubmissionForm() {
		setFormName("DataSubmissionForm");
	}

	public SiteInformation getDataSubUrl() {
		return dataSubUrl;
	}

	public void setDataSubUrl(SiteInformation dataSubUrl) {
		this.dataSubUrl = dataSubUrl;
	}

	public List<DataIndicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<DataIndicator> indicators) {
		this.indicators = indicators;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
}
