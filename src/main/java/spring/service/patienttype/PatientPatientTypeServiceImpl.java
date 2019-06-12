package spring.service.patienttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.patient.PatientTypeService;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

@Service
public class PatientPatientTypeServiceImpl extends BaseObjectServiceImpl<PatientPatientType, String>
		implements PatientPatientTypeService {
	@Autowired
	protected PatientPatientTypeDAO baseObjectDAO;
	@Autowired
	private PatientTypeService patientTypeService;

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
	public PatientType getPatientTypeForPatient(String id) {
		PatientPatientType patientPatientType = getPatientPatientTypeForPatient(id);

		if (patientPatientType != null) {
			PatientType patientType = new PatientType();
			patientType.setId(patientPatientType.getPatientTypeId());
			patientTypeService.getData(patientType);

			return patientType;
		}
		return null;
	}
}
