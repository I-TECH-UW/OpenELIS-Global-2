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
 * bugzilla 1765 changed to validate city only (combination of city/zip is validated elsewhere)
 */
public class CityValidationProvider extends BaseValidationProvider {

	public CityValidationProvider() {
		super();
	}

	public CityValidationProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get id from request
		String city = (String) request.getParameter("id");
		String formField = (String) request.getParameter("field");
		String result = validate(city);
		ajaxServlet.sendData(formField, result, request, response);
	}


	public String validate(String city)
			throws LIMSRuntimeException {

		StringBuffer s = new StringBuffer();

		if (!StringUtil.isNullorNill(city)) {
		    //bugzilla 1545
			CityStateZipDAO cityStateZipDAO = new CityStateZipDAOImpl();
			CityStateZip cityStateZip = new CityStateZip();
			cityStateZip.setCity(city.trim());
			cityStateZip = cityStateZipDAO.getCity(cityStateZip);
			
			if (cityStateZip == null) {
				s.append(INVALID);
			} else {
                s.append(VALID);
			}
		}	else {
			s.append(VALID);
		}

		return s.toString();
	}

}
