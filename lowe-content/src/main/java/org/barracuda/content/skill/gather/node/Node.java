package org.barracuda.content.skill.gather.node;

import java.util.function.Consumer;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.model.Entity;

/**
 * Nodes are controllable resource deposits. This can range from a tree to a
 * fishing spot.
 * 
 * Objects that deplete will almost always change id, but npc's that are being
 * used as deposit can do several things such as change id, move location or
 * vanish altogether.
 * 
 * @author koga
 *
 */
public class Node {
	
	/**
	 * The entity
	 */
	private final Entity entity;
	
	/**
	 * The action performed upon depletion
	 */
	private final Consumer<Entity> consumer;
	
	/**
	 * The definition
	 */
	private final ResourceDefinition definition;

	/**
	 * Constructor
	 * 
	 * @param entity
	 * @param consumer
	 * @param definition
	 */
	public Node(Entity entity, Consumer<Entity> consumer, ResourceDefinition definition) {
		this.entity = entity;
		this.consumer = consumer;
		this.definition = definition;
	}

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @return the consumer
	 */
	public Consumer<Entity> getConsumer() {
		return consumer;
	}

	/**
	 * @return the definition
	 */
	public ResourceDefinition getDefinition() {
		return definition;
	}

}
