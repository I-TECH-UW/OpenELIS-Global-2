package spring.unused.sample.form;

import java.sql.Timestamp;
import java.util.Collection;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SampleConfirmationEntryForm extends BaseForm {
	private Timestamp lastupdated;

	private String interviewDate = "";

	private String interviewTime = "";

	private String currentDate = "";

	private String patientLastUpdated = "";

	private String personLastUpdated = "";

	private Collection confirmationItems;

	private Collection sampleTypes;

	private String patientPK = "";

	private PatientManagementInfo patientProperties;

	private PatientSearch patientSearch;

	private Collection requestingOrganizationList;

	private String requestAsXML;

	private Collection initialSampleConditionList;

	private SampleOrderItem sampleOrderItems;

	public SampleConfirmationEntryForm() {
		setFormName("SampleConfirmationEntryForm");
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getPatientLastUpdated() {
		return patientLastUpdated;
	}

	public void setPatientLastUpdated(String patientLastUpdated) {
		this.patientLastUpdated = patientLastUpdated;
	}

	public String getPersonLastUpdated() {
		return personLastUpdated;
	}

	public void setPersonLastUpdated(String personLastUpdated) {
		this.personLastUpdated = personLastUpdated;
	}

	public Collection getConfirmationItems() {
		return confirmationItems;
	}

	public void setConfirmationItems(Collection confirmationItems) {
		this.confirmationItems = confirmationItems;
	}

	public Collection getSampleTypes() {
		return sampleTypes;
	}

	public void setSampleTypes(Collection sampleTypes) {
		this.sampleTypes = sampleTypes;
	}

	public String getPatientPK() {
		return patientPK;
	}

	public void setPatientPK(String patientPK) {
		this.patientPK = patientPK;
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

	public Collection getRequestingOrganizationList() {
		return requestingOrganizationList;
	}

	public void setRequestingOrganizationList(Collection requestingOrganizationList) {
		this.requestingOrganizationList = requestingOrganizationList;
	}

	public String getRequestAsXML() {
		return requestAsXML;
	}

	public void setRequestAsXML(String requestAsXML) {
		this.requestAsXML = requestAsXML;
	}

	public Collection getInitialSampleConditionList() {
		return initialSampleConditionList;
	}

	public void setInitialSampleConditionList(Collection initialSampleConditionList) {
		this.initialSampleConditionList = initialSampleConditionList;
	}

	public SampleOrderItem getSampleOrderItems() {
		return sampleOrderItems;
	}

	public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
		this.sampleOrderItems = sampleOrderItems;
	}
}
