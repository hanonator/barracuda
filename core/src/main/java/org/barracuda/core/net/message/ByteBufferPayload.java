package org.barracuda.core.net.message;

import java.nio.ByteBuffer;

/**
 * Simple Payload implementation
 * 
 * @author brock
 *
 */
public class ByteBufferPayload implements Payload {

	/**
	 * The buffer
	 */
	private final ByteBuffer buffer;

	/**
	 * Constructor
	 * 
	 * @param buffer
	 */
	public ByteBufferPayload(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public ByteBuffer get() {
		return buffer;
	}

}
