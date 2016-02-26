package org.barracuda.content.skill.artisan.impl;

import org.barracuda.content.skill.artisan.ArtisanSkill;
import org.barracuda.content.skill.artisan.ProductDefinition;
import org.barracuda.content.skill.artisan.view.ChatboxCraftInterface;
import org.barracuda.core.game.event.ui.ItemsCombined;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.player.Stats;

/**
 * Everything to do with the general fletching skill should be present in this
 * class. Very specific things that happen in things like quests can be put in
 * that class, but in general, every viable way of training the skills should be
 * centralized here.
 * 
 * Skills should generally be session specific as they heavily rely on events
 * triggered by the player with an unknown timespan during which the injected
 * fields can change and some information may be sent to the wrong player.
 * 
 * @author koga
 *
 */
@Discoverable
@SessionScoped
public class Fletching extends ArtisanSkill {

	/**
	 * Called when 2 items are combined
	 * 
	 * @param event
	 */
	public void on_combine(@Observes ItemsCombined event, Channel channel) {
		ProductDefinition definition = combine(event.getPrimaryItem(), event.getSecondaryItem()) == 0 ? new ProductDefinition() : null;
		if (definition != null && definition.getSkill() == Stats.FLETCHING) {
			new ChatboxCraftInterface(definition).listener(super::craft).open(channel);
		}
	}

}
