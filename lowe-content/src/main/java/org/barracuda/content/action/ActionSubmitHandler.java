package org.barracuda.content.action;

public interface ActionSubmitHandler {

	/**
	 * Called when the action is being submitted
	 * 
	 * @param container
	 */
	void onSubmit(ActionContainer container);

}
