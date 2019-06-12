package spring.service.patienttype;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public interface PatientPatientTypeService extends BaseObjectService<PatientPatientType, String> {
	PatientType getPatientTypeForPatient(String id);

	PatientPatientType getPatientPatientTypeForPatient(String patientId);
}
