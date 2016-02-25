package org.barracuda.horvik.inject;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Contextual;

/**
 * 
 * @author brock
 *
 * @param <T>
 */
public interface InjectionPoint<T> {

	/**
	 * Attempts to inject point
	 * 
	 * @param container
	 * @param bean
	 * @param instance
	 */
	void inject(HorvikContainer container, Bean<T> bean, Object instance, Contextual contextual);

}
