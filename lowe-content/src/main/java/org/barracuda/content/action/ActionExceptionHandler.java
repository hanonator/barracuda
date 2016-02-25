package org.barracuda.content.action;

/**
 * 
 * @author brock
 *
 */
@FunctionalInterface
public interface ActionExceptionHandler {

	/**
	 * Called when an exception occurred during the execution of the action
	 * 
	 * @param exception
	 */
	void exceptionCaught(ActionContainer action, Exception exception);

}
