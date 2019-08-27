package org.openelisglobal.sample.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.validator.CustomDateValidator.DateRelation;
import org.openelisglobal.patient.action.IPatientUpdate.PatientUpdateStatus;
import org.openelisglobal.patient.action.bean.PatientClinicalInfo;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.project.valueholder.Project;
import org.openelisglobal.sample.bean.SampleOrderItem;
import org.openelisglobal.validation.annotations.ValidDate;

public class SamplePatientEntryForm extends BaseForm {

    public interface SamplePatientEntryBatch {

    }

    public interface SamplePatientEntry {
    }

    @ValidDate(relative = DateRelation.TODAY, groups = { SamplePatientEntry.class, SamplePatientEntryBatch.class })
    private String currentDate = "";

    @Valid
    private List<Project> projects;

    private PatientUpdateStatus patientUpdateStatus = PatientUpdateStatus.ADD;

    // for display
    private List<IdValuePair> sampleTypes;

    // in validator
    private String sampleXML = "";

    @Valid
    private PatientManagementInfo patientProperties;

    // for display
    private PatientSearch patientSearch;

    @Valid
    private PatientClinicalInfo patientClinicalProperties;

    @Valid
    private SampleOrderItem sampleOrderItems;

    // for display
    private List<IdValuePair> initialSampleConditionList;

    // for display
    private List<IdValuePair> testSectionList;

    @NotNull(groups = { SamplePatientEntry.class })
    private Boolean warning = false;

    public SamplePatientEntryForm() {
        setFormName("samplePatientEntryForm");
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public PatientUpdateStatus getPatientUpdateStatus() {
        return patientUpdateStatus;
    }

    public void setPatientUpdateStatus(PatientUpdateStatus patientUpdateStatus) {
        this.patientUpdateStatus = patientUpdateStatus;
    }

    public List<IdValuePair> getSampleTypes() {
        return sampleTypes;
    }

    public void setSampleTypes(List<IdValuePair> sampleTypes) {
        this.sampleTypes = sampleTypes;
    }

    public String getSampleXML() {
        return sampleXML;
    }

    public void setSampleXML(String sampleXML) {
        this.sampleXML = sampleXML;
    }

    public PatientManagementInfo getPatientProperties() {
        return patientProperties;
    }

    public void setPatientProperties(PatientManagementInfo patientProperties) {
        this.patientProperties = patientProperties;
    }

    public PatientSearch getPatientSearch() {
        return patientSearch;
    }

    public void setPatientSearch(PatientSearch patientSearch) {
        this.patientSearch = patientSearch;
    }

    public PatientClinicalInfo getPatientClinicalProperties() {
        return patientClinicalProperties;
    }

    public void setPatientClinicalProperties(PatientClinicalInfo patientClinicalProperties) {
        this.patientClinicalProperties = patientClinicalProperties;
    }

    public SampleOrderItem getSampleOrderItems() {
        return sampleOrderItems;
    }

    public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
        this.sampleOrderItems = sampleOrderItems;
    }

    public List<IdValuePair> getInitialSampleConditionList() {
        return initialSampleConditionList;
    }

    public void setInitialSampleConditionList(List<IdValuePair> initialSampleConditionList) {
        this.initialSampleConditionList = initialSampleConditionList;
    }

    public List<IdValuePair> getTestSectionList() {
        return testSectionList;
    }

    public void setTestSectionList(List<IdValuePair> testSectionList) {
        this.testSectionList = testSectionList;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }
}
