package org.barracuda.core.game.event;

import org.barracuda.model.actor.Player;

public class PlayerInitialized {

	/**
	 * The player that has just been initialized
	 */
	private final Player player;

	/**
	 * Constructor
	 * 
	 * @param player
	 */
	public PlayerInitialized(Player player) {
		this.player = player;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
