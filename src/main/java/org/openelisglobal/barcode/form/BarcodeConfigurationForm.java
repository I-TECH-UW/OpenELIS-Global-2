package org.openelisglobal.barcode.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;

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

    private boolean prePrintDontUseAltAccession;

    @Pattern(regexp = ValidationHelper.ALPHA_NUM_REGEX)
    @Size(min = 4, max = 4)
    private String prePrintAltAccessionPrefix;

    // for display/validation logic only
    private String sitePrefix;

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

    public boolean getPrePrintDontUseAltAccession() {
        return prePrintDontUseAltAccession;
    }

    public void setPrePrintDontUseAltAccession(boolean prePrintDontUseAltAccession) {
        this.prePrintDontUseAltAccession = prePrintDontUseAltAccession;
    }

    public String getPrePrintAltAccessionPrefix() {
        return prePrintAltAccessionPrefix;
    }

    public void setPrePrintAltAccessionPrefix(String prePrintAltAccessionPrefix) {
        this.prePrintAltAccessionPrefix = prePrintAltAccessionPrefix;
    }

    public String getSitePrefix() {
        return sitePrefix;
    }

    public void setSitePrefix(String sitePrefix) {
        this.sitePrefix = sitePrefix;
    }
}
