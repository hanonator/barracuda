package org.barracuda.model.actor.sync.attribute;

/**
 * An animation
 * 
 * @author brock
 *
 */
public class Animation implements Attribute {

	/**
	 * Cancels all animations when this is sent
	 */
	public static final Animation CANCEL_ANIMATION = new Animation(65535);

	/**
	 * Cycles between received and performed
	 */
	private final int delay;
	
	/**
	 * Id of the animation
	 */
	private final int id;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param delay
	 */
	public Animation(int id, int delay) {
		this.delay = delay;
		this.id = id;
	}

	/**
	 * Constructor with a default delay of 0
	 * 
	 * @param id
	 */
	public Animation(int id) {
		this(id, 0);
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}