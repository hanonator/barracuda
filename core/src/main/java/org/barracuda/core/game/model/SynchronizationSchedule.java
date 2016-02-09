package org.barracuda.core.game.model;

import java.util.HashMap;
import java.util.Map;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Synchronizer;
import org.barracuda.model.actor.sync.player.PlayerSynchronizationContext;
import org.barracuda.model.actor.sync.player.PlayerSynchronizer;
import org.barracuda.model.realm.Realm;
import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

/**
 * 
 * @author brock
 *
 */
@Discoverable
@ApplicationScoped
public class SynchronizationSchedule implements ClockWorker {

	/**
	 * The object responsible for the player synchronization
	 */
	private final Synchronizer<Player, PlayerSynchronizationContext> playerSynchronizer = new PlayerSynchronizer();
	
	/**
	 * The clock for this application
	 */
	@Inject private Clock clock;
	
	/**
	 * Gets the realm that needs to be updated
	 */
	@Inject private Realm realm;
	
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
		Map<Player, PlayerSynchronizationContext> contexts = new HashMap<>();

		/*
		 * Create a new synchronization object for each player
		 */
		realm.getPlayers().forEachRemaining(player -> contexts.put(player, playerSynchronizer.create(player)));
		
		/*
		 * Update all of the players
		 */
		realm.getPlayers().forEachRemaining(player -> playerSynchronizer.synchronize(player, contexts.get(player)));
		
		/*
		 * Clean up after synchronizing the players
		 */
		realm.getPlayers().forEachRemaining(player -> playerSynchronizer.destroy(player, contexts.get(player)));
	}

}
