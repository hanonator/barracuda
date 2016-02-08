package org.barracuda.model.actor;

import org.barracuda.model.Entity;

public abstract class Actor implements Entity {

	/**
	 * The actor's unique id
	 */
	private int index;
	
	@Override
	public int getId() {
		return index;
	}

}
