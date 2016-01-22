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
	 * 
	 * @return
	 */
	ByteBuffer get();

}
