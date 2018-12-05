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
package us.mn.state.health.lims.common.exception;

import org.apache.commons.logging.Log;

/**
 * 
 * Base exception used by the LIMS. Unless special handling can be done with an
 * exception, errors should throw this exception. In the UI portion of the
 * application, this base exception can be caught and an error page displayed.
 * 
 * Specialized exceptions should extend this class.
 */
public class LIMSRuntimeException extends RuntimeException {
	private Exception exception; // Holds the detailed exception

	/**
	 * Creates a new LIMSException wrapping another exception including a
	 * detailed message.
	 * 
	 * @param String
	 *            the detailed message
	 * @param Exception
	 *            the wrapped exception
	 */
	public LIMSRuntimeException(String pMessage, Exception pException) {
		super(pMessage);
		this.exception = pException;
	}

	/**
	 * Creates a new LIMSException wrapping another exception including a
	 * detailed message. Takes a Log object, and will log the same message as an
	 * error.
	 * 
	 * @param String
	 *            the detailed message
	 * @param Exception
	 *            the wrapped exception
	 * @param Log
	 *            the Log to write a message to
	 */
	public LIMSRuntimeException(String pMessage, Exception pException, Log pLog) {
		super(pMessage);
		this.exception = pException;
		if (pLog != null) {
			pLog.error(pMessage, pException);
		}
	}

	/**
	 * Creates a new LIMSException with a detailed message
	 * 
	 * @param String
	 *            the detailed message
	 */
	public LIMSRuntimeException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Creates a new LIMSException wrapping another exception
	 * 
	 * @param Exception
	 *            the wrapped exception
	 */
	public LIMSRuntimeException(Exception pException) {
		this(null, pException);
	}

	/**
	 * Retreive the wrapped exception
	 * 
	 * @return Exception the Wrapped exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Retrieves the root cause exception.
	 * 
	 * @return the root cause exception.
	 */
	public Exception getRootCause() {
		if (exception instanceof LIMSRuntimeException) {
			return ((LIMSRuntimeException) exception).getRootCause();
		}
		return exception == null ? this : exception;
	}
}
