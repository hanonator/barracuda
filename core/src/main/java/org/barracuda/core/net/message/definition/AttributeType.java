package org.barracuda.core.net.message.definition;

import org.barracuda.core.net.ByteBufferUtil;
import org.barracuda.core.net.message.Message;

public enum AttributeType {

	/**
	 * 
	 */
	BYTE(message -> message.getPayload().getBuffer().readByte()),
	
	/**
	 * 
	 */
	SHORT(message -> message.getPayload().getBuffer().readShort()),
	
	/**
	 * 
	 */
	MEDIUM(message -> (message.getPayload().getBuffer().readByte() << 16) + message.getPayload().getBuffer().readShort()),
	
	/**
	 * 
	 */
	INTEGER(message -> message.getPayload().getBuffer().readInt()),
	
	/**
	 * 
	 */
	LONG(message -> message.getPayload().getBuffer().readLong()),
	
	/**
	 * 
	 */
	STRING(message -> ByteBufferUtil.readString(message.getPayload().getBuffer())),
	
	/**
	 * 
	 */
	OPCODE(message -> message.getHeader().opcode());

	/**
	 * 
	 */
	private final Extractor extractor;

	/**
	 * constructor
	 * 
	 * @param extractor
	 */
	private AttributeType(Extractor extractor) {
		this.extractor = extractor;
	}
	
	/**
	 * Extracts the object from the message
	 * 
	 * @param message
	 * @return
	 */
	public Object extract(Message message) {
		return extractor.extract(message);
	}

	/**
	 * 
	 * @author brock
	 *
	 */
	private static interface Extractor {
		
		/**
		 * Extracts an object from the message
		 * 
		 * @param buffer
		 * @return
		 */
		Object extract(Message message);
	}

}
