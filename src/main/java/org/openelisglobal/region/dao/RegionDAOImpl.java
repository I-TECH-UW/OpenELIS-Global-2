package org.openelisglobal.region.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.region.valueholder.Region;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RegionDAOImpl extends BaseDAOImpl<Region, String> implements RegionDAO {
  RegionDAOImpl() {
    super(Region.class);
  }
}
