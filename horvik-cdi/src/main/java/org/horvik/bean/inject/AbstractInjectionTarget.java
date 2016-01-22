package org.horvik.bean.inject;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

public abstract class AbstractInjectionTarget<T> implements InjectionTarget<T> {

	/**
	 * The bean that needs to be injected
	 */
	private final Bean<T> bean;

	/**
	 * The set of injection points
	 */
	private final Set<InjectionPoint> injectionPoints;

	public AbstractInjectionTarget(Bean<T> bean, Set<InjectionPoint> injectionPoints) {
		this.bean = bean;
		this.injectionPoints = injectionPoints;
	}

	@Override
	public void inject(T instance, CreationalContext<T> ctx) {
		
	}

	@Override
	public T produce(CreationalContext<T> ctx) {
		T incompleteInstance = bean.create(ctx);
		inject(incompleteInstance, ctx);
		ctx.push(incompleteInstance);
		return incompleteInstance;
	}

	@Override
	public void postConstruct(T instance) {
		
	}

	@Override
	public void preDestroy(T instance) {
		
	}

	@Override
	public void dispose(T instance) {
		
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return injectionPoints;
	}

}
