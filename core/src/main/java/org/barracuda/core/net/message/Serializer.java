package org.barracuda.core.net.message;

import io.netty.buffer.ByteBufAllocator;

public interface Serializer<T> {

	/**
	 * Serializes the input into a message
	 * 
	 * @param input
	 * @param session
	 * @return
	 */
	Message serialize(T input, ByteBufAllocator allocator);

}
