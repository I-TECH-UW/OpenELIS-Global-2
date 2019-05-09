package us.mn.state.health.lims.sourceofsample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;

@Component
public class SourceOfSampleDAOImpl extends BaseDAOImpl<SourceOfSample> implements SourceOfSampleDAO {
  SourceOfSampleDAOImpl() {
    super(SourceOfSample.class);
  }
}
