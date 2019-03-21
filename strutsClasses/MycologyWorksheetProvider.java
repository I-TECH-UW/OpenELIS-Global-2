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
package us.mn.state.health.lims.common.provider.reports;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;

/**
 * @author benzd1
 *
 */
public class MycologyWorksheetProvider extends BaseReportsProvider {

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.provider.reports.BaseReportsProvider#processRequest(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * bugzilla 2274: added boolean successful
	 */
	public boolean processRequest(Map parameters, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		File reportFile = new File(context
				.getRealPath("/WEB-INF/reports/specimen_list.jasper"));

		ServletOutputStream servletOutputStream = response.getOutputStream();

		byte[] bytes = null;
		Connection conn = null;

		ActionError error = null;
		ActionMessages errors = new ActionMessages();

		try {


			InitialContext ic = new InitialContext();
			DataSource nativeDS = (DataSource) ic.lookup(SystemConfiguration.getInstance().getDefaultDataSource().toString());
			conn = nativeDS.getConnection();


			// get yesterday's date as date received

			// Put the current system date in a Calendar object.
			GregorianCalendar gc = new GregorianCalendar();
			// Subtract one day from the object.
			gc.add(Calendar.DATE, -1);
			// Get a Date object based on the Calendar object.
			Date dayAgo = gc.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String dateAsText = sdf.format(dayAgo);
				
			//convert string date to java.util.Date by going through java.sql.Date
			//bugzilla 2274 - date conversion fixed 
			String locale = SystemConfiguration.getInstance().getDefaultLocale()
			.toString();
	        java.sql.Date sDate = DateUtil.convertStringDateToSqlDate(
			dateAsText, locale);
			java.util.Date date = 
		        new java.util.Date(sDate.getTime());


			parameters.put("Param_Received_Date", date);

			bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
					parameters, conn);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException jre) {
			//bugzilla 2154
			LogEvent.logError("MycologyWorksheetProvider","processRequest()",jre.toString());			
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			//jre.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
            error = new ActionError("errors.jasperreport.general", null, null);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("MycologyWorksheetProvider","processRequest()",e.toString());
            error = new ActionError("errors.jasperreport.general", null, null);

		} finally {
			try {
				conn.close();
			} catch (SQLException sqle) {
				//bugzilla 2154
				LogEvent.logError("MycologyWorksheetProvider","processRequest()",sqle.toString());
			}
			
			if (error != null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				request.setAttribute(Globals.ERROR_KEY, errors);
                return false;
			} else {
				return true;
			}
		}

	}

}
