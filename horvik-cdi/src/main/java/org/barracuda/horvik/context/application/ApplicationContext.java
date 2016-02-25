package org.barracuda.horvik.context.application;

import java.util.HashMap;
import java.util.Map;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Context;
import org.barracuda.horvik.context.Contextual;
import org.barracuda.horvik.inject.Instantiator;
import org.barracuda.horvik.util.ReflectionUtil;

public class ApplicationContext implements Context {

	/**
	 * The horvik container
	 */
	private final HorvikContainer container;
	
	/**
	 * The collection to store all of the instantiated classes
	 */
	private final Map<Class<?>, ? super Object> instances = new HashMap<>();
	
	/**
	 * Constructor
	 * @param container
	 */
	public ApplicationContext(HorvikContainer container) {
		this.container = container;
	}

	@Override
	public <T> T get(Contextual key, Bean<T> type) {
		return instances.containsKey(type.getJavaClass()) ? ReflectionUtil.cast(instances.get(type.getJavaClass())) : create(key, type);
	}

	@Override
	public <T> T create(Contextual key, Bean<T> type) {
		Instantiator<T> instantiator = container.getInstantiator(type.getJavaClass());
		instances.put(type.getJavaClass(), instantiator.instantiate(type.getJavaClass(), container));
		return get(key, type);
	}
	
	public <T> void push(Class<T> type, T instance) {
		instances.put(type, instance);
	}

}
