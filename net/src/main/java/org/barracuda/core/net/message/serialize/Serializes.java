package org.barracuda.core.net.message.serialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Serializes {

	/**
	 * The class that this intercepter will serialize in order to send to the
	 * client
	 * 
	 * @return
	 */
	Class<?> value();

}