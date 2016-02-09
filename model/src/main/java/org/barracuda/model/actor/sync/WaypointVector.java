package org.barracuda.model.actor.sync;

import org.barracuda.model.location.Location;

/**
 * Contains all of the waypoints that have been queued for the player
 * 
 * @author brock
 *
 */
public interface WaypointVector {

	/**
	 * 
	 * @return
	 */
	Location next();

}
