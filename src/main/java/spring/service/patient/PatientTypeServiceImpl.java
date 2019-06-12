package spring.service.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

@Service
public class PatientTypeServiceImpl extends BaseObjectServiceImpl<PatientType, String> implements PatientTypeService {

	@Autowired
	private PatientTypeDAO baseObjectDAO;

	public PatientTypeServiceImpl() {
		super(PatientType.class);
	}

	@Override
	protected PatientTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List getAllPatientTypes() throws LIMSRuntimeException {
		return getBaseObjectDAO().getAllPatientTypes();
	}

	@Override
	public List getPageOfPatientType(int startingRecNo) throws LIMSRuntimeException {
		return getBaseObjectDAO().getPageOfPatientType(startingRecNo);
	}

	@Override
	public void getData(PatientType patientType) throws LIMSRuntimeException {
		getBaseObjectDAO().getData(patientType);
	}

	@Override
	public List getPatientTypes(String filter) throws LIMSRuntimeException {
		return getBaseObjectDAO().getPatientTypes(filter);
	}

	@Override
	public List getNextPatientTypeRecord(String id) throws LIMSRuntimeException {
		return getBaseObjectDAO().getNextPatientTypeRecord(id);
	}

	@Override
	public List getPreviousPatientTypeRecord(String id) throws LIMSRuntimeException {
		return getBaseObjectDAO().getPreviousPatientTypeRecord(id);
	}

	@Override
	public PatientType getPatientTypeByName(PatientType patientType) throws LIMSRuntimeException {
		return getBaseObjectDAO().getPatientTypeByName(patientType);
	}

	@Override
	public Integer getTotalPatientTypeCount() throws LIMSRuntimeException {
		return getBaseObjectDAO().getTotalPatientTypeCount();
	}

	@Override
	public String insert(PatientType patientType) {
		if (duplicatePatientTypeExists(patientType)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + patientType.getDescription());
		}
		return super.insert(patientType);
	}

	@Override
	public PatientType save(PatientType patientType) {
		if (duplicatePatientTypeExists(patientType)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + patientType.getDescription());
		}
		return super.save(patientType);
	}

	@Override
	public PatientType update(PatientType patientType) {
		if (duplicatePatientTypeExists(patientType)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + patientType.getDescription());
		}
		return super.update(patientType);
	}

	private boolean duplicatePatientTypeExists(PatientType patientType) {
		return baseObjectDAO.duplicatePatientTypeExists(patientType);
	}

}
