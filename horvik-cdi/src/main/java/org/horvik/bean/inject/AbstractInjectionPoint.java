package org.horvik.bean.inject;

import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

import javax.decorator.Delegate;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Qualifier;

public abstract class AbstractInjectionPoint implements InjectionPoint {

	/**
	 * The type of object that needs to be injected
	 */
	private final Type type;
	
	/**
	 * The bean for this injection point
	 */
	private final Bean<?> bean;

	public AbstractInjectionPoint(Type type, Bean<?> bean) {
		this.type = type;
		this.bean = bean;
	}

	@Override
	public Set<Annotation> getQualifiers() {
		return getAnnotated().getAnnotations().stream()
				.filter(annotation -> annotation.annotationType().isAnnotationPresent(Qualifier.class))
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isDelegate() {
		return getAnnotated().isAnnotationPresent(Delegate.class);
	}

	@Override
	public boolean isTransient() {
		return getAnnotated().isAnnotationPresent(Transient.class);
	}

	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public Bean<?> getBean() {
		return bean;
	}

}
