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
	public ActionPromise queue(int delay, Action action) {
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
			container.setFuture(clock.schedule(null, container.getDelay()));
			container.getFuture()
					.listener((worker, clock) -> next())
					.listener((worker, clock) -> container.getPromise().getSuccessHandler().onSuccess(container))
					.error((error, worker, clock) -> container.getPromise().getExceptionHandler().exceptionCaught(container, error));
			activeContainer = actions.poll();
		}
	}

}
