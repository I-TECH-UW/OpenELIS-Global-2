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
package us.mn.state.health.lims.common.servlet.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.provider.reports.BaseReportsProvider;
import us.mn.state.health.lims.common.provider.reports.ReportsProviderFactory;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;

/**
 * @author benzd1
 *
 */
public class ReportsServlet extends HttpServlet {

	private BaseReportsProvider reportProvider = null;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
	{
		//check for authentication
		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		if (userModuleDAO.isSessionExpired(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println(StringUtil.getMessageForKey("message.error.unauthorized"));
			return;
		}
		String reportsProvider = request.getParameter("provider");
		Map paramMap = request.getParameterMap();
		
		HashMap parameters = new HashMap();
		
		Set keySet = paramMap.keySet();
		
		Collection values = paramMap.values();
		
        Iterator itKey = keySet.iterator();
        
        List keyList = new ArrayList();
        
        while (itKey.hasNext()) {
        	String key = (String)itKey.next();
        	keyList.add(key);
        }
        
        Iterator itVal = values.iterator();
        
        List valList = new ArrayList();
        
        while (itVal.hasNext()) {
        	String[] vals = (String[])itVal.next();
        	valList.add(vals[0]);
        }
        
        //populate HashMap
        for (int i = 0; i < keyList.size(); i++) {
        	parameters.put(keyList.get(i), valList.get(i));
        }



		reportProvider = (BaseReportsProvider)ReportsProviderFactory
				.getInstance().getReportsProvider(reportsProvider);

		reportProvider.setServlet(this);
		//bugzilla 2274: added error handling
		boolean success = reportProvider.processRequest(parameters, request, response);

        //if unsuccessful route back to reports menu
		if (!success) {
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/MainMenu.do");
			dispatcher.forward(request, response);
		}

	}

}
