package org.barracuda.core.net.message.game;

import org.barracuda.core.net.message.Header;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.Payload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * The implementation of Message for the game
 * 
 * @author brock
 *
 */
public class GameMessage implements Message {

	/**
	 * The header
	 */
	private final Header header;
	
	/**
	 * The payload
	 */
	private final Payload payload;

	/**
	 * Constructor
	 * 
	 * @param header
	 * @param payload
	 */
	public GameMessage(Header header, Payload payload) {
		this.header = header;
		this.payload = payload;
	}

	@Override
	public ByteBuf serialize(ByteBufAllocator allocator) {
		ByteBuf header_buffer = header.serialize(allocator);
		ByteBuf payload_buffer = payload.getBuffer();
		
		return allocator.buffer(1 + header_buffer.capacity() + payload_buffer.capacity()).writeBytes(header_buffer).writeBytes(payload_buffer);
	}

	@Override
	public Header getHeader() {
		return header;
	}

	@Override
	public Payload getPayload() {
		return payload;
	}

}
