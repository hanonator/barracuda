package org.barracuda.model.actor.sync.attribute;

import java.util.Set;
import java.util.TreeSet;

import io.netty.buffer.ByteBuf;

/**
 * An actor's attributes
 * 
 * @author brock
 *
 */
public class Attributes {

	/**
	 * The collection of attributes
	 */
	private final Set<Attribute> attributes = new TreeSet<>();

	/**
	 * Assembles the attributes in a binary chunk of data that can be
	 * synchronized with the client
	 * 
	 * @param buffer
	 * @return
	 */
	public ByteBuf assemble(ByteBuf buffer) {
		return buffer;
	}

	/**
	 * Adds an attribute to the collection
	 * 
	 * @param attribute
	 * @return
	 */
	public boolean add(Attribute attribute) {
		return attributes.add(attribute);
	}

}
