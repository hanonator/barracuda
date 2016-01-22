package org.horvik;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.horvik.bean.BeanManager;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

/**
 * 
 * @author brock
 *
 */
public class HorvikContainer {

	/**
	 * The reflections object
	 */
	private final Reflections reflections;
	
	/**
	 * The bean manager
	 */
	private final BeanManager manager = new BeanManager();

	/**
	 * The container
	 */
	HorvikContainer(String classpath) {
		this.reflections = new Reflections(classpath, new MethodAnnotationsScanner(), new SubTypesScanner(), new MethodParameterScanner(), new TypeAnnotationsScanner());
	}

	/**
	 * Get types annotated with a given annotation, both classes and annotations 
	 * 
	 * @param annotation
	 * @return
	 */
	public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
		return reflections.getTypesAnnotatedWith(annotation, true);
	}

	/**
	 * Gets the bean manager
	 * 
	 * @return
	 */
	public BeanManager getManager() {
		return manager;
	}

}
