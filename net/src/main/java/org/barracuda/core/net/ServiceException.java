package org.barracuda.core.net;

import java.io.IOException;

/**
 * Exceptions that are thrown when accessing a service
 * @author brock
 *
 */
public class ServiceException extends IOException {

	/**
	 * The serial version uid
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
