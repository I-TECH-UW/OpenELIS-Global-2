package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.MessageXmit;

@Component
public class MessageXmitDAOImpl extends BaseDAOImpl<MessageXmit> implements MessageXmitDAO {
  MessageXmitDAOImpl() {
    super(MessageXmit.class);
  }
}
