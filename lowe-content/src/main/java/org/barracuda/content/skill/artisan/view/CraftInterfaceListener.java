package org.barracuda.content.skill.artisan.view;

public interface CraftInterfaceListener {

	/**
	 * Called when the player selects an item to craft. The index of the item is
	 * provided and the amount entered
	 * 
	 * @param index
	 * @param amount
	 */
	void craft(int index, int amount);

}
