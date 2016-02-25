package org.barracuda.model.location;

/**
 * TODO
 * 
 * @author brock
 *
 */
public class Region {
	
	/**
	 * The region coordinates
	 */
	private final int x, y;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Region(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param location
	 */
	public Region(Location location) {
		this (location.getX() >> 3, location.getY() >> 3);
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public Location getSmallCoordinate(Location location) {
		return new Coordinate(location.getX() - (x << 3), location.getY() - (y << 3));
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

}
