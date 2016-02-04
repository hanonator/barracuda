package org.barracuda.horvik.inject;

import java.lang.reflect.Field;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.HorvikException;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Contextual;

public class FieldInjectionPoint<T> implements InjectionPoint<T> {

	/**
	 * The field that needs to be injected
	 */
	private final Field field;
	
	/**
	 * The type
	 */
	@SuppressWarnings("unused")
	private final Class<T> type;

	public FieldInjectionPoint(Field field, Class<T> type) {
		this.field = field;
		this.type = type;
	}

	@Override
	public void inject(HorvikContainer container, Bean<T> bean, Object instance, Contextual contextual) {
		try {
			Class<?> field_class = field.getType();
			Bean<?> field_bean = container.getBean(field_class);
			Object field_instance = container.getInjectedReference(field_bean, contextual);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(instance, field_instance);
		} catch (Exception ex) {
			throw new HorvikException("error injecting field", ex);
		}
	}

}
