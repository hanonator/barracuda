package org.horvik.bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.NormalScope;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Stereotype;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.InjectionTargetFactory;
import javax.enterprise.inject.spi.ObserverMethod;
import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.interceptor.InterceptorBinding;

import org.horvik.bean.inject.annotated.AnnotatedElementFactory;

/**
 * 
 * 
 * @author brock
 *
 */
public class BeanManager {

	/**
	 * The collection of beans
	 */
	private final Set<Bean<?>> beans = new HashSet<>();
	
	/**
	 * The factory that creates and resolves all of the annotated elements
	 */
	private final AnnotatedElementFactory factory = new AnnotatedElementFactory(this);

	/**
	 * Adds a bean to the collection of beans
	 * 
	 * @param bean
	 */
	public void addBean(Bean<?> bean) {
		this.beans.add(bean);
	}

	/**
	 * Fire an event and notify observers.
	 * 
	 * @param event
	 * @param qualifiers
	 */
	public void fireEvent(Object event, Annotation... qualifiers) {
		resolveObserverMethods(event, qualifiers).stream().forEach(observer_method -> observer_method.notify(event));
	}

	/**
	 * Return the set of observers for an event.
	 * 
	 * @param event
	 * @param qualifiers
	 * @return
	 */
	public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers) {
		return null;
	}
	
	/**
	 * Obtains an active context object for the given scope .
	 * @param scopeType
	 * @return
	 */
	public Context getContext(Class<? extends Annotation> scopeType) {
		return null;
	}
	
	/**
	 * Obtains a container provided implementation of InjectionPoint for the given AnnotatedField.
	 * 
	 * @param field
	 * @return
	 */
	public InjectionPoint createInjectionPoint(AnnotatedField<?> field) {
		return null;
	}
	
	/**
	 * Obtains a container provided implementation of InjectionPoint for the given AnnotatedParameter.
	 * 
	 * @param parameter
	 * @return
	 */
	public InjectionPoint createInjectionPoint(AnnotatedParameter<?> parameter) {
		return null;
	}
	
	/**
	 * Obtains an InjectionTarget for the given AnnotatedType.
	 * @param type
	 * @return
	 */
	public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type) {
		return null;
	}
	
	
	/**
	 * Obtains a contextual reference for a certain bean and a certain bean type of the bean.
	 * 
	 * @param bean
	 * @param beanType
	 * @param ctx
	 * @return
	 */
//	public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx) {
//		return null;
//	}
	
	
	/**
	 * Obtains a contextual reference for a certain bean and a certain bean type of the bean.
	 * 
	 * @param bean
	 * @param beanType
	 * @param ctx
	 * @return
	 */
	public <T> T getReference(Bean<T> bean, Class<T> beanType, CreationalContext<T> ctx) {
		return createInjectionTarget(createAnnotatedType(beanType)).produce(ctx);
	}
	
	/**
	 * Obtains a Bean for the given BeanAttributes, bean class and InjectionTarget.
	 * 
	 * @param attributes
	 * @param beanClass
	 * @param injectionTargetFactory
	 * @return
	 */
	public <T> Bean<T> createBean(BeanAttributes<T> attributes, Class<T> beanClass, InjectionTargetFactory<T> injectionTargetFactory) {
		return null;
	}

	/**
	 * Obtain an AnnotatedType that may be used to read the annotations of the given class or interface.
	 * 
	 * @param type
	 * @return
	 */
	public <T> AnnotatedType<T> createAnnotatedType(Class<T> type) {
		return factory.newAnnotatedType(type);
	}
	
	/**
	 * Apply the ambiguous dependency resolution rules to a set of beans.
	 * 
	 * FIXME: Currently gets the first available bean
	 * 
	 * @param beans
	 * @return
	 */
	public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans) {
		return beans.iterator().next();
	}

	/**
	 * Return the set of beans which have the given EL name and are available
	 * for injection in the module or library containing the class into which
	 * the BeanManager was injected or the Java EE component from whose JNDI
	 * environment namespace the BeanManager was obtained, according to the
	 * rules of EL name resolution.
	 * 
	 * @param name
	 * @return
	 */
	public Set<Bean<?>> getBeans(String name) {
		return beans.stream().filter(bean -> bean.getName().equals(name)).collect(Collectors.toSet());
	}

	/**
	 * Return the set of beans which have the given required type and qualifiers
	 * and are available for injection in the module or library containing the
	 * class into which the BeanManager was injected or the Java EE component
	 * from whose JNDI environment namespace the BeanManager was obtained,
	 * according to the rules of typesafe resolution.
	 * 
	 * @param beanType
	 * @param qualifiers
	 * @return
	 */
	public Set<Bean<?>> getBeans(Class<?> beanType, Annotation... qualifiers) {
		return beans.stream().filter(bean -> bean.getTypes().contains(beanType)).collect(Collectors.toSet()); // TODO: Qualifiers
	}
	
	/**
	 * Returns true when the given type matches with all the given qualifiers
	 * 
	 * @param type
	 * @param qualifiers
	 * @return
	 */
	protected boolean qualifies(Class<?> type, Annotation... qualifiers) {
		return !Arrays.stream(qualifiers).anyMatch(annotation -> !type.isAnnotationPresent(annotation.annotationType()));
	}

	/**
	 * Test the given annotation type to determine if it is an interceptor
	 * binding type.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isInterceptorBinding(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(InterceptorBinding.class);
	}

	/**
	 * Test the given annotation type to determine if it is a normal scope type.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isNormalScope(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(NormalScope.class);
	}

	/**
	 * Test the given annotation type to determine if it is a passivating scope
	 * type.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isPassivatingScope(Class<? extends Annotation> annotationType) {
		return isNormalScope(annotationType) && annotationType.getAnnotation(NormalScope.class).passivating();
	}

	/**
	 * Test the given annotation type to determine if it is a qualifier type.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isQualifier(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Qualifier.class);
	}

	/**
	 * Test the given annotation type to determine if it is a scope type.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isScope(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(NormalScope.class) || annotationType.isAnnotationPresent(Scope.class);
	}

	/**
	 * Test the given annotation type to determine if it is a stereotype.
	 * 
	 * @param annotationType
	 * @return
	 */
	public boolean isStereotype(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Stereotype.class);
	}

}
