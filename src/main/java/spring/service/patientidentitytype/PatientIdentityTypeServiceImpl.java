package spring.service.patientidentitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;

@Service
public class PatientIdentityTypeServiceImpl extends BaseObjectServiceImpl<PatientIdentityType>
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
	public void insertData(PatientIdentityType patientIdenityType) {
		getBaseObjectDAO().insertData(patientIdenityType);

	}

	@Override
	public PatientIdentityType getNamedIdentityType(String name) {
		return getBaseObjectDAO().getNamedIdentityType(name);
	}

	@Override
	public List<PatientIdentityType> getAllPatientIdenityTypes() {
		return getBaseObjectDAO().getAllPatientIdenityTypes();
	}
}
