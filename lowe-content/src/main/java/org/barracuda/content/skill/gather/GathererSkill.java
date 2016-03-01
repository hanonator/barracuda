package org.barracuda.content.skill.gather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.RequirementNotMetException;
import org.barracuda.content.skill.gather.node.Node;
import org.barracuda.content.skill.gather.node.NodeController;
import org.barracuda.core.game.contract.TextMessage;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.Entity;
import org.barracuda.model.actor.Player;
import org.barracuda.roald.Clock;
import org.barracuda.roald.util.Timer;

public abstract class GathererSkill<T extends Entity> {

	/**
	 * The random number generator
	 */
	private static final Random random = new Random();
	
	/**
	 * The action queue
	 */
	@Inject private ActionQueue queue;
	
	/**
	 * The player
	 */
	@Inject private Player player;
	
	/**
	 * The player
	 */
	@Inject private Channel channel;
	
	/**
	 * The player
	 */
	@Inject private Clock clock;
	
	/**
	 * The player
	 */
	@Inject private NodeController nodes;
	
	/**
	 * The action performed when the 
	 * @return
	 */
	abstract Consumer<Node<T>> getDepletedAction();
	
	/**
	 * Validates the entity the player is attempting to gather from
	 * 
	 * @param entity
	 * @return
	 */
	abstract boolean validate(T entity);

	/**
	 * Allows the user to gather resources from a selected entity
	 * 
	 * @param object
	 * @return
	 */
	public ActionPromise gather(T entity, ResourceDefinition definition) {
		final Timer timer = new Timer(5, clock);
		return queue.submit(1, ActionQueue.MAXIMUM_REPETITION, container -> {
			if (random.nextDouble() <= nextChance(player, definition, null)) {
				Resource item = randomItem(definition);
				player.getInventory().add(item.getResource());
				player.getStats().addExperience(definition.getSkill(), item.getExperience());
				if (random.nextInt(definition.getCount()) == 0) {
//					nodes.get(entity, definition).deplete();
				}
			}
		}).submit(container -> {
			if (timer.finished() && validate(entity) && checkRequirements(definition)) {
				player.playAnimation(definition.getTool().getBestAvailableTool(player).getAnimation());
				timer.rewind();
			}
		}).fail(exception -> channel.write(new TextMessage(exception.getMessage())));
	}

	/**
	 * Checks to see if the player is eligible to harvest the resources
	 * 
	 * @param definition
	 * @return
	 */
	protected boolean checkRequirements(ResourceDefinition definition) {
		if (definition.getTool().getBestAvailableTool(player) == null) {
			throw new RequirementNotMetException("tool_unavailable");
		}
		else if (!Arrays.stream(definition.getResources()).anyMatch(resource -> resource.getLevel() > player.getStats().getLevel(definition.getSkill()))) {
			throw new RequirementNotMetException("level_too_low");
		}
		return true;
	}
	
	/**
	 * Calculates the frequency at which the player gathers materials
	 * 
	 * @param player
	 * @param definition
	 * @return
	 */
	public double nextChance(Player player, ResourceDefinition definition, Tool tool) {
		return 0.1;
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	private Resource randomItem(ResourceDefinition definition) {
		List<Resource> resources = new ArrayList<>();
		for (Resource resource : definition.getResources()) {
			if (resource.getLevel() <= player.getStats().getLevel(definition.getSkill())) {
				resources.add(resource);
			}
		}
		Resource[] out = resources.toArray(new Resource[0]);
		return out[random.nextInt(out.length)];
	}

}
