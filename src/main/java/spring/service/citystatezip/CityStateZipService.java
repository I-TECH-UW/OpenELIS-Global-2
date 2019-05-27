package spring.service.citystatezip;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;

public interface CityStateZipService extends BaseObjectService<CityStateZip> {
	CityStateZip getState(CityStateZip cityStateZip);

	List getValidCityStateZipCombosForHumanSampleEntry(CityStateZip cityStateZip);

	List getCities(String filter);

	CityStateZip getZipCode(CityStateZip cityStateZip);

	List getAllStateCodes();

	CityStateZip getCity(CityStateZip cityStateZip);

	List getCitiesByZipCode(CityStateZip cityStateZip);

	String getCountyCodeByStateAndZipCode(CityStateZip cityStateZip);

	List getZipCodesByCity(CityStateZip cityStateZip);

	boolean isCityStateZipComboValid(CityStateZip cityStateZip);

	CityStateZip getCityStateZipByCityAndZipCode(CityStateZip cityStateZip);
}
