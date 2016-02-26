package org.barracuda.model.fixed;

import org.barracuda.model.Entity;
import org.barracuda.model.location.Location;

public class FixedEntity implements Entity {

	/**
	 * The index in this context is not used as an identifier but as the client
	 * id to recognise the type of entity it is
	 */
	private int index;
	
	/**
	 * The entity's location. As it is a fixed entity, this position should not be
	 * able to be changed apart from very few occasions
	 */
	private final Location location;

	/**
	 * Constructor
	 * 
	 * @param location
	 */
	public FixedEntity(Location location) {
		this.location = location;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

}
