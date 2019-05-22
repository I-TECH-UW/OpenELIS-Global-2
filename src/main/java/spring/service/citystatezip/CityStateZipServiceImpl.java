package spring.service.citystatezip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return baseObjectDAO;
	}

	@Override
	@Transactional 
	public List getAllStateCodes() {
		return baseObjectDAO.getAllStateCodes();
	}
}
