package spring.service.datasubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;

@Service
public class DataIndicatorServiceImpl extends BaseObjectServiceImpl<DataIndicator> implements DataIndicatorService {
  @Autowired
  protected DataIndicatorDAO baseObjectDAO;

  DataIndicatorServiceImpl() {
    super(DataIndicator.class);
  }

  @Override
  protected DataIndicatorDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
