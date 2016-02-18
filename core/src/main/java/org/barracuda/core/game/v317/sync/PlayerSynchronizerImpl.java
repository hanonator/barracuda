package org.barracuda.core.game.v317.sync;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.player.PlayerSynchronizationContext;
import org.barracuda.model.actor.sync.player.PlayerSynchronizer;
import org.barracuda.util.net.BitChannel;

import io.netty.buffer.ByteBuf;

/**
 * The player synchronizer class for the 317 protocol
 * 
 * @author brock
 *
 */
public class PlayerSynchronizerImpl extends PlayerSynchronizer {

	@Override
	public Message wrap(ByteBuf byte_vector, BitChannel bit_vector) {
		return new MessageBuilder(byte_vector.alloc())
				.header(81, MetaData.BIG)
				.writeBytes(bit_vector)
				.writeBytes(byte_vector).build();
	}

	@Override
	public void synchronizeSelf(Player entity, PlayerSynchronizationContext context, BitChannel bit_vector) {
		/*
		 * Check if the player is teleporting.
		 */
		if(context.getLocationMetaData().isTeleporting() || context.getLocationMetaData().hasMapRegionChanged()) {
			/*
			 * They are, so an update is required.
			 */
			bit_vector.write(true);
			
			/*
			 * This value indicates the player teleported.
			 */
			bit_vector.write(3, 2);
	
			/*
			 * This is the new player height.
			 */
			bit_vector.write(entity.getLocation().getZ(), 2);
			
			/*
			 * This indicates that the client should discard the walking queue.
			 */
			bit_vector.write(context.getLocationMetaData().isTeleporting());
			
			/*
			 * This flag indicates if an update block is appended.
			 */
			bit_vector.write(context.getRenderingContext().isUpdateRequired());
			
			/*
			 * These are the positions.
			 * 
			 * TODO: What the fuck is this
			 */
			bit_vector.write(entity.getLocation().localize().getSmallCoordinate(entity.getLocation()).getX() + 48, 7);
			bit_vector.write(entity.getLocation().localize().getSmallCoordinate(entity.getLocation()).getY() + 48, 7);
		} else {
			/*
			 * Otherwise, check if the player moved.
			 */
			if(context.getLocationMetaData().getPrimaryDirection() == null) {
				/*
				 * The player didn't move. Check if an update is required.
				 */
				if(context.getRenderingContext().isUpdateRequired()) {
					/*
					 * Signifies an update is required.
					 */
					bit_vector.write(true);
					
					/*
					 * But signifies that we didn't move.
					 */
					bit_vector.write(0, 2);
				} else {
					/*
					 * Signifies that nothing changed.
					 */
					bit_vector.write(false);
				}
			} else {
				/*
				 * Check if the player was running.
				 */
				if(context.getLocationMetaData().getSecondaryDirection() == null) {
					/*
					 * The player walked, an update is required.
					 */
					bit_vector.write(true);
					
					/*
					 * This indicates the player only walked.
					 */
					bit_vector.write(1, 2);
					
					/*
					 * This is the player's walking direction.
					 */
					bit_vector.write(context.getLocationMetaData().getPrimaryDirection(), 3);
					
					/*
					 * This flag indicates an update block is appended.
					 */
					bit_vector.write(context.getRenderingContext().isUpdateRequired());
				} else {
					/*
					 * The player ran, so an update is required.
					 */
					bit_vector.write(true);
					
					/*
					 * This indicates the player ran.
					 */
					bit_vector.write(2, 2);
					
					/*
					 * This is the walking direction.
					 */
					bit_vector.write(context.getLocationMetaData().getPrimaryDirection(), 3);
					
					/*
					 * And this is the running direction.
					 */
					bit_vector.write(context.getLocationMetaData().getSecondaryDirection(), 3);
					
					/*
					 * And this flag indicates an update block is appended.
					 */
					bit_vector.write(context.getRenderingContext().isUpdateRequired());
				}
			}
		}
	}

	@Override
	public void synchronizeOther(Player other, PlayerSynchronizationContext context, BitChannel bit_vector) {
		/*
		 * Check which type of movement took place.
		 */
		if(context.getLocationMetaData().getPrimaryDirection() == null) {
			/*
			 * If no movement did, check if an update is required.
			 */
			if(!context.getRenderingContext().isUpdateRequired()) {
				/*
				 * Signify that an update happened.
				 */
				bit_vector.write(true);
				
				/*
				 * Signify that there was no movement.
				 */
				bit_vector.write(0, 2);
			} else {
				/*
				 * Signify that nothing changed.
				 */
				bit_vector.write(false);
			}
		} else if(context.getLocationMetaData().getSecondaryDirection() == null) {
			/*
			 * The player moved but didn't run. Signify that an update is
			 * required.
			 */
			bit_vector.write(true);
			
			/*
			 * Signify we moved one tile.
			 */
			bit_vector.write(1, 2);
			
			/*
			 * Write the primary sprite (i.e. walk direction).
			 */
			bit_vector.write(context.getLocationMetaData().getPrimaryDirection(), 3);
			
			/*
			 * Write a flag indicating if a block update happened.
			 */
			bit_vector.write(!context.getRenderingContext().isUpdateRequired());
		} else {
			/*
			 * The player ran. Signify that an update happened.
			 */
			bit_vector.write(true);
			
			/*
			 * Signify that we moved two tiles.
			 */
			bit_vector.write(2, 2);
			
			/*
			 * Write the primary sprite (i.e. walk direction).
			 */
			bit_vector.write(context.getLocationMetaData().getPrimaryDirection(), 3);
			
			/*
			 * Write the secondary sprite (i.e. run direction).
			 */
			bit_vector.write(context.getLocationMetaData().getSecondaryDirection(), 3);
			
			/*
			 * Write a flag indicating if a block update happened.
			 */
			bit_vector.write(!context.getRenderingContext().isUpdateRequired());
		}
	}

	@Override
	public void synchronizeNew(Player self, Player other, PlayerSynchronizationContext context, BitChannel bit_vector) {
		/*
		 * Write the player index.
		 */
		bit_vector.write(other.getIndex(), 11);

		/*
		 * Signify that the player needs to be updated
		 */
		bit_vector.write(true);
		
		/*
		 * Signify that the player needs to be added to the stack of local players
		 */
		bit_vector.write(true);
		
		/*
		 * Write the difference in player coordinates to the client.
		 */
		bit_vector.write(other.getLocation().getX() - self.getLocation().getX(), 5);
		bit_vector.write(other.getLocation().getY() - self.getLocation().getY(), 5);
	}

	@Override
	public void desynchronize(Player other, PlayerSynchronizationContext context, BitChannel bit_vector) {
		bit_vector.write(true);
		bit_vector.write(3, 2);
	}

}
