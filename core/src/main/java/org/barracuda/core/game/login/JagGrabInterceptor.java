package org.barracuda.core.game.login;

import org.barracuda.core.game.GameSession;
import org.barracuda.core.game.login.model.JagGrabFileRequest;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.event.Observes;

@RequestScoped
@Discoverable
public class JagGrabInterceptor implements Interceptor<Message, JagGrabFileRequest> {

	@Override
	public JagGrabFileRequest intercept(Message input, GameSession session) {
		return null;
	}
	
	/**
	 * Called when the file request has been passed through to the channel
	 * 
	 * @param request
	 */
	public void on_request(@Observes JagGrabFileRequest request) {
		
	}

}
