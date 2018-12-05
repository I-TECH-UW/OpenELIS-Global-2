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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.common.util;

import java.io.InputStream;
import java.util.Properties;

import us.mn.state.health.lims.common.dao.DatabaseChangeLogDAO;
import us.mn.state.health.lims.common.daoimpl.DatabaseChangeLogDAOImpl;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;

public class Versioning {

	private static final String PROPERTY_FILE = "/build.properties";
	private static String buildNumber = "Not set";
	private static String releaseNumber = " ";


	static {
		InputStream propertyStream = null;
		Properties properties = null;

		try {
			propertyStream = new Versioning().getClass().getResourceAsStream(PROPERTY_FILE);

			properties = new Properties();

			properties.load(propertyStream);

		} catch (Exception e) {
			LogEvent.logError("Versioning","",e.toString());
		} finally {
			if (null != propertyStream) {
				try {
					propertyStream.close();
					propertyStream = null;
				} catch (Exception e) {
			        LogEvent.logError("Versioning","static initializer",e.toString());
				}
			}
		}
		if( properties != null){
			releaseNumber = properties.getProperty("release", " ");
			buildNumber = properties.getProperty("build", "Not set");
		}
	}

	public static String getDatabaseVersion(){
		DatabaseChangeLogDAO databaseChangeLogDAO = new DatabaseChangeLogDAOImpl();
		DatabaseChangeLog changeLog = databaseChangeLogDAO.getLastExecutedChange();

		if( changeLog != null){
			return changeLog.getAuthor() + "/" + changeLog.getId() + "/" + changeLog.getFileName();
		}

		return "";
	}

	public static String getBuildNumber(){
		return buildNumber;
	}
	
	public static String getReleaseNumber() {
		return releaseNumber;
	}
}
