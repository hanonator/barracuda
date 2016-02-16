package org.barracuda.horvik.util;

import org.barracuda.horvik.HorvikException;

/**
 * Utility class for static reflection-type operations
 *
 * @author Pete Muir
 * @author Ales Justin
 * @author Marko Luksa
 */
public class ReflectionUtil {

	/**
	 * Casts the object to the requested type
	 * 
	 * @param obj
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }
    
    /**
     * Forcefully creates a new instance of the given generic type
     * 
     * @param type
     * @param target_type
     * @return
     */
    public static <T> T createForcedType(Class<?> type, Class<T> target_type) {
    	try {
    		return target_type.cast(type.newInstance());
    	} catch (Exception ex) {
    		throw new HorvikException("could not create forced type", ex);
    	}
    }

}