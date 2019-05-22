package us.mn.state.health.lims.sampledomain.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.sampledomain.valueholder.SampleDomain;

@Component
@Transactional 
public class SampleDomainDAOImpl extends BaseDAOImpl<SampleDomain> implements SampleDomainDAO {
  SampleDomainDAOImpl() {
    super(SampleDomain.class);
  }
}
