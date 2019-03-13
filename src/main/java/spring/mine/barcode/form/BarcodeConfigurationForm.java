package spring.mine.barcode.form;

import spring.mine.common.form.BaseForm;

public class BarcodeConfigurationForm extends BaseForm {
	private int numOrderLabels;

	private int numSpecimenLabels;

	private int numAliquotLabels;

	private float heightOrderLabels;

	private float widthOrderLabels;

	private float heightSpecimenLabels;

	private float widthSpecimenLabels;

	private boolean collectionDateCheck;

	private boolean testsCheck;

	private boolean patientSexCheck;

	public BarcodeConfigurationForm() {
		setFormName("BarcodeConfigurationForm");
	}

	public int getNumOrderLabels() {
		return numOrderLabels;
	}

	public void setNumOrderLabels(int numOrderLabels) {
		this.numOrderLabels = numOrderLabels;
	}

	public int getNumSpecimenLabels() {
		return numSpecimenLabels;
	}

	public void setNumSpecimenLabels(int numSpecimenLabels) {
		this.numSpecimenLabels = numSpecimenLabels;
	}

	public int getNumAliquotLabels() {
		return numAliquotLabels;
	}

	public void setNumAliquotLabels(int numAliquotLabels) {
		this.numAliquotLabels = numAliquotLabels;
	}

	public float getHeightOrderLabels() {
		return heightOrderLabels;
	}

	public void setHeightOrderLabels(float heightOrderLabels) {
		this.heightOrderLabels = heightOrderLabels;
	}

	public float getWidthOrderLabels() {
		return widthOrderLabels;
	}

	public void setWidthOrderLabels(float widthOrderLabels) {
		this.widthOrderLabels = widthOrderLabels;
	}

	public float getHeightSpecimenLabels() {
		return heightSpecimenLabels;
	}

	public void setHeightSpecimenLabels(float heightSpecimenLabels) {
		this.heightSpecimenLabels = heightSpecimenLabels;
	}

	public float getWidthSpecimenLabels() {
		return widthSpecimenLabels;
	}

	public void setWidthSpecimenLabels(float widthSpecimenLabels) {
		this.widthSpecimenLabels = widthSpecimenLabels;
	}

	public boolean getCollectionDateCheck() {
		return collectionDateCheck;
	}

	public void setCollectionDateCheck(boolean collectionDateCheck) {
		this.collectionDateCheck = collectionDateCheck;
	}

	public boolean getTestsCheck() {
		return testsCheck;
	}

	public void setTestsCheck(boolean testsCheck) {
		this.testsCheck = testsCheck;
	}

	public boolean getPatientSexCheck() {
		return patientSexCheck;
	}

	public void setPatientSexCheck(boolean patientSexCheck) {
		this.patientSexCheck = patientSexCheck;
	}
}
