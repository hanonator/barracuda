package org.barracuda.core.game.event.ui;

public class ItemOnObject extends ObjectEvent {

	/**
	 * The id of the item being used
	 */
	private int item;
	
	/**
	 * The slot of the item that is being used
	 */
	private int slot;

	/**
	 * The interface id
	 */
	private int interfaceId;

	/**
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * @param interfaceId the interfaceId to set
	 */
	public void setInterfaceId(int interfaceId) {
		this.interfaceId = interfaceId;
	}

	/**
	 * @return the item
	 */
	public int getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(int item) {
		this.item = item;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}

}
