package spring.service.unitofmeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Service
public class UnitOfMeasureServiceImpl extends BaseObjectServiceImpl<UnitOfMeasure> implements UnitOfMeasureService {
  @Autowired
  protected UnitOfMeasureDAO baseObjectDAO;

  UnitOfMeasureServiceImpl() {
    super(UnitOfMeasure.class);
  }

  @Override
  protected UnitOfMeasureDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
