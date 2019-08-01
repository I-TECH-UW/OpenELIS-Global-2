package org.openelisglobal.common.servlet.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.query.BaseQueryProvider;
import org.openelisglobal.common.provider.query.QueryProviderFactory;
import org.openelisglobal.common.servlet.validation.AjaxServlet;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.login.dao.UserModuleService;
import org.openelisglobal.security.SecureXmlHttpServletRequest;

public class AjaxQueryXMLServlet extends AjaxServlet {

    /**
     *
     */
    private static final long serialVersionUID = -7346331231442794642L;

    @Override
    public void sendData(String field, String message, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");

        if (!StringUtil.isNullorNill(field)) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<fieldmessage>");
            response.getWriter().write("<formfield>" + field + "</formfield>");
            response.getWriter().write("<message>" + message + "</message>");
            response.getWriter().write("</fieldmessage>");
        } else {
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

        String queryProvider = request.getParameter("provider");
        BaseQueryProvider provider = QueryProviderFactory.getInstance().getQueryProvider(queryProvider);
        provider.setServlet(this);
        provider.processRequest(new SecureXmlHttpServletRequest(request), response);
    }

}
