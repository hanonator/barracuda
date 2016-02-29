package org.barracuda.content.action;

@FunctionalInterface
public interface Action {

	/**
	 * Performs the action
	 * 
	 * @param container
	 */
	void fire(ActionContainer container);

}
