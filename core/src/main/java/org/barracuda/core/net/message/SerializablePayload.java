package org.barracuda.core.net.message;

import java.nio.ByteBuffer;

/**
 * A payload that will contain a serializable object
 * 
 * @author brock
 *
 * @param <T>
 */
public class SerializablePayload<T extends Serializable> implements Payload {

	/**
	 * The object that will be serialized to pose as the payload
	 */
	private final T object;

	/**
	 * Constructor
	 * 
	 * @param object
	 */
	public SerializablePayload(T object) {
		this.object = object;
	}

	@Override
	public ByteBuffer get() {
		return object.serialize();
	}

}
