package org.barracuda.core.game.controller;

import org.barracuda.core.game.event.MovementCommand;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;

@SessionScoped
@Discoverable
public class WalkingController {

	/**
	 * The player this controller is controlling (lol)
	 */
	@Inject
	private Player player;

	/**
	 * Adds all of the queried waypoints to the player's movement queue
	 * 
	 * @param command
	 */
	public void on_walk(@Observes MovementCommand command) {
		player.getWaypoints().clear();
		command.getWaypoints().forEach(player.getWaypoints()::add);
		player.getWaypoints().finish();
	}

}
