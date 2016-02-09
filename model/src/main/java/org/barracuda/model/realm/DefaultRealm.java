package org.barracuda.model.realm;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
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
	public Iterator<Player> getPlayers() {
		return players.iterator();
	}

	@Override
	public Player getPlayer(int id) {
		return players.get(id);
	}

	@Override
	public Player getPlayer(String name) {
		return players.get().filter(player -> player.getCredentials().getDisplayname().equals(name)).findAny().orElse(null);
	}

	@Override
	public void register(Player player) {
		player.setIndex(players.getIndexPool().claim());
		players.add(player);
	}

	@Override
	public void unregister(Player player) {
		players.getIndexPool().release(player.getIndex());
		players.remove(player);
	}
	@Override
	public Iterator<NPC> getNpcs() {
		return npcs.iterator();
	}

	@Override
	public NPC getNpc(int id) {
		return npcs.get(id);
	}

	@Override
	public Set<NPC> getNpcs(int type) {
		return npcs.get().filter(npc -> npc.getType() == type).collect(Collectors.toSet());
	}

	@Override
	public void register(NPC npc) {
		npc.setIndex(npcs.getIndexPool().claim());
		npcs.add(npc);
	}

	@Override
	public void unregister(NPC npc) {
		npcs.getIndexPool().release(npc.getIndex());
		npcs.remove(npc);
	}

}
