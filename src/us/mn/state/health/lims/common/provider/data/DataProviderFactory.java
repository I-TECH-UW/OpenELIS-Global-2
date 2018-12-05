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
package us.mn.state.health.lims.common.provider.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;

/**
 * This class will abstract the DataProvider creation. It will read the
 * name of the class file from properties file and create the class
 * 
 * @version 1.0
 * @author diane benz
 * bugzilla 2443
 */

public class DataProviderFactory {

	private static DataProviderFactory instance; // Instance of this

	// class

	// Properties object that holds data provider mappings
	private Properties dataProviderClassMap = null;

	/**
	 * Singleton global access for DataProviderFactory
	 * 
	 */

	public static DataProviderFactory getInstance() {
		if (instance == null) {
			synchronized (DataProviderFactory.class) {
				if (instance == null) {
					instance = new DataProviderFactory();
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
			LogEvent.logError("DataProviderFactory","createObject()",e.toString());
			throw new LIMSRuntimeException("Unable to create an object for "
					+ className, e, LogEvent.getLog(DataProviderFactory.class));
		}
		return object;
	}

	/**
	 * Search for the DataProvider implementation class name in the
	 * Data.properties file for the given DataProvider name
	 * 
	 * @param String
	 *            DataProvider name e.g "NextTestSortOrderDataProvider"
	 * @return String Full implementation class e.g
	 *         "us.mn.state.health.lims.common.data.provider"
	 */
	protected String getDataProviderClassName(
			String dataProvidername) throws LIMSRuntimeException {
		if (dataProviderClassMap == null) { // Need to load the property
			// object with the class
			// mappings
			ResourceLocator rl = ResourceLocator.getInstance();
			InputStream propertyStream = null;
			// Now load a java.util.Properties object with the properties
			dataProviderClassMap = new Properties();
			try {
				propertyStream = rl
						.getNamedResourceAsInputStream(ResourceLocator.AJAX_PROPERTIES);

				dataProviderClassMap.load(propertyStream);
			} catch (IOException e) {
                //bugzilla 2154
			    LogEvent.logError("DataProviderFactory","getDataProviderClassName()",e.toString());
				throw new LIMSRuntimeException(
						"Unable to load data provider class mappings.",
						e, LogEvent.getLog(DataProviderFactory.class));
			} finally {
				if (null != propertyStream) {
					try {
						propertyStream.close();
						propertyStream = null;
					} catch (Exception e) {
                		//bugzilla 2154
			    		LogEvent.logError("DataProviderFactory","getDataProviderClassName()",e.toString());
					}
				}
			}
		}

		String mapping = dataProviderClassMap
				.getProperty(dataProvidername);
		if (mapping == null) {
			//bugzilla 2154
			LogEvent.logError("DataProviderFactory","getDataProviderClassName()",dataProvidername);
			throw new LIMSRuntimeException(
					"getDataProviderClassName - Unable to find mapping for "
							+ dataProvidername);
		}
		return mapping;
	}

	/**
	 * Data Provider creation method
	 * 
	 * @param name
	 * @return Data Provider object
	 * 
	 */
	public BaseDataProvider getDataProvider(String name)
			throws LIMSRuntimeException {
		BaseDataProvider provider = null;

		String className = getDataProviderClassName(name);

		provider = (BaseDataProvider) createObject(className);

		return provider;
	}

}