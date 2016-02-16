package org.barracuda.model.actor.sync.attribute;

import org.barracuda.model.Entity;

public class FaceEntity implements Attribute {

	/**
	 * The entity being faced
	 */
	private final Entity entity;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public FaceEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

}
