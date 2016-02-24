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
	
	/**
	 * 
	 * @author brock
	 *
	 */
	private static class Herb {
		
		/**
		 * The item id of the unidentified herb
		 */
		private int unidentified_id;
		
		/**
		 * The item id of the identified herb
		 */
		private int identified_id;
		
		/**
		 * The experience gained from cleaning the herb
		 */
		private int experience;
		
		/**
		 * The level required to clean the herb
		 */
		private int level;
		
	}
	
	/**
	 * 
	 * @author brock
	 *
	 */
	private static class Potion {
		
		/**
		 * The id of the primary ingredient
		 */
		private int primary_ingredient;
		
		/**
		 * The id of the secondary ingredient
		 */
		private int secondary_ingredient;
		
		/**
		 * The id of the item produced
		 */
		private int produce;
		
		/**
		 * The experience gained from mixing the potion
		 */
		private int experience;
		
		/**
		 * The level required to mix the potion
		 */
		private int level;
		
	}

}
