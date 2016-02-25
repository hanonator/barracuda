package org.barracuda.core.net.message;

import io.netty.buffer.ByteBuf;

/**
 * The payload of the message
 * 
 * @author brock
 *
 */
public interface Payload {

	/**
	 * Gets the buffer behind the payload
	 * 
	 * @return
	 */
	ByteBuf getBuffer();

}
