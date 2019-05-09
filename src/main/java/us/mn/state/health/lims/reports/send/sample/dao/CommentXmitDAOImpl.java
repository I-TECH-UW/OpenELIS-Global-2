package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.CommentXmit;

@Component
public class CommentXmitDAOImpl extends BaseDAOImpl<CommentXmit> implements CommentXmitDAO {
  CommentXmitDAOImpl() {
    super(CommentXmit.class);
  }
}
