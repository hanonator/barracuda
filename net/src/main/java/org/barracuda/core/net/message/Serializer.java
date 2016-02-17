package org.barracuda.core.net.message;

import java.util.List;

import io.netty.buffer.ByteBufAllocator;

public interface Serializer<T> {

	/**
	 * Serializes the input into a message
	 * 
	 * @param input
	 * @param session
	 * @return
	 */
	void serialize(T input, ByteBufAllocator allocator, List<Message> out);

}
