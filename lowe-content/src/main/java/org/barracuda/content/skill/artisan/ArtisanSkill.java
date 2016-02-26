package org.barracuda.content.skill.artisan;

import java.util.Arrays;

import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.action.RepetitionPredicate;
import org.barracuda.content.skill.AbstractTrainingMethod;
import org.barracuda.content.skill.RequirementNotMetException;
import org.barracuda.core.game.contract.TextMessage;
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
	 * Starts producing items
	 * 
	 * @param definition
	 * @param index
	 * @param amount
	 */
	public ActionPromise craft(ProductDefinition definition, int index, int amount) {
		final Product product = definition.getProduct(index);
		return queue.queue(container -> {
			if (product != null && validateRequirements(definition, product)) {
				player.getInventory().remove(definition.getResources()); // TODO: Leave the undepletable items in the inventory
				player.getInventory().add(product.getId());
				player.getStats().addExperience(definition.getSkill(), product.getExperience());
			}
		}, new RepetitionPredicate(amount))
				.submit(container -> player.playAnimation(definition.getAnimation()))
				.error((container, exception) -> channel.write(new TextMessage(exception.getMessage())));
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
