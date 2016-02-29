package org.barracuda.content.action;

/**
 * 
 * @author koga
 *
 */
public interface ActionQueue {

	/**
	 * The maximum amount of repetition is 120 as the idle logout timer is set to
	 * 1 minute and the minimum delay is set to 1. The only downside to this is that
	 * the player will stop doing an action after 120 rotations, but he may or may
	 * not actually be finished.
	 */
	int MAXIMUM_REPETITION = 120;

	/**
	 * Submits an action to the queue
	 * 
	 * @param action
	 * @param delay
	 * @return
	 */
	ActionPromise submit(long delay, int repetition, Action action);

}
