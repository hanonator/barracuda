package org.barracuda.model.actor.sync;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.barracuda.model.actor.Actor;


@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Synchronizes {

	/**
	 * 
	 * @return
	 */
	Class<? extends Actor> value();

}
