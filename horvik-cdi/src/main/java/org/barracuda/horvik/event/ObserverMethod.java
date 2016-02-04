package org.barracuda.horvik.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.HorvikException;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Contextual;

public class ObserverMethod<T> {

	/**
	 * The bean containing the observer method
	 */
	private final Bean<?> bean;
	
	/**
	 * The type that is being observed
	 */
	private final Class<T> observedType;
	
	/**
	 * The method that needs to be injected
	 * 
	 * TODO: Use a method injection point and allow it to inject references into the parameters
	 */
	private final Method method;
	
	/**
	 * The container
	 */
	private final HorvikContainer container;
	
	/**
	 * The collection of qualifiers
	 */
	private final Set<Class<? extends Annotation>> qualifiers;

	/**
	 * Constructor
	 * 
	 * @param observedType
	 * @param bean
	 * @param container
	 */
	public ObserverMethod(Class<T> observedType, Bean<?> bean, Method method, HorvikContainer container) {
		this.bean = bean;
		this.method = method;
		this.observedType = observedType;
		this.container = container;
		this.qualifiers = loadQualifiers();
	}

	/**
	 * Calls the method and notifies the listener that an event has occured
	 * 
	 * @param event
	 * @param contextual
	 */
	public void notify(T event, Contextual contextual) {
		try {
			if (bean == null) {
				throw new HorvikException("no bean found for " + method.getDeclaringClass());
			}
			Object bean_instance = container.getInjectedReference(bean, contextual);
			Set<Object> parameters = new LinkedHashSet<>();
			for (Parameter parameter : method.getParameters()) {
				if (!parameter.isAnnotationPresent(Observes.class)) {
					parameters.add(container.getInjectedReference(container.getBean(parameter.getType()), contextual));
				} else {
					parameters.add(event);
				}
			}
			method.invoke(bean_instance, parameters.toArray(new Object[0]));
		} catch (Exception ex) {
			throw new HorvikException("could not call observer method", ex);
		}
	}

	/**
	 * Obtains the bean class of the bean that declares the observer method.
	 * 
	 * @return
	 */
	public Bean<?> getBean() {
		return bean;
	}

	/**
	 * Obtains the observed event type.
	 * 
	 * @return
	 */
	public Class<T> getObservedType() {
		return observedType;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Class<? extends Annotation>> getQualifiers() {
		return qualifiers;
	}

	/**
	 * Loads the qualifiers
	 * 
	 * @return
	 */
	private Set<Class<? extends Annotation>> loadQualifiers() {
		Parameter annotated_parameter = null;
		for (Parameter parameter : method.getParameters()) {
			if (parameter.isAnnotationPresent(Observes.class)) {
				annotated_parameter = parameter;
			}
		}
		return Arrays.stream(annotated_parameter.getAnnotations()).filter(annotation -> container.isQualifier(annotation))
				.map(annotation -> annotation.annotationType()).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return "ObserverMethod [bean=" + bean + ", observedType=" + observedType + ", method=" + method + "]";
	}
	
}
