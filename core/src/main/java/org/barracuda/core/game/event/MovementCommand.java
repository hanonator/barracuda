package org.barracuda.core.game.event;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.barracuda.model.location.Location;

public class MovementCommand {

	/**
	 * The collection of locations that make up the request
	 */
	private final Queue<Location> waypoints = new LinkedList<>();

	/**
	 * Indicates the player is running
	 */
	private final boolean running;
	
	/**
	 * The desination location
	 */
	private final Location destination;

	/**
	 * Constructor
	 * @param running
	 * @param destination
	 * @param locations
	 */
	public MovementCommand(boolean running, Location destination, Collection<Location> locations) {
		this.running = running;
		this.destination = destination;
		this.waypoints.addAll(locations);
	}

	/**
	 * @return the waypoints
	 */
	public Queue<Location> getWaypoints() {
		return waypoints;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the destination
	 */
	public Location getDestination() {
		return destination;
	}


}