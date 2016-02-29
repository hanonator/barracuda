package org.barracuda.content.action.clock;

import java.util.LinkedList;
import java.util.Queue;

import org.barracuda.content.action.Action;
import org.barracuda.content.action.ActionPromise;
import org.barracuda.content.action.ActionQueue;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.roald.Clock;

@SessionScoped
@Discoverable(ActionQueue.class)
public class ClockActionQueue implements ActionQueue {

	/**
	 * The clock object used to schedule the actions
	 */
	@Inject
	private Clock clock;

	/**
	 * The currently active container
	 */
	private ClockActionContainer activeContainer;

	/**
	 * The collection of all queued actions
	 */
	private final Queue<ClockActionContainer> containers = new LinkedList<>();

	@Override
	public ActionPromise submit(long delay, int repetition, Action action) {
		ClockActionPromise promise = new ClockActionPromise();
		for (int i = 0; i < repetition; i++) {
			containers.add(new ClockActionContainer(delay, action, promise));
		}
		next();
		return promise;
	}
	
	/**
	 * 
	 */
	protected void next() {
		if (activeContainer == null || activeContainer.canceled() && !containers.isEmpty()) {
			activeContainer = containers.poll();
			
			if (activeContainer.getPromise().getSubmit() != null) {
				activeContainer.getPromise().getSubmit().accept(activeContainer);
			}
			clock.schedule(new ClockActionClockWorker(activeContainer), activeContainer.getDelay())
					.listener((worker, clock) -> next());
		}
	}

}
