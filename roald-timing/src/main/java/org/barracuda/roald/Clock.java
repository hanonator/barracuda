package org.barracuda.roald;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.roald.future.Future;
import org.barracuda.roald.util.Timer;

/**
 * Represents a clock to which you can schedule tasks that will be run
 * asynchronously to the main game logic.
 * 
 * The default cycle rate should be set to 600 ms in RSPS but this can be used
 * to schedule daily resets as well or whatever floats your boat.
 * 
 * @author brock
 *
 */
@Discoverable
@ApplicationScoped
public class Clock implements Runnable {

	/**
	 * The rate at which the clock is cycling. For RSPS this should be set to
	 * 600 ms.
	 */
	private final long rate;
	
	/**
	 * The current cycle count
	 */
	private final AtomicLong cycle = new AtomicLong();
	
	/**
	 * The collection of active clock workers
	 */
	private final Queue<Future> workers = new ConcurrentLinkedQueue<>();
	
	/**
	 * The executor for this class
	 */
	private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

	/**
	 * Constructor used to allow the CDI container to create a default clock
	 * with a cycle rate of 600 ms. This is kind of a cheap hack, but it works.
	 * 
	 * @param rate
	 */
	public Clock() {
		this(600L);
	}

	/**
	 * Constructor
	 * 
	 * @param rate
	 */
	public Clock(long rate) {
		this.rate = rate;
	}

	/**
	 * Initializes this clock
	 * 
	 * @param event
	 */
	public void initialize(@Observes ContainerInitialized event) {
		service.scheduleAtFixedRate(this, 0L, rate, TimeUnit.MILLISECONDS);
	}

	/**
	 * Cycles through each of the clock workers and processes them each tick
	 */
	@Override
	public void run() {
		try {
			/*
			 * Execute all of the applicable workers
			 */
			for (Iterator<Future> iterator = workers.iterator(); iterator.hasNext(); ) {
				Future future = iterator.next();
				if (future.isCanceled()) {
					future.getListeners().forEach(listener -> listener.onFinish(future.getWorker(), this));
					iterator.remove();
				}
				else if (future.getTimer() == null || future.getTimer().finished()) {
					future.getWorker().execute(this);
					future.getListeners().forEach(listener -> listener.onFinish(future.getWorker(), this));
					iterator.remove();
				}
			}
			
			/*
			 * Increment the current server cycle count
			 */
			cycle.incrementAndGet();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param worker
	 */
	public Future schedule(ClockWorker worker, Timer timer) {
		Future future = new Future(worker, timer);
		workers.add(future);
		timer.start();
		return future;
	}
	
	/**
	 * 
	 * @param worker
	 */
	public Future schedule(ClockWorker worker, long delay) {
		return this.schedule(worker, new Timer(delay, this));
	}
	
	/**
	 * 
	 * @param worker
	 */
	public Future schedule(ClockWorker worker) {
		return this.schedule(worker, 1);
	}
	
	/**
	 * Shuts down this service
	 */
	public void shutdown() {
		workers.clear();
		service.shutdownNow();
	}

	/**
	 * Gets the current clock time
	 * @return
	 */
	public long getTime() {
		return cycle.get();
	}

}
