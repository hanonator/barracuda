package org.barracuda.model.actor.sync;

import java.util.HashSet;
import java.util.Set;

import org.barracuda.model.actor.Player;

/**
 * The player's camera containing all of the visible entities/nodes
 * 
 * @author brock
 *
 */
public class Camera {

	/**
	 * The collection of visible players
	 */
	private final Set<Player> visiblePlayers = new HashSet<>();

	/**
	 * @return the visiblePlayers
	 */
	public Set<Player> getVisiblePlayers() {
		return visiblePlayers;
	}

}
