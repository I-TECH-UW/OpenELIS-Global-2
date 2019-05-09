package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.CodeElementXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.CodeElementXmit;

@Service
public class CodeElementXmitServiceImpl extends BaseObjectServiceImpl<CodeElementXmit> implements CodeElementXmitService {
  @Autowired
  protected CodeElementXmitDAO baseObjectDAO;

  CodeElementXmitServiceImpl() {
    super(CodeElementXmit.class);
  }

  @Override
  protected CodeElementXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
