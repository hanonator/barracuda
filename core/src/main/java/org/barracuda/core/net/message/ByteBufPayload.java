package org.barracuda.core.net.message;

import io.netty.buffer.ByteBuf;

/**
 * Simple Payload implementation
 * 
 * @author brock
 *
 */
public class ByteBufPayload implements Payload {

	/**
	 * The buffer
	 */
	private final ByteBuf buffer;

	/**
	 * Constructor
	 * 
	 * @param buffer
	 */
	public ByteBufPayload(ByteBuf buffer) {
		this.buffer = buffer;
	}

	@Override
	public ByteBuf getBuffer() {
		return buffer;
	}

}
