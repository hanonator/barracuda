package org.barracuda.horvik.inject;

import java.lang.reflect.Method;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.HorvikException;
import org.barracuda.horvik.bean.PostConstruct;

public class DefaultInstantiator<T> implements Instantiator<T> {

	@Override
	public T instantiate(Class<T> type, HorvikContainer container) {
		try {
			T instance = type.newInstance();
			for (Method method : type.getDeclaredMethods()) {
				if (method.isAnnotationPresent(PostConstruct.class)) {
					method.invoke(instance);
				}
			}
			return instance;
		} catch (Exception ex) {
			throw new HorvikException(ex);
		}
	}

}
