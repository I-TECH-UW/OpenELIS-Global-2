package spring.service.patienttype;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public interface PatientPatientTypeService extends BaseObjectService<PatientPatientType> {
	void updateData(PatientPatientType patientType);

	boolean insertData(PatientPatientType patientType);

	PatientType getPatientTypeForPatient(String id);

	PatientPatientType getPatientPatientTypeForPatient(String patientId);
}
