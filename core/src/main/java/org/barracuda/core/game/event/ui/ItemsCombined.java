package org.barracuda.core.game.event.ui;

public class ItemsCombined extends InterfaceEvent {

	/**
	 * The item being used
	 */
	private int primaryItem;
	
	/**
	 * The item the primary item is used on
	 */
	private int secondaryItem;
	
	/**
	 * The slot of the primary item
	 */
	private int primaryItemSlot;
	
	/**
	 * The slot of the secondary item
	 */
	private int secondaryItemSlot;
	
	/**
	 * Unknown value
	 */
	private int value;

	/**
	 * @return the secondaryItemSlot
	 */
	public int getSecondaryItemSlot() {
		return secondaryItemSlot;
	}

	/**
	 * @param secondaryItemSlot
	 *            the secondaryItemSlot to set
	 */
	public void setSecondaryItemSlot(int secondaryItemSlot) {
		this.secondaryItemSlot = secondaryItemSlot;
	}

	/**
	 * @return the primaryItemSlot
	 */
	public int getPrimaryItemSlot() {
		return primaryItemSlot;
	}

	/**
	 * @param primaryItemSlot
	 *            the primaryItemSlot to set
	 */
	public void setPrimaryItemSlot(int primaryItemSlot) {
		this.primaryItemSlot = primaryItemSlot;
	}

	/**
	 * @return the secondaryItem
	 */
	public int getSecondaryItem() {
		return secondaryItem;
	}

	/**
	 * @param secondaryItem
	 *            the secondaryItem to set
	 */
	public void setSecondaryItem(int secondaryItem) {
		this.secondaryItem = secondaryItem;
	}

	/**
	 * @return the primaryItem
	 */
	public int getPrimaryItem() {
		return primaryItem;
	}

	/**
	 * @param primaryItem
	 *            the primaryItem to set
	 */
	public void setPrimaryItem(int primaryItem) {
		this.primaryItem = primaryItem;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
