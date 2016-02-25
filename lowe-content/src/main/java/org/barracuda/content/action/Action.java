package org.barracuda.content.action;

/**
 * 
 * @author brock
 *
 */
@FunctionalInterface
public interface Action {

	/**
	 * Acts out the action breh
	 * 
	 * @param container
	 */
	void act(ActionContainer container);

}
