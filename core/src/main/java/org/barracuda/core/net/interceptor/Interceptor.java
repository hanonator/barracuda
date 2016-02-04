package org.barracuda.core.net.interceptor;

import org.barracuda.core.game.GameSession;

@FunctionalInterface
public interface Interceptor<I, O> {

	/**
	 * 
	 * 
	 * @param input
	 * @return
	 */
	O intercept(I input, GameSession session);

}