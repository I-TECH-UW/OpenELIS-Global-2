package us.mn.state.health.lims.common.servlet.query;
        
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.query.BaseQueryProvider;
import us.mn.state.health.lims.common.provider.query.QueryProviderFactory;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.security.SecureXmlHttpServletRequest;

public class AjaxQueryXMLServlet extends AjaxServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7346331231442794642L;

	public void sendData(String field, String message,
			HttpServletRequest request, HttpServletResponse response)
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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, LIMSRuntimeException {
		//check for authentication
		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		if (userModuleDAO.isSessionExpired(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println(StringUtil.getMessageForKey("message.error.unauthorized"));
			return;
		}

		String queryProvider = request.getParameter("provider");
		BaseQueryProvider provider = (BaseQueryProvider) QueryProviderFactory
				.getInstance().getQueryProvider(queryProvider);
		provider.setServlet(this);
		provider.processRequest(new SecureXmlHttpServletRequest(request), response);
	}

}
