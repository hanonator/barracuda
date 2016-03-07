package org.barracuda.core.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.horvik.util.ReflectionUtil;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Synchronizer;
import org.barracuda.model.actor.sync.Synchronizes;
import org.barracuda.model.actor.sync.player.PlayerSynchronizationContext;
import org.barracuda.model.actor.sync.player.PlayerSynchronizer;
import org.barracuda.model.realm.Realm;
import org.barracuda.roald.Clock;
import org.barracuda.roald.ClockWorker;

/**
 * @author brock
 */
@Discoverable
@ApplicationScoped
public class SynchronizationSchedule implements ClockWorker {

	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SynchronizationSchedule.class);

	/**
	 * The object responsible for the player synchronization
	 */
	private static Synchronizer<Player, PlayerSynchronizationContext> playerSynchronizer;

	/**
	 * Loads the synchronizers
	 * 
	 * @param event
	 * @param container
	 */
	public static void load_synchronizers(@Observes ContainerInitialized event, HorvikContainer container) {
		Set<Class<?>> synchronizers = container.getTypesAnnotatedWith(Synchronizes.class);
		synchronizers.stream().forEach(synchronizer_class -> {
			if (playerSynchronizer == null && synchronizer_class.getAnnotation(Synchronizes.class).value() == Player.class) {
				playerSynchronizer = ReflectionUtil.createForcedType(synchronizer_class, PlayerSynchronizer.class);
				logger.info("Synchronizer -> {} is being used as player synchronizer", synchronizer_class.getName());
			}
		});
	}

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
