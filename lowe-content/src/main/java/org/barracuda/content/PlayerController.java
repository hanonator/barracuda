package org.barracuda.content;

import org.barracuda.core.game.contract.ui.SidebarInterface;
import org.barracuda.core.game.event.PlayerInitialized;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.player.Stats;

@Discoverable
@ApplicationScoped
public class PlayerController {
	
	/**
	 * The player's channel
	 */
	@Inject
	private Channel channel;
	
	/**
	 * Called when the player has been initialized for the very first time
	 * 	
	 * TODO: Find a better place for this method
	 * 
	 * @param event
	 */
	public void on_initialized(@Observes PlayerInitialized event) {
		for (int i = 0; i < SidebarInterface.DEFAULTS.length; i++) {
			if (SidebarInterface.DEFAULTS[i] > 0) {
				channel.write(new SidebarInterface(i, SidebarInterface.DEFAULTS[i]));
			}
		}
		for (int i = 0; i < Stats.SKILL_NAME.length; i++) {
			channel.write(event.getPlayer().getStats().get(i));
		}
	}

}

