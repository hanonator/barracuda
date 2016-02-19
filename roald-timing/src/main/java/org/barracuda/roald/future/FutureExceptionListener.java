package org.barracuda.roald.future;

import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

public interface FutureExceptionListener {

	void onException(Exception exception, ClockWorker worker, Clock clock);

}
