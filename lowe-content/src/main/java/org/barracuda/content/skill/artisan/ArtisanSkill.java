package org.barracuda.content.skill.artisan;

import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.AbstractTrainingMethod;
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
	 * 
	 * 
	 * @param definition
	 * @param index
	 * @param amount
	 */
	public ActionPromise craft(ProductDefinition definition, int index, int amount) {
		return queue.queue(container -> {
			
		});
	}

	/**
	 * Gets the hash for when the player combines two items
	 * 
	 * @param primaryItem
	 * @param secondaryItem
	 * @return
	 */
	protected long combine(long primaryItem, long secondaryItem) {
		return (Math.max(primaryItem, secondaryItem) << 32) | Math.min(primaryItem, secondaryItem);
	}

	/**
	 * Gets the hash for when the player triggers an item by clicking on them
	 * 
	 * @param item
	 * @param option
	 * @return
	 */
	protected long click(long item, long option) {
		return (option << 32) | item;
	}

}
