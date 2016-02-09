package org.barracuda.model.actor.sync.attribute;

import io.netty.buffer.ByteBuf;

public interface Attribute {

	/**
	 * The identifier in the client
	 * 
	 * @return
	 */
	int getIdentifier();

	/**
	 * Serializes the attribute to the ByteBuf
	 * 
	 * @param buffer
	 */
	void serialize(ByteBuf buffer);

}
