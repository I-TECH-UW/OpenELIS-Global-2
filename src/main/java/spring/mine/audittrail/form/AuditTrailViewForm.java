package spring.mine.audittrail.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class AuditTrailViewForm extends BaseForm {

	private String accessionNumberSearch = "";

	private List<AuditTrailItem> log;

	private String accessionNumber = "";

	private SampleOrderItem sampleOrderItems;

	private PatientManagementInfo patientProperties;

	public AuditTrailViewForm() {
		setFormName("AuditTrailViewForm");
	}

	public String getAccessionNumberSearch() {
		return accessionNumberSearch;
	}

	public void setAccessionNumberSearch(String accessionNumberSearch) {
		this.accessionNumberSearch = accessionNumberSearch;
	}

	public List<AuditTrailItem> getLog() {
		return log;
	}

	public void setLog(List<AuditTrailItem> log) {
		this.log = log;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public SampleOrderItem getSampleOrderItems() {
		return sampleOrderItems;
	}

	public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
		this.sampleOrderItems = sampleOrderItems;
	}

	public PatientManagementInfo getPatientProperties() {
		return patientProperties;
	}

	public void setPatientProperties(PatientManagementInfo patientProperties) {
		this.patientProperties = patientProperties;
	}
}
