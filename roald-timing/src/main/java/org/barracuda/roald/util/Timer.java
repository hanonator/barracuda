package org.barracuda.roald.util;

import org.barracuda.roald.Clock;

public class Timer {

	/**
	 * The time at which the timer has been submitted
	 */
	private long submittedTime;
	
	/**
	 * The time it takes for the timer to complete
	 */
	private final long delay;

	/**
	 * The clock
	 */
	private final Clock clock;

	/**
	 * 
	 * @param delay
	 */
	public Timer(long delay, Clock clock) {
		this.delay = delay;
		this.clock = clock;
	}
	
	/**
	 * Starts the timer
	 */
	public void start() {
		this.submittedTime = clock.getTime();
	}
	
	/**
	 * Rewinds the timer. This is the exact same as start but I feel
	 * like it would look weird calling start multiple times without
	 * stop or anyting like that
	 */
	public void rewind() {
		this.submittedTime = clock.getTime();
	}
	
	/**
	 * Checks to see if the timer has run out
	 * 
	 * @return
	 */
	public boolean finished() {
		return this.clock.getTime() - this.submittedTime >= delay;
	}

	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}
	
}
