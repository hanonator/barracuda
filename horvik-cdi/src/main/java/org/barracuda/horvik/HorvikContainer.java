package org.barracuda.horvik;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.Qualifier;
import org.barracuda.horvik.context.Context;
import org.barracuda.horvik.context.Contextual;
import org.barracuda.horvik.context.Scope;
import org.barracuda.horvik.inject.DefaultInjector;
import org.barracuda.horvik.inject.DefaultInstantiator;
import org.barracuda.horvik.inject.FieldInjectionPoint;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.horvik.inject.InjectionPoint;
import org.barracuda.horvik.inject.Injector;
import org.barracuda.horvik.inject.Instantiator;
import org.barracuda.horvik.inject.ResourceInjectionPoint;
import org.barracuda.horvik.util.ReflectionUtil;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import javassist.Modifier;

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
	 * The collection of beans;
	 */
	private final Map<Class<?>, Bean<?>> beans;
	
	/**
	 * The collection of contexts
	 */
	private final Map<Class<? extends Annotation>, Context> contexts;
	
	/**
	 * The collection of services
	 */
	private final Map<Class<?>, ? super Object> services;
	
	/**
	 * Collection of resources
	 */
	private final Map<String, String> resources = new HashMap<>();

	/**
	 * constructor
	 */
	HorvikContainer(String classpath) {
		this.beans = new HashMap<>();
		this.contexts = new HashMap<>();
		this.services = new HashMap<>();
		this.reflections = new Reflections(classpath, new MethodAnnotationsScanner(), new SubTypesScanner(), new MethodParameterScanner(), new TypeAnnotationsScanner());
	}
	
	/**
	 * Gets or creates a new instance of the bean and will then inject it with
	 * the correct references
	 * 
	 * @param type
	 * @return
	 */
	public <T> T getInjectedReference(Bean<T> bean, Contextual contextual) {
		try {
			Context context = this.getContext(bean.getScope());
			T instance = context.get(contextual, bean);
			if (instance == null) {
				instance = context.create(contextual, bean);
			}
			Injector<T> injector = getInjector(bean.getJavaClass());
			injector.inject(this, bean, instance, contextual);
			return instance;
		} catch (Exception ex) {
			throw new HorvikException("could not inject " + bean, ex);
		}
	}

	/**
	 * Gets the collection of injectionpoints for the given class
	 * 
	 * @param type
	 * @return
	 */
	public <T> Set<InjectionPoint<T>> getInjectionPoints(Class<T> type) {
		Set<InjectionPoint<T>> injection_points = new HashSet<>();
		for (Field field : type.getDeclaredFields()) {
			if (field.isAnnotationPresent(Inject.class) && !Modifier.isStatic(field.getModifiers())) {
				injection_points.add(new FieldInjectionPoint<>(field, type));
			}
			if (field.isAnnotationPresent(Resource.class)) {
				injection_points.add(new ResourceInjectionPoint<>(resources, field));
			}
		}
		return injection_points;
	}

	/**
	 * Gets the bean for the given class
	 * 
	 * @param type
	 * @return
	 */
	public <T> Bean<T> getBean(Class<T> type) {
		return ReflectionUtil.cast(beans.get(type));
	}
	
	/**
	 * Registers a new context with this container
	 * 
	 * @param annotation
	 * @param context
	 */
	public void addContext(Class<? extends Annotation> annotation, Context context) {
		contexts.put(annotation, context);
	}
	
	/**
	 * Registers a bean to this container
	 * 
	 * @param bean
	 */
	public void registerBean(Bean<?> bean) {
		this.beans.put(bean.getJavaClass(), bean);
	}
	
	/**
	 * Registers a bean to this container
	 * 
	 * @param bean
	 */
	public void registerBean(Class<?> alias, Bean<?> bean) {
		this.beans.put(alias, bean);
	}
	
	/**
	 * Retrieves the context for the given annotation
	 * 
	 * @param annotation
	 * @return
	 */
	public <C extends Context> C getContext(Class<? extends Annotation> annotation) {
		return ReflectionUtil.cast(contexts.get(annotation));
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
	 * Get types annotated with a given annotation, both classes and annotations 
	 * 
	 * @param annotation
	 * @return
	 */
	public Class<?> getTypeAnnotatedWith(Class<? extends Annotation> annotation) {
		return reflections.getTypesAnnotatedWith(annotation, true).iterator().next();
	}

	/**
	 * get methods with any parameter annotated with given annotation 
	 * 
	 * @param annotation
	 * @return
	 */
	public Set<Method> getMethodsWithAnyParamAnnotated(Class<? extends Annotation> annotation) {
		return reflections.getMethodsWithAnyParamAnnotated(annotation);
	}

	/**
	 * get all fields annotated with a given annotation 
	 * 
	 * @param annotation
	 * @return
	 */
	public Set<Field> getFieldsAnnotatedWith(Class<? extends Annotation> annotation) {
		return reflections.getFieldsAnnotatedWith(annotation);
	}
	
	/**
	 * gets all sub types in hierarchy of a given type depends on SubTypesScanner configured
	 * 
	 * @param type
	 * @return
	 */
	public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
		return reflections.getSubTypesOf(type);
	}
	
	/**
	 * Gets the set of available contexts
	 * 
	 * @return
	 */
	public Map<Class<? extends Annotation>, Context> getAvailableContexts() {
		return contexts;
	}

	/**
	 * Checks to see if the given annotation class defines a scope
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isScope(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Scope.class) || contexts.containsKey(annotationType);
	}

	/**
	 * Checks to see if the given annotation class defines a scope
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isQualifier(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Qualifier.class);
	}

	/**
	 * Checks to see if the given annotation class defines a scope
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isQualifier(Annotation annotationType) {
		return isQualifier(annotationType.annotationType());
	}
	
	/**
	 * 
	 * @param serviceClass
	 */
	public void registerService(Class<?> serviceClass, Object instance) {
		services.put(serviceClass, instance);
	}
	
	/**
	 * 
	 * @param serviceClass
	 * @return
	 */
	public <T> T getService(Class<T> serviceClass) {
		return ReflectionUtil.cast(services.get(serviceClass));
	}
	
	/**
	 * The injector for a given type
	 * 
	 * @param type
	 * @return
	 */
	public <T> Injector<T> getInjector(Class<T> type) {
		return new DefaultInjector<>();
	}
	
	/**
	 * The instantiator for a given type
	 * 
	 * @param type
	 * @return
	 */
	public <T> Instantiator<T> getInstantiator(Class<T> type) {
		return new DefaultInstantiator<>();
	}
	
	/**
	 * Adds a resource key and value pair
	 * 
	 * @param key
	 * @param value
	 */
	void addResource(String key, String value) {
		resources.put(key, value);
	}

	@Override
	public String toString() {
		return "HorvikContainer [beans=" + beans.size() + ", contexts=" + contexts.keySet() + ", services=" + services.keySet() + "]";
	}

}
