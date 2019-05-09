package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.MNPHLXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.MNPHLXmit;

@Service
public class MNPHLXmitServiceImpl extends BaseObjectServiceImpl<MNPHLXmit> implements MNPHLXmitService {
  @Autowired
  protected MNPHLXmitDAO baseObjectDAO;

  MNPHLXmitServiceImpl() {
    super(MNPHLXmit.class);
  }

  @Override
  protected MNPHLXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
