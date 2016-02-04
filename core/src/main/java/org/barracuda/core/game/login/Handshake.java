package org.barracuda.core.game.login;

import org.barracuda.core.game.login.HandshakeInterceptor.RequestType;

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

	public Handshake(int data, RequestType requestType) {
		this.data = data;
		this.requestType = requestType;
	}

	public int getData() {
		return data;
	}

	public RequestType getRequestType() {
		return requestType;
	}
	
}
