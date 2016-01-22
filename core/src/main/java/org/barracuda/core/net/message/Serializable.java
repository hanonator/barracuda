package org.barracuda.core.net.message;

import java.nio.ByteBuffer;

/**
 * Represents an abstract message sent to the server
 * 
 * @author brock
 *
 */
public interface Serializable {

	/**
	 * Serializes the byte buffer
	 * 
	 * @return
	 */
	ByteBuffer serialize();

}
