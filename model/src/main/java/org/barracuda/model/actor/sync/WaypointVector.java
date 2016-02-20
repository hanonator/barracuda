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
	 * Processes the next waypoint
	 * 
	 * @return
	 */
	LocationMetaData update();
	
	/**
	 * Adds a new waypoint to the vector
	 * 
	 * @param waypoint
	 * @return
	 */
	WaypointVector add(int x, int y);
	
	/**
	 * Adds a new waypoint to the vector
	 * 
	 * @param waypoint
	 * @return
	 */
	default WaypointVector add(Location location) {
		return this.add(location.getX(), location.getY());
	}
	
	/**
	 * Clears the vector so the entity has no more waypoints
	 */
	WaypointVector clear();
	
	/**
	 * dunno, was in hyperion
	 */
	WaypointVector finish();

}
