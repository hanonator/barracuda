package org.barracuda.content.action;

/**
 * 
 * @author koga
 *
 */
public interface ActionQueue {

	/**
	 * The maximum amount of repetition is 120 as the idle logout timer is set to
	 * 1 minute and the minimum delay is set to 1.
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
