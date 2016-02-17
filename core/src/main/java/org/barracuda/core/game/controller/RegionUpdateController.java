package org.barracuda.core.game.controller;

import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.event.RegionUpdatedEvent;

@ApplicationScoped
@Discoverable
public class RegionUpdateController {

	/**
	 * The player's IO channel
	 */
	@Inject
	private Channel channel;
	
	/**
	 * The player
	 */
	@Inject
	private Player player;

	/**
	 * Called when the player's region changes
	 * 
	 * @param event
	 */
	public void on_regionupdate(@Observes RegionUpdatedEvent event) {
		channel.write(event.getCurrentRegion());
		player.appear();
	}

}
