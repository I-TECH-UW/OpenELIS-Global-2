package spring.mine.barcode.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleEditItem;

public class PrintBarcodeForm extends BaseForm {
	private String accessionNumber;

	private String patientId;

	private String barcode;

	private String patientName = "";

	private String dob = "";

	private String gender = "";

	private String nationalId = "";

	private String searchCriteria;

	private List<SampleEditItem> existingTests;

	private PatientSearch patientSearch;

	public PrintBarcodeForm() {
		setFormName("PrintBarcodeForm");
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public List<SampleEditItem> getExistingTests() {
		return existingTests;
	}

	public void setExistingTests(List<SampleEditItem> existingTests) {
		this.existingTests = existingTests;
	}

	public PatientSearch getPatientSearch() {
		return patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}
}
