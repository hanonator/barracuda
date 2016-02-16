package org.barracuda.core.game.model;

import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.event.RegionUpdatedEvent;

@SessionScoped
@Discoverable
public class RegionUpdateController {

	/**
	 * The player's IO channel
	 */
	@Inject
	private Channel channel;

	/**
	 * Called when the player's region changes
	 * 
	 * @param event
	 */
	public void on_regionupdate(@Observes RegionUpdatedEvent event) {
		channel.write(event.getCurrentRegion());
	}

}
