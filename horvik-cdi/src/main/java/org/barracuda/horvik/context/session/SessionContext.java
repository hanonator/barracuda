package org.barracuda.horvik.context.session;

import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.HorvikException;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.context.Context;
import org.barracuda.horvik.context.Contextual;
import org.barracuda.horvik.inject.Instantiator;

public class SessionContext implements Context {

	/**
	 * The horvik container
	 */
	private final HorvikContainer container;
	
	/**
	 * Constructor
	 * @param container
	 */
	public SessionContext(HorvikContainer container) {
		this.container = container;
	}

	@Override
	public <T> T get(Contextual contextual, Bean<T> type) {
		if (contextual instanceof Session) {
			return ((Session) contextual).retrieve(type);
		}
		throw new HorvikException("Attempted to look up session bean without defined session");
	}

	@Override
	public <T> T create(Contextual contextual, Bean<T> type) {
		if (contextual instanceof Session) {
			Session session = (Session) contextual;
			Instantiator<T> instantiator = container.getInstantiator(type.getJavaClass());
			T instance = instantiator.instantiate(type.getJavaClass(), container);
			session.associate(type, instance);
			return instance;
		}
		throw new HorvikException("Attempted to look up session bean without defined session");
	}

}
