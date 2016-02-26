package org.barracuda.content.action;

import java.util.function.Predicate;

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
	ActionPromise queue(Action action, Predicate<ActionContainer> predicate, int delay);

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * This is performed with a default delay of 3 game ticks as most actions
	 * are of this kind
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action, Predicate<ActionContainer> predicate) {
		return this.queue(action, predicate, 3);
	}

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action) {
		return this.queue(action, null, 3);
	}

	/**
	 * Queues a new action to be scheduled right now or when the player has
	 * performed all of its other actions
	 * 
	 * @param action
	 * @return
	 */
	default ActionPromise queue(Action action, int delay) {
		return this.queue(action, null, delay);
	}

	/**
	 * Clears the action queue
	 */
	void clear();

}
