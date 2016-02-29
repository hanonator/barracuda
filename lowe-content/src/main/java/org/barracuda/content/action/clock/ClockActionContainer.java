package org.barracuda.content.action.clock;

import org.barracuda.content.action.Action;
import org.barracuda.content.action.ActionContainer;
import org.barracuda.roald.future.Future;

public class ClockActionContainer implements ActionContainer {

	/**
	 * The amount of cycles after which the action will be executed
	 */
	private final long delay;
	
	/**
	 * The action that will be executed
	 */
	private final Action action;
	
	/**
	 * The promise that is responsible for what happens during the execution
	 * of the action
	 */
	private final ClockActionPromise promise;
	
	/**
	 * When the action is scheduled, its future will be here
	 */
	private Future future;

	/**
	 * Constructor
	 * 
	 * @param delay
	 * @param action
	 * @param promise
	 */
	public ClockActionContainer(long delay, Action action, ClockActionPromise promise) {
		this.delay = delay;
		this.action = action;
		this.promise = promise;
	}

	/**
	 * @see org.barracuda.content.action.ActionContainer#cancel()
	 */
	@Override
	public ActionContainer cancel() {
		future.cancel();
		return this;
	}

	/**
	 * @see org.barracuda.content.action.ActionContainer#canceled()
	 */
	@Override
	public boolean canceled() {
		return future.isCanceled() || future.isFinished();
	}

	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @return the promise
	 */
	public ClockActionPromise getPromise() {
		return promise;
	}

	/**
	 * @return the future
	 */
	public Future getFuture() {
		return future;
	}

	/**
	 * @param future the future to set
	 */
	public void setFuture(Future future) {
		this.future = future;
	}

}
