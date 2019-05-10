package spring.service.typeofsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
public class TypeOfSampleTestServiceImpl extends BaseObjectServiceImpl<TypeOfSampleTest> implements TypeOfSampleTestService {
  @Autowired
  protected TypeOfSampleTestDAO baseObjectDAO;

  TypeOfSampleTestServiceImpl() {
    super(TypeOfSampleTest.class);
  }

  @Override
  protected TypeOfSampleTestDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
