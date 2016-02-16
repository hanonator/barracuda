package org.barracuda.core.game.v317.login;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.crypto.Cipher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.game.GameSession;
import org.barracuda.core.game.v317.login.model.Authentication;
import org.barracuda.core.game.v317.login.model.AuthenticationResponse;
import org.barracuda.core.game.v317.login.model.VersionMetaData;
import org.barracuda.core.net.ByteBufferUtil;
import org.barracuda.core.net.Channel;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.security.ISAACPair;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.player.Credentials;
import org.barracuda.model.actor.player.Privilege;
import org.barracuda.model.actor.player.misc.CRCTable;
import org.barracuda.model.actor.player.misc.Detail;
import org.barracuda.model.realm.Realm;

import io.netty.buffer.ByteBuf;

@RequestScoped
@Discoverable
public class AuthenticationInterceptor implements Interceptor<Message, Authentication> {

	/**
	 * The static logger instance for this class
	 */
	private static final Logger logger = LogManager.getLogger(AuthenticationInterceptor.class);

	/**
	 * The cipher object used to decrypt the RSA encrypted block
	 * 
	 * TODO: Needs to be implemented
	 */
	@SuppressWarnings("unused")
	private static Cipher cipher = null;
	
	/**
	 * The public key for the RSA decoder
	 * 
	 * TODO: Needs to be implemented
	 */
	@Resource(name = "service.cryption.rsa_key")
	private static BigInteger rsa_key = null;
	
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
	 * TODO: Find a way to process the variables sent by the client and handle them
	 * appropriately
	 */
	@Override
	@SuppressWarnings("unused")
	public Authentication intercept(Message input, GameSession session) {
		ByteBuf payload = input.getPayload().getBuffer();
		
		/*
		 * The identifier used to determine if the player has connected or
		 * reconnected. This can be used in determining whether or not a flag is
		 * sent to the user that clears some historical data
		 * 
		 * This needs to be either 16 (for a fresh connection) or 18 (for a
		 * reconnect attempt).
		 */
		int identifier = payload.readByte();
		
		/*
		 * Size of the RSA encrypted block.
		 */
		int rsa_block_size = payload.readUnsignedByte();
		
		/*
		 * Gets the client version meta data. This consists of major and minor release version.
		 */
		VersionMetaData version = new VersionMetaData(payload.readByte(), payload.readShort());
		
		/*
		 * The detail of the client. This value should only be 0 or 1. If 0, the
		 * client is run in low detail otherwise it is assumed the client is
		 * high detail
		 */
		Detail detail = payload.readByte() == 0 ? Detail.LOW : Detail.HIGH;
		
		/*
		 * Reads the archive's CRC keys from the client and matches those to those found
		 * in the cache loaded by the server.
		 */
		CRCTable crc_table = CRCTable.extract(payload);
		
		/*
		 * Gets the size of the credential block consisting of the username and password.
		 * This is used in the RSA decoding
		 */
		int credential_block_size = payload.readByte();

		/*
		 * The secure id. This needs to be 10. I have no idea what this 
		 */
		int secure_id = payload.readByte();
		
		/*
		 * The ISAAC keys.
		 */
		ISAACPair isaac_pair = new ISAACPair(payload.readLong(), payload.readLong());

		/*
		 * The client's uuid
		 */
		int uuid = payload.readInt();
		
		/*
		 * The player's credentials
		 */
		String username = ByteBufferUtil.readString(payload);
		String password = ByteBufferUtil.readString(payload);
		
		/*
		 * Create the authentication object
		 */
		return new Authentication(username, password, isaac_pair);
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
		Player player = new Player(horvik);
		
		/*
		 * Load the player's account properties
		 */
		player.setCredentials(new Credentials(authentication.getUsername(), authentication.getUsername(), Privilege.FULL));
		
		/*
		 * Register the player with the realm
		 */
		realm.getPlayers().register(player);
		
		/*
		 * Debug information
		 */
		logger.debug("session {} - user.username: {}", session.getId(), authentication.getUsername());
		logger.debug("session {} - user.password: {}", session.getId(), authentication.getPassword());
		logger.debug("session {} - user.id: {}", session.getId(), player.getIndex());
		logger.debug("session {} - cipher.server_key: {}", session.getId(), authentication.getCipher().getServerKey());
		logger.debug("session {} - cipher.client_key: {}", session.getId(), authentication.getCipher().getClientKey());
	}

}