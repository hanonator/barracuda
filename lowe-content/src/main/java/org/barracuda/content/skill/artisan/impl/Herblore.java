package org.barracuda.content.skill.artisan.impl;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.core.game.event.ItemInteractionEvent;
import org.barracuda.core.game.event.ItemOnItemInteractionEvent;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.item.Inventory;

@Discoverable
@ApplicationScoped
public class Herblore extends ArtisanSkill {

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemOnItemInteractionEvent event) {
		if (event.getInterfaceId() == Inventory.INTERFACE && definition(combine(event.getPrimaryId(), event.getSecondaryId())) != null) {
			craft(definition(combine(event.getPrimaryId(), event.getSecondaryId())));
		}
	}

	/**
	 * Called when the player clicks an item
	 * 
	 * @param event
	 */
	public void on_interact(@Observes ItemInteractionEvent event) {
		if (event.getInterfaceId() == Inventory.INTERFACE && definition(click(event.getId(), event.getOption())) != null) {
			craft(definition(click(event.getId(), event.getOption())));
		}
	}

}
