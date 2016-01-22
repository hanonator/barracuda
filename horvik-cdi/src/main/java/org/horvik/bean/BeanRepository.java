package org.horvik.bean;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.spi.Bean;

public class BeanRepository {

	/**
	 * The collection storing all of the objects
	 */
	private final Map<Bean<?>, Object> repository = new HashMap<>();

	/**
	 * 
	 * @param bean
	 * @param instance
	 */
	public <T> void add(Bean<T> bean, T instance) {
		repository.put(bean, instance);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	public <T> T get(Bean<T> bean) {
//		return bean.getBeanClass().cast(repository.get(bean));
		return null;
	}
	
	/**
	 * Destroys the repository
	 */
	public void destroy() {
		repository.clear();
	}

}
