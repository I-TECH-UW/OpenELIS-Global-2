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
package org.openelisglobal.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Category;
import org.owasp.encoder.Encode;

/**
 * @author Hung Nguyen
 */

public class LogEvent {
    private static final int MAX_STACK_DEPTH = 10;

    /**
     * Write to the log file (type error)
     *
     * @param errorMessage the error message
     * @param throwable    the error to log
     */
    public static void logError(String className, String methodName, String errorMessage) {
        getLog().error(
                "Class: " + className + ", Method: " + methodName + ", Error: " + sanitizeLogMessage(errorMessage));
    }

    /**
     * Write to the log file (type error)
     *
     * @param errorMessage the error message
     * @param throwable    the error to log
     */
    public static void logError(String errorMessage, Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        getLog().error(
                "Class: " + className + ", Method: " + methodName + ", Error: " + sanitizeLogMessage(errorMessage));
    }

    /**
     * Write to the log file (type error)
     *
     * @param throwable the error to log
     */
    public static void logError(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        getLog().error("Class: " + className + ", Method: " + methodName + ", Error: "
                + sanitizeLogMessage(throwable.getMessage()));
    }

    /**
     * Write to the log file (type error)
     *
     * @param throwable -- exception which will be used to generate the stack trace
     */
    public static void logErrorStack(Throwable throwable) {
        StringBuilder stackErrorMessage = new StringBuilder();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (int i = 0; i < MAX_STACK_DEPTH; ++i) {
            stackErrorMessage.append(sanitizeLogMessage(stackTrace[i].toString()));
            stackErrorMessage.append(System.lineSeparator());
        }
        logError(stackErrorMessage.toString(), throwable);
        getLog().error("Class: " + stackTrace[0].getClassName() + ", Method: " + stackTrace[0].getMethodName(),
                throwable);
    }

    public static void logTrace(String className, String methodName, String debugMessage) {
        getLog().trace(
                "Class: " + className + ", Method: " + methodName + ", Trace: " + sanitizeLogMessage(debugMessage));
    }

    /**
     * Write to the log file (type debug)
     *
     * @param className    the class name
     * @param methodName   the method name
     * @param debugMessage the debug message
     */
    public static void logDebug(String className, String methodName, String debugMessage) {
        getLog().debug(
                "Class: " + className + ", Method: " + methodName + ", Debug: " + sanitizeLogMessage(debugMessage));
    }

    /**
     * Write to the log file (type error)
     *
     * @param errorMessage the error message
     * @param throwable    the error to log
     */
    public static void logDebug(String debugMessage, Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        getLog().debug(
                "Class: " + className + ", Method: " + methodName + ", Error: " + sanitizeLogMessage(debugMessage));
    }

    /**
     * Write to the log file (type error)
     *
     * @param throwable the error to log
     */
    public static void logDebug(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        getLog().debug("Class: " + className + ", Method: " + methodName + ", Error: "
                + sanitizeLogMessage(throwable.getMessage()));
    }

    /**
     * Write to the log file (type info)
     *
     * @param className   the class name
     * @param methodName  the method name
     * @param infoMessage the info message
     */
    public static void logInfo(String className, String methodName, String infoMessage) {
        getLog().info("Class: " + className + ", Method: " + methodName + ", Info: " + sanitizeLogMessage(infoMessage));
    }

    /**
     * Write to the log file (type warning)
     *
     * @param className   the class name
     * @param methodName  the method name
     * @param warnMessage the warning message
     */
    public static void logWarn(String className, String methodName, String warnMessage) {
        getLog().warn(
                "Class: " + className + ", Method: " + methodName + ", Warning:" + sanitizeLogMessage(warnMessage));
    }

    /**
     * Write to the log file (type fatal)
     *
     * @param className   the class name
     * @param methodName  the method name
     * @param warnMessage the fatal message
     */
    public static void logFatal(String className, String methodName, String fatalMessage) {
        getLog().fatal(
                "Class: " + className + ", Method: " + methodName + ", Fatal:" + sanitizeLogMessage(fatalMessage));
    }

    public static Log getLog(Class className) {
        Log log = LogFactory.getLog(className);
        return log;
    }

    private static Category getLog() {
        return Category.getInstance(LogEvent.class);
    }

    // for preventing log forging
    private static String sanitizeLogMessage(String logMessage) {
        if (logMessage != null) {
            String sanitizedLogMessage = logMessage.replace('\n', '_').replace('\r', '_').replace('\t', '_');
            return Encode.forHtml(sanitizedLogMessage);
        }
        return null;
    }
}