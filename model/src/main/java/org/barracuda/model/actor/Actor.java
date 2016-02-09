package org.barracuda.model.actor;

import org.barracuda.model.Entity;

public abstract class Actor implements Entity {

	/**
	 * The actor's unique id
	 */
	private int index;

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
	public void setIndex(int index) {
		this.index = index;
	}

}
