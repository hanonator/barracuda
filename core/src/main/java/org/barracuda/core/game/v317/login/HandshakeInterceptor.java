package org.barracuda.core.game.v317.login;

import org.barracuda.core.net.event.Authentication;
import org.barracuda.core.net.event.Handshake;
import org.barracuda.core.net.event.Handshake.RequestType;
import org.barracuda.core.net.interceptor.Handshaker;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;

@RequestScoped
@Discoverable
@Handshaker
public class HandshakeInterceptor implements Interceptor<Message, Handshake> {

	@Override
	public Handshake intercept(Message input) {
		/*
		 * The id of the requested resource
		 */
		int request_id = input.getPayload().getBuffer().readUnsignedByte();
		
		/*
		 * The request id must be
		 */
		if (request_id != 14 && request_id != 15) {
			throw new IllegalStateException("request id must be 14 or 15");
		}
		
		/*
		 * junk data?
		 */
		int junk$0 = input.getPayload().getBuffer().readUnsignedByte();
		
		/*
		 * Construct the hand shake
		 */
		return new Handshake(junk$0, request_id == Authentication.REQUEST_ID ? RequestType.AUTHENTICATION : RequestType.CLIENT_UPDATE);
	}

}
