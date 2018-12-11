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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;

/**
 * This class will abstract the ReportsProvider creation. It will read the
 * name of the class file from properties file and create the class
 * 
 * @version 1.0
 * @author diane benz
 * 
 */

public class ReportsProviderFactory {

	private static ReportsProviderFactory instance; // Instance of this

	// class

	// Properties object that holds reports provider mappings
	private Properties reportsProviderClassMap = null;

	/**
	 * Singleton global access for ReportsProviderFactory
	 * 
	 */

	public static ReportsProviderFactory getInstance() {
		if (instance == null) {
			synchronized (ReportsProviderFactory.class) {
				if (instance == null) {
					instance = new ReportsProviderFactory();
				}
			}

		}
		return instance;
	}

	/**
	 * Create an object for the full class name passed in.
	 * 
	 * @param String
	 *            full class name
	 * @return Object Created object
	 */
	protected Object createObject(String className) throws LIMSRuntimeException {
		Object object = null;
		try {
			Class classDefinition = Class.forName(className);
			object = classDefinition.newInstance();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ReportsProviderFactory","createObject()",e.toString());			
			throw new LIMSRuntimeException("Unable to create an object for "
					+ className, e, LogEvent.getLog(ReportsProviderFactory.class));
		}
		return object;
	}

	/**
	 * Search for the ReportsProvider implementation class name in the
	 * Reports.properties file for the given ReportsProvider name
	 * 
	 * @param String
	 *            ReportsProvider name e.g
	 *            "MycologyWorksheetProvider"
	 * @return String Full implementation class e.g
	 *         "us.mn.state.health.lims.common.reports.provider"
	 */
	protected String getReportsProviderClassName(
			String reportsProvidername) throws LIMSRuntimeException {
		if (reportsProviderClassMap == null) { // Need to load the property
			// object with the class
			// mappings
			ResourceLocator rl = ResourceLocator.getInstance();
			InputStream propertyStream = null;
			// Now load a java.util.Properties object with the properties
			reportsProviderClassMap = new Properties();
			try {
				propertyStream = rl
						.getNamedResourceAsInputStream(ResourceLocator.REPORTS_PROPERTIES);

				reportsProviderClassMap.load(propertyStream);
			} catch (IOException e) {
				//bugzilla 2154
				LogEvent.logError("ReportsProviderFactory","getReportsProviderClassName()",e.toString());			
				throw new LIMSRuntimeException(
						"Unable to load reports provider class mappings.",
						e, LogEvent.getLog(ReportsProviderFactory.class));
			} finally {
				if (null != propertyStream) {
					try {
						propertyStream.close();
						propertyStream = null;
					} catch (Exception e) {
						//bugzilla 2154
						LogEvent.logError("ReportsProviderFactory","getReportsProviderClassName()",e.toString());
					}
				}
			}
		}

		String mapping = reportsProviderClassMap
				.getProperty(reportsProvidername);
		if (mapping == null) {
    		//bugzilla 2154    		
    		LogEvent.logError("ReportsProviderFactory","getReportsProviderClassName()",reportsProvidername);
			throw new LIMSRuntimeException(
					"getReportsProviderClassName - Unable to find mapping for "
							+ reportsProvidername);
		}
		return mapping;
	}

	/**
	 * Reports Provider creation method
	 * 
	 * @param name
	 * @return Reports Provider object
	 * 
	 */
	public BaseReportsProvider getReportsProvider(String name)
			throws LIMSRuntimeException {
		BaseReportsProvider provider = null;

		String className = getReportsProviderClassName(name);

		provider = (BaseReportsProvider) createObject(className);

		return provider;
	}

}