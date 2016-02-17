package org.barracuda.core.game.v317.login;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.crypto.Cipher;

import org.barracuda.core.game.util.VersionMetaData;
import org.barracuda.core.net.ByteBufferUtil;
import org.barracuda.core.net.event.Authentication;
import org.barracuda.core.net.interceptor.Authenticator;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.security.ISAACPair;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.model.actor.player.misc.CRCTable;
import org.barracuda.model.actor.player.misc.Detail;

import io.netty.buffer.ByteBuf;

@RequestScoped
@Discoverable
@Authenticator
public class AuthenticationInterceptor implements Interceptor<Message, Authentication> {

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
	 * TODO: Find a way to process the variables sent by the client and handle them
	 * appropriately
	 */
	@Override
	@SuppressWarnings("unused")
	public Authentication intercept(Message input) {
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

}