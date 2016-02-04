package org.barracuda.horvik.bean;

import java.util.HashMap;
import java.util.Map;

public class BeanRepository {

	/**
	 * The collection of objects mapped to their bean
	 */
	private final Map<Bean<?>, Object> repository = new HashMap<>();

	/**
	 * Retrieves the saved instance for a given bean
	 * 
	 * @param bean
	 * @return
	 * @throws NullPointerException
	 */
	public Object get(Bean<?> bean) throws NullPointerException {
		return repository.get(bean);
	}

	/**
	 * 
	 * @param type
	 * @param instance
	 */
	public void put(Bean<?> type, Object instance) {
		repository.put(type, instance);
	}

	@Override
	public String toString() {
		return "BeanRepository [size=" + repository.size() + "]";
	}

}
