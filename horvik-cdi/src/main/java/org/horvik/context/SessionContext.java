package org.horvik.context;

import java.lang.annotation.Annotation;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

public class SessionContext implements Context {

	@Override
	public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		return null;
	}

	@Override
	public <T> T get(Contextual<T> contextual) {
		return null;
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return SessionScoped.class;
	}

}
