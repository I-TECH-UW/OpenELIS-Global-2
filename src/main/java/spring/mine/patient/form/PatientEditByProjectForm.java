package spring.mine.patient.form;

import us.mn.state.health.lims.patient.action.bean.PatientSearch;

public class PatientEditByProjectForm extends PatientEntryByProjectForm {

	private PatientSearch patientSearch;

	private String warning;

	public PatientEditByProjectForm() {
		setFormName("PatientEditByProjectForm");
	}

	public PatientSearch getPatientSearch() {
		return patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}
}
