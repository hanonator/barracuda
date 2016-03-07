package org.barracuda.core.game.event;

public class ObjectInteractionEvent {

	/**
	 * The x coordinate of the object that has been interacted with
	 */
	private int x;
	
	/**
	 * The y coordinate of the object that has been interacted with
	 */
	private int y;
	
	/**
	 * The id of the object that has been interacted with
	 */
	private int id;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public ObjectInteractionEvent(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

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
