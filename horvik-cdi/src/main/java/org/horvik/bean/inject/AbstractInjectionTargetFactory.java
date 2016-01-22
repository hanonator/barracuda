package org.horvik.bean.inject;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionTarget;

import org.horvik.bean.BeanManager;

public class AbstractInjectionTargetFactory<T> implements javax.enterprise.inject.spi.InjectionTargetFactory<T> {

	/**
	 * The bean manager reference
	 */
	private final BeanManager manager;

	public AbstractInjectionTargetFactory(BeanManager manager) {
		this.manager = manager;
	}

	@Override
	public InjectionTarget<T> createInjectionTarget(Bean<T> bean) {
		return null;
	}

}
