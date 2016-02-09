package org.barracuda.roald.future;

import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

/**
 * 
 * @author brock
 *
 */
public interface FutureListener {

	/**
	 * 
	 * 
	 * @param worker
	 * @param clock
	 */
	void onFinish(ClockWorker worker, Clock clock);

}
