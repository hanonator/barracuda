package org.barracuda.model.actor.sync;

/**
 * The direction a player walks in
 * 
 * @author goku
 */
public enum Direction {

	NORTH_WEST(-1, 1),
	NORTH(0, 1),
	NORTH_EAST(1, 1),
	WEST(-1, 0),
	EAST(1, 0),
	SOUTH_WEST(-1, -1),
	SOUTH(0, -1),
	SOUTH_EAST(1, -1);

	/**
	 * The x direction
	 */
	private final int x;
	
	/**
	 * The y direction
	 */
	private final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}