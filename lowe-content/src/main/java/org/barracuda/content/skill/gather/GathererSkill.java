package org.barracuda.content.skill.gather;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.RequirementNotMetException;
import org.barracuda.content.skill.gather.node.Node;
import org.barracuda.core.game.contract.TextMessage;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.Entity;
import org.barracuda.model.actor.Player;
import org.barracuda.roald.Clock;

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
	 * Allows the user to gather resources from a selected entity
	 * 
	 * @param object
	 * @return
	 */
	public ActionPromise gather(Node<T> node, ResourceDefinition definition) {
		return queue.submit(1, ActionQueue.MAXIMUM_REPETITION, container -> {
			if (node.validate() && node.isDepleted() && checkRequirements(definition)) {
				Resource resource = randomItem(definition, player.getStats().getLevel(definition.getSkill()));
				
				/*
				 * Add the player's reward for succesfully gathering the resource
				 */
				player.getInventory().add(resource.getResource());
				player.getStats().addExperience(definition.getSkill(), resource.getExperience());
				
				/*
				 * If the node should deplete after gathering from it, notify it
				 */
				if (random.nextInt(definition.getCount()) == 0) {
					node.deplete();
					container.cancel();
				}
			}
			else {
				container.cancel();
			}
		})
				
		/*
		 * Whenever the action is submitted, see if the player needs to start his animation
		 */
		.submit(container -> {
			if (node.validate() && checkRequirements(definition)) {
				player.playAnimation(definition.getTool().getBestAvailableTool(player).getAnimation());
			}
		})
		
		/*
		 * An exception should only occur when RequirementNotMetException is thrown so by
		 * printing out the message thrown, it will provide the player with sufficient feedback
		 * as to why the action has been canceled
		 */
		.fail(exception -> channel.write(new TextMessage(exception.getMessage())));
	}

	/**
	 * Checks to see if the player is eligible to harvest the resources
	 * 
	 * @param definition
	 * @return
	 */
	protected boolean checkRequirements(ResourceDefinition definition) {
		/*
		 * Check to see if the player has the required tool for gathering from the resource
		 */
		if (definition.getTool().getBestAvailableTool(player) == null) {
			throw new RequirementNotMetException("tool_unavailable");
		}
		
		/*
		 * Check to see if the player has the required level to gather from the resource
		 */
		else if (!Arrays.stream(definition.getResources())
				.anyMatch(resource -> resource.getLevel() > player.getStats().getLevel(definition.getSkill()))) {
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
	 * Gets a random resource from the definition that the player is eligible for
	 * 
	 * @param items
	 * @return
	 */
	private Resource randomItem(ResourceDefinition definition, int level) {
		return random(Arrays.stream(definition.getResources()).filter(resource -> level > resource.getLevel()).collect(Collectors.toList()));
	}
	
	/**
	 * Gets a random element from the collection
	 * 
	 * @param collection
	 * @return
	 */
	private <E> E random(List<E> collection) {
		return collection.get((int) (Math.random() * collection.size()));
	}

}
