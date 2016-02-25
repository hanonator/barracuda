package org.barracuda.core.game.event.ui;

public class ButtonPressed extends InterfaceEvent {

	/**
	 * The id of the button pressed
	 */
	private int id;

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

}
