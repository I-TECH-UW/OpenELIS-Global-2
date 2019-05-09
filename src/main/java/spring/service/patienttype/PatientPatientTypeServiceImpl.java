package spring.service.patienttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;

@Service
public class PatientPatientTypeServiceImpl extends BaseObjectServiceImpl<PatientPatientType> implements PatientPatientTypeService {
  @Autowired
  protected PatientPatientTypeDAO baseObjectDAO;

  PatientPatientTypeServiceImpl() {
    super(PatientPatientType.class);
  }

  @Override
  protected PatientPatientTypeDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
