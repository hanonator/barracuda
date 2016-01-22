package org.horvik.bean;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.InjectionPoint;

public class AbstractBean<T> extends ForwardingBeanAttributes<T> implements javax.enterprise.inject.spi.Bean<T> {

	/**
	 * The bean attributes. These will be forwarded
	 */
	private BeanAttributes<T> attributes;

	public AbstractBean(BeanAttributes<T> attributes) {
		this.attributes = attributes;
	}

	@Override
	public T create(CreationalContext<T> creationalContext) {
		return null;
	}

	@Override
	public void destroy(T instance, CreationalContext<T> creationalContext) {
		
	}

	@Override
	public Class<T> getBeanClass() {
		return null;
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return null;
	}

	@Override
	public boolean isNullable() {
		return false;
	}

	public void setAttributes(BeanAttributes<T> attributes) {
		this.attributes = attributes;
	}

	@Override
	protected BeanAttributes<T> attributes() {
		return attributes;
	}

}
