package spring.mine.barcode.form;

import spring.mine.common.form.BaseForm;

public class PrintBarcodeForm extends BaseForm {
	private String accessionNumber;

	private String patientId;

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

}
