package org.barracuda.core.net.message.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {

	/**
	 * Name of the field that has to be set to the given value
	 * 
	 * @return
	 */
	String field();
	
	/**
	 * The type of field
	 * 
	 * @return
	 */
	AttributeType type();

}
