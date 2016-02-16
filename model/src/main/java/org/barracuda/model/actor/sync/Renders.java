package org.barracuda.model.actor.sync;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.sync.attribute.Attribute;

/**
 * TODO: Move away from this annotation and add values as abstract methods
 * 
 * @author brock
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Renders {
	
	/**
	 * Attribute the class will render out
	 * 
	 * @return
	 */
	Class<? extends Attribute> attribute();
	
	/**
	 * Attribute the class will render out
	 * 
	 * @return
	 */
	Class<? extends Actor> entity();
	
}
