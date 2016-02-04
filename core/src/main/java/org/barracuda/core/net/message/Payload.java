package org.barracuda.core.net.message;

import java.nio.ByteBuffer;

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
	ByteBuffer getBuffer();

}
