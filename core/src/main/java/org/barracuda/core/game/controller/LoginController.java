package org.barracuda.core.game.controller;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.game.contract.login.AuthenticationResponse;
import org.barracuda.core.game.contract.login.HandshakeResponse;
import org.barracuda.core.net.Channel;
import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.event.Authentication;
import org.barracuda.core.net.event.Handshake;
import org.barracuda.core.net.event.PlayerDisconnected;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.player.Credentials;
import org.barracuda.model.actor.player.Privilege;
import org.barracuda.model.realm.Realm;

@Discoverable
@RequestScoped
public class LoginController {

	/**
	 * The static logger instance for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	/**
	 * The random number generator, used for generating a random server seed
	 */
	private static final Random random = new Random();
	
	/**
	 * The realm
	 */
	@Inject
	private Realm realm;
	
	/**
	 * The horvik instance
	 */
	@Inject
	private Horvik horvik;
	
	/**
	 * The session that has attempted to authenticate
	 */
	@Inject
	private Session session;
	
	/**
	 * The channel of the session that has attempted to authenticate
	 */
	@Inject
	private Channel channel;
	
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
	 * Called when the authentication has been passed through to the channel
	 * 
	 * @param authentication
	 */
	public void on_authentication(@Observes Authentication authentication) {
		/*
		 * Send request to the authentication server
		 */
		channel.writeAndFlush(new AuthenticationResponse());
		
		/*
		 * If the user has successfully authenticated, create a player object
		 */
		Player player = new Player(horvik, session, channel);
		
		/*
		 * Load the player's account properties
		 */
		player.setCredentials(new Credentials(authentication.getUsername(), authentication.getUsername(), Privilege.FULL));
		
		/*
		 * Register the player with the realm
		 */
		realm.getPlayers().register(player);
		
		/*
		 * Associate the player bean with the player
		 */
		session.associate(horvik.getContainer().getBean(Player.class), player);
		
		/*
		 * The player enters authentication
		 */
		channel.attr(ChannelState.ATTRIBUTE_KEY).set(ChannelState.GAME);
		
		/*
		 * Debug information
		 */
		logger.debug("session {} - user.username: {}", session.getId(), authentication.getUsername());
		logger.debug("session {} - user.password: {}", session.getId(), authentication.getPassword());
		logger.debug("session {} - user.id: {}", session.getId(), player.getIndex());
		logger.debug("session {} - cipher.server_key: {}", session.getId(), authentication.getCipher().getServerKey());
		logger.debug("session {} - cipher.client_key: {}", session.getId(), authentication.getCipher().getClientKey());
	}

	/**
	 * Unregisters the player from the realm on disconnect
	 * 
	 * @param disconnected
	 */
	public void on_disconnect(@Observes PlayerDisconnected disconnected, Player player) {
		realm.getPlayers().unregister(player);
	}

}
