package org.openelisglobal.common.formfields;

public class FormField {

    private Boolean inUse;

    private String labelKey;

    public FormField() {

    }

    public FormField(String labelKey) {
        this.labelKey = labelKey;
    }

    public FormField(boolean inUse, String labelKey) {
        this.inUse = inUse;
        this.labelKey = labelKey;
    }

    public FormField(boolean inUse) {
        this.inUse = inUse;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

}
