package spring.service.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.valueholder.Patient;

@Service
public class PatientServiceImpl extends BaseObjectServiceImpl<Patient> implements PatientService {
  @Autowired
  protected PatientDAO baseObjectDAO;

  PatientServiceImpl() {
    super(Patient.class);
  }

  @Override
  protected PatientDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
