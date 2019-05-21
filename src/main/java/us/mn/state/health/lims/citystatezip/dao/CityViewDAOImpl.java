package us.mn.state.health.lims.citystatezip.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.citystatezip.valueholder.CityView;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;

@Component
@Transactional
public class CityViewDAOImpl extends BaseDAOImpl<CityView> implements CityViewDAO {
  CityViewDAOImpl() {
    super(CityView.class);
  }
}
