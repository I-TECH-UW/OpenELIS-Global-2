package spring.mine.patient.form;

import us.mn.state.health.lims.patient.action.bean.PatientSearch;

public class PatientEditByProjectForm extends PatientEntryByProjectForm {

	private PatientSearch patientSearch;

	private Boolean warning;

	public PatientEditByProjectForm() {
		setFormName("PatientEditByProjectForm");
	}

	public PatientSearch getPatientSearch() {
		return patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}

	public Boolean getWarning() {
		return warning;
	}

	public void setWarning(Boolean warning) {
		this.warning = warning;
	}
}
