package org.barracuda.content.skill.gather;

import java.util.Random;

import org.barracuda.content.action.ActionQueue;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.Entity;
import org.barracuda.model.actor.Player;
import org.barracuda.model.fixed.ResourceDefinition;

public abstract class GathererSkill<T extends Entity> {

	/**
	 * The random number generator
	 */
	private static final Random random = new Random();
	
	/**
	 * The action queue
	 */
	@Inject private ActionQueue queue;
	
	/**
	 * The player
	 */
	@Inject private Player player;
	
	/**
	 * The player
	 */
	@Inject private Channel channel;

	/**
	 * Validates the entity the player is attempting to gather from
	 * 
	 * @param entity
	 * @return
	 */
	abstract boolean validate(T entity);

	/**
	 * Allows the user to gather resources from a selected entity
	 * 
	 * @param object
	 * @return
	 */
	public void gather(T entity, ResourceDefinition definition) {
	}

	/**
	 * 
	 * @param definition
	 * @return
	 */
	protected boolean checkRequirements(ResourceDefinition definition) {
		return true;
	}

}
