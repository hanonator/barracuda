package org.barracuda.content.skill.artisan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.AbstractTrainingMethod;
import org.barracuda.content.skill.RequirementNotMetException;
import org.barracuda.content.skill.artisan.view.ChatboxCraftInterface;
import org.barracuda.content.skill.artisan.view.CraftInterface;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.item.Item;

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
	 * Contains the items that are not consumed once used. These are things
	 * like a knife, pestle and mortar, ...
	 */
	private static final Set<Integer> non_consumables = new HashSet<>(Arrays.asList(new Integer[] {
			946, 233, 1733, 
	}));

	/**
	 * The collection of product definitions
	 */
	private final Map<Long, ProductDefinition> definitions = new HashMap<>();

	/**
	 * The player
	 */
	@Inject
	private Player player;

	/**
	 * The player
	 */
	@Inject
	private Channel channel;

	/**
	 * The action queue
	 */
	@Inject
	private ActionQueue actionQueue;

	/**
	 * 
	 * @param definition
	 */
	public void craft(ProductDefinition definition) {
		channel.write(openInterface(definition).listener((index, amount) -> actionQueue.queue(container -> {
				if (validate_requirements(definition.getProduct(index), definition)) {
					Product product = definition.getProduct(index);
					
					/*
					 * Replace the unidentified herb with the identified one
					 */
					Arrays.stream(definition.getResources()).filter(resource -> !non_consumables.contains(resource))
							.forEach(resource -> player.getInventory().remove(new Item(resource, 1))); 
					
					/*
					 * Add the produced item to the player's inventory and update it
					 */
					player.getInventory().add(new Item(product.getId(), product.getAmount()));

					/*
					 * Add the experience
					 */
					player.getStats().addExperience(definition.getSkill(), product.getExperience());
				}
			})
		));
	}

	/**
	 * Checks to see if the player meets all of the requirements for the given 
	 * 
	 * @param player
	 * @param product
	 * @return
	 */
	protected boolean validate_requirements(Product product, ProductDefinition definition) {
		if (!Arrays.stream(definition.getResources()).allMatch(resource -> player.getInventory().contains(resource))) {
			throw new RequirementNotMetException("message_no_resources");
		}
		else if (player.getStats().get(definition.getSkill()).getLevel() < product.getLevel()) {
			throw new RequirementNotMetException("message_insufficient_level");
		}
		return true;
	}

	/**
	 * Checks to see if the player is eligible to craft at least one of the products possible
	 * 
	 * @param player
	 * @param product
	 * @return
	 */
	protected boolean validate_requirements(ProductDefinition definition) {
		if (!Arrays.stream(definition.getProducts()).anyMatch(product -> player.getStats().get(definition.getSkill()).getLevel() > product.getLevel())) {
			throw new RequirementNotMetException("message_insufficient_level");
		}
		return true;
	}
	
	/**
	 * Opens the crafting interface. By default this will be a standard
	 * chatbox interface. This is in a different method so it can be 
	 * overridden in order to deal with different crafting interfaces
	 * such as the smithing and smelting interfaces.
	 * 
	 * @param definition
	 */
	protected CraftInterface openInterface(ProductDefinition definition) {
		return new ChatboxCraftInterface(definition);
	}

	/**
	 * Generates the definition hash for when the player clicks the item
	 * 
	 * @param id
	 * @return
	 */
	protected long click(int id, int option) {
		return ((long) option << 32) | id;
	}

	/**
	 * Generates the definition hash for when the player combines the item
	 * with another
	 * 
	 * @param id
	 * @return
	 */
	protected long combine(int primary_id, int secondary_id) {
		int min = Math.min(primary_id, secondary_id);
		int max = Math.max(primary_id, secondary_id);
		return ((long) max << 32) | min;
	}
	
	/**
	 * Gets a definition
	 * @param hash
	 * @return
	 */
	protected ProductDefinition definition(long hash) {
		return definitions.get(hash);
	}

}
