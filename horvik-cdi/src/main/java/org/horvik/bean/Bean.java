package org.horvik.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.spi.BeanAttributes;

public class Bean<T> implements BeanAttributes<T> {

	/**
	 * The raw type of the bean
	 */
	private final Class<T> type;
	
	/**
	 * The name of the bean
	 */
	private final String name;

	public Bean(Class<T> type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public Set<Type> getTypes() {
		return null;
	}

	@Override
	public Set<Annotation> getQualifiers() {
		return null;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Set<Class<? extends Annotation>> getStereotypes() {
		return null;
	}

	@Override
	public boolean isAlternative() {
		return false;
	}

}
