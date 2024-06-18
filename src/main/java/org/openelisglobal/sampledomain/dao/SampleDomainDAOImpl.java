package org.openelisglobal.sampledomain.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.sampledomain.valueholder.SampleDomain;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SampleDomainDAOImpl extends BaseDAOImpl<SampleDomain, String>
    implements SampleDomainDAO {
  SampleDomainDAOImpl() {
    super(SampleDomain.class);
  }
}
