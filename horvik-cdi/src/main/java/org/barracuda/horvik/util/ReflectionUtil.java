/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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