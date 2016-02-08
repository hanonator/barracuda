package org.barracuda.model.actor.player;

/**
 * 
 * 
 * @author brock
 *
 */
public enum Privilege {
	
	/**
	 * A regular player is a player who can play the game without
	 * any extra privileges
	 */
	NONE,
	
	/**
	 * A moderator is a player who can play the game, has a gray crown
	 * next to his name and has elevated privileges.
	 */
	ELEVATED,
	
	/**
	 * An administrator cannot play the game regularly, has a yellow crown
	 * next to his name and has full privileges
	 */
	FULL;
}
