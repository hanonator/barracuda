package org.barracuda.horvik.inject;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Contextual;

public interface Injector<T> {

	/**
	 * 
	 * 
	 * @param container
	 * @param bean
	 * @param reference
	 */
	void inject(HorvikContainer container, Bean<T> bean, Object reference, Contextual contextual);

}
