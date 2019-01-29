package spring.mine.barcode.form;

import spring.mine.common.form.BaseForm;

public class BarcodeConfigurationForm extends BaseForm {
	private String lastupdated;

	private String numOrderLabels;

	private String numSpecimenLabels;

	private String numAliquotLabels;

	private String heightOrderLabels;

	private String widthOrderLabels;

	private String heightSpecimenLabels;

	private String widthSpecimenLabels;

	private String collectionDateCheck;

	private String testsCheck;

	private String patientSexCheck;

	public BarcodeConfigurationForm() {
		setFormName("BarcodeConfigurationForm");
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getNumOrderLabels() {
		return numOrderLabels;
	}

	public void setNumOrderLabels(String numOrderLabels) {
		this.numOrderLabels = numOrderLabels;
	}

	public String getNumSpecimenLabels() {
		return numSpecimenLabels;
	}

	public void setNumSpecimenLabels(String numSpecimenLabels) {
		this.numSpecimenLabels = numSpecimenLabels;
	}

	public String getNumAliquotLabels() {
		return numAliquotLabels;
	}

	public void setNumAliquotLabels(String numAliquotLabels) {
		this.numAliquotLabels = numAliquotLabels;
	}

	public String getHeightOrderLabels() {
		return heightOrderLabels;
	}

	public void setHeightOrderLabels(String heightOrderLabels) {
		this.heightOrderLabels = heightOrderLabels;
	}

	public String getWidthOrderLabels() {
		return widthOrderLabels;
	}

	public void setWidthOrderLabels(String widthOrderLabels) {
		this.widthOrderLabels = widthOrderLabels;
	}

	public String getHeightSpecimenLabels() {
		return heightSpecimenLabels;
	}

	public void setHeightSpecimenLabels(String heightSpecimenLabels) {
		this.heightSpecimenLabels = heightSpecimenLabels;
	}

	public String getWidthSpecimenLabels() {
		return widthSpecimenLabels;
	}

	public void setWidthSpecimenLabels(String widthSpecimenLabels) {
		this.widthSpecimenLabels = widthSpecimenLabels;
	}

	public String getCollectionDateCheck() {
		return collectionDateCheck;
	}

	public void setCollectionDateCheck(String collectionDateCheck) {
		this.collectionDateCheck = collectionDateCheck;
	}

	public String getTestsCheck() {
		return testsCheck;
	}

	public void setTestsCheck(String testsCheck) {
		this.testsCheck = testsCheck;
	}

	public String getPatientSexCheck() {
		return patientSexCheck;
	}

	public void setPatientSexCheck(String patientSexCheck) {
		this.patientSexCheck = patientSexCheck;
	}
}
