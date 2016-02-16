package org.barracuda.model.location;

/**
 * 
 * 
 * @author brock
 */
public interface Location {
	
	/**
	 * Translates the coordinate with the given values
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	Location translate(int x, int y, int z);
	
	/**
	 * Translates the coordinate with the given x and y but z is set to 0
	 * @param x
	 * @param y
	 */
	default Location translate(int x, int y) {
		return this.translate(x, y, 0);
	}

	/**
	 * Transforms the coordinate to a different coordinate
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	Location transform(int x, int y, int z);


	/**
	 * Transforms the x and y values
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	default Location transform(int x, int y) {
		return this.transform(x, y, 0);
	}

	/**
	 * Gets the manhattan distance between the given location and this location
	 * @param location
	 * @return
	 */
	int distance(Location location);

	/**
	 * The x value of the location
	 * 
	 * @return
	 */
	int getAbsoluteX();
	
	/**
	 * The y value of the location
	 * 
	 * @return
	 */
	int getAbsoluteY();

	/**
	 * The x value of the location
	 * 
	 * @return
	 */
	int getRelativeX();
	
	/**
	 * The y value of the location
	 * 
	 * @return
	 */
	int getRelativeY();

}
