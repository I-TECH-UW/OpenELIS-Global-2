package spring.service.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.region.valueholder.Region;

@Service
public class RegionServiceImpl extends BaseObjectServiceImpl<Region> implements RegionService {
  @Autowired
  protected BaseDAO<Region> baseObjectDAO;

  RegionServiceImpl() {
    super(Region.class);
  }

  @Override
  protected BaseDAO<Region> getBaseObjectDAO() {
    return baseObjectDAO;}
}
