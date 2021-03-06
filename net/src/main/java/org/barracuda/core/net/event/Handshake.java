package org.barracuda.core.net.event;

/**
 * The handshake
 * 
 * @author brock
 */
public class Handshake {
	
	/**
	 * The data of the very first byte.
	 * FIXME: unknown value
	 */
	private final int data;
	
	/**
	 * Opcode of the requested packet
	 */
	private final RequestType requestType;

	/**
	 * Constructor
	 * 
	 * @param data
	 * @param requestType
	 */
	public Handshake(int data, RequestType requestType) {
		this.data = data;
		this.requestType = requestType;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @return the requestType
	 */
	public RequestType getRequestType() {
		return requestType;
	}

	/**
	 * 
	 * @author brock
	 *
	 */
	public static enum RequestType {
		
		/**
		 * Indicates the player wants to authenticate onto the server
		 */
		AUTHENTICATION,
		
		/**
		 * Indicates the player wants to update his client through the JAGGRAB protocol
		 */
		CLIENT_UPDATE;
	}
	
}
