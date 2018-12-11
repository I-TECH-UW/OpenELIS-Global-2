package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;

/**
 * This class will abstract the ValidationProvider creation. It will read the
 * name of the class file from properties file and create the class
 * 
 * @version 1.0
 * @author diane benz
 * 
 */

public class QueryProviderFactory {

	private static QueryProviderFactory instance; // Instance of this

	// class

	// Logger
	private static Log log = LogFactory.getLog(QueryProviderFactory.class);

	// Properties object that holds validation provider mappings
	private Properties queryProviderClassMap = null;

	/**
	 * Singleton global access for ValidationProviderFactory
	 * 
	 */

	public static QueryProviderFactory getInstance() {
		if (instance == null) {
			synchronized (QueryProviderFactory.class) {
				if (instance == null) {
					instance = new QueryProviderFactory();
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
			throw new LIMSRuntimeException("Unable to create an object for "
					+ className, e, log);
		}
		return object;
	}

	/**
	 * Search for the ValidationProvider implementation class name in the
	 * Validation.properties file for the given ValidationProvider name
	 * 
	 * @param String
	 *            ValidationProvider name e.g "OrganizationLocalAbbreviationValidationProvider"
	 * @return String Full implementation class e.g
	 *         "us.mn.state.health.lims.common.validation.provider"
	 */
	protected String getQueryProviderClassName(	String queryProvidername) throws LIMSRuntimeException {
		if (queryProviderClassMap == null) { // Need to load the property
			// object with the class
			// mappings
			ResourceLocator rl = ResourceLocator.getInstance();
			InputStream propertyStream = null;
			// Now load a java.util.Properties object with the properties
			queryProviderClassMap = new Properties();
			try {
				propertyStream = rl.getNamedResourceAsInputStream(ResourceLocator.AJAX_PROPERTIES);

				queryProviderClassMap.load(propertyStream);
			} catch (IOException e) {
				throw new LIMSRuntimeException(
						"Unable to load validation provider class mappings.",
						e, log);
			} finally {
				if (null != propertyStream) {
					try {
						propertyStream.close();
						propertyStream = null;
					} catch (Exception e) {
					}
				}
			}
		}

		String mapping = queryProviderClassMap.getProperty(queryProvidername);
		if (mapping == null) {
			log.error("getQueryProviderClassName - Unable to find mapping for "
							+ queryProvidername);
			throw new LIMSRuntimeException(
					"getQueryProviderClassName - Unable to find mapping for "
							+ queryProvidername);
		}
		return mapping;
	}


	public BaseQueryProvider getQueryProvider(String name)	throws LIMSRuntimeException {
		
		BaseQueryProvider provider = null;

		String className = getQueryProviderClassName(name);

		provider = (BaseQueryProvider) createObject(className);

		return provider;
	}

}