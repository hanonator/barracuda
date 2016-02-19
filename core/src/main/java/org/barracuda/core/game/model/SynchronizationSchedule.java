package org.barracuda.core.game.model;

import java.util.HashMap;
import java.util.Map;

import org.barracuda.core.game.v317.sync.PlayerSynchronizerImpl;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Synchronizer;
import org.barracuda.model.actor.sync.player.PlayerSynchronizationContext;
import org.barracuda.model.realm.Realm;
import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

/**
 * TODO: loops through the players 3 times each cycle
 * 
 * @author brock
 *
 */
@Discoverable
@ApplicationScoped
public class SynchronizationSchedule implements ClockWorker {

	/**
	 * The object responsible for the player synchronization
	 * 
	 * TODO: Depends on release-317 currently. This needs to be loaded from somwhere independent on implementation
	 */
	private final Synchronizer<Player, PlayerSynchronizationContext> playerSynchronizer = new PlayerSynchronizerImpl();

	/**
	 * The object responsible for the player synchronization
	 */
//	private final Synchronizer<NPC, NPCSynchronizationContext> npcSynchronizer = new NPCSynchronizer();

	/**
	 * The clock for this application
	 */
	@Inject
	private Clock clock;
	
	/**
	 * Gets the realm that needs to be updated
	 */
	@Inject
	private Realm realm;
	
	/**
	 * Initializes this class and schedules a clockworker responsible for updating
	 * the player with the client
	 * 
	 * @param event
	 */
	public void initialize(@Observes ContainerInitialized event) {
		clock.schedule(this).repeat();
	}

	@Override
	public void execute(Clock clock) {
		Map<Player, PlayerSynchronizationContext> player_contexts = new HashMap<>();
//		Map<Player, NPCSynchronizationContext> npc_contexts = new HashMap<>();
		
		/*
		 * Create a new synchronization object for each player
		 */
		realm.getPlayers().forEach(player -> {
			player_contexts.put(player, playerSynchronizer.create(player, realm));
//			npc_contexts.put(player, npcSynchronizer.create(player, realm));
		});
		
		/*
		 * Update all of the players
		 */
		realm.getPlayers().forEach(player -> {
			player.getChannel().writeAndFlush(playerSynchronizer.synchronize(player, player_contexts.get(player), realm, player_contexts));
//			player.getChannel().writeAndFlush(npcSynchronizer.synchronize(player, npc_contexts.get(player), realm));
		});
		
		/*
		 * Clean up after synchronizing the players
		 */
		realm.getPlayers().forEach(player -> {
			playerSynchronizer.destroy(player, player_contexts.get(player));
//			npcSynchronizer.destroy(player, npc_contexts.get(player));
		});
	}

}
