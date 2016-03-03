package org.barracuda.content.skill.gather.node;

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
public abstract class Node<T extends Entity> {
	
	/**
	 * Indicates the resource has been depleted
	 */
	protected boolean depleted;
	
	/**
	 * The entity
	 */
	protected final T entity;
	
	/**
	 * The definition
	 */
	protected final ResourceDefinition definition;

	/**
	 * Constructor
	 * 
	 * @param entity
	 * @param consumer
	 * @param definition
	 */
	public Node(T entity, ResourceDefinition definition) {
		this.entity = entity;
		this.definition = definition;
	}
	
	/**
	 * Attempts to validate the node
	 * 
	 * @return
	 */
	public abstract boolean validate();
	
	/**
	 * Called when the resource depletes
	 */
	public abstract void deplete();
	
	/**
	 * Called when the resource respawns
	 */
	public abstract void respawn();

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
	 * @return the depleted
	 */
	public boolean isDepleted() {
		return depleted;
	}

	/**
	 * @param depleted the depleted to set
	 */
	public void setDepleted(boolean depleted) {
		this.depleted = depleted;
	}

}
