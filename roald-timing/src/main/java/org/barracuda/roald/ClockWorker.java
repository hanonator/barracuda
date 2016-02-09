package org.barracuda.roald;

/**
 * Performs an action at a given time. May be rescheduled after completion to
 * create a repeating action
 * 
 * TODO: Make workers injectable???
 * 
 * @author brock
 *
 */
public interface ClockWorker {

	/**
	 * Executes
	 * 
	 * @param clock
	 */
	void execute(Clock clock);

}
