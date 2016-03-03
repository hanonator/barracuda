package org.barracuda.model.item;

import org.barracuda.model.actor.Player;

public class Equipment extends Container {

	/**
	 * The capacity of the inventory.
	 */
	public static final int CAPACITY = 28;

	/**
	 * The interface id
	 */
	public static final int INTERFACE = 3214;

	/**
	 * The player for this inventory
	 */
	private final Player player;

	/**
	 * 
	 * @param capacity
	 * @param player
	 */
	public Equipment(Player player) {
		super(CAPACITY);
		this.player = player;
	}

	/**
	 * 
	 * @param item
	 * @param slot
	 */
	public void equip(Item item, int slot) {
		
	}
	
	/**
	 * 
	 * @param slot
	 */
	public void unequip(int slot) {
		
	}

	/**
	 * Indicates the player's inventory has updated
	 * 
	 * @author brock
	 */
	public static class EquipmentUpdated {

		/**
		 * The inventory that has been udpated
		 */
		private final Equipment equipment;

		/**
		 * Constructor
		 * 
		 * @param equipment
		 */
		public EquipmentUpdated(Equipment equipment) {
			this.equipment = equipment;
		}

		/**
		 * @return the inventory
		 */
		public Equipment getEquipment() {
			return equipment;
		}

	}

}
