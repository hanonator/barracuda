package org.barracuda.content.action;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Predicate;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;
import org.barracuda.roald.future.FutureListener;

/**
 * Action queue implementation that uses the CDI module to queue
 * its tasks to the clock
 * 
 * @author brock
 *
 */
@SessionScoped
@Discoverable(ActionQueue.class)
public class ClockActionQueue implements ActionQueue {

	/**
	 * The application instance for the clock
	 */
	@Inject
	private Clock clock;
	
	/**
	 * The player for this queue
	 */
	@Inject
	private Player player;
	
	/**
	 * The currently active container
	 */
	private ActionContainer activeContainer;
	
	/**
	 * The collection of actions
	 */
	private final Deque<ActionContainer> actions = new LinkedList<>();

	@Override
	public ActionPromise queue(Action action, Predicate<ActionContainer> predicate, int delay) {
		ActionPromise promise = new ActionPromise();
		actions.add(new ActionContainer(action, delay, promise, predicate));
		return promise;
	}

	@Override
	public void next() {
		if (actions.isEmpty()) {
			return;
		}
		if (activeContainer == null || activeContainer.isCanceled() || activeContainer.getFuture().isCanceled() || activeContainer.getFuture().isFinished()) {
			ActionContainer container = actions.peek();
			container.setFuture(clock.schedule(null, container.getDelay()));
			container.getFuture()
					.listener(new PredicateFutureListener(container.getPredicate(), container))
					.listener((worker, clock) -> container.getPromise().getSuccessHandler().onSuccess(container))
					.error((error, worker, clock) -> container.getPromise().getExceptionHandler().exceptionCaught(container, error));
			activeContainer = actions.poll();
		}
	}

	@Override
	public void clear() {
		activeContainer.cancel();
		actions.clear();
	}
	
	/**
	 * FutureListener that will reschedule or cancel the given action depending
	 * on if the predicate fails
	 * 
	 * @author koga
	 *
	 */
	private class PredicateFutureListener implements FutureListener {

		/**
		 * The predicate
		 */
		private final Predicate<ActionContainer> predicate;
		
		/**
		 * The action container
		 */
		private final ActionContainer container;

		/**
		 * Constructor
		 * 
		 * @param predicate
		 */
		public PredicateFutureListener(Predicate<ActionContainer> predicate, ActionContainer container) {
			this.predicate = predicate;
			this.container = container;
		}

		@Override
		public void onFinish(ClockWorker worker, Clock clock) {
			if (predicate.test(container)) {
				clock.schedule(worker, container.getFuture().getTimer().getDelay());
			}
			else {
				next();
			}
		}
		
	}

}
