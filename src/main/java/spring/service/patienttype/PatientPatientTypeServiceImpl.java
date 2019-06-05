package spring.service.patienttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

@Service
public class PatientPatientTypeServiceImpl extends BaseObjectServiceImpl<PatientPatientType, String> implements PatientPatientTypeService {
	@Autowired
	protected PatientPatientTypeDAO baseObjectDAO;

	PatientPatientTypeServiceImpl() {
		super(PatientPatientType.class);
	}

	@Override
	protected PatientPatientTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public PatientPatientType getPatientPatientTypeForPatient(String id) {
		return getMatch("patientId", id).orElse(null);
	}

	@Override
	public void updateData(PatientPatientType patientType) {
		getBaseObjectDAO().updateData(patientType);

	}

	@Override
	public boolean insertData(PatientPatientType patientType) {
		return getBaseObjectDAO().insertData(patientType);
	}

	@Override
	public PatientType getPatientTypeForPatient(String id) {
		return getBaseObjectDAO().getPatientTypeForPatient(id);
	}
}
