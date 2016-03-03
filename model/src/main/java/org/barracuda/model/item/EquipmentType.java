package org.barracuda.model.item;

/**
 * Type of equipment
 * 
 * @author brock
 *
 */
public enum EquipmentType {
	
	/**
	 * Items that aren't able to be wielded
	 */
	NONE(-1),
	
	/**
	 * Items going into the shield slot
	 */
	SHIELD(5),
	
	/**
	 * Items going into the feet slot
	 */
	BOOTS(10),
	
	/**
	 * Items going into the ring/jewelry slot
	 */
	RING(12),
	
	/**
	 * Items going into the legs slot
	 */
	LEGS(7),
	
	/**
	 * Items going into the hands slot
	 */
	GLOVES(9),
	
	/**
	 * Items going into the head slot
	 */
	HAT(0),
	
	/**
	 * Items going into the weapon slot
	 */
	WEAPON(3),
	
	/**
	 * Items going into the back slot
	 */
	CAPE(1),
	
	/**
	 * Items going into the neck slot
	 */
	AMULET(2),
	
	/**
	 * Items going into the body slot
	 */
	BODY(4),
	
	/**
	 * Items going into the ammo slot
	 */
	ARROWS(13);
	
	
	
	/**
	 * 
	 */
	private final int slot;

	/**
	 * Constructor
	 * 
	 * @param slot
	 */
	private EquipmentType(int slot) {
		this.slot = slot;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

}