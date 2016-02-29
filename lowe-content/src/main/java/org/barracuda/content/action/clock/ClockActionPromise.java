package org.barracuda.content.action.clock;

import java.util.function.Consumer;

import org.barracuda.content.action.ActionContainer;
import org.barracuda.content.action.ActionPromise;

public class ClockActionPromise implements ActionPromise {
	
	/**
	 * The consumer called when the action has been successfully executed
	 */
	private Consumer<ActionContainer> success;
	
	/**
	 * The consumer called when the action is being submitted
	 */
	private Consumer<ActionContainer> submit;
	
	/**
	 * The consumer that gets called when the action has thrown an exception
	 */
	private Consumer<Exception> fail;

	@Override
	public ActionPromise success(Consumer<ActionContainer> consumer) {
		this.success = consumer;
		return this;
	}

	@Override
	public ActionPromise fail(Consumer<Exception> consumer) {
		this.fail = consumer;
		return this;
	}

	@Override
	public ActionPromise submit(Consumer<ActionContainer> consumer) {
		this.submit = consumer;
		return this;
	}

	/**
	 * @return the success
	 */
	public Consumer<ActionContainer> getSuccess() {
		return success;
	}

	/**
	 * @return the submit
	 */
	public Consumer<ActionContainer> getSubmit() {
		return submit;
	}

	/**
	 * @return the fail
	 */
	public Consumer<Exception> getFail() {
		return fail;
	}

}
