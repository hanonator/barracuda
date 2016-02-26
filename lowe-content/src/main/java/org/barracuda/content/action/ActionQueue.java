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
	 * @return
	 */
	ActionPromise queue(Action action, int delay);

	/**
	 * Clears the action queue
	 */
	void clear();

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action) {
		return this.queue(action, 3);
	}

}
