package org.openelisglobal.patient.form;

import org.openelisglobal.patient.action.bean.PatientSearch;

public class PatientEditByProjectForm extends PatientEntryByProjectForm {
  // for display
  private PatientSearch patientSearch;

  // for display
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
