package org.barracuda.content.skill.artisan;

import org.barracuda.content.action.ActionQueue;
import org.barracuda.content.skill.AbstractTrainingMethod;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;

/**
 * 
 * @author brock
 *
 */
public abstract class ArtisanSkill extends AbstractTrainingMethod {

	/**
	 * The player
	 */
	@Inject
	private Player player;

	/**
	 * The action queue
	 */
	@Inject
	private ActionQueue actionQueue;

}
