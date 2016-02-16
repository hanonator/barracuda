package org.barracuda.model.actor.sync;

import java.util.Collection;
import java.util.Queue;

import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.sync.attribute.Attribute;

public interface RenderingOrder {

	/**
	 * Puts the attributes in the order expected by the client
	 * 
	 * @param attributes
	 * @return
	 */
	Queue<Attribute> order(Collection<Attribute> attributes);
	
	/**
	 * Gets the type of entities this order is for
	 */
	Class<? extends Actor> getRenderedType();

}
