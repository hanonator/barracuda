package org.barracuda.model.actor.player;

public class Credentials {

	/**
	 * The player's username
	 */
	private final String username;
	
	/**
	 * The player's displayname
	 */
	private final String displayname;
	
	/**
	 * The password stored as a hash
	 */
	private final String password;

	/**
	 * Constructor
	 * 
	 * @param username
	 * @param displayname
	 * @param password
	 */
	public Credentials(String username, String displayname, String password) {
		this.username = username;
		this.displayname = displayname;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public String getPassword() {
		return password;
	}

}
