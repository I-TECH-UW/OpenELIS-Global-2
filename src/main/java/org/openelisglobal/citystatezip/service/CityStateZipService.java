package org.openelisglobal.citystatezip.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.citystatezip.valueholder.CityStateZip;

public interface CityStateZipService extends BaseObjectService<CityStateZip, String> {
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
