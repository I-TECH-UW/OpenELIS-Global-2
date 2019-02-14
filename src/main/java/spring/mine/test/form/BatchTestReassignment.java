package spring.mine.test.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;

public class BatchTestReassignment extends BaseForm {
	private List<IdValuePair> sampleList;

	private String statusChangedSampleType = "";

	private String statusChangedCurrentTest = "";

	private String statusChangedNextTest = "";

	private List statusChangedList;

	private String jsonWad = "";

	private Timestamp lastupdated;

	public BatchTestReassignment() {
		setFormName("BatchTestReassignment");
	}

	public List<IdValuePair> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<IdValuePair> sampleList) {
		this.sampleList = sampleList;
	}

	public String getStatusChangedSampleType() {
		return statusChangedSampleType;
	}

	public void setStatusChangedSampleType(String statusChangedSampleType) {
		this.statusChangedSampleType = statusChangedSampleType;
	}

	public String getStatusChangedCurrentTest() {
		return statusChangedCurrentTest;
	}

	public void setStatusChangedCurrentTest(String statusChangedCurrentTest) {
		this.statusChangedCurrentTest = statusChangedCurrentTest;
	}

	public String getStatusChangedNextTest() {
		return statusChangedNextTest;
	}

	public void setStatusChangedNextTest(String statusChangedNextTest) {
		this.statusChangedNextTest = statusChangedNextTest;
	}

	public List getStatusChangedList() {
		return statusChangedList;
	}

	public void setStatusChangedList(List statusChangedList) {
		this.statusChangedList = statusChangedList;
	}

	public String getJsonWad() {
		return jsonWad;
	}

	public void setJsonWad(String jsonWad) {
		this.jsonWad = jsonWad;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}
}
