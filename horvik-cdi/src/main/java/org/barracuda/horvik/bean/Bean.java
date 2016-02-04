package org.barracuda.horvik.bean;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.barracuda.horvik.inject.InjectionPoint;

public interface Bean<T> {

	/**
	 * Gets the java class
	 * 
	 * @return
	 */
	Class<T> getJavaClass();

	/**
	 * The collection of injection points
	 * 
	 * @return
	 */
	Set<InjectionPoint<T>> getInjectionPoints();

	/**
	 * The scope of the bean
	 * 
	 * @return
	 */
	Class<? extends Annotation> getScope();

}
