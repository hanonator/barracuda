package org.barracuda.content.skill.gather.node;

import java.util.function.Consumer;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.model.Entity;
import org.barracuda.roald.util.Timer;

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
public class Node<T extends Entity> {
	
	/**
	 * The entity
	 */
	private final T entity;
	
	/**
	 * Consumer that get gets called when the node replenishes
	 */
	private final Consumer<Node<T>> replenishConsumer;
	
	/**
	 * The definition
	 */
	private final ResourceDefinition definition;
	
	/**
	 * The node's respawn timer
	 */
	private Timer timer;

	/**
	 * Constructor
	 * 
	 * @param entity
	 * @param consumer
	 * @param definition
	 */
	public Node(T entity, ResourceDefinition definition, Consumer<Node<T>> replenishConsumer) {
		this.entity = entity;
		this.definition = definition;
		this.replenishConsumer = replenishConsumer;
	}
	
	/**
	 * 
	 */
	public void replenish() {
		replenishConsumer.accept(this);
	}

	/**
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * @return the definition
	 */
	public ResourceDefinition getDefinition() {
		return definition;
	}

	/**
	 * @return the timer
	 */
	Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	void setTimer(Timer timer) {
		this.timer = timer;
	}

}
