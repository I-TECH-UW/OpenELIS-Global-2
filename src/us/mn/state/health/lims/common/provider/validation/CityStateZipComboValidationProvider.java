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
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.citystatezip.dao.CityStateZipDAO;
import us.mn.state.health.lims.citystatezip.daoimpl.CityStateZipDAOImpl;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.StringUtil;


/**
 * @author benzd1
 * bugzilla 1765 validates combinations of city/state/zip
 */
public class CityStateZipComboValidationProvider extends BaseValidationProvider {

	public CityStateZipComboValidationProvider() {
		super();
	}

	public CityStateZipComboValidationProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get id from request
		String formField = (String) request.getParameter("field");
		String city = (String) request.getParameter("city");
    	String zip = request.getParameter("zipCode");
		String state = request.getParameter("state");

		if (StringUtil.isNullorNill(city)) {
			city = "";
		}
		if (StringUtil.isNullorNill(state)) {
			state = "";
		}
		if (StringUtil.isNullorNill(zip)) {
			zip = "";
		}
		String result = validate(city, state, zip);
		ajaxServlet.sendData(formField, result, request, response);
	}


	public String validate(String city, String state, String zipCode )
			throws LIMSRuntimeException {

		StringBuffer s = new StringBuffer();

		    //bugzilla 1545
			CityStateZipDAO cityStateZipDAO = new CityStateZipDAOImpl();
			CityStateZip cityStateZip = new CityStateZip();
			cityStateZip.setCity(city.trim());
			cityStateZip.setZipCode(zipCode);
			cityStateZip.setState(state);
			if (cityStateZipDAO.isCityStateZipComboValid(cityStateZip)) {
				s.append(VALID);
			} else {
				s.append(INVALID);
			}


		return s.toString();
	}

}
