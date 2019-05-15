package spring.service.patienttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.patienttype.valueholder.BasePatientType;

@Service
public class BasePatientTypeServiceImpl extends BaseObjectServiceImpl<BasePatientType> implements BasePatientTypeService {
  @Autowired
  protected BaseDAO<BasePatientType> baseObjectDAO;

  BasePatientTypeServiceImpl() {
    super(BasePatientType.class);
  }

  @Override
  protected BaseDAO<BasePatientType> getBaseObjectDAO() {
    return baseObjectDAO;}
}
