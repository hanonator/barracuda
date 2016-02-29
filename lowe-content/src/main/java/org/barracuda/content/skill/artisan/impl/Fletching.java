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
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Player;

import com.google.gson.Gson;

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
	 * The collection of herblore configurations
	 */
	private static final Map<Long, ProductDefinition> definitions = new HashMap<>();

	/**
	 * Listens to the startup event and then initializes all the product definitions
	 * 
	 * @param initialized
	 * @param gson
	 */
	public static void initialize(@Observes ContainerInitialized initialized, Gson gson) {
		InputStream stream = ClassLoader.getSystemResourceAsStream("static/game/artisan/bows.out.json");
		ProductDefinition[] temp = gson.fromJson(new InputStreamReader(stream, Charset.forName("UTF-8")), ProductDefinition[].class);
		for (ProductDefinition definition : temp) {
			definitions.put(combine(definition.getResources()[0], definition.getResources()[1]), definition);
		}
	}

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event, Channel channel, Player player) {
		ProductDefinition definition = definitions.get(combine(event.getPrimaryItem(), event.getSecondaryItem()));
		if (definitions != null) {
			player.attribute(CraftInterface.ATTRIBUTE_NAME, new GenericCraftInterface(definition)
					.listener((def, index, amount) -> super.craft(def, definition.getProduct(index), amount)).open(channel));
		}
	}

}
