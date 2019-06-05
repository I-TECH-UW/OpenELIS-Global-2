package spring.service.patienttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patienttype.dao.BasePatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.BasePatientType;

@Service
public class BasePatientTypeServiceImpl extends BaseObjectServiceImpl<BasePatientType, String>
		implements BasePatientTypeService {
	@Autowired
	protected BasePatientTypeDAO baseObjectDAO;

	BasePatientTypeServiceImpl() {
		super(BasePatientType.class);
	}

	@Override
	protected BasePatientTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
