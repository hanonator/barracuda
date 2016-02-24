package org.barracuda.content.skill.artisan.impl;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.core.game.event.ui.ItemClicked;
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.item.Inventory;

import com.google.gson.Gson;

@Discoverable
@ApplicationScoped
public class Herblore extends ArtisanSkill {

	/**
	 * Called on startup
	 * 
	 * @param event
	 */
	public void initialize(@Observes ContainerInitialized event, Gson gson) {
		super.loadJson(gson, "static/game/herblore/herbs.out.json", ArtisanSkill.CLICK);
		super.loadJson(gson, "static/game/herblore/potions.out.json", ArtisanSkill.COMBINE);
	}

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event) {
		if (event.getInterfaceId() == Inventory.INTERFACE && combine(event.getPrimaryItem(), event.getSecondaryItem()) != null) {
			craft(combine(event.getPrimaryItem(), event.getSecondaryItem()));
		}
	}

	/**
	 * Called when the player clicks an item
	 * 
	 * @param event
	 */
	public void on_interact(@Observes ItemClicked event) {
		if (event.getInterfaceId() == Inventory.INTERFACE && click(event.getId(), event.getOption()) != null) {
			craft(click(event.getId(), event.getOption()));
		}
	}

}
