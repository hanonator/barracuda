package org.barracuda.model.actor.sync.player;

import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Synchronizer;

public class PlayerSynchronizer implements Synchronizer<Player, PlayerSynchronizationContext> {

	@Override
	public PlayerSynchronizationContext create(Player entity) {
		return null;
	}

	@Override
	public void synchronize(Player entity, PlayerSynchronizationContext context) {
		
	}

	@Override
	public void destroy(Player entity, PlayerSynchronizationContext context) {
		
	}

}
