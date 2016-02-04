package org.barracuda.horvik.inject;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.HorvikException;

public class DefaultInstantiator<T> implements Instantiator<T> {

	@Override
	public T instantiate(Class<T> type, HorvikContainer container) {
		try {
			// TODO: This is pretty shit
			return type.newInstance();
		} catch (Exception ex) {
			throw new HorvikException(ex);
		}
	}

}
