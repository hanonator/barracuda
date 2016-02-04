package org.barracuda.core.net.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Intercepts {

	/**
	 * When this is not null, the intercepter looks for messages that have this
	 * number as opcode
	 * 
	 * @return
	 */
	int opcode() default -1;
	
	/**
	 * The length of the packet
	 * 
	 * @return
	 */
	int length() default 0;

}