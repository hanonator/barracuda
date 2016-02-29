package org.barracuda.content.action.clock;

import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

class ClockActionClockWorker implements ClockWorker {

	/**
	 * The container being executed
	 */
	private final ClockActionContainer container;

	/**
	 * Constructor
	 * 
	 * @param container
	 * @param queue
	 */
	public ClockActionClockWorker(ClockActionContainer container) {
		this.container = container;
	}

	@Override
	public void execute(Clock clock) {
		if (!container.canceled() && container.getAction() != null) {
			try {
				container.getAction().fire(container);
				if (container.getPromise().getSuccess() != null) {
					container.getPromise().getSuccess().accept(container);
				}
			} catch (Exception ex) {
				if (container.getPromise().getFail() != null) {
					container.getPromise().getFail().accept(ex);
				}
			}
		}
	}

}
