package org.barracuda.core.net.message.definition;

import org.barracuda.core.net.message.Message;

public enum AttributeType {

	/**
	 * 
	 */
	BYTE(message -> message.getPayload().get().get()),
	
	/**
	 * 
	 */
	SHORT(message -> message.getPayload().get().getShort()),
	
	/**
	 * 
	 */
	MEDIUM(message -> (message.getPayload().get().get() << 16) + message.getPayload().get().getShort()),
	
	/**
	 * 
	 */
	INTEGER(message -> message.getPayload().get().getInt()),
	
	/**
	 * 
	 */
	LONG(message -> message.getPayload().get().getLong()),
	
	/**
	 * 
	 */
	STRING(message -> "hello, this is a string"),
	
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
