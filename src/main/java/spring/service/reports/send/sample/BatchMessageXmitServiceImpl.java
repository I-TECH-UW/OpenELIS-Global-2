package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.BatchMessageXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.BatchMessageXmit;

@Service
public class BatchMessageXmitServiceImpl extends BaseObjectServiceImpl<BatchMessageXmit> implements BatchMessageXmitService {
  @Autowired
  protected BatchMessageXmitDAO baseObjectDAO;

  BatchMessageXmitServiceImpl() {
    super(BatchMessageXmit.class);
  }

  @Override
  protected BatchMessageXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
