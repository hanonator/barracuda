package org.barracuda.content.skill.artisan.impl;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.item.Inventory;

@Discoverable
@ApplicationScoped
public class Fletching extends ArtisanSkill {

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event) {
		/*
		 * TODO: Inventory.INTERFACE is currently version dependant (it only works on version < 400)
		 */
		if (event.getInterfaceId() == Inventory.INTERFACE && combine(event.getPrimaryItem(), event.getSecondaryItem()) != null) {
			craft(combine(event.getPrimaryItem(), event.getSecondaryItem()));
		}
	}

}
