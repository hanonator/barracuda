package org.barracuda.core.game.login;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import javax.annotation.Resource;
import javax.crypto.Cipher;

import org.barracuda.core.game.GameSession;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.event.Observes;

@RequestScoped
@Discoverable
public class AuthenticationInterceptor implements Interceptor<Message, Authentication> {

	/**
	 * The cipher object used to decrypt the RSA encrypted block
	 * 
	 * TODO: Needs to be implemented
	 */
	@SuppressWarnings("unused")
	private static final Cipher cipher = null;
	
	/**
	 * The public key for the RSA decoder
	 * 
	 * TODO: Needs to be implemented
	 */
	@Resource(name = "service.cryption.rsa_key")
	private static final BigInteger rsa_key = null;

	@Override
	public Authentication intercept(Message input, GameSession session) {
		return null;
	}
	
	/**
	 * Called when the authentication has been passed through to the channel
	 * 
	 * @param authentication
	 */
	public void on_authentication(@Observes Authentication authentication) {
		System.out.println("Authentication received");
	}

}