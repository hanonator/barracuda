package org.barracuda.model.actor.event;

import org.barracuda.model.location.Region;

public class RegionUpdatedEvent {

	/**
	 * The previous region
	 */
	private final Region previousRegion;
	
	/**
	 * The current region
	 */
	private final Region currentRegion;

	/**
	 * Constructor
	 * 
	 * @param previousRegion
	 * @param currentRegion
	 */
	public RegionUpdatedEvent(Region previousRegion, Region currentRegion) {
		this.previousRegion = previousRegion;
		this.currentRegion = currentRegion;
	}

	/**
	 * @return the previousRegion
	 */
	public Region getPreviousRegion() {
		return previousRegion;
	}

	/**
	 * @return the currentRegion
	 */
	public Region getCurrentRegion() {
		return currentRegion;
	}

}
