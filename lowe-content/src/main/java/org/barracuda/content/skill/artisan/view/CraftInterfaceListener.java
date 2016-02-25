package org.barracuda.content.skill.artisan.view;

import org.barracuda.content.skill.artisan.ProductDefinition;

public interface CraftInterfaceListener {

	/**
	 * Called when the player selects an item to craft. The index of the item is
	 * provided and the amount entered
	 * 
	 * @param index
	 * @param amount
	 */
	void craft(ProductDefinition definition, int index, int amount);

}
