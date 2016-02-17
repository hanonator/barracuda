package org.barracuda.model.item;

public class Item {

	/**
	 * The item id
	 */
	private final int id;
	
	/**
	 * The item amount
	 */
	private int amount;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param amount
	 */
	public Item(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}
	
	/**
	 * @return the definition 
	 */
	public ItemDefinition getDefinition() {
		return ItemDefinition.forId(id);
	}

	/**
	 * To string breh
	 */
	public String toString() {
		return String.format("item (%d, %d)", id, amount);
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	
}
