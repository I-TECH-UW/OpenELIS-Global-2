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
package org.openelisglobal.common.provider.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.services.ExchangeConfigurationService;
import org.openelisglobal.common.util.XMLUtil;

public class ConnectionTestProvider extends BaseQueryProvider {

    /**
     * @throws LIMSInvalidConfigurationException
     * @see org.openelisglobal.common.provider.query.BaseQueryProvider#processRequest(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExchangeConfigurationService service = new ExchangeConfigurationService(request.getParameter("connectionId"));
        String testResult = service.testConnection(request.getParameter("url"));

        StringBuilder xml = new StringBuilder();
        createXml(testResult, xml);
        String result = VALID;

        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    private void createXml(String testResult, StringBuilder xml) {
        xml.append("<testResult ");
        XMLUtil.appendAttributeKeyValue("result", testResult, xml);
        xml.append(" />");
    }

}
