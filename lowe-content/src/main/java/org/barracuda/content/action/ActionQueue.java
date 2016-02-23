package org.barracuda.content.action;

public interface ActionQueue {

	/**
	 * Attempts to start the next action in the queue
	 */
	void next();

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * @param action
	 * @param delay
	 * @return
	 */
	ActionPromise queue(int delay, Action action);

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action) {
		return this.queue(1, action);
	}

	/**
	 * Clears the action queue
	 */
	void clear();

}
