package org.barracuda.core.net.message.game;

import java.nio.ByteBuffer;

import org.barracuda.core.net.message.AbstractHeader;

/**
 * The Header implementation of for the Game
 * 
 * @author brock
 *
 */
public class GameHeader extends AbstractHeader {
	
	/**
	 * Contains information about the length of the header
	 */
	private final MetaData meta;

	/**
	 * Constructor
	 * 
	 * @param opcode
	 * @param length
	 * @param meta
	 */
	public GameHeader(int opcode, int length, MetaData meta) {
		super(opcode, length);
		
		this.meta = meta;
	}

	/**
	 * Gets the meta data
	 * @return
	 */
	public MetaData meta() {
		return meta;
	}
	
	/**
	 * Serializes the header.
	 * 
	 * A header that has metadata of small will add the length of the packet in
	 * a byte, the header that has a metadata of big will add the length of the
	 * packet in a short
	 */
	@Override
	public ByteBuffer serialize() {
		ByteBuffer buffer = ByteBuffer.allocate(1 + meta().ordinal()).put((byte) opcode());
		switch (meta()) {
		case EMPTY:
			break;
		case SMALL:
			buffer.put((byte) length());
			break;
		case BIG:
			buffer.putShort((short) length());
			break;
		}
		return buffer;
	}
	
	/**
	 * Defines the way the header will bundle the opcode/size. 
	 * 
	 * @author brock
	 *
	 */
	public static enum MetaData {
		
		/**
		 * The header will only contain the opcode
		 */
		EMPTY, 
		
		/**
		 * The header will contain the opcode and the length will be stored as a byte
		 */
		SMALL,
		
		/**
		 * The header will contain the opcode and the length will be stored as a short
		 */
		BIG;
	}

}
