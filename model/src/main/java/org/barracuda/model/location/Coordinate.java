package org.barracuda.model.location;

import org.barracuda.model.Location;

/**
 * 
 * @author brock
 *
 */
public class Coordinate implements Location {

	/**
	 * The euclidean coordinate values
	 */
	private int x, y, z;

	/**
	 * Constructor with default z value of 0
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this (x, y, 0);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Coordinate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Transforms this coordinate to the given coordinate
	 * 
	 * @param other
	 * @return
	 */
	public Location transform(Location other) {
		return this;
	}


	/**
	 * Transforms the x and y values
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public Location transform(int x, int y) {
		return this.transform(x, y, z);
	}

	/**
	 * Transforms the coordinate to a different coordinate
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Location transform(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * A translation with a default z offset of 0
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Location translate(int x, int y) {
		return this.translate(x, y, 0);
	}
	
	/**
	 * Translates the coordinate with the given values
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Location translate(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	

}
