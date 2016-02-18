package org.barracuda.content.action;

import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;
import org.barracuda.roald.future.Future;

public class ActionContainer implements ClockWorker {

	/**
	 * The delay between registration and activation
	 */
	private final int delay;

	/**
	 * The action
	 */
	private final Action action;
	
	/**
	 * The promise
	 */
	private final ActionPromise promise;
	
	/**
	 * Indicates the action has been canceled
	 */
	private boolean canceled;
	
	/**
	 * The future
	 * 
	 */
	private Future future;

	/**
	 * Constructor
	 * 
	 * @param action
	 * @param promise
	 * @param future
	 */
	public ActionContainer(Action action, int delay, ActionPromise promise) {
		this.action = action;
		this.promise = promise;
		this.delay = delay;
	}

	@Override
	public void execute(Clock clock) {
		try {
			action.act(this);
		} catch (Exception exception) {
			promise.getExceptionHandler().exceptionCaught(this, exception);
		}
	}
	
	/**
	 * Cancels the action and does not call any of the promise methods (this is deliberate)
	 */
	public void cancel() {
		this.future.cancel();
		this.canceled = true;
	}

	/**
	 * @return the future
	 */
	Future getFuture() {
		return future;
	}

	/**
	 * @return the future
	 */
	void setFuture(Future future) {
		this.future = future;
	}

	/**
	 * @return the canceled
	 */
	boolean isCanceled() {
		return canceled;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

}
