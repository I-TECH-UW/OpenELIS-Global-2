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
package org.openelisglobal.common.servlet.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.data.BaseDataProvider;
import org.openelisglobal.common.provider.data.DataProviderFactory;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.login.dao.UserModuleService;
import org.openelisglobal.security.SecureXmlHttpServletRequest;
import org.openelisglobal.spring.util.SpringContext;

/**
 * 
 * @author diane benz bugzilla 2443
 */
public class AjaxXMLServlet extends AjaxServlet {

    private BaseDataProvider dataProvider = null;

    @Override
    public void sendData(String field, String message, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (!StringUtil.isNullorNill(field)) {

            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<fieldmessage>");
            response.getWriter().write("<formfield>" + field + "</formfield>");
            response.getWriter().write("<message>" + message + "</message>");
            response.getWriter().write("</fieldmessage>");
        } else {
            // System.out.println("Returning no content with field " + field + " message " +
            // message);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, LIMSRuntimeException {
        // check for authentication
        UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
        if (userModuleService.isSessionExpired(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println(MessageUtil.getMessage("message.error.unauthorized"));
            return;
        }

        String dataProvider = request.getParameter("provider");
        BaseDataProvider provider = DataProviderFactory.getInstance().getDataProvider(dataProvider);
        provider.setServlet(this);
        provider.processRequest(new SecureXmlHttpServletRequest(request), response);
    }

}
