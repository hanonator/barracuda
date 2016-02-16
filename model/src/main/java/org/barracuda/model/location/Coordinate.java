package org.barracuda.model.location;

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
	 * 
	 */
	public int distance(Location other) {
		return (this.x - other.getAbsoluteX()) + (this.y - other.getAbsoluteY());
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

	/**
	 * @return the absoluteX
	 */
	public int getAbsoluteX() {
		return x;
	}

	/**
	 * @param absoluteX the absoluteX to set
	 */
	public void setAbsoluteX(int absoluteX) {
		this.x = absoluteX;
	}

	/**
	 * @return the absoluteY
	 */
	public int getAbsoluteY() {
		return y;
	}

	/**
	 * @param absoluteY the absoluteY to set
	 */
	public void setAbsoluteY(int absoluteY) {
		this.y = absoluteY;
	}

	/**
	 * @return the absoluteZ
	 */
	public int getAbsoluteZ() {
		return z;
	}

	/**
	 * @param absoluteZ the absoluteZ to set
	 */
	public void setAbsoluteZ(int absoluteZ) {
		this.z = absoluteZ;
	}

	@Override
	public int getRelativeX() {
		return x - (getRegion().getAbsoluteX() * 8);
	}

	@Override
	public int getRelativeY() {
		return y - (getRegion().getAbsoluteY() * 8);
	}

	private Location getRegion() {
		return new Coordinate((x >> 3) - 6, (y >> 3) - 6);
	}

}
