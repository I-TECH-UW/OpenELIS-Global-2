package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.valueholder.ResultInventory;

@Service
public class ResultInventoryServiceImpl extends BaseObjectServiceImpl<ResultInventory> implements ResultInventoryService {
  @Autowired
  protected ResultInventoryDAO baseObjectDAO;

  ResultInventoryServiceImpl() {
    super(ResultInventory.class);
  }

  @Override
  protected ResultInventoryDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
