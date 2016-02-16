package org.barracuda.model.actor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.barracuda.model.Entity;
import org.barracuda.model.actor.sync.Camera;
import org.barracuda.model.actor.sync.attribute.Attribute;
import org.barracuda.model.location.Location;

/**
 * An actor is an entity that is actively synchronized each tick and is able to
 * perform and queue actions (e.g. players and npcs)
 * 
 * @author brock
 *
 */
public abstract class Actor implements Entity {

	/**
	 * The actor's unique id
	 */
	private int index;
	
	/**
	 * The actor's location
	 */
	private Location location;
	
	/**
	 * The requested teleport target
	 */
	private Location teleportRequest;
	
	/**
	 * The camera
	 */
	private final Camera camera = new Camera();

	/**
	 * The collection of attributes that affect the way the actor is rendered in
	 * the client during the synchronization process
	 */
	private final Set<Attribute> renderingHints = new HashSet<>();

	/**
	 * @return the index
	 */
	@Override
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Adds a rendering hint
	 * 
	 * @param attribute
	 */
	public void addRenderingHint(Attribute attribute) {
		renderingHints.add(attribute);
	}

	/**
	 * Removes all attributes off the given class. This should only ever be 1,
	 * but you never know
	 * 
	 * @param attribute
	 */
	public void removeRenderingHints(Class<? extends Attribute> attribute) {
		renderingHints.removeAll(renderingHints.stream().filter(hint -> hint.getClass() == attribute).collect(Collectors.toSet()));
	}

	/**
	 * @return the renderingHints
	 */
	public Set<Attribute> getRenderingHints() {
		return renderingHints;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @return the teleportRequest
	 */
	public Location getTeleportRequest() {
		return teleportRequest;
	}

	/**
	 * @param teleportRequest the teleportRequest to set
	 */
	public void setTeleportRequest(Location teleportRequest) {
		this.teleportRequest = teleportRequest;
	}

}
