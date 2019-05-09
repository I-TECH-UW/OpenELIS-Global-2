package spring.service.citystatezip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;

@Service
public class CityStateZipServiceImpl extends BaseObjectServiceImpl<CityStateZip> implements CityStateZipService {
  @Autowired
  protected CityStateZipDAO baseObjectDAO;

  CityStateZipServiceImpl() {
    super(CityStateZip.class);
  }

  @Override
  protected CityStateZipDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
