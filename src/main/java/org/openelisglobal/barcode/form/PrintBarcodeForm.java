package org.openelisglobal.barcode.form;

import org.hibernate.validator.constraints.SafeHtml;

import org.openelisglobal.common.form.BaseForm;

//values used for fetching, tight validation not needed
public class PrintBarcodeForm extends BaseForm {
	@SafeHtml
	private String accessionNumber;

	@SafeHtml
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
