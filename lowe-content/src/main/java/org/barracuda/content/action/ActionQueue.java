package org.barracuda.content.action;

public interface ActionQueue {

	/**
	 * 
	 * @param action
	 * @param delay
	 * @return
	 */
	ActionPromise queue(Action action, int delay);

	/**
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action) {
		return this.queue(action, 1);
	}
	
	/**
	 * 
	 */
	void next();

}
