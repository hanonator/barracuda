package org.barracuda.content.skill.gather.node;

import java.util.ArrayList;
import java.util.List;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.util.ReflectionUtil;
import org.barracuda.model.fixed.RSObject;

@Discoverable
@ApplicationScoped
public class NodeController {

	/**
	 * Collection of nodes
	 */
	private final List<Node<?>> nodes = new ArrayList<>();

	/**
	 * Attempts to get an existing object node otherwise creates a new and adds it to the list
	 * @param object
	 * @param definition
	 * @return
	 */
	public Node<RSObject> getObjectNode(RSObject object, ResourceDefinition definition) {
		if (nodes.stream().anyMatch(node -> node.getEntity().equals(object))) {
			return ReflectionUtil.cast(nodes.stream().filter(node -> node.getEntity().equals(object)).findFirst().get());
		}
		Node<RSObject> node = new RSObjectNode(object, definition);
		nodes.add(node);
		return node;
	}

}
