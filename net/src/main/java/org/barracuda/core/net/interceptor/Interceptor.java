package org.barracuda.core.net.interceptor;

@FunctionalInterface
public interface Interceptor<I, O> {

	/**
	 * 
	 * 
	 * @param input
	 * @return
	 */
	O intercept(I input);

}