package us.mn.state.health.lims.samplenewborn.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.samplenewborn.valueholder.SampleNewborn;

@Component
@Transactional 
public class SampleNewbornDAOImpl extends BaseDAOImpl<SampleNewborn> implements SampleNewbornDAO {
  SampleNewbornDAOImpl() {
    super(SampleNewborn.class);
  }
}
