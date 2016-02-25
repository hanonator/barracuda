package org.barracuda.horvik.inject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.resource.IntegerResourceParser;
import org.barracuda.horvik.bean.resource.ResourceParser;
import org.barracuda.horvik.bean.resource.StringResourceParser;
import org.barracuda.horvik.context.Contextual;

@SuppressWarnings("unused")
public class ResourceInjectionPoint<T> implements InjectionPoint<T> {

	/**
	 * 
	 */
	private static final Map<Class<?>, ResourceParser<?>> parsers = new HashMap<>();

	static {
		parsers.put(Integer.class, new IntegerResourceParser());
		parsers.put(String.class, new StringResourceParser());
	}
	
	/**
	 * The collection of resources
	 */
	private final Map<String, String> resources;
	
	/**
	 * The field
	 */
	private final Field field;

	/**
	 * Constructor
	 * 
	 * @param resources
	 * @param field
	 */
	public ResourceInjectionPoint(Map<String, String> resources, Field field) {
		this.resources = resources;
		this.field = field;
	}

	@Override
	public void inject(HorvikContainer container, Bean<T> bean, Object instance, Contextual contextual) {
//		try {
//			Resource resource = field.getDeclaredAnnotation(Resource.class);
//			if (!resources.containsKey(resource.name())) {
//				throw new IllegalArgumentException("resource " + resource.name() + " is undefined.");
//			}
//			field.setAccessible(true);
//			field.set(instance, parsers.get(field.getType()).parse(resources.get(resource.name())));
//		} catch (Exception ex) {
//			throw new HorvikException("could not inject resource for " + field.getName() + " in "+ bean.getJavaClass().getName(), ex);
//		}
		// TODO: fixme
	}

}
