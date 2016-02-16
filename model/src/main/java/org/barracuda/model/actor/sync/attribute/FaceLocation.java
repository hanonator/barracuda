package org.barracuda.model.actor.sync.attribute;

import org.barracuda.model.location.Location;

public class FaceLocation implements Attribute {

	/**
	 * The location the entity is facing
	 */
	private final Location location;

	/**
	 * Constructor
	 * 
	 * @param location
	 */
	public FaceLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

}
