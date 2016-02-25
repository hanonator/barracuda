package org.barracuda.horvik.bean;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.inject.InjectionPoint;

public class ManagedBean<T> implements Bean<T> {

	/**
	 * The type of this bean
	 */
	private final Class<T> type;
	
	/**
	 * The collection of injection points for this bean
	 */
	private final Set<InjectionPoint<T>> injection_points;
	
	/**
	 * The scope of the bean
	 */
	private final Class<? extends Annotation> scope;

	public ManagedBean(Class<T> type, Set<InjectionPoint<T>> injection_points) {
		this.type = type;
		this.scope = null;
		this.injection_points = injection_points;
	}

	public ManagedBean(Class<T> type, HorvikContainer container) {
		this.type = type;
		this.scope = findScope(container);
		this.injection_points = container.getInjectionPoints(type);
	}
	
	private Class<? extends Annotation> findScope(HorvikContainer container) {
		for (Annotation annotation : type.getDeclaredAnnotations()) {
			if (container != null && container.isScope(annotation.annotationType())) {
				return annotation.annotationType();
			}
		}
		return ApplicationScoped.class;
	}

	@Override
	public Class<T> getJavaClass() {
		return type;
	}

	@Override
	public Set<InjectionPoint<T>> getInjectionPoints() {
		return injection_points;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return scope;
	}

	@Override
	public String toString() {
		return "ManagedBean [type=" + type + ", injection_points=" + injection_points + ", scope=" + scope + "]";
	}

}
