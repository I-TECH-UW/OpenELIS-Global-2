package spring.service.patient;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public interface PatientTypeService extends BaseObjectService<PatientType, String> {

	public List getAllPatientTypes() throws LIMSRuntimeException;

	public List getPageOfPatientType(int startingRecNo) throws LIMSRuntimeException;

	public void getData(PatientType patientType) throws LIMSRuntimeException;

	public List getPatientTypes(String filter) throws LIMSRuntimeException;

	public List getNextPatientTypeRecord(String id) throws LIMSRuntimeException;

	public List getPreviousPatientTypeRecord(String id) throws LIMSRuntimeException;

	public PatientType getPatientTypeByName(PatientType patientType) throws LIMSRuntimeException;

	public Integer getTotalPatientTypeCount() throws LIMSRuntimeException;

}
