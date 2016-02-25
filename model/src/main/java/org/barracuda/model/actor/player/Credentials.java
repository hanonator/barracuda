package org.barracuda.model.actor.player;

/**
 * The player's credentials with which he plays the game
 * 
 * @author brock
 *
 */
public class Credentials {

	/**
	 * The player's username
	 */
	private final String username;
	
	/**
	 * The player's display name
	 */
	private final String displayname;

	/**
	 * The type the player has
	 */
	private final Privilege type;

	/**
	 * Constructor
	 * 
	 * @param username
	 * @param displayname
	 * @param type
	 */
	public Credentials(String username, String displayname, Privilege type) {
		this.username = username;
		this.displayname = displayname;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public Privilege getType() {
		return type;
	}
	
}
