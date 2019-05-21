package us.mn.state.health.lims.county.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.county.valueholder.County;

@Component
public class CountyDAOImpl extends BaseDAOImpl<County> implements CountyDAO {
  CountyDAOImpl() {
    super(County.class);
  }
}
