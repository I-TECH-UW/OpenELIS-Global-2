package spring.service.typeofsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
public class TypeOfSampleServiceImpl extends BaseObjectServiceImpl<TypeOfSample> implements TypeOfSampleService {
  @Autowired
  protected TypeOfSampleDAO baseObjectDAO;

  TypeOfSampleServiceImpl() {
    super(TypeOfSample.class);
  }

  @Override
  protected TypeOfSampleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
