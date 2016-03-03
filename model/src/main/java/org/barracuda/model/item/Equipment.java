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
	public void equip(int slot) {
		try {
			Item equipment = player.getInventory().get(slot);
			Item current = super.get(equipment.getDefinition().getEquipmentType().getSlot());
			EquipmentType target = equipment.getDefinition().getEquipmentType();
	
			/*
			 * If the player attempts to wield a 2 handed weapon and is already holding a shield
			 * it needs to be unequiped
			 */
			if (equipment.getDefinition().isTwoHanded() && !unequip(EquipmentType.SHIELD.getSlot())) {
				return;
			}
			
			/*
			 * If the player attempts to wield a shield and is already holding a two handed
			 * weapon, it needs to be unequiped
			 */
			else if (target == EquipmentType.SHIELD && !unequip(EquipmentType.WEAPON.getSlot())) {
				return;
			}
			
			/*
			 * If the player attempts to wield a stackable item (usually ammunition) it needs to
			 * be added to the current equipped stack rather than replace it. Only when the items
			 * trying to be wielded is the same as the one currently equipped
			 */
			else if (current != null && current.getId() == equipment.getId() && equipment.getDefinition().isStackable()) {
				player.getInventory().remove(equipment, slot);
				add(equipment, target.getSlot());
				return;
			}
	
			if (current != null) {
				player.getInventory().remove(equipment, slot);
				player.getInventory().add(current, slot);
				remove(current, equipment.getDefinition().getEquipmentType().getSlot());
				add(equipment, equipment.getDefinition().getEquipmentType().getSlot());
			}
		} finally {
			player.notify(new EquipmentUpdated(this));
		}
	}
	
	/**
	 * 
	 * @param slot
		 * 
	 */
	public boolean unequip(int slot) {
		try {
			if (player.getInventory().remaining() == 0 || super.get(slot) == null) {
				System.out.println("no room");
				return false;
			}
			player.getInventory().add(get(slot));
			remove(get(slot), slot);
		} finally {
			player.notify(new EquipmentUpdated(this));
		}
		return true;
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
