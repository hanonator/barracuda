package org.barracuda.core.net.message;

import org.barracuda.core.net.message.game.GameHeader;
import org.barracuda.core.net.message.game.GameMessage;
import org.barracuda.util.net.BitChannel;
import org.barracuda.core.net.message.game.GameHeader.MetaData;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class MessageBuilder {

	/**
	 * The message's opcode
	 */
	private int opcode = 0;

	/**
	 * The message's meta data
	 */
	private MetaData meta = MetaData.EMPTY;
	
	/**
	 * The payload
	 */
	private ByteBuf payload;
	
	/**
	 * Constructor
	 * 
	 * @param allocator
	 */
	public MessageBuilder(ByteBufAllocator allocator) {
		this.payload = allocator.buffer();
	}

	/**
	 * @param opcode the opcode to set
	 */
	public MessageBuilder opcode(int opcode) {
		this.opcode = opcode;
		return this;
	}

	/**
	 * @param meta the meta to set
	 */
	public MessageBuilder meta(MetaData meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * @param meta the meta to set
	 */
	public MessageBuilder header(int opcode, MetaData meta) {
		this.meta = meta;
		this.opcode = opcode;
		return this;
	}

	/**
	 * @param value
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeByte(int)
	 */
	public MessageBuilder writeByte(int value) {
		payload.writeByte(value);
		return this;
	}

	/**
	 * @param value
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeShort(int)
	 */
	public MessageBuilder writeShort(int value) {
		payload.writeShort(value);
		return this;
	}

	/**
	 * @param value
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeMedium(int)
	 */
	public MessageBuilder writeMedium(int value) {
		payload.writeMedium(value);
		return this;
	}

	/**
	 * @param value
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeInt(int)
	 */
	public MessageBuilder writeInt(int value) {
		payload.writeInt(value);
		return this;
	}

	/**
	 * @param value
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeLong(long)
	 */
	public MessageBuilder writeLong(long value) {
		payload.writeLong(value);
		return this;
	}

	/**
	 * @param src
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeBytes(io.netty.buffer.ByteBuf)
	 */
	public MessageBuilder writeBytes(ByteBuf src) {
		payload.writeBytes(src);
		return this;
	}

	/**
	 * @param src
	 * @return
	 * @see io.netty.buffer.ByteBuf#writeBytes(io.netty.buffer.ByteBuf)
	 */
	public MessageBuilder writeBytes(BitChannel src) {
		return writeBytes(src.collect());
	}

	/**
	 * parse a message
	 * @return
	 */
	public Message build() {
		return new GameMessage(new GameHeader(opcode, payload.readableBytes(), meta), new ByteBufPayload(payload));
	}

}
