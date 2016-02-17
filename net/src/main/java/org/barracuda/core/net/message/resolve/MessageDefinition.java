package org.barracuda.core.net.message.resolve;

import org.barracuda.core.net.message.game.GameHeader.MetaData;

/**
 * 
 * @author brock
 *
 */
public class MessageDefinition {
	
	/**
	 * The opcode of the message
	 */
	private final int opcode;
	
	/**
	 * The length of the message
	 */
	private final int length;
	
	/**
	 * The class that will decode the message
	 */
	private final MessageDecoder decoder;
	
	/**
	 * The packet's meta data
	 */
	private final MetaData meta;

	/**
	 * The constructor
	 * 
	 * @param opcode
	 * @param length
	 * @param decoder
	 */
	public MessageDefinition(int opcode, int length, MetaData meta, MessageDecoder decoder) {
		this.opcode = opcode;
		this.length = length;
		this.meta = meta;
		this.decoder = decoder;
	}

	public int getOpcode() {
		return opcode;
	}

	public MessageDecoder getDecoder() {
		return decoder;
	}

	public int getLength() {
		return length;
	}

	public MetaData getMeta() {
		return meta;
	}
	
}