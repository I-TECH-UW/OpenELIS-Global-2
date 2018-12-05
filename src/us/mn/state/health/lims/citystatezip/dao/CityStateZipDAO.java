/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.citystatezip.dao;

import java.util.List;

import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface CityStateZipDAO extends BaseDAO {

	public List getCities(String filter) throws LIMSRuntimeException;

	public List getZipCodesByCity(CityStateZip cityStateZip)throws LIMSRuntimeException;
	
    public List getCitiesByZipCode(CityStateZip cityStateZip) throws LIMSRuntimeException;
    
	public List getAllStateCodes() throws LIMSRuntimeException;
	
	public CityStateZip getCityStateZipByCityAndZipCode(CityStateZip cityStateZip) throws LIMSRuntimeException;
	
	public CityStateZip getState(CityStateZip cityStateZip) throws LIMSRuntimeException;
	
	//bugzilla 1765
	public CityStateZip getCity(CityStateZip cityStateZip) throws LIMSRuntimeException;
	
	//bugzilla 1765
	public CityStateZip getZipCode(CityStateZip cityStateZip) throws LIMSRuntimeException;
	
	//bugzilla 1765
	public boolean isCityStateZipComboValid(CityStateZip cityStateZip) throws LIMSRuntimeException;

    //bugzilla 1765
	public List getValidCityStateZipCombosForHumanSampleEntry(CityStateZip cityStateZip) throws LIMSRuntimeException;
	
	//bugizla 2393
	public String getCountyCodeByStateAndZipCode(CityStateZip cityStateZip) throws LIMSRuntimeException;

}
