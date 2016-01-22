package org.barracuda.core.net.message;

public interface MessageAttributes {

	/**
	 * 
	 * @return
	 */
	Header getHeader();

	/**
	 * 
	 * @return
	 */
	Payload getPayload();

}
