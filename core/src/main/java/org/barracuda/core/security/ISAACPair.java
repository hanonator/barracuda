package org.barracuda.core.security;

import io.netty.util.AttributeKey;

/**
 * 
 * @author brock
 *
 */
public class ISAACPair {
	
	/**
	 * The attribute key used for storing and retrieving the session's ISAAC pair
	 */
	public static final AttributeKey<ISAACPair> ATTRIBUTE_KEY = AttributeKey.valueOf("isaac_pair");

	/**
	 * The cipher for decoding message opcodes
	 */
	private final ISAAC decodingCipher;
	
	/**
	 * The cipher for encoding message opcodes
	 */
	private final ISAAC encodingCipher;

	/**
	 * 
	 * @param server_key
	 * @param client_key
	 */
	public ISAACPair(long server_key, long client_key) {
		this.decodingCipher = new ISAAC(server_key);
		this.encodingCipher = new ISAAC(client_key);
	}

	public ISAAC getDecodingCipher() {
		return decodingCipher;
	}

	public ISAAC getEncodingCipher() {
		return encodingCipher;
	}

}
