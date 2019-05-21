package us.mn.state.health.lims.region.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.region.valueholder.Region;

@Component
public class RegionDAOImpl extends BaseDAOImpl<Region> implements RegionDAO {
  RegionDAOImpl() {
    super(Region.class);
  }
}
