package org.barracuda.model.actor.sync.attribute;

/**
 * TODO: Needs a better name
 * 
 * @author brock
 *
 */
public class Graphic implements Attribute {

	/**
	 * The delay between the call and the execution of the graphic
	 */
	private final int delay;
	
	/**
	 * The height of the animation. Default height should be 100
	 */
	private final int height;
	
	/**
	 * The id of the graphic
	 */
	private final int id;

	/**
	 * Constructor
	 * 
	 * @param delay
	 * @param height
	 * @param id
	 */
	public Graphic(int delay, int height, int id) {
		this.delay = delay;
		this.height = height;
		this.id = id;
	}

	/**
	 * Constructor with a default delay of 0
	 * 
	 * @param height
	 * @param id
	 */
	public Graphic(int height, int id) {
		this(0, height, id);
	}

	/**
	 * Constructor with a default delay of 0 and height of 100
	 * 
	 * @param id
	 */
	public Graphic(int id) {
		this(100, id);
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
