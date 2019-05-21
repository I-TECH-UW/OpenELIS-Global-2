package spring.service.sampledomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.sampledomain.valueholder.SampleDomain;

@Service
public class SampleDomainServiceImpl extends BaseObjectServiceImpl<SampleDomain> implements SampleDomainService {
  @Autowired
  protected BaseDAO<SampleDomain> baseObjectDAO;

  SampleDomainServiceImpl() {
    super(SampleDomain.class);
  }

  @Override
  protected BaseDAO<SampleDomain> getBaseObjectDAO() {
    return baseObjectDAO;}
}
