package spring.mine.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;

public class TestRenameEntryForm extends BaseForm {
	private List<IdValuePair> testList;

	private String nameEnglish = "";

	private String nameFrench = "";

	private String reportNameEnglish = "";

	private String reportNameFrench = "";

	private String testId = "";

	public TestRenameEntryForm() {
		setFormName("testRenameEntryForm");
	}

	public List<IdValuePair> getTestList() {
		return testList;
	}

	public void setTestList(List<IdValuePair> testList) {
		this.testList = testList;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameFrench() {
		return nameFrench;
	}

	public void setNameFrench(String nameFrench) {
		this.nameFrench = nameFrench;
	}

	public String getReportNameEnglish() {
		return reportNameEnglish;
	}

	public void setReportNameEnglish(String reportNameEnglish) {
		this.reportNameEnglish = reportNameEnglish;
	}

	public String getReportNameFrench() {
		return reportNameFrench;
	}

	public void setReportNameFrench(String reportNameFrench) {
		this.reportNameFrench = reportNameFrench;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
}
