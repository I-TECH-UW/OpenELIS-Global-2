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
package us.mn.state.health.lims.common.servlet.autocomplete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import us.mn.state.health.lims.common.provider.autocomplete.AutocompleteProviderFactory;
import us.mn.state.health.lims.common.provider.autocomplete.BaseAutocompleteProvider;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;

public class AjaxXMLServlet extends BaseAjaxServlet {

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//check for authentication
		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		if (userModuleDAO.isSessionExpired(request)) {
			return new AjaxXmlBuilder().toString();
		}

		String autocompleteProvider = request.getParameter("provider");
		String autocompleteFieldName = request.getParameter("fieldName");
		String autocompleteId = request.getParameter("idName");

		BaseAutocompleteProvider provider = (BaseAutocompleteProvider) AutocompleteProviderFactory
				.getInstance().getAutocompleteProvider(autocompleteProvider);

		provider.setServlet(this);
		List list = provider.processRequest(request, response);

		return  new AjaxXmlBuilder().addItems(list, autocompleteFieldName, autocompleteId).toString();
	}

}
