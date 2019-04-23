package spring.mine.sample.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import spring.mine.common.form.BaseForm;
import spring.mine.validation.annotations.ValidDate;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.patient.action.IPatientUpdate.PatientUpdateStatus;
import us.mn.state.health.lims.patient.action.bean.PatientClinicalInfo;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SamplePatientEntryForm extends BaseForm {

	public interface SamplePatientEntry {
	}

	@ValidDate(relative = DateRelation.TODAY, groups = { SamplePatientEntry.class })
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
