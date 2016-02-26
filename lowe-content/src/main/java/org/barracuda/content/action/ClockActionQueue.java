package org.barracuda.content.action;

import java.util.Deque;
import java.util.LinkedList;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.roald.Clock;

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
	public ActionPromise queue(Action action, int delay) {
		ActionPromise promise = new ActionPromise();
		actions.add(new ActionContainer(action, delay, promise));
		return promise;
	}

	@Override
	public void next() {
		if (actions.isEmpty()) {
			return;
		}
		if (activeContainer == null || activeContainer.isCanceled() || activeContainer.getFuture().isCanceled() || activeContainer.getFuture().isFinished()) {
			ActionContainer container = actions.peek();
			if (container.getPromise().getSubmitHandler() != null) {
				container.getPromise().getSubmitHandler().onSubmit(container);
			}
			container.setFuture(clock.schedule(null, container.getDelay()));
			container.getFuture()
					.listener((worker, clock) -> {
						if (container.getPromise().getSuccessHandler() != null) {
							container.getPromise().getSuccessHandler().onSuccess(container);
						}
					})
					.error((error, worker, clock) -> {
						if (container.getPromise().getExceptionHandler() != null) {
							container.getPromise().getExceptionHandler().exceptionCaught(container, error);
						}
					});
			activeContainer = actions.poll();
		}
	}

	@Override
	public void clear() {
		activeContainer.cancel();
		actions.forEach(container -> container.cancel());
	}

}
