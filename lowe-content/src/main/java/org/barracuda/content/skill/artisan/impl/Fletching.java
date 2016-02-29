package org.barracuda.content.skill.artisan.impl;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.content.skill.artisan.Product;
import org.barracuda.content.skill.artisan.ProductDefinition;
import org.barracuda.content.skill.artisan.view.CraftInterface;
import org.barracuda.content.skill.artisan.view.GenericCraftInterface;
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.event.RegionLoaded;
import org.barracuda.model.actor.player.Stats;
import org.barracuda.model.item.Item;

/**
 * Everything to do with the general fletching skill should be present in this
 * class. Very specific things that happen in things like quests can be put in
 * that class, but in general, every viable way of training the skills should be
 * centralized here.
 * 
 * Skills should generally be session specific as they heavily rely on events
 * triggered by the player with an unknown timespan during which the injected
 * fields can change and some information may be sent to the wrong player.
 * 
 * @author koga
 *
 */
@Discoverable
@SessionScoped
public class Fletching extends ArtisanSkill {
	
	/**
	 * Gives the player some starting resources
	 * 
	 * @param event
	 */
	public void give_resource(@Observes RegionLoaded event, Player player) {
		player.getInventory().add(new Item(227, 11));
		player.getInventory().add(new Item(249, 11));
	}

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event, Channel channel, Player player) {
		long test_hash = combine(227, 249);
		if (combine(event.getPrimaryItem(), event.getSecondaryItem()) == test_hash) {
			Product product = new Product();
			product.setExperience(100);
			product.setAmount(1);
			product.setId(91);
			product.setLevel(1);
			
			ProductDefinition definition = new ProductDefinition();
			definition.setSkill(Stats.HERBLORE);
			definition.setResources(new int[] {227, 249});
			definition.setAnimation(363);
			definition.setProducts(new Product[] {
				product
			});
			player.attribute(CraftInterface.ATTRIBUTE_NAME, new GenericCraftInterface(definition)
					.listener((def, index, amount) -> super.craft(def, product, amount)).open(channel));
		}
	}

}
