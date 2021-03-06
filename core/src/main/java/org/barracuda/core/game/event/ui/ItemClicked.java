package org.barracuda.core.game.event.ui;

public class ItemClicked extends InterfaceEvent {

	/**
	 * The id of the item
	 */
	private int id;
	
	/**
	 * The slot of the item
	 */
	private int slot;
	
	/**
	 * The option index
	 */
	private int option;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}

}
