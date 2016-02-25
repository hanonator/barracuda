package org.barracuda.model.realm;

import org.barracuda.model.EntityList;
import org.barracuda.model.actor.NPC;
import org.barracuda.model.actor.Player;

/**
 * The realm
 * 
 * @author brock
 *
 */
public interface Realm {
	
	/**
	 * The collection of players
	 */
	EntityList<Player> getPlayers();
	
	/**
	 * The collection of npcs
	 * 
	 * @return
	 */
	EntityList<NPC> getNpcs();

}
