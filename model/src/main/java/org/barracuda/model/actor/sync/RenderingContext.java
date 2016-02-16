package org.barracuda.model.actor.sync;

import java.util.concurrent.atomic.AtomicInteger;

import org.barracuda.model.actor.Player;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 
 * @author brock
 *
 */
public class RenderingContext {

	/**
	 * The player
	 */
	private final Player player;
	
	/**
	 * The rendered block
	 */
	private ByteBuf block;
	
	/**
	 * The rendered block
	 */
	private ByteBuf forcedAppearanceBlock;
	
	/**
	 * Indicates the update is required
	 */
	private boolean updateRequired;

	/**
	 * Constructor
	 * @param entity
	 */
	public RenderingContext(Player player) {
		this.player = player;
	}

	/**
	 * Renders the player to the buffer
	 * 
	 * @return
	 */
	public RenderingContext render() {
		/*
		 * Render the player regularly
		 */
		this.block = render(Unpooled.buffer(1024 * 8));
		
		/*
		 * Add the player's appearance update
		 */
		// TODO
		
		/*
		 * Render the player with the appearance forcefully added to the block. This is for
		 * players that have just logged in who need to update all the players in the vicinity
		 */
		this.forcedAppearanceBlock = render(Unpooled.buffer(1024 * 8));
		return this;
	}
	
	/**
	 * Renders the player to a specific buffer
	 * @param buffer
	 */
	private ByteBuf render(ByteBuf buffer) {
		updateRequired = !player.getRenderingHints().isEmpty();
		AtomicInteger bit_set = new AtomicInteger(0);
		player.getRenderingHints().forEach(hint -> bit_set.set(bit_set.get() | hint.getIdentifier()));
		buffer.writeByte(bit_set.get());
		player.getRenderingHints().forEach(hint -> hint.serialize(buffer));
		return buffer;
	}

	/**
	 * @return the block
	 */
	public ByteBuf getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(ByteBuf block) {
		this.block = block;
	}

	/**
	 * @return the forcedAppearanceBlock
	 */
	public ByteBuf getForcedAppearanceBlock() {
		return forcedAppearanceBlock;
	}

	/**
	 * @param forcedAppearanceBlock the forcedAppearanceBlock to set
	 */
	public void setForcedAppearanceBlock(ByteBuf forcedAppearanceBlock) {
		this.forcedAppearanceBlock = forcedAppearanceBlock;
	}

	/**
	 * Destroys this context
	 */
	public void destroy() {
		block.release();
		forcedAppearanceBlock.release();
	}

	/**
	 * @return the updateRequired
	 */
	public boolean isUpdateRequired() {
		return updateRequired;
	}

}
