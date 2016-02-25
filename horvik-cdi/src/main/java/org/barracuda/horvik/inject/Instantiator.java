package org.barracuda.horvik.inject;

import org.barracuda.horvik.HorvikContainer;

public interface Instantiator<T> {

	/**
	 * 
	 * @param container
	 * @return
	 */
	T instantiate(Class<T> type, HorvikContainer container);

}
