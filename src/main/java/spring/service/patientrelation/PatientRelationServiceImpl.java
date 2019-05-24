package spring.service.patientrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patientrelation.dao.PatientRelationDAO;
import us.mn.state.health.lims.patientrelation.valueholder.PatientRelation;

@Service
public class PatientRelationServiceImpl extends BaseObjectServiceImpl<PatientRelation> implements PatientRelationService {
	@Autowired
	protected PatientRelationDAO baseObjectDAO;

	PatientRelationServiceImpl() {
		super(PatientRelation.class);
	}

	@Override
	protected PatientRelationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
