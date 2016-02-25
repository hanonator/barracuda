package org.barracuda.model.actor.event;

import org.barracuda.core.net.message.resolve.Silent;
import org.barracuda.model.location.Region;

/**
 * This event is sent by the server once the player has crossed a region
 * boundary server-side.
 * 
 * This should be used over RegionLoaded as it is called before the player
 * physically enters the next region, except times where the client needs to
 * load specific region sided entities as they will be reset when the
 * RegionLoaded event is sent by the client.
 * 
 * @author koga
 *
 */
@Silent
public class RegionEntered {

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
	public RegionEntered(Region previousRegion, Region currentRegion) {
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
