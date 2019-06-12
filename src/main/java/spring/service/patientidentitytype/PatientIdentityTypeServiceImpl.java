package spring.service.patientidentitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;

@Service
public class PatientIdentityTypeServiceImpl extends BaseObjectServiceImpl<PatientIdentityType, String>
		implements PatientIdentityTypeService {
	@Autowired
	protected PatientIdentityTypeDAO baseObjectDAO;

	public PatientIdentityTypeServiceImpl() {
		super(PatientIdentityType.class);
	}

	@Override
	protected PatientIdentityTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public PatientIdentityType getNamedIdentityType(String name) {
		return getBaseObjectDAO().getNamedIdentityType(name);
	}

	@Override
	public List<PatientIdentityType> getAllPatientIdenityTypes() {
		return getBaseObjectDAO().getAllPatientIdenityTypes();
	}

	@Override
	public String insert(PatientIdentityType patientIdentityType) {
		if (duplicatePatientIdentityTypeExists(patientIdentityType)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + patientIdentityType.getIdentityType());
		}
		return super.insert(patientIdentityType);
	}

	@Override
	public PatientIdentityType save(PatientIdentityType patientIdentityType) {
		if (duplicatePatientIdentityTypeExists(patientIdentityType)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + patientIdentityType.getIdentityType());
		}
		return super.save(patientIdentityType);
	}

	@Override
	public PatientIdentityType update(PatientIdentityType patientIdentityType) {
		if (duplicatePatientIdentityTypeExists(patientIdentityType)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + patientIdentityType.getIdentityType());
		}
		return super.update(patientIdentityType);
	}

	private boolean duplicatePatientIdentityTypeExists(PatientIdentityType patientIdentityType) {
		return baseObjectDAO.duplicatePatientIdentityTypeExists(patientIdentityType);
	}
}
