package org.barracuda.content.skill.artisan;

import java.util.Arrays;

import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.AbstractTrainingMethod;
import org.barracuda.content.skill.RequirementNotMetException;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;

/**
 * An artisan skill is a skill that modifies resources into another.
 * 
 * Herblore, crafting, fletching, smithing, etc. are all artisan skills. The
 * artisan skills will all utilize the crafting interface unless they strictly
 * need not use it because of balance issues.
 * 
 * @author brock
 *
 */
public abstract class ArtisanSkill extends AbstractTrainingMethod {
	
	/**
	 * Time in between game ticks
	 */
	private static final int CRAFTING_DELAY = 3;
	
	/**
	 * The action queue responsible for queuing all of the player's
	 * actions
	 */
	@Inject
	private ActionQueue queue;
	
	/**
	 * The player performing the skill
	 */
	@Inject
	private Player player;
	
	/**
	 * The channel everything will be written to
	 */
	@Inject
	private Channel channel;

	/**
	 * Starts producing items
	 * 
	 * @param definition
	 * @param index
	 * @param amount
	 */
	public ActionPromise craft(ProductDefinition definition, int index, int amount) {
		return queue.submit(CRAFTING_DELAY, amount, container -> {
			
		});
	}
	
	/**
	 * Checks to see if the player meets the requirements to create the given item
	 * 
	 * @param definition
	 * @param product
	 * @return
	 */
	protected boolean validateRequirements(ProductDefinition definition, Product product) {
		if (!Arrays.stream(definition.getResources()).allMatch(resource -> player.getInventory().contains(resource))) {
			throw new RequirementNotMetException("message_no_resources");
		}
		else if (player.getStats().get(definition.getSkill()).getLevel() < product.getLevel()) {
			throw new RequirementNotMetException("message_insufficient_level");
		}
		return true;
	}

}
