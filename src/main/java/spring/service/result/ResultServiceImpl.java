package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.valueholder.Result;

@Service
public class ResultServiceImpl extends BaseObjectServiceImpl<Result> implements ResultService {
  @Autowired
  protected ResultDAO baseObjectDAO;

  ResultServiceImpl() {
    super(Result.class);
  }

  @Override
  protected ResultDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
