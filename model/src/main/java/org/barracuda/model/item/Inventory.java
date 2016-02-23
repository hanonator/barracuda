package org.barracuda.model.item;

import org.barracuda.model.actor.Player;

public class Inventory extends Container {

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
	public Inventory(Player player) {
		super(CAPACITY);
		this.player = player;
	}

	/**
	 * Notifies the player of an inventory update
	 */
	public void update() {
		player.notify(new InventoryUpdated(this));
	}

	@Override
	public void add(Item item) {
		super.add(item);
		update();
	}

	@Override
	public void remove(Item item) {
		super.remove(item);
		update();
	}

	/**
	 * Indicates the player's inventory has updated
	 * 
	 * @author brock
	 */
	public static class InventoryUpdated {

		/**
		 * The inventory that has been udpated
		 */
		private final Inventory inventory;

		/**
		 * Constructor
		 * 
		 * @param inventory
		 */
		public InventoryUpdated(Inventory inventory) {
			this.inventory = inventory;
		}

		/**
		 * @return the inventory
		 */
		public Inventory getInventory() {
			return inventory;
		}

	}

}