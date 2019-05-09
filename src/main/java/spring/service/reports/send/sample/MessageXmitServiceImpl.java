package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.MessageXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.MessageXmit;

@Service
public class MessageXmitServiceImpl extends BaseObjectServiceImpl<MessageXmit> implements MessageXmitService {
  @Autowired
  protected MessageXmitDAO baseObjectDAO;

  MessageXmitServiceImpl() {
    super(MessageXmit.class);
  }

  @Override
  protected MessageXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
