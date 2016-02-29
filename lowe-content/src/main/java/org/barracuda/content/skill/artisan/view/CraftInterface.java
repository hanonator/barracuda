package org.barracuda.content.skill.artisan.view;

import java.util.Collection;

import org.barracuda.core.net.Channel;

/**
 * 
 * @author brock
 *
 */
public interface CraftInterface {

	/**
	 * The name of the attribute in the player's attribute store
	 */
	String ATTRIBUTE_NAME = "craft_interface";

	/**
	 * Opens the craft interface
	 * 
	 * @return
	 */
	CraftInterface open(Channel channel);
	
	/**
	 * Adds a listener to the interface
	 * 
	 * @param listener
	 * @return
	 */
	CraftInterface listener(CraftInterfaceListener listener);
	
	/**
	 * Adds an item to the interface
	 * 
	 * @param listener
	 * @return
	 */
	CraftInterface item(int id);
	
	/**
	 * Called when the player has clicked an item on the interface
	 * 
	 * @param index
	 * @param amount
	 * @return
	 */
	void interact(int index, int amount);
	
	/**
	 * Adds a collection of items to the interface
	 * 
	 * @param listener
	 * @return
	 */
	default CraftInterface items(Collection<Integer> ids) {
		ids.forEach(this::item);
		return this;
	}

}
