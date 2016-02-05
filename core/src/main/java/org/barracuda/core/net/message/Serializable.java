package org.barracuda.core.net.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

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
	ByteBuf serialize(ByteBufAllocator allocator);

}
