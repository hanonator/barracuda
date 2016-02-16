package org.barracuda.core.game.v317.login;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.game.GameSession;
import org.barracuda.core.game.v317.login.model.Authentication;
import org.barracuda.core.game.v317.login.model.Handshake;
import org.barracuda.core.game.v317.login.model.HandshakeResponse;
import org.barracuda.core.net.Channel;
import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;

@RequestScoped
@Discoverable
public class HandshakeInterceptor implements Interceptor<Message, Handshake> {
	
	/**
	 * The static logger instance for this class
	 */
	private static final Logger logger = LogManager.getLogger(HandshakeInterceptor.class);
	
	/**
	 * The random number generator, used for generating a random server seed
	 */
	private static final Random random = new Random();

	/**
	 * The channel to which we have to write
	 */
	@Inject
	private Channel channel;

	/**
	 * The session for debugging purposes
	 */
	@Inject
	private Session session;

	@Override
	public Handshake intercept(Message input, GameSession session) {
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
	
	/**
	 * Called when the handshake has been passed through to the channel
	 * 
	 * @param handshake
	 */
	public void on_handshake(@Observes Handshake handshake) {
		/*
		 * The server key (for ISAAC encryption)
		 */
		long server_key = random.nextLong();
		
		/*
		 * Write the handshake response
		 */
		channel.writeAndFlush(new HandshakeResponse(server_key));
		
		/*
		 * The player enters authentication
		 */
		channel.attr(ChannelState.ATTRIBUTE_KEY).set(ChannelState.AUTHENTICATION);
		
		/*
		 * Debug information
		 */
		logger.debug("session {} - request.type: {}", session.getId(), handshake.getRequestType());
		logger.debug("session {} - request.unknown: {}", session.getId(), handshake.getData());
		logger.debug("session {} - cipher.generated_key: {}", session.getId(), server_key);
	}

	/**
	 * 
	 * @author brock
	 *
	 */
	public static enum RequestType {
		
		/**
		 * Indicates the player wants to authenticate onto the server
		 */
		AUTHENTICATION,
		
		/**
		 * Indicates the player wants to update his client through the JAGGRAB protocol
		 */
		CLIENT_UPDATE;
	}

}
