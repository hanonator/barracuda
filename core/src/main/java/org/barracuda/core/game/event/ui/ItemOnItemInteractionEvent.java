package org.barracuda.core.game.event.ui;

public class ItemOnItemInteractionEvent extends InterfaceEvent {

	/**
	 * The id of the item being used
	 */
	private int primaryId;
	
	/**
	 * The id of the item the primary item is used on
	 */
	private int secondaryId;
	
	/**
	 * The slot of the item being used
	 */
	private int primarySlot;
	
	/**
	 * The slot of the item the primary item is used on
	 */
	private int secondarySlot;

	/**
	 * @return the primaryId
	 */
	public int getPrimaryId() {
		return primaryId;
	}

	/**
	 * @param primaryId the primaryId to set
	 */
	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	/**
	 * @return the secondaryId
	 */
	public int getSecondaryId() {
		return secondaryId;
	}

	/**
	 * @param secondaryId the secondaryId to set
	 */
	public void setSecondaryId(int secondaryId) {
		this.secondaryId = secondaryId;
	}

	/**
	 * @return the primarySlot
	 */
	public int getPrimarySlot() {
		return primarySlot;
	}

	/**
	 * @param primarySlot the primarySlot to set
	 */
	public void setPrimarySlot(int primarySlot) {
		this.primarySlot = primarySlot;
	}

	/**
	 * @return the secondarySlot
	 */
	public int getSecondarySlot() {
		return secondarySlot;
	}

	/**
	 * @param secondarySlot the secondarySlot to set
	 */
	public void setSecondarySlot(int secondarySlot) {
		this.secondarySlot = secondarySlot;
	}

}
