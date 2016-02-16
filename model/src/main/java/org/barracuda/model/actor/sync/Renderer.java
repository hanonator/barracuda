package org.barracuda.model.actor.sync;

import org.barracuda.model.actor.sync.attribute.Attribute;

import io.netty.buffer.ByteBuf;

public interface Renderer<T extends Attribute> {


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
	void serialize(T attribute, ByteBuf buffer);

}
