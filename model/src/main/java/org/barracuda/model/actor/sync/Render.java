package org.barracuda.model.actor.sync;

import java.util.List;

import org.barracuda.model.actor.sync.attribute.Attribute;

import io.netty.buffer.ByteBuf;

/**
 * The render
 * 
 * @author brock
 *
 */
@Deprecated
public class Render {

	/**
	 * The list of attribute classes present in the render
	 */
	private final List<Class<? extends Attribute>> attributes;

	/**
	 * The rendered entity
	 */
	private final ByteBuf buffer;

	/**
	 * Constructor
	 * 
	 * @param attributes
	 * @param buffer
	 */
	public Render(List<Class<? extends Attribute>> attributes, ByteBuf buffer) {
		this.attributes = attributes;
		this.buffer = buffer;
	}

	/**
	 * @return the attributes
	 */
	public List<Class<? extends Attribute>> getAttributes() {
		return attributes;
	}

	/**
	 * @return the buffer
	 */
	public ByteBuf getBuffer() {
		return buffer;
	}

}
