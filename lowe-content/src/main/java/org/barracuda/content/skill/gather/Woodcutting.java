package org.barracuda.content.skill.gather;

import org.barracuda.core.game.event.ObjectInteractionEvent;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.fixed.RSObject;

@Discoverable
@SessionScoped
public class Woodcutting extends GathererSkill<RSObject> {

	/**
	 * 
	 * @param event
	 */
	public void on_object(@Observes ObjectInteractionEvent event) {
		
	}

	@Override
	boolean validate(RSObject entity) {
		return false;
	}

}
