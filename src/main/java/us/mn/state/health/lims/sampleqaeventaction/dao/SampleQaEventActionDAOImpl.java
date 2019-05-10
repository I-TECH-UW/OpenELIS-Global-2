package us.mn.state.health.lims.sampleqaeventaction.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.sampleqaeventaction.valueholder.SampleQaEventAction;

@Component
public class SampleQaEventActionDAOImpl extends BaseDAOImpl<SampleQaEventAction> implements SampleQaEventActionDAO {
  SampleQaEventActionDAOImpl() {
    super(SampleQaEventAction.class);
  }
}
