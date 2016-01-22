package org.horvik.util;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class GenericsUtil {

	/**
	 * 
	 * @param baseType
	 * @param owningClass
	 * @return
	 */
	public static final Set<Type> getTypeClosure(Type baseType, Class<?> owningClass) {
		return extractBaseTypes((Class<?>) baseType, new HashSet<>());
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private static final Set<Type> extractBaseTypes(Class<?> type, Set<Type> types) {
		if (type.getSuperclass() != null) {
			types.addAll(extractBaseTypes(type.getSuperclass(), types));
		}
		if (type.getInterfaces() != null) {
			Arrays.stream(type.getInterfaces()).forEach(iface -> types.addAll(extractBaseTypes(iface, types)));
		}
		types.add(type);
		return types;
	}

}
