package org.barracuda.model.fixed;

import org.barracuda.model.location.Coordinate;
import org.barracuda.model.location.Location;

public class RSObject extends FixedEntity {

	/**
	 * The type of object
	 */
	private int type;
	
	/**
	 * The object's orientation
	 */
	private int orientation;

	/**
	 * An rs object
	 * 
	 * @param location
	 */
	public RSObject(Location location) {
		super(location);
	}

	/**
	 * Constructor
	 * 
	 * @param index
	 * @param x
	 * @param y
	 * @param type
	 * @param orientation
	 */
	public RSObject(int index, int x, int y, int type, int orientation) {
		super (new Coordinate(x, type));
		super.setIndex(index);
		this.type = type;
		this.orientation = orientation;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

}
