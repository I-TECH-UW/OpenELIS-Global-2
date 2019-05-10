package spring.service.typeoftestresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeoftestresult.dao.TypeOfTestResultDAO;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

@Service
public class TypeOfTestResultServiceImpl extends BaseObjectServiceImpl<TypeOfTestResult> implements TypeOfTestResultService {
  @Autowired
  protected TypeOfTestResultDAO baseObjectDAO;

  TypeOfTestResultServiceImpl() {
    super(TypeOfTestResult.class);
  }

  @Override
  protected TypeOfTestResultDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
