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
package us.mn.state.health.lims.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Category;

/**
 *  @author		Hung Nguyen
 */

public class LogEvent {
	/**
	 * Write to the log file (type error)
	 * @param className the class name
	 * @param methodName the method name
	 * @param errorMessage the error message
	 */
	public static void logError(String className, String methodName, String errorMessage) {
		getLog().error("Class: " + className + ", Method: " + methodName + ", Error: " + errorMessage);
	}

	/**
	 * Write to the log file (type error)
	 * @param className the class name
	 * @param methodName the method name
	 * @param throwable -- exception which will be used to generate the stack trace
	 */
	public static void logErrorStack(String className, String methodName, Throwable throwable) {
		logError(className, methodName, throwable.toString());
		getLog().error("Class: " + className + ", Method: " + methodName , throwable);
	}
	/**
	 * Write to the log file (type debug)
	 * @param className the class name
	 * @param methodName the method name
	 * @param debugMessage the debug message
	 */
	public static void logDebug(String className, String methodName, String debugMessage) {
	    getLog().debug("Class: " + className + ", Method: " + methodName + ", Debug: " + debugMessage);
	}

	/**
	 * Write to the log file (type info)
	 * @param className the class name
	 * @param methodName the method name
	 * @param infoMessage the info message
	 */
	public static void logInfo(String className, String methodName, String infoMessage) {
		getLog().info("Class: " + className + ", Method: " + methodName + ", Info: " + infoMessage);
	}

	/**
	 * Write to the log file (type warning)
	 * @param className the class name
	 * @param methodName the method name
	 * @param warnMessage the warning message
	 */
	public static void logWarn(String className, String methodName, String warnMessage) {
		getLog().warn("Class: " + className + ", Method: " + methodName + ", Warning:" + warnMessage);
	}

	/**
	 * Write to the log file (type fatal)
	 * @param className the class name
	 * @param methodName the method name
	 * @param warnMessage the fatal message
	 */
	public static void logFatal(String className, String methodName, String fatalMessage) {
		getLog().fatal("Class: " + className + ", Method: " + methodName + ", Fatal:" + fatalMessage);
	}

	public static Log getLog(Class className) {
		Log log = LogFactory.getLog(className);
		return log;
	}

    private static Category getLog() {
        return Category.getInstance(LogEvent.class);
    }
}