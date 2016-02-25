package org.barracuda.core.net.event;

import org.barracuda.core.security.ISAACPair;

/**
 * The authentication block
 * 
 * @author brock
 */
public class Authentication {

	/**
	 * The request id used to identify what action the player wants to perform
	 * during handshake
	 */
	public static final int REQUEST_ID = 14;

	/**
	 * The player's username (This can different from the displayname)
	 */
	private final String username;

	/**
	 * The password, used as plain text here
	 */
	private final String password;

	/**
	 * The ISAAC pair for decoding/encoding packets
	 */
	private final ISAACPair cipher;

	/**
	 * Constructor
	 * 
	 * @param username
	 * @param password
	 * @param cipher
	 */
	public Authentication(String username, String password, ISAACPair cipher) {
		this.username = username;
		this.password = password;
		this.cipher = cipher;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the cipher
	 */
	public ISAACPair getCipher() {
		return cipher;
	}

}