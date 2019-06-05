package spring.service.citystatezip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;

@Service
public class CityStateZipServiceImpl extends BaseObjectServiceImpl<CityStateZip, String> implements CityStateZipService {
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

	@Override
	public CityStateZip getState(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getState(cityStateZip);
	}

	@Override
	public List getValidCityStateZipCombosForHumanSampleEntry(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getValidCityStateZipCombosForHumanSampleEntry(cityStateZip);
	}

	@Override
	public List getCities(String filter) {
        return getBaseObjectDAO().getCities(filter);
	}

	@Override
	public CityStateZip getZipCode(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getZipCode(cityStateZip);
	}

	@Override
	public CityStateZip getCity(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getCity(cityStateZip);
	}

	@Override
	public List getCitiesByZipCode(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getCitiesByZipCode(cityStateZip);
	}

	@Override
	public String getCountyCodeByStateAndZipCode(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getCountyCodeByStateAndZipCode(cityStateZip);
	}

	@Override
	public List getZipCodesByCity(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getZipCodesByCity(cityStateZip);
	}

	@Override
	public boolean isCityStateZipComboValid(CityStateZip cityStateZip) {
        return getBaseObjectDAO().isCityStateZipComboValid(cityStateZip);
	}

	@Override
	public CityStateZip getCityStateZipByCityAndZipCode(CityStateZip cityStateZip) {
        return getBaseObjectDAO().getCityStateZipByCityAndZipCode(cityStateZip);
	}
}
