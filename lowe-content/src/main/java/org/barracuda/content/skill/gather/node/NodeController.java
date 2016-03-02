package org.barracuda.content.skill.gather.node;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.model.Entity;

@Discoverable
@ApplicationScoped
public class NodeController {
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public <T extends Entity> Node<T> get(T entity, ResourceDefinition definition) {
		return null;
	}

}
