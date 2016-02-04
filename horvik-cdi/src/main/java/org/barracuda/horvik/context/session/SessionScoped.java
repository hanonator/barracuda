package org.barracuda.horvik.context.session;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.barracuda.horvik.context.Scope;

@Scope
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionScoped { }
