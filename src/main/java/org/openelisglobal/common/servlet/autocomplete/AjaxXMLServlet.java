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
package org.openelisglobal.common.servlet.autocomplete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.provider.autocomplete.AutocompleteProviderFactory;
import org.openelisglobal.common.provider.autocomplete.BaseAutocompleteProvider;
import org.openelisglobal.login.dao.UserModuleService;

public class AjaxXMLServlet extends BaseAjaxServlet {

    @Override
    public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // check for authentication
        UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
        if (userModuleService.isSessionExpired(request)) {
            return new AjaxXmlBuilder().toString();
        }

        String autocompleteProvider = request.getParameter("provider");
        String autocompleteFieldName = request.getParameter("fieldName");
        String autocompleteId = request.getParameter("idName");

        BaseAutocompleteProvider provider = AutocompleteProviderFactory.getInstance()
                .getAutocompleteProvider(autocompleteProvider);

        provider.setServlet(this);
        List list = provider.processRequest(request, response);

        return new AjaxXmlBuilder().addItems(list, autocompleteFieldName, autocompleteId).toString();
    }

}
