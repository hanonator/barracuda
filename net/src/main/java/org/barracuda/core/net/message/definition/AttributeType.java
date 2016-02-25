package org.barracuda.core.net.message.definition;

import org.barracuda.core.net.ByteBufferUtil;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;

public enum AttributeType {

	/**
	 * 
	 */
	BYTE(message -> message.getPayload().getBuffer().readByte(),
			(value, buffer) -> buffer.writeByte(Integer.parseInt(value.toString()))),
	
	/**
	 * 
	 */
	SHORT(message -> message.getPayload().getBuffer().readShort(),
			(value, buffer) -> buffer.writeShort(Integer.parseInt(value.toString()))),
	
	/**
	 * 
	 */
	MEDIUM(message -> message.getPayload().getBuffer().readMedium(),
			(value, buffer) -> buffer.writeMedium(Integer.parseInt(value.toString()))),
	
	/**
	 * 
	 */
	INTEGER(message -> message.getPayload().getBuffer().readInt(),
			(value, buffer) -> buffer.writeInt(Integer.parseInt(value.toString()))),
	
	/**
	 * 
	 */
	LONG(message -> message.getPayload().getBuffer().readLong(),
			(value, buffer) -> buffer.writeLong(Long.parseLong(value.toString()))),
	
	/**
	 * 
	 */
	STRING(message -> ByteBufferUtil.readString(message.getPayload().getBuffer()),
			(value, buffer) -> buffer.writeBytes(value.toString().getBytes()).writeByte(10)),
	
	/**
	 * 
	 */
	OPCODE(message -> message.getHeader().opcode(), null);

	/**
	 * 
	 */
	private final Extractor extractor;
	
	/**
	 * 
	 */
	private final Inserter<?> inserter;

	/**
	 * constructor
	 * 
	 * @param extractor
	 */
	private AttributeType(Extractor extractor, Inserter<?> inserter) {
		this.extractor = extractor;
		this.inserter = inserter;
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
	 * @param value
	 * @param buffer
	 */
	@SuppressWarnings("unchecked")
	public void insert(Object value, MessageBuilder buffer) {
		((Inserter<Object>) inserter).insert(value, buffer);
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

	/**
	 * 
	 * @author brock
	 *
	 */
	private static interface Inserter<T> {
		
		/**
		 * Extracts an object from the message
		 * 
		 * @param buffer
		 * @return
		 */
		void insert(T value, MessageBuilder buffer);
	}

}
