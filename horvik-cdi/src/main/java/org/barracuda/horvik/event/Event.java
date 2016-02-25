package org.barracuda.horvik.event;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.barracuda.horvik.context.Contextual;

public class Event<T> {
	
	/**
	 * The collection of observer methods
	 */
	private final Set<ObserverMethod<T>> observer_methods;
	
	/**
	 * 
	 * @param observedType
	 * @param observers
	 */
	public Event(Class<T> observedType, Set<ObserverMethod<T>> observers) {
		this.observer_methods = observers;
	}

	/**
	 * Adds an observer to the list
	 * 
	 * @param observer
	 */
	public void addObserver(ObserverMethod<T> observer) {
		this.observer_methods.add(observer);
	}

	/**
	 * Fires an event with the specified qualifiers and notifies observers.
	 * 
	 * @param event
	 */
	public void fire(T event) {
		this.fire(event, null);
	}

	/**
	 * Fires an event with the specified qualifiers and notifies observers.
	 * 
	 * @param event
	 */
	public void fire(T event, Contextual contextual) {
		observer_methods.forEach(consumer -> consumer.notify(event, contextual));
	}

	/**
	 * Obtains a child Event for the given additional required qualifiers.
	 * 
	 * @param subtype
	 * @param qualifiers
	 * @return
	 */
	@SafeVarargs
	@SuppressWarnings("unchecked")
	public final <U extends T> Event<U> select(Class<U> subtype, Class<? extends Annotation>... qualifier) {
		Set<ObserverMethod<U>> methods = new HashSet<>();
		for (ObserverMethod<T> method : observer_methods) {
			if (method.getObservedType().isAssignableFrom(subtype) && method.getQualifiers().containsAll(Arrays.asList(qualifier))) {
				methods.add((ObserverMethod<U>) method);
			}
		}
		return new Event<>(subtype, methods);
	}

}