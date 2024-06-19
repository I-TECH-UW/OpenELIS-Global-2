package org.openelisglobal.patienttype.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.patienttype.valueholder.PatientPatientType;
import org.openelisglobal.patienttype.valueholder.PatientType;

public interface PatientPatientTypeService extends BaseObjectService<PatientPatientType, String> {
  PatientType getPatientTypeForPatient(String id);

  PatientPatientType getPatientPatientTypeForPatient(String patientId);
}
