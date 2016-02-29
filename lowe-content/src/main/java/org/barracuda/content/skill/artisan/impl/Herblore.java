package org.barracuda.content.skill.artisan.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.content.skill.artisan.ProductDefinition;
import org.barracuda.content.skill.artisan.view.CraftInterface;
import org.barracuda.content.skill.artisan.view.GenericCraftInterface;
import org.barracuda.core.game.event.ui.ItemClicked;
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.event.RegionLoaded;
import org.barracuda.model.item.Item;

import com.google.gson.Gson;

@Discoverable
@ApplicationScoped
public class Herblore extends ArtisanSkill {

	/**
	 * The collection of herblore configurations
	 */
	private static final Map<Long, ProductDefinition> definitions = new HashMap<>();

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
	 * Listens to the startup event and then initializes all the product definitions
	 * 
	 * @param initialized
	 * @param gson
	 */
	public static void initialize(@Observes ContainerInitialized initialized, Gson gson) {
		InputStream stream = ClassLoader.getSystemResourceAsStream("static/game/artisan/potions.out.json");
		ProductDefinition[] temp = gson.fromJson(new InputStreamReader(stream, Charset.forName("UTF-8")), ProductDefinition[].class);
		for (ProductDefinition definition : temp) {
			definitions.put(combine(definition.getResources()[0], definition.getResources()[1]), definition);
		}
		
		stream = ClassLoader.getSystemResourceAsStream("static/game/artisan/herbs.out.json");
		temp = gson.fromJson(new InputStreamReader(stream, Charset.forName("UTF-8")), ProductDefinition[].class);
		for (ProductDefinition definition : temp) {
			definitions.put(click(1, definition.getResources()[0]), definition);
		}
	}

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event, Channel channel, Player player) {
		ProductDefinition definition = definitions.get(combine(event.getPrimaryItem(), event.getSecondaryItem()));
		if (player.getInventory().get(event.getPrimaryItemSlot()).getId() == event.getPrimaryItem() 
				&& player.getInventory().get(event.getSecondaryItemSlot()).getId() == event.getSecondaryItem() && definition != null) {
			player.attribute(CraftInterface.ATTRIBUTE_NAME, new GenericCraftInterface(definition)
					.listener((def, index, amount) -> super.craft(def, definition.getProduct(index), amount)).open(channel));
		}
	}

	/**
	 * Called when the player clicks an item
	 * 
	 * @param event
	 */
	public void on_interact(@Observes ItemClicked event, Channel channel, Player player) {
		ProductDefinition definition = definitions.get(click(1, event.getId()));
		if (player.getInventory().get(event.getSlot()).getId() == event.getId() && definition != null) {
			player.attribute(CraftInterface.ATTRIBUTE_NAME, new GenericCraftInterface(definition)
					.listener((def, index, amount) -> super.craft(def, definition.getProduct(index), amount)).open(channel));
		}
	}

}
