package org.barracuda.model.actor;

import org.barracuda.model.Entity;
import org.barracuda.model.actor.sync.Camera;
import org.barracuda.model.actor.sync.RenderingHints;
import org.barracuda.model.actor.sync.attribute.AbstractHit.Sprite;
import org.barracuda.model.actor.sync.attribute.Animation;
import org.barracuda.model.actor.sync.attribute.ChatForced;
import org.barracuda.model.actor.sync.attribute.FaceEntity;
import org.barracuda.model.actor.sync.attribute.FaceLocation;
import org.barracuda.model.actor.sync.attribute.Graphic;
import org.barracuda.model.actor.sync.attribute.HitPrimary;
import org.barracuda.model.actor.sync.attribute.HitSecondary;
import org.barracuda.model.location.Coordinate;
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
	private Location location = new Coordinate(0, 0, 0);
	
	/**
	 * The requested teleport target
	 */
	private Location teleportRequest = new Coordinate(3222, 3222);
	
	/**
	 * The camera
	 */
	private final Camera camera = new Camera();

	/**
	 * The collection of attributes that affect the way the actor is rendered in
	 * the client during the synchronization process
	 */
	private final RenderingHints renderingHints = new RenderingHints(this.getClass());

	/**
	 * Turns the actor in a direction so that he is facing the given location
	 * 
	 * @param coordinate
	 */
	public void face(Location coordinate) {
		renderingHints.add(new FaceLocation(coordinate));
	}

	/**
	 * Turns the actor in a direction so that he is facing the given location
	 * 
	 * @param coordinate
	 */
	public void face(int x, int y) {
		this.face(new Coordinate(x, y));
	}
	
	/**
	 * Turns the player towards an entity and will keep him facing the entity until
	 * 
	 * @param entity
	 */
	public void face(Entity entity) {
		renderingHints.add(new FaceEntity(entity));
	}
	
	/**
	 * Hits the player for a specified amount
	 * @param amount
	 * @param sprite
	 */
	public void hit(int amount, Sprite sprite) {
		renderingHints.add(renderingHints.contains(HitPrimary.class) ? new HitSecondary(amount, sprite) : new HitPrimary(amount, sprite));
	}
	
	/**
	 * Hits the actor for a specified amount
	 * @param amount
	 * @param sprite
	 */
	public void hit(int amount) {
		this.hit(amount, amount > 0 ? Sprite.RED : Sprite.BLUE);
	}
	
	/**
	 * plays the graphic
	 * 
	 * @param graphic
	 */
	public void play(Graphic graphic) {
		renderingHints.add(graphic);
	}

	/**
	 * Plays the animation for the actor
	 * 
	 * @param animation
	 */
	public void play(Animation animation) {
		renderingHints.add(animation);
	}
	
	/**
	 * Plays an animation
	 * 
	 * @param id
	 * @param delay
	 */
	public void playAnimation(int id, int delay) {
		this.play(new Animation(id, delay));
	}
	
	/**
	 * Plays an animation
	 * 
	 * @param id
	 */
	public void playAnimation(int id) {
		this.playAnimation(id, 0);
	}
	
	/**
	 * Plays a graphic
	 * 
	 * @param id
	 * @param delay
	 */
	public void playGraphic(int id, int delay, int height) {
		this.play(new Graphic(delay, height, id));
	}
	
	/**
	 * Plays a graphic
	 * 
	 * @param id
	 */
	public void playGraphic(int id, int height) {
		this.playGraphic(id, 0, height);
	}
	
	/**
	 * Forces the actor to say something
	 * 
	 * @param text
	 */
	public void talk(String text) {
		this.renderingHints.add(new ChatForced(text));
	}

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
	 * @return the renderingHints
	 */
	public RenderingHints getRenderingHints() {
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
