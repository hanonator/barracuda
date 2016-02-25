package org.barracuda.core.net.message;

/**
 * 
 * 
 * @author brock
 */
public abstract class AbstractHeader implements Header {

	/**
	 * The opcode of the message
	 */
	private final int opcode;
	
	/**
	 * The length of the message
	 */
	private final int length;

	/**
	 * Constructor
	 * 
	 * @param opcode
	 * @param length
	 * @param meta
	 */
	public AbstractHeader(int opcode, int length) {
		this.opcode = opcode;
		this.length = length;
	}

	@Override
	public int opcode() {
		return opcode;
	}

	@Override
	public int length() {
		return length;
	}

}