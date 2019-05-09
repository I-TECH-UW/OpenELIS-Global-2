package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.UHLXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.UHLXmit;

@Service
public class UHLXmitServiceImpl extends BaseObjectServiceImpl<UHLXmit> implements UHLXmitService {
  @Autowired
  protected UHLXmitDAO baseObjectDAO;

  UHLXmitServiceImpl() {
    super(UHLXmit.class);
  }

  @Override
  protected UHLXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
