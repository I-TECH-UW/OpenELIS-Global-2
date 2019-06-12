package us.mn.state.health.lims.patienttype.dao;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;

public interface PatientPatientTypeDAO extends BaseDAO<PatientPatientType, String> {

//	public boolean insertData(PatientPatientType patientType) throws LIMSRuntimeException;

//	public void updateData(PatientPatientType patientType) throws LIMSRuntimeException;

//	public PatientType getPatientTypeForPatient(String id);

	public PatientPatientType getPatientPatientTypeForPatient(String patientId) throws LIMSRuntimeException;
}
