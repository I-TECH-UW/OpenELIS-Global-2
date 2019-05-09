package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.CommentXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.CommentXmit;

@Service
public class CommentXmitServiceImpl extends BaseObjectServiceImpl<CommentXmit> implements CommentXmitService {
  @Autowired
  protected CommentXmitDAO baseObjectDAO;

  CommentXmitServiceImpl() {
    super(CommentXmit.class);
  }

  @Override
  protected CommentXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
