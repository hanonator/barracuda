package org.barracuda.core.net;

import io.netty.util.AttributeKey;

/**
 * The current state of the channel
 * 
 * @author brock
 *
 */
public enum ChannelState {
	
	/**
	 * The user has connected, but the client has not been identified and has not authenticated
	 */
	HANDSHAKE,
	
	/**
	 * The user has connected and the client has been identified but has not authenticated yet
	 */
	AUTHENTICATION,
	
	/**
	 * The user has connected, the client has been identified and has been succesfully authenticated
	 */
	GAME,
	
	/**
	 * The user has not been connected
	 */
	DISCONNECTED;
	
	/**
	 * The attribute key for storage in the netty context attributes
	 */
	public static final AttributeKey<ChannelState> ATTRIBUTE_KEY = AttributeKey.newInstance("channel_state");
}
