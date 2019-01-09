package spring.mine.sample.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.patient.action.bean.PatientClinicalInfo;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SamplePatientEntryForm extends BaseForm {
	private Timestamp lastupdated;

	private String currentDate = "";

	private String project = "";

	private List<Project> projects;

	private String patientProcessingStatus = "Add";

	private List<IdValuePair> sampleTypes;

	private String sampleXML = "";

	private PatientManagementInfo patientProperties;

	private PatientSearch patientSearch;

	private PatientClinicalInfo patientClinicalProperties;

	private SampleOrderItem sampleOrderItems;

	private List<IdValuePair> initialSampleConditionList;

	private List<IdValuePair> testSectionList;

	public SamplePatientEntryForm() {
		setFormName("samplePatientEntryForm");
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getPatientProcessingStatus() {
		return patientProcessingStatus;
	}

	public void setPatientProcessingStatus(String patientProcessingStatus) {
		this.patientProcessingStatus = patientProcessingStatus;
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
}
