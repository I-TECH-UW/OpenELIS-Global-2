package us.mn.state.health.lims.patientrelation.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.patientrelation.valueholder.PatientRelation;

@Component
@Transactional 
public class PatientRelationDAOImpl extends BaseDAOImpl<PatientRelation> implements PatientRelationDAO {
  PatientRelationDAOImpl() {
    super(PatientRelation.class);
  }
}
