package org.barracuda.model.actor.sync.npc;

import org.barracuda.model.actor.NPC;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Synchronizer;

public class NPCSynchronizer implements Synchronizer<NPC, NPCSynchronizationContext> {

	@Override
	public NPCSynchronizationContext create(NPC entity) {
		return null;
	}

	@Override
	public void synchronize(Player player, NPCSynchronizationContext context) {
		
	}

	@Override
	public void destroy(NPC entity, NPCSynchronizationContext context) {
		
	}

}
