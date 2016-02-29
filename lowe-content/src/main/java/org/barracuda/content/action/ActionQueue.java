package org.barracuda.content.action;

/**
 * 
 * @author koga
 *
 */
public interface ActionQueue {

	/**
	 * Submits an action to the queue
	 * 
	 * @param action
	 * @param delay
	 * @return
	 */
	ActionPromise submit(long delay, int repetition, Action action);

}
