package org.barracuda.content.action;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class RepetitionPredicate implements Predicate<ActionContainer> {

	/**
	 * The counter
	 */
	private final AtomicLong counter;

	/**
	 * Constructor
	 * 
	 * @param amount
	 */
	public RepetitionPredicate(int amount) {
		this.counter = new AtomicLong(amount);
	}

	/**
	 * Constructor
	 * 
	 * @param counter
	 */
	public RepetitionPredicate(AtomicLong counter) {
		this.counter = counter;
	}

	@Override
	public boolean test(ActionContainer t) {
		return counter.decrementAndGet() > 0;
	}

}
