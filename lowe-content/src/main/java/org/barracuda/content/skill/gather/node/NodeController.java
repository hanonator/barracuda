package org.barracuda.content.skill.gather.node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.barracuda.content.skill.gather.ResourceDefinition;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.horvik.util.ReflectionUtil;
import org.barracuda.model.Entity;
import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

@Discoverable
@ApplicationScoped
public class NodeController implements ClockWorker {

	/**
	 * The collection of nodes
	 */
	private final Set<Node<?>> nodes = new HashSet<>();
	
	/**
	 * The clock
	 */
	@Inject
	private Clock clock;

	@Override
	public void execute(Clock clock) {
		for (Iterator<Node<?>> iterator = nodes.iterator(); iterator.hasNext(); ) {
			Node<?> node = iterator.next();
			
			if (node != null && node.getTimer() != null && node.getTimer().finished()) {
				node.replenish();
				iterator.remove();
			}
		}
	}

	/**
	 * Schedule this worker
	 * 
	 * @param event
	 */
	public void initialize(@Observes ContainerInitialized event) {
		clock.schedule(this);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public <T extends Entity> Node<T> get(T entity, ResourceDefinition definition) {
		if (nodes.stream().anyMatch(node -> node.getEntity() == entity)) {
			return ReflectionUtil.cast(nodes.stream().filter(node -> node.getEntity() == entity).findFirst().get());
		}
		return null; // register(new Node<T>(entity, definition));
	}

	/**
	 * Registers a node to the collection
	 * 
	 * @param node
	 * @return
	 */
	private <T extends Entity> Node<T> register(Node<T> node) {
		nodes.add(node);
		return node;
	}

}
