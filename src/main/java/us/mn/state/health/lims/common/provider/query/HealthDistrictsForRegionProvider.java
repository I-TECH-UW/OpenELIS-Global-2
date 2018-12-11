/*
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
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.util.XMLUtil;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;

public class HealthDistrictsForRegionProvider extends BaseQueryProvider {

	/**
	 * @throws LIMSInvalidConfigurationException
	 * @see us.mn.state.health.lims.common.provider.query.BaseQueryProvider#processRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder xml = new StringBuilder();
		String result = VALID;

		List<Organization> districts = new OrganizationDAOImpl().getOrganizationsByParentId( request.getParameter("regionId"));
		createDistrictXml(districts, request.getParameter("selectedValue"), xml);
		
		ajaxServlet.sendData(xml.toString(), result, request, response);
	}

	private void createDistrictXml(List<Organization> districts, String selectedValue,StringBuilder xml) {
		xml.append("<districts>");
		for( Organization org : districts){
			xml.append("<district ");
			XMLUtil.appendKeyValueAttribute("id", org.getId(), xml);
			XMLUtil.appendKeyValueAttribute("value", org.getOrganizationName(), xml);
			xml.append(" />");
		}
		xml.append("</districts>");
		XMLUtil.appendKeyValue("selectedValue", selectedValue, xml);
	}



}
