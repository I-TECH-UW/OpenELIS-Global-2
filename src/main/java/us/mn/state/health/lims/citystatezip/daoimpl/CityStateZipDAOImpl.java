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
package us.mn.state.health.lims.citystatezip.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;
import us.mn.state.health.lims.citystatezip.valueholder.CityView;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * @author diane benz
 */
/**
 * @author benzd1
 * 
 */
public class CityStateZipDAOImpl extends BaseDAOImpl implements CityStateZipDAO {

	public List getCities(String filter) throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();

		try {
			String sql = "from CityView csz where upper(csz.city) like upper(:param) order by upper(csz.city)";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", filter + "%");
			//for performance
			query.setMaxResults(100);
		    list = query.list();
		    
			for (int i = 0; i < list.size(); i++) {
				CityStateZip csz = new CityStateZip();
				csz.setId(String.valueOf(i));
				CityView c = new CityView();
				c = (CityView)list.get(i);
				csz.setCity(c.getCity());
				cityStateZips.add(csz);
			}

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getCities()",e.toString());			
			throw new LIMSRuntimeException(
					"Error in CityStateZip getCities(String filter)", e);
		}

		return cityStateZips;
	}

	public List getZipCodesByCity(CityStateZip cityStateZip)
			throws LIMSRuntimeException {
		List cityStateZips = new ArrayList();
		try {

			String sql = "select distinct csz.zipCode, csz.city from CityStateZip csz where upper(csz.city) = :param";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", cityStateZip.getCity().trim()
					.toUpperCase());

			List list = query.list();

			// different behavior using select distinct...we need to put data
			// into valueholder manually and load up the list to return
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				String zipCode = ((String) obj[0]).trim();
				String city = ((String) obj[1]).trim();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				String id = String.valueOf(i);
				CityStateZip csz = new CityStateZip();
				csz.setId(id);
				csz.setCity(city);
				csz.setZipCode(zipCode);
				cityStateZips.add(csz);
			}

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return cityStateZips;

		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getZipCodesByCity()",e.toString());
			throw new LIMSRuntimeException(
					"Error in CityStateZip getZipCodesByCity()", e);
		}
	}

	public List getCitiesByZipCode(CityStateZip cityStateZip)
			throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();
		try {
			String sql = "select distinct csz.city, csz.zipCode from CityStateZip csz where csz.zipCode = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", cityStateZip.getZipCode().trim());

			list = query.list();
			// different behavior using select distinct...we need to put data
			// into valueholder manually and load up the list to return
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				String city = ((String) obj[0]).trim();
				String zipCode = ((String) obj[1]).trim();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				String id = String.valueOf(i);
				CityStateZip csz = new CityStateZip();
				csz.setId(id);
				csz.setCity(city);
				csz.setZipCode(zipCode);
				cityStateZips.add(csz);
			}
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getCitiesByZipCode()",e.toString());
			throw new LIMSRuntimeException("Error in getCitiesByZipCode()", e);
		}

		return cityStateZips;
	}

	public List getAllStateCodes() throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();
		try {
		    //bugzilla 1908 postgres error on order by
			String sql = "select distinct upper(csz.state) from CityStateZip csz order by upper(csz.state)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			list = query.list();

			for (int i = 0; i < list.size(); i++) {
				String state = ((String) list.get(i)).trim();
				CityStateZip cityStateZip = new CityStateZip();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				cityStateZip.setId(String.valueOf(i));
				cityStateZip.setState(state);
				cityStateZips.add(cityStateZip);
			}

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getAllStateCodes()",e.toString());
			throw new LIMSRuntimeException(
					"Error in CityStateZip getAllStateCodes()", e);
		}

		return cityStateZips;
	}

	public CityStateZip getCityStateZipByCityAndZipCode(
			CityStateZip cityStateZip) throws LIMSRuntimeException {
		CityStateZip csz = null;
		try {
			String sql = "from CityStateZip csz where upper(csz.city)) = :param and csz.zipCode = :param2";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("param", cityStateZip.getCity().trim()
					.toUpperCase());
			query.setParameter("param2", cityStateZip.getZipCode());

			List list = query.list();

			if (list != null && list.size() > 0)
				csz = (CityStateZip) list.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getCityStateZipByCityAndZipCode()",e.toString());
			throw new LIMSRuntimeException(
					"Error in getCityStateZipByCityAndZipCode()", e);
		}

		return csz;
	}

	public CityStateZip getState(CityStateZip cityStateZip)
			throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();
		CityStateZip csz = null;
		try {
			String sql = "select distinct csz.state from CityStateZip csz where upper(csz.state) = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);

			query.setParameter("param", cityStateZip.getState().trim()
					.toUpperCase());
			list = query.list();

			for (int i = 0; i < list.size(); i++) {
				String state = ((String) list.get(i)).trim();
				CityStateZip cszip = new CityStateZip();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				cszip.setId(String.valueOf(i));
				cszip.setState(state);
				cityStateZips.add(cszip);
			}

			if (cityStateZips != null && cityStateZips.size() > 0)
				csz = (CityStateZip) cityStateZips.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","CityStateZip()",e.toString());
			throw new LIMSRuntimeException("Error in CityStateZip getState()",
					e);
		}

		return csz;
	}
	
	//bugzilla 1765
	public CityStateZip getCity(CityStateZip cityStateZip)
			throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();
		CityStateZip csz = null;
		try {
			String sql = "select distinct csz.city from CityStateZip csz where upper(csz.city) = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);

			query.setParameter("param", cityStateZip.getCity().trim()
					.toUpperCase());
			list = query.list();

			for (int i = 0; i < list.size(); i++) {
				String city = ((String) list.get(i)).trim();
				CityStateZip cszip = new CityStateZip();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				cszip.setId(String.valueOf(i));
				cszip.setCity(city);
				cityStateZips.add(cszip);
			}

			if (cityStateZips != null && cityStateZips.size() > 0)
				csz = (CityStateZip) cityStateZips.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getCity()",e.toString());
			throw new LIMSRuntimeException("Error in CityStateZip getCity()",
					e);
		}

		return csz;
	}
	
	//bugzilla 1765
	public CityStateZip getZipCode(CityStateZip cityStateZip)
			throws LIMSRuntimeException {
		List list = new Vector();
		List cityStateZips = new ArrayList();
		CityStateZip csz = null;
		try {
			String sql = "select distinct csz.zipCode from CityStateZip csz where upper(csz.zipCode) = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);

			query.setParameter("param", cityStateZip.getZipCode().trim()
					.toUpperCase());
			list = query.list();

			for (int i = 0; i < list.size(); i++) {
				String zipCode = ((String) list.get(i)).trim();
				CityStateZip cszip = new CityStateZip();
				// give the cityStateZip object an artificial id needed for
				// autocomplete but not stored anywhere
				cszip.setId(String.valueOf(i));
				cszip.setCity(zipCode);
				cityStateZips.add(cszip);
			}

			if (cityStateZips != null && cityStateZips.size() > 0)
				csz = (CityStateZip) cityStateZips.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getZip()",e.toString());
			throw new LIMSRuntimeException("Error in CityStateZip getZip()", e);
		}

		return csz;
	}

	//bugzilla 1765 - validate city state zip combination
	public boolean isCityStateZipComboValid(
			CityStateZip cityStateZip) throws LIMSRuntimeException {
		boolean isValid = false;
		try {
			String sql = null;
			String state = cityStateZip.getState();
			String city = cityStateZip.getCity();
			String zipCode = cityStateZip.getZipCode();
			List list = new Vector();

			if (!StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.zipCode, csz.city, csz.state from CityStateZip csz where upper(csz.city) = :param and csz.zipCode = :param2 and upper(csz.state) = :param3";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				query.setParameter("param2", cityStateZip.getZipCode().trim()
						.toUpperCase());
				query.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				list = query.list();
			} else if (!StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.city, csz.state from CityStateZip csz where upper(csz.city) = :param and upper(csz.state) = :param3";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				query.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				list = query.list();
			} else if (StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.zipCode, csz.city from CityStateZip csz where upper(csz.city) = :param and csz.zipCode = :param2";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				query.setParameter("param2", cityStateZip.getZipCode().trim()
						.toUpperCase());
				list = query.list();
			} else if (!StringUtil.isNullorNill(state)
					&& StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.zipCode, csz.state from CityStateZip csz where csz.zipCode = :param2 and upper(csz.state) = :param3";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param2", cityStateZip.getZipCode().trim()
						.toUpperCase());
				query.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				list = query.list();
			} else if (StringUtil.isNullorNill(state) && StringUtil.isNullorNill(zipCode) && !StringUtil.isNullorNill(city)) {
				sql = "select distinct csz.city from CityStateZip csz where upper(csz.city) = :param";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				list = query.list();
			} else if (StringUtil.isNullorNill(state) && StringUtil.isNullorNill(city) && !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.zipCode from CityStateZip csz where csz.zipCode = :param2";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param2", cityStateZip.getZipCode().trim()
						.toUpperCase());
				list = query.list();
			} else if (StringUtil.isNullorNill(city) && StringUtil.isNullorNill(zipCode) && !StringUtil.isNullorNill(state)) {
				sql = "select distinct csz.state from CityStateZip csz where upper(csz.state) = :param3";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				list = query.list();
			} else if (StringUtil.isNullorNill(state)
					&& StringUtil.isNullorNill(city)
					&& StringUtil.isNullorNill(zipCode)) {
				isValid = true;
			}

			if (list.size() > 0)
				isValid = true;

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return isValid;

		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","isCityStateZipValidForHumanSampleEntry()",e.toString());
			throw new LIMSRuntimeException(
					"Error in isCityStateZipValidForHumanSampleEntry()", e);
		}
	}
	
	//bugzilla 1765 - validate city state zip combination 
	public List getValidCityStateZipCombosForHumanSampleEntry(
			CityStateZip cityStateZip) throws LIMSRuntimeException {

		List cityStateZips = new ArrayList();
		try {
			String sql = null;
			String state = cityStateZip.getState();
			String city = cityStateZip.getCity();
			String zipCode = cityStateZip.getZipCode();
			List listByCity = new Vector();
			List listByZip = new Vector();
			List list = new Vector();

			//1) ALL THREE PARAMETERS NEED TO BE SEARCHED (CITY, STATE, ZIP)
			if (!StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				//first get all where city matches (and state)
				sql = "select distinct csz.zipCode, csz.city, csz.state from CityStateZip csz where upper(csz.city) = :param and upper(csz.state) = :param3 order by csz.zipCode";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				query.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				listByCity = query.list();
				for (int i = 0; i < listByCity.size(); i++) {
					Object[] obj = (Object[]) listByCity.get(i);
					String z = ((String) obj[0]).trim();
					String c = ((String) obj[1]).trim();
					String s = ((String) obj[2]).trim();
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
				//now get all where zip matches (and state)
				sql = "select distinct csz.zipCode, csz.city, csz.state from CityStateZip csz where csz.zipCode = :param2 and upper(csz.state) = :param3 order by csz.city";
				org.hibernate.Query query2 = HibernateUtil.getSession()
						.createQuery(sql);
				query2.setParameter("param2", cityStateZip.getZipCode().trim());
				query2.setParameter("param3", cityStateZip.getState().trim()
						.toUpperCase());
				listByZip = query2.list();
				int j = cityStateZips.size();
				for (int i = 0; i < listByZip.size(); i++) {
					Object[] obj = (Object[]) listByZip.get(i);
					String z = ((String) obj[0]).trim();
					String c = ((String) obj[1]).trim();
					String s = ((String) obj[2]).trim();
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(j + i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
				
				//for case that state entered is incorrect for both city and zip we should list all matches for city and zip regardless of state
				if (cityStateZips.size() == 0) {
					sql = "select distinct csz.zipCode, csz.city, csz.state from CityStateZip csz where upper(csz.city) = :param order by csz.zipCode";
					org.hibernate.Query query3 = HibernateUtil.getSession()
							.createQuery(sql);
					query3.setParameter("param", cityStateZip.getCity().trim()
							.toUpperCase());
					listByCity = query3.list();
					for (int i = 0; i < listByCity.size(); i++) {
						Object[] obj = (Object[]) listByCity.get(i);
						String z = ((String) obj[0]).trim();
						String c = ((String) obj[1]).trim();
						String s = ((String) obj[2]).trim();
						// give the cityStateZip object an artificial id needed for
						// autocomplete but not stored anywhere
						String id = String.valueOf(i);
						CityStateZip csz = new CityStateZip();
						csz.setId(id);
						setCityStateZipValueholder(csz, c, s, z);
						cityStateZips.add(csz);
					}
					//now get all where zip matches 
					sql = "select distinct csz.zipCode, csz.city, csz.state from CityStateZip csz where csz.zipCode = :param2 order by csz.city";
					org.hibernate.Query query4 = HibernateUtil.getSession()
							.createQuery(sql);
					query4.setParameter("param2", cityStateZip.getZipCode().trim());
					listByZip = query4.list();
					int k = cityStateZips.size();
					for (int i = 0; i < listByZip.size(); i++) {
						Object[] obj = (Object[]) listByZip.get(i);
						String z = ((String) obj[0]).trim();
						String c = ((String) obj[1]).trim();
						String s = ((String) obj[2]).trim();
						// give the cityStateZip object an artificial id needed for
						// autocomplete but not stored anywhere
						String id = String.valueOf(k + i);
						CityStateZip csz = new CityStateZip();
						csz.setId(id);
						setCityStateZipValueholder(csz, c, s, z);
						cityStateZips.add(csz);
					}
				}
			} else if (!StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.city, csz.state from CityStateZip csz where upper(csz.city) = :param order by csz.state";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				list = query.list();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String c = ((String) obj[0]).trim();
					String s = ((String) obj[1]).trim();
					String z = null;
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
			} else if (StringUtil.isNullorNill(state)
					&& !StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.zipCode, csz.city from CityStateZip csz where upper(csz.city) = :param order by csz.zipCode";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param", cityStateZip.getCity().trim()
						.toUpperCase());
				listByCity = query.list();
				for (int i = 0; i < listByCity.size(); i++) {
					Object[] obj = (Object[]) listByCity.get(i);
					String z = ((String) obj[0]).trim();
					String c = ((String) obj[1]).trim();
					String s = null;
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
				//now get all where zip matches 
				sql = "select distinct csz.zipCode, csz.city from CityStateZip csz where csz.zipCode = :param2 order by csz.city";
				org.hibernate.Query query2 = HibernateUtil.getSession()
						.createQuery(sql);
				query2.setParameter("param2", cityStateZip.getZipCode().trim());
    			listByZip = query2.list();
				int k = cityStateZips.size();
				for (int i = 0; i < listByZip.size(); i++) {
					Object[] obj = (Object[]) listByZip.get(i);
					String z = ((String) obj[0]).trim();
					String c = ((String) obj[1]).trim();
					String s = null;
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(k + i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
			} else if (!StringUtil.isNullorNill(state)
					&& StringUtil.isNullorNill(city)
					&& !StringUtil.isNullorNill(zipCode)) {
				sql = "select distinct csz.state, csz.zipCode from CityStateZip csz where csz.zipCode = :param2 order by csz.state";
				org.hibernate.Query query = HibernateUtil.getSession()
						.createQuery(sql);
				query.setParameter("param2", cityStateZip.getZipCode().trim());
				list = query.list();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String s = ((String) obj[1]).trim();
					String z = ((String) obj[2]).trim();
					String c = null;
					// give the cityStateZip object an artificial id needed for
					// autocomplete but not stored anywhere
					String id = String.valueOf(i);
					CityStateZip csz = new CityStateZip();
					csz.setId(id);
					setCityStateZipValueholder(csz, c, s, z);
					cityStateZips.add(csz);
				}
			}

		
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return cityStateZips;

		} catch (Exception e) {
			//buzilla 2154
			LogEvent.logError("CityStateZipDAOImpl","getValidCityStateZipCombosForHumanSampleEntry()",e.toString());
			throw new LIMSRuntimeException(
					"Error in getValidCityStateZipCombosForHumanSampleEntry()", e);
		}
	}
	
	//make sure values are not null but blank if non-existant
	private CityStateZip  setCityStateZipValueholder(CityStateZip csz, String city, String state, String zip) {
		if (StringUtil.isNullorNill(city)) {
			city = "";
		}
		if (StringUtil.isNullorNill(state)) {
			state = "";
		}
		if (StringUtil.isNullorNill(zip)) {
			zip = "";
		}
		
		csz.setState(state);
		csz.setCity(city);
		csz.setZipCode(zip);
		return csz;
	}
	
	//bugzilla 2393
	public String getCountyCodeByStateAndZipCode(
			CityStateZip cityStateZip) throws LIMSRuntimeException {
		List list = new Vector();
		String countyCode = null;
		try {
			
		    list = HibernateUtil.getSession().getNamedQuery(
					"cityStateZip.getCountyCodeByStateAndZipCode")
					.setParameter("param", cityStateZip.getState().trim().toUpperCase())
					.setParameter("param2", cityStateZip.getZipCode().trim())
					.list();
			
			if (list != null && list.size() > 0)
				countyCode = (String) list.get(0);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("CityStateZipDAOImpl","getCountyCodeByStateAndZipCode()",e.toString());
			throw new LIMSRuntimeException(
					"Error in getCountyCodeByStateAndZipCode()", e);
		}

		return countyCode;
	}
	
}