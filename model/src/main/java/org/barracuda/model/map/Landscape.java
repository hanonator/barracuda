package org.barracuda.model.map;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.barracuda.model.fixed.RSObject;
import org.barracuda.model.location.Coordinate;
import org.barracuda.model.location.Location;

public class Landscape {
	
	/**
	 * The collection of objects in this landscape
	 */
	@SuppressWarnings("unused")
	private final Set<Tile> tiles;
	
	/**
	 * The collection of objects in this landscape
	 */
	private final Set<RSObject> objects;
	
	/**
	 * Objects that are dynamically removed and added to the landscape during runtime.
	 */
	@SuppressWarnings("unused")
	private final Set<RSObject> runtime_objects = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param objects
	 * @param tiles
	 */
	public Landscape(Set<RSObject> objects, Set<Tile> tiles) {
		this.objects = objects;
		this.tiles = tiles;
	}

	/**
	 * Gets an object by its coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Set<RSObject> getObjects(int x, int y) {
		return objects.stream().filter(object -> object.getLocation().equals(new Coordinate(x, y))).collect(Collectors.toSet());
	}

	/**
	 * Gets an object by its coordinate and id
	 * 
	 * @param x
	 * @param y
	 * @param id
	 * @return
	 */
	public RSObject getObject(int x, int y, int id) {
		return getObjects(x, y).stream().filter(object -> object.getIndex() == id).findAny().orElse(null);
	}

	/**
	 * Gets the objects present at the given location
	 * 
	 * @param location
	 * @return
	 */
	public Set<RSObject> getObjects(Location location) {
		return this.getObjects(location.getX(), location.getY());
	}

	/**
	 * Gets an object by its id and location
	 * 
	 * @param location
	 * @param id
	 * @return
	 */
	public RSObject getObject(Location location, int id) {
		return this.getObject(location.getX(), location.getY(), id);
	}

}
