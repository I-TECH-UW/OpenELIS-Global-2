package spring.service.resultlimits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;

@Service
public class ResultLimitServiceImpl extends BaseObjectServiceImpl<ResultLimit> implements ResultLimitService {
  @Autowired
  protected ResultLimitDAO baseObjectDAO;

  ResultLimitServiceImpl() {
    super(ResultLimit.class);
  }

  @Override
  protected ResultLimitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
