package org.barracuda.core.net.message.game;

import java.nio.ByteBuffer;

import org.barracuda.core.net.message.Header;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.Payload;

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
	public ByteBuffer serialize() {
		ByteBuffer header_buffer = (ByteBuffer) header.serialize().flip();
		ByteBuffer payload_buffer = (ByteBuffer) payload.getBuffer().flip();
		
		return ByteBuffer.allocate(1 + header_buffer.capacity() + payload_buffer.capacity()).put(header_buffer).put(payload_buffer);
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
