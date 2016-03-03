package org.barracuda.content.skill.gather.node;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.model.fixed.RSObject;
import org.barracuda.model.map.WorldMap;

public class RSObjectNode extends Node<RSObject> {

	/**
	 * 
	 * @param entity
	 * @param definition
	 */
	public RSObjectNode(RSObject entity, ResourceDefinition definition) {
		super(entity, definition);
	}

	@Override
	public boolean validate() {
		return WorldMap.get(entity.getLocation()).getObject(entity.getLocation(), entity.getIndex()) != null;
	}

	@Override
	public void deplete() {
		super.setDepleted(true);
	}

	@Override
	public void respawn() {
		super.setDepleted(false);
	}

}
