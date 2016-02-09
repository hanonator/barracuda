package org.barracuda.roald.future;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.barracuda.roald.ClockWorker;
import org.barracuda.roald.util.Timer;

/**
 * Contains information about the scheduled worker
 * 
 * @author brock
 *
 */
public class Future implements Iterable<FutureListener>, Supplier<Stream<FutureListener>> {

	/**
	 * The worker that this future is watching
	 */
	private final ClockWorker worker;

	/**
	 * The timer keeping track of the delay between submission and execution
	 */
	private final Timer timer;
	
	/**
	 * Indicates the clock worker has been canceled
	 */
	private boolean canceled;
	
	/**
	 * The collection of listeners that get called when the clock worker has
	 * finished executing
	 */
	private final Set<FutureListener> listeners = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param worker
	 * @param timer
	 */
	public Future(ClockWorker worker, Timer timer) {
		this.worker = worker;
		this.timer = timer;
	}

	/**
	 * Prevents the worker from being executed
	 */
	public void cancel() {
		this.canceled = true;
	}

	/**
	 * Indicates the clock worker has been canceled
	 * 
	 * @return
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * @return the worker
	 */
	public ClockWorker getWorker() {
		return worker;
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param listener
	 * @return
	 */
	public Future listener(FutureListener listener) {
		listeners.add(listener);
		return this;
	}

	/**
	 * @return the listeners
	 */
	public Set<FutureListener> getListeners() {
		return listeners;
	}

	/**
	 * This will repeat the action
	 */
	public void repeat() {
		listeners.add((worker, clock) -> clock.schedule(worker, timer.getDelay()).repeat());
	}

	@Override
	public Stream<FutureListener> get() {
		return listeners.stream();
	}

	@Override
	public Iterator<FutureListener> iterator() {
		return listeners.iterator();
	}

}
