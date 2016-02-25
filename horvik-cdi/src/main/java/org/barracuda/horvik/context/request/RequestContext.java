package org.barracuda.horvik.context.request;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Context;
import org.barracuda.horvik.context.Contextual;
import org.barracuda.horvik.inject.Instantiator;

public class RequestContext implements Context {

	/**
	 * The horvik container
	 */
	private final HorvikContainer container;
	
	/**
	 * Constructor
	 * @param container
	 */
	public RequestContext(HorvikContainer container) {
		this.container = container;
	}

	@Override
	public <T> T get(Contextual key, Bean<T> type) {
		return create(key, type);
	}

	@Override
	public <T> T create(Contextual key, Bean<T> type) {
		Instantiator<T> instantiator = container.getInstantiator(type.getJavaClass());
		return instantiator.instantiate(type.getJavaClass(), container);
	}

}
