package org.barracuda.model.actor.sync.attribute;

public class Transformation implements Attribute {

	/**
	 * The transform type
	 */
	private final int type;

	/**
	 * Constructor
	 * @param type
	 */
	public Transformation(int type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

}
