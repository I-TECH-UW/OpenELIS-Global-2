package spring.mine.datasubmission.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class DataSubmissionForm extends BaseForm {
	private Timestamp lastupdated;

	private SiteInformation dataSubUrl;

	private List<DataIndicator> indicators;

	private Integer month;

	private Integer year;

	private String siteId;

	public DataSubmissionForm() {
		setFormName("DataSubmissionForm");
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
}
