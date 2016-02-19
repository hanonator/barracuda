package org.barracuda.content.action;

public interface ActionQueue {

	/**
	 * 
	 * @param action
	 * @param delay
	 * @return
	 */
	ActionPromise queue(int delay, Action action);

	/**
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action) {
		return this.queue(1, action);
	}
	
	/**
	 * 
	 */
	void next();

}
