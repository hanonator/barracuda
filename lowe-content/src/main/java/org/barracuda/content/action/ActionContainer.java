package org.barracuda.content.action;

public interface ActionContainer {

	/**
	 * Cancels the currently running task and will interrupt it if
	 * it is running
	 * 
	 * @return
	 */
	ActionContainer cancel();
	
	/**
	 * Indicates the action has been canceled
	 * 
	 * @return
	 */
	boolean canceled();

}
