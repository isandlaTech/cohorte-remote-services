/**
 * Copyright 2014 isandlaTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cohorte.ecf.provider.jabsorb;

import org.eclipse.ecf.core.util.Trace;

/**
 * Utility methods for host and client containers
 * 
 * @author Thomas Calmant
 */
public class Utilities {

	/**
	 * Logs (and traces) a message
	 * 
	 * @param aLevel
	 *            Log level
	 * @param aMethodName
	 *            Calling method name
	 * @param aClass
	 *            Class of the calling method
	 * @param aMessage
	 *            Message to log
	 */
	public static void log(final int aLevel, final String aMethodName, final Class<?> aClass, final String aMessage) {

		log(aLevel, aMessage, aClass, aMessage, null);
	}

	/**
	 * Logs (and traces) a message and its error
	 * 
	 * @param aLevel
	 *            Log level
	 * @param aMethodName
	 *            Calling method name
	 * @param aClass
	 *            Class of the calling method
	 * @param aMessage
	 *            Message to log
	 * @param aThrowable
	 *            An exception
	 */
	public static void log(final int aLevel, final String aMethodName, final Class<?> aClass, final String aMessage,
			final Throwable aThrowable) {

		// Trace the message
		if (aThrowable != null) {
			traceDebug(aMethodName, aClass, aMessage + ": " + aThrowable);

		} else {
			traceDebug(aMethodName, aClass, aMessage);
		}

		// Forge a log message
		final StringBuilder logMessage = new StringBuilder();
		if (aClass != null) {
			// Set the class name
			logMessage.append(aClass.getName()).append(".");
		}

		// Set the method name and the message
		logMessage.append(aMethodName).append("(): ").append(aMessage);

		// Log it
		Activator.get().log(aLevel, aMessage, aThrowable);
	}

	/**
	 * Traces a message using the ECF tracing API
	 * 
	 * @param aMethodName
	 *            Tracing method
	 * @param aDebugOption
	 *            Eclipse tracing flag name (e.g. ecf.jabsorb/debug)
	 * @param aClass
	 *            Class of the tracing method
	 * @param aMessage
	 *            Message to trace
	 */
	public static void trace(final String aMethodName, final String aDebugOption, final Class<?> aClass,
			final String aMessage) {

		Trace.trace(Activator.PLUGIN_ID, aDebugOption, aClass, aMethodName, aMessage);
	}

	/**
	 * Traces a message using the ECF tracing API
	 * 
	 * @param aMethodName
	 *            Tracing method
	 * @param aClass
	 *            Class of the tracing method
	 * @param aMessage
	 *            Message to trace
	 */
	public static void traceDebug(final String aMethodName, final Class<?> aClass, final String aMessage) {

		Trace.trace(Activator.PLUGIN_ID, "debug", aClass, aMethodName, aMessage);
	}

	/**
	 * Hidden constructor
	 */
	private Utilities() {

	}
}
