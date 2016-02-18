package org.barracuda.content.action;

/**
 * 
 * @author brock
 *
 */
@FunctionalInterface
public interface ActionSuccessHandler {

	/**
	 * Called when the action has been completed succesfully
	 * 
	 * @param action
	 */
	void onSuccess(ActionContainer action);

}
