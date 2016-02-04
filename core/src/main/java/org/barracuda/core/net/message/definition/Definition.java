package org.barracuda.core.net.message.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.barracuda.core.net.message.game.GameHeader.MetaData;

/**
 * Defines a bean
 * 
 * @author brock
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Definition {

	/**
	 * The attributes of the entity
	 * 
	 * @return
	 */
	Attribute[] attributes();
	
	/**
	 * The length of the message
	 * 
	 * @return
	 */
	int length();

	/**
	 * The opcode of the message received that parses into the object
	 * 
	 * @return
	 */
	int opcode();
	
	/**
	 * The meta data of the packet
	 * @return
	 */
	MetaData meta() default MetaData.EMPTY;

}
