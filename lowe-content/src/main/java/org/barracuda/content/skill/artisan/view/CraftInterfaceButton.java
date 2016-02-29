package org.barracuda.content.skill.artisan.view;

public class CraftInterfaceButton {

	/**
	 * Id of the button being pressed
	 */
	private int id;
	
	/**
	 * The slot of the item being selected in the craft interface
	 */
	private int index;
	
	/**
	 * The amount the user has selected
	 */
	private int amount;

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
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
