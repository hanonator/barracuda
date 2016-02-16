package org.barracuda.model.actor.sync;

public class Waypoint {

	/**
	 * The x-coordinate.
	 */
	private final int x;

	/**
	 * The y-coordinate.
	 */
	private final int y;

	/**
	 * The direction to walk to this point.
	 */
	private final Direction direction;

	/**
	 * Creates a point.
	 * 
	 * @param x
	 *            X coord.
	 * @param y
	 *            Y coord.
	 * @param dir
	 *            Direction to walk to this point.
	 */
	public Waypoint(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.direction = dir;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

}
