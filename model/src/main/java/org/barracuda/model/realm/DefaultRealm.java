package org.barracuda.model.realm;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.model.EntityList;
import org.barracuda.model.actor.NPC;
import org.barracuda.model.actor.Player;

/**
 * Standard implementation of the Realm interface that stores the 2
 * main types of entities into an entity list
 * 
 * @author brock
 *
 */
@Discoverable(Realm.class)
@ApplicationScoped
public class DefaultRealm implements Realm {

	/**
	 * The players that have been registered to this realm
	 */
	private final EntityList<Player> players = new EntityList<>(2046, Player.class);

	/**
	 * The npcs that have been registered to this realm
	 */
	private final EntityList<NPC> npcs = new EntityList<>(16382, NPC.class);

	@Override
	public EntityList<Player> getPlayers() {
		return players;
	}

	@Override
	public EntityList<NPC> getNpcs() {
		return npcs;
	}

}
