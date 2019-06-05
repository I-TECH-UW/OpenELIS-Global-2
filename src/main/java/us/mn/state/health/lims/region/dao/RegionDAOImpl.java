package us.mn.state.health.lims.region.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.region.valueholder.Region;

@Component
@Transactional 
public class RegionDAOImpl extends BaseDAOImpl<Region, String> implements RegionDAO {
  RegionDAOImpl() {
    super(Region.class);
  }
}
