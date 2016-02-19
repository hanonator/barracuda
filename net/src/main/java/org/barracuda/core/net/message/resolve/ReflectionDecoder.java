package org.barracuda.core.net.message.resolve;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.definition.Attribute;
import org.barracuda.core.net.message.definition.AttributeDefinition;

public class ReflectionDecoder implements MessageDecoder {

	/**
	 * The collection of decoders for the given object
	 */
	private final Queue<ElementDecoder> decoders = new LinkedList<>();

	/**
	 * The raw type of the class that needs to be created
	 */
	private final Class<?> type;

	/**
	 * 
	 * @param attributes
	 * @param type
	 */
	public ReflectionDecoder(AttributeDefinition[] attributes, Class<?> type) {
		this.type = type;
		for (AttributeDefinition definition : attributes) {
			decoders.add(new ElementDecoder(definition));
		}
	}

	/**
	 * 
	 * @param attributes
	 * @param type
	 */
	public ReflectionDecoder(Attribute[] attributes, Class<?> type) {
		this.type = type;
		for (Attribute definition : attributes) {
			decoders.add(new ElementDecoder(definition));
		}
	}

	@Override
	public Object decode(Message message) throws Exception {
		Constructor<?> constructor = type.getDeclaredConstructor(new Class<?>[0]);
		Object object = constructor.newInstance(new Object[0]);
		for (ElementDecoder decoder : decoders) {
			decoder.decode(message, object, type);
		}
		return object;
	}

	/**
	 * The element decoder
	 * 
	 * @author brock
	 *
	 */
	private static class ElementDecoder {
		
		/**
		 * The attribute
		 */
		private final AttributeDefinition attribute;

		/**
		 * Constructor
		 * 
		 * @param attribute
		 */
		public ElementDecoder(AttributeDefinition attribute) {
			this.attribute = attribute;
		}

		/**
		 * Constructor
		 * 
		 * @param attribute
		 */
		public ElementDecoder(Attribute attribute) {
			this.attribute = new AttributeDefinition(attribute);
		}
		
		/**
		 * 
		 * @param message
		 * @param partial
		 * @param message
		 * @throws SecurityException 
		 * @throws NoSuchFieldException 
		 * @throws IllegalAccessException 
		 * @throws IllegalArgumentException 
		 */
		public void decode(Message message, Object partial, Class<?> type) throws Exception {
			Field field = type.getDeclaredField(attribute.getField());
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(partial, attribute.getType().extract(message));
		}
		
	}

}
