package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.ResultXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.ResultXmit;

@Service
public class ResultXmitServiceImpl extends BaseObjectServiceImpl<ResultXmit> implements ResultXmitService {
  @Autowired
  protected ResultXmitDAO baseObjectDAO;

  ResultXmitServiceImpl() {
    super(ResultXmit.class);
  }

  @Override
  protected ResultXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
