package org.barracuda.content.action;

import java.util.function.Consumer;

public interface ActionPromise {

	/**
	 * Called when the action has been performed successfully. This can be used
	 * to implement random events or something
	 * 
	 * @param consumer
	 * @return
	 */
	ActionPromise success(Consumer<ActionContainer> consumer);

	/**
	 * Called when an error has occurred during the execution of the action
	 * 
	 * @param consumer
	 * @return
	 */
	ActionPromise fail(Consumer<Exception> consumer);

	/**
	 * Called when the action is being submitted
	 * 
	 * @param consumer
	 * @return
	 */
	ActionPromise submit(Consumer<ActionContainer> consumer);

}
