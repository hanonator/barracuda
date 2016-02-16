package org.barracuda.core.game.v317.login;

import org.barracuda.core.net.event.PlayerDisconnected;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.realm.Realm;

@SessionScoped
@Discoverable
public class DisconnectedController {

	/**
	 * The realm that the player is signing off from
	 */
	@Inject
	private Realm realm;
	
	/**
	 * The player that needs to be unregistered
	 */
	@Inject
	private Player player;

	/**
	 * Unregisters the player from the realm on disconnect
	 * 
	 * @param disconnected
	 */
	public void on_disconnect(@Observes PlayerDisconnected disconnected) {
		realm.getPlayers().unregister(player);
	}

}
