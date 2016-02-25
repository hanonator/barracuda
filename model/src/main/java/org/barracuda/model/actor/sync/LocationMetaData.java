package org.barracuda.model.actor.sync;

import org.barracuda.model.location.Location;

public class LocationMetaData {
	
	/**
	 * The target teleportation location
	 */
	private final Location teleportTarget;
	
	/**
	 * The direction of the first processed waypoint
	 */
	private final Direction primaryDirection;
	
	/**
	 * The direction of the second processed waypoint
	 */
	private final Direction secondaryDirection;
	
	/**
	 * map region changed
	 */
	private final boolean mapRegionChanged;

	/**
	 * Constructor
	 * 
	 * @param teleportTarget
	 * @param primaryDirection
	 * @param secondaryDirection
	 * @param mapRegionChanged
	 */
	public LocationMetaData(Location teleportTarget, Direction primaryDirection, Direction secondaryDirection, boolean mapRegionChanged) {
		this.teleportTarget = teleportTarget;
		this.primaryDirection = primaryDirection;
		this.secondaryDirection = secondaryDirection;
		this.mapRegionChanged = mapRegionChanged;
	}

	/**
	 * @return the teleportTarget
	 */
	public Location getTeleportTarget() {
		return teleportTarget;
	}

	/**
	 * @return the primaryDirection
	 */
	public Direction getPrimaryDirection() {
		return primaryDirection;
	}

	/**
	 * @return the secondaryDirection
	 */
	public Direction getSecondaryDirection() {
		return secondaryDirection;
	}

	/**
	 * @return the mapRegionChanged
	 */
	public boolean hasMapRegionChanged() {
		return mapRegionChanged;
	}

	/**
	 * @return if the player is teleporting or naw
	 */
	public boolean isTeleporting() {
		return teleportTarget != null;
	}

}
