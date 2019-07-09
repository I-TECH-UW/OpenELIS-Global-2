package us.mn.state.health.lims.sourceofsample.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;

@Component
@Transactional 
public class SourceOfSampleDAOImpl extends BaseDAOImpl<SourceOfSample, String> implements SourceOfSampleDAO {
  SourceOfSampleDAOImpl() {
    super(SourceOfSample.class);
  }
}
