package us.mn.state.health.lims.patienttype.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.patienttype.valueholder.BasePatientType;

@Component
public class BasePatientTypeDAOImpl extends BaseDAOImpl<BasePatientType> implements BasePatientTypeDAO {
  BasePatientTypeDAOImpl() {
    super(BasePatientType.class);
  }
}
