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
	 * Calculates the distance between this location and the other
	 */
	public int distance(Location other) {
		return (this.x - other.getX()) + (this.y - other.getY());
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
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

}
