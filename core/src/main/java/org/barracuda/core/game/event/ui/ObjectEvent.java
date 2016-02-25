package org.barracuda.core.game.event.ui;

class ObjectEvent {

	/**
	 * The id of the object interacted with
	 */
	private int objectId;
	
	/**
	 * The object's x coordinate
	 */
	private int x;
	
	/**
	 * The object's y coordinate
	 */
	private int y;

	/**
	 * @return the objectId
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(int objectId) {
		this.objectId = objectId;
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

}
