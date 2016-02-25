package org.barracuda.core.game.controller;

import org.barracuda.core.game.event.MovementCommand;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Player;

@ApplicationScoped
@Discoverable
public class WalkingController {

	/**
	 * Adds all of the queried waypoints to the player's movement queue
	 * 
	 * @param command
	 */
	public void on_walk(@Observes MovementCommand command, Player player) {
		player.getWaypoints().clear();
		command.getWaypoints().forEach(player.getWaypoints()::add);
		player.getWaypoints().finish();
	}

}
