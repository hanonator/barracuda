package org.horvik.session;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

public class Session implements Contextual<Object> {

	@Override
	public Object create(CreationalContext<Object> creationalContext) {
		return null;
	}

	@Override
	public void destroy(Object instance, CreationalContext<Object> creationalContext) {
		
	}

}
