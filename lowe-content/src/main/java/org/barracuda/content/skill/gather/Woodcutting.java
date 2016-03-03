package org.barracuda.content.skill.gather;

import org.barracuda.content.skill.gather.node.NodeController;
import org.barracuda.core.game.event.ObjectInteractionEvent;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.fixed.RSObject;

@Discoverable
@SessionScoped
public class Woodcutting extends GathererSkill<RSObject> {
	
	/**
	 * The player
	 */
	@Inject private NodeController nodes;

	/**
	 * 
	 * @param event
	 */
	public void on_object(@Observes ObjectInteractionEvent event) {
		
	}

}
