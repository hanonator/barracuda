package org.barracuda.model.realm;

import java.util.Iterator;
import java.util.Set;

import org.barracuda.model.actor.NPC;
import org.barracuda.model.actor.Player;

public interface Realm {
	
	/**
	 * Returns all of the players present in the realm
	 * 
	 * @return
	 */
	Iterator<Player> getPlayers();

	/**
	 * Gets a player by his unique player id
	 * 
	 * @param id
	 * @return
	 */
	Player getPlayer(int id);
	
	/**
	 * Gets a player by display name
	 * 
	 * @param name
	 * @return
	 */
	Player getPlayer(String name);
	
	/**
	 * Registers the new player
	 * 
	 * @param player
	 */
	void register(Player player);
	
	/**
	 * Removes the player
	 * 
	 * @param player
	 */
	void unregister(Player player);
	
	/**
	 * Returns all of the npcs present in the realm
	 * 
	 * @return
	 */
	Iterator<NPC> getNpcs();

	/**
	 * Gets an npc by their unique npc id
	 * 
	 * @param id
	 * @return
	 */
	NPC getNpc(int id);
	
	/**
	 * Gets a set of NPC's by their type
	 * 
	 * @param type
	 * @return
	 */
	Set<NPC> getNpcs(int type);
	
	/**
	 * Registers the new player
	 * 
	 * @param player
	 */
	void register(NPC npc);
	
	/**
	 * Removes the player
	 * 
	 * @param player
	 */
	void unregister(NPC npc);

}
