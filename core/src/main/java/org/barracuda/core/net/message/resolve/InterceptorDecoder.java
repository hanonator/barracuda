package org.barracuda.core.net.message.resolve;

import org.barracuda.core.game.GameSession;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;

/**
 * A proxy class that will allow an interceptor to decode a message
 * 
 * @author brock
 *
 * @param <T>
 */
public class InterceptorDecoder<T> implements MessageDecoder {

	/**
	 * The interceptor
	 */
	private final Interceptor<Message, T> interceptor;

	/**
	 * Constructor
	 * 
	 * @param interceptor
	 */
	public InterceptorDecoder(Interceptor<Message, T> interceptor) {
		this.interceptor = interceptor;
	}

	@Override
	public Object decode(Message message, GameSession session) throws Exception {
		return interceptor.intercept(message, session);
	}

}
