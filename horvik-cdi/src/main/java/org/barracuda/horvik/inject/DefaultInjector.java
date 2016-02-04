package org.barracuda.horvik.inject;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Contextual;

public class DefaultInjector<T> implements Injector<T> {

	@Override
	public void inject(HorvikContainer container, Bean<T> bean, Object reference, Contextual contextual) {
		bean.getInjectionPoints().forEach(injection_point -> {
			injection_point.inject(container, bean, reference, contextual);
		});
	}

}
