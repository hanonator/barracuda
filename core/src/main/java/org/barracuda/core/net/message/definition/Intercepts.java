package org.barracuda.core.net.message.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Intercepts {

	/**
	 * Id of the packet the intercepter will intercept
	 * 
	 * @return
	 */
	int value();

}
