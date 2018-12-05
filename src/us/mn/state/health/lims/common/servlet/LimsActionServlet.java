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
package us.mn.state.health.lims.common.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;

public class LimsActionServlet extends ActionServlet {

	public LimsActionServlet() {
		super();
		//System.out.println("LimsActionServlet");
	}

	public void init() throws ServletException {

		// Wraps the entire initialization in a try/catch to better handle
		// unexpected exceptions and errors to provide better feedback
		// to the developer
		//System.out.println("LimsActionServlet init()");
		try {
			initInternal();
			initOther();
			initServlet();

			getServletContext().setAttribute(Globals.ACTION_SERVLET_KEY, this);
			initModuleConfigFactory();
			// Initialize modules as needed
			ModuleConfig moduleConfig = initModuleConfig("", config);
			initModuleMessageResources(moduleConfig);
			initModuleDataSources(moduleConfig);
			initModulePlugIns(moduleConfig);
			
			//bugzilla 1431
			ActionConfig [] actionConfigs = moduleConfig.findActionConfigs();
			for (int i = 0; i < actionConfigs.length; i++) {
	
					ActionConfig actionConfig = actionConfigs[i];
					ForwardConfig [] forwardConfigs = actionConfig.findForwardConfigs();

					for (int j = 0; j < forwardConfigs.length; j++) {
						ForwardConfig forwardConfig = forwardConfigs[j];
						if (StringUtil.isNullorNill(forwardConfig.getModule()) || !forwardConfig.getModule().equals(IActionConstants.OPEN_REPORTS_MODULE)) {
							continue;
						}
						actionConfig.removeForwardConfig(forwardConfig);
 						forwardConfig.setModule(SystemConfiguration.getInstance().getOpenReportsSwitchModulePath());
						forwardConfig.setPath(forwardConfig.getModule() + forwardConfig.getPath());
						actionConfig.addForwardConfig(forwardConfig);
					}


			}
				
			moduleConfig.freeze();

			Enumeration names = getServletConfig().getInitParameterNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (!name.startsWith("config/")) {
					continue;
				}
				String prefix = name.substring(6);
				moduleConfig = initModuleConfig(prefix, getServletConfig()
						.getInitParameter(name));
				initModuleMessageResources(moduleConfig);
				initModuleDataSources(moduleConfig);
				initModulePlugIns(moduleConfig);
				moduleConfig.freeze();
			}

			this.initModulePrefixes(this.getServletContext());

			this.destroyConfigDigester();

		} catch (UnavailableException ex) {
			//bugzilla 2154
			LogEvent.logError("LimsActionServlet","init()",ex.toString());	
			throw ex;
		} catch (Throwable t) {

			// The follow error message is not retrieved from internal message
			// resources as they may not have been able to have been
			// initialized
			//bugzilla 2154
			LogEvent.logError("LimsActionServlet","init()","Unable to initialize Struts ActionServlet due to an "
									+ "unexpected exception or error thrown, so marking the "
									+ "servlet as unavailable.  Most likely, this is due to an "
									+ "incorrect or missing library dependency." + " " + t.getMessage());
			throw new UnavailableException(t.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
 		//System.out.println("I am in doGet of ActionServlet "
		//		+ request.getRequestURL());
		process(request, response);

	}

}