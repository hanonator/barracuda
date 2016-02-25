package org.barracuda.model.actor.sync.player;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.barracuda.core.net.message.Message;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.LocationMetaData;
import org.barracuda.model.actor.sync.RenderingContext;
import org.barracuda.model.actor.sync.Synchronizer;
import org.barracuda.model.realm.Realm;
import org.barracuda.util.net.BitChannel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 
 * @author brock
 *
 */
public abstract class PlayerSynchronizer implements Synchronizer<Player, PlayerSynchronizationContext> {

	/**
	 * Removes a player from the local player list in the client. The name of
	 * this method is kind of a joke but it works :)
	 * 
	 * @param other
	 * @param packet
	 */
	public abstract void desynchronize(Player other, PlayerSynchronizationContext context, BitChannel bit_vector);

	/**
	 * Updates a player's position that has been discovered before.
	 * 
	 * @param other
	 * @param bit_vector
	 */
	public abstract void synchronizeOther(Player other, PlayerSynchronizationContext context, BitChannel bit_vector);

	/**
	 * Notifies the client of a new local player entry
	 * 
	 * @param other
	 * @param bit_vector
	 */
	public abstract void synchronizeNew(Player self, Player other, PlayerSynchronizationContext context, BitChannel bit_vector);

	/**
	 * Synchronizes the own player
	 * 
	 * @param entity
	 */
	public abstract void synchronizeSelf(Player entity, PlayerSynchronizationContext context, BitChannel bit_vector);
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	public abstract Message wrap(ByteBuf vector, BitChannel bit_vector);

	@Override
	public PlayerSynchronizationContext create(Player entity, Realm realm) {
		/*
		 * Process the next waypoint
		 */
		LocationMetaData locationMetaData = entity.getWaypoints().update();

		/*
		 * Create the rendering context
		 */
		RenderingContext renderingContext = new RenderingContext(entity).render();

		/*
		 * Find all the currently visible players
		 */
		Set<Player> localPlayers = realm.getPlayers().get()
				.filter(player -> player.getLocation().distance(entity.getLocation()) < 16).collect(Collectors.toSet());

		/*
		 * Create the synchronization context
		 */
		return new PlayerSynchronizationContext(localPlayers, renderingContext, locationMetaData);
	}

	@Override
	public Message synchronize(Player entity, PlayerSynchronizationContext context, Realm realm, Map<Player, PlayerSynchronizationContext> contexts) {
		ByteBuf byte_vector = Unpooled.buffer();
		BitChannel bit_vector = new BitChannel();

		/*
		 * Update the own player
		 */
		synchronizeSelf(entity, context, bit_vector);
		
		/*
		 * Append the player's render to the packet buffer
		 */
		if (context.getRenderingContext().isUpdateRequired()) {
			byte_vector.writeBytes(context.getRenderingContext().getBlock());
		}
		
		/*
		 * The players that are already been rendered before by the current
		 * player
		 */
		bit_vector.write((byte) entity.getCamera().getVisiblePlayers().size());
		for (Iterator<Player> iterator = entity.getCamera().getVisiblePlayers().iterator(); iterator.hasNext();) {
			Player other_player = iterator.next();
			PlayerSynchronizationContext other_context = contexts.get(other_player);

			/*
			 * If the other player does not exist, remove it from the list
			 */
			if (!realm.getPlayers().contains(other_player)) {
				desynchronize(other_player, other_context, bit_vector);
				iterator.remove();
			}

			/*
			 * Otherwise synchronize the player's movement cycle and process the
			 * player
			 */
			else {
				synchronizeOther(other_player, other_context, bit_vector);
				if (other_context.getRenderingContext().isUpdateRequired()) {
					byte_vector.writeBytes(other_context.getRenderingContext().getBlock());
				}
			}
		}

		/*
		 * Loop through all of the players in the vicinity
		 */
		for (Iterator<Player> iterator = context.getLocalPlayers().iterator(); iterator.hasNext();) {
			Player other_player = iterator.next();

			/*
			 * No need to update players twice. Only those who have been newly
			 * discovered need to be included here.
			 */
			if (other_player != entity && !entity.getCamera().getVisiblePlayers().contains(other_player)) {
				PlayerSynchronizationContext other_context = contexts.get(other_player);

				/*
				 * Add the new player to the player's client
				 */
				entity.getCamera().getVisiblePlayers().add(other_player);
				synchronizeNew(entity, other_player, other_context, bit_vector);

				/*
				 * If the player is a newly discovered player, the appearance
				 * update flag needs to be forcefully set in order to make the
				 * player visible
				 */
				if (other_context.getRenderingContext().isUpdateRequired()) {
					byte_vector.writeBytes(other_context.getRenderingContext().getForcedAppearanceBlock());
				}
			}
		}
		
		/*
		 * Close off the bit_vector with a player id of 2047
		 */
		if (byte_vector.isReadable()) {
			bit_vector.write(2047, 11);
		}
		return wrap(byte_vector, bit_vector.flush());
	}

	@Override
	public void destroy(Player entity, PlayerSynchronizationContext context) {
		context.getRenderingContext().destroy();
		context.getLocalPlayers().clear();
	}

}
