package org.openelisglobal.barcode.form;

import org.hibernate.validator.constraints.Range;
import org.openelisglobal.common.form.BaseForm;

public class BarcodeConfigurationForm extends BaseForm {

    @Range(min = 0, max = 2000)
    private int numOrderLabels;

    @Range(min = 0, max = 2000)
    private int numSpecimenLabels;

    @Range(min = 0, max = 2000)
    private int numAliquotLabels;

    @Range(min = 0, max = 1000)
    private float heightOrderLabels;

    @Range(min = 0, max = 1000)
    private float widthOrderLabels;

    @Range(min = 0, max = 1000)
    private float heightSpecimenLabels;

    @Range(min = 0, max = 1000)
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
