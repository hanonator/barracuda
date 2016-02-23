package org.barracuda.content;

import org.barracuda.core.game.contract.TextMessage;
import org.barracuda.core.game.contract.ui.SidebarInterface;
import org.barracuda.core.game.event.PlayerInitialized;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.player.Skill;
import org.barracuda.model.actor.player.Skill.Levelup;
import org.barracuda.model.actor.player.Skill.SkillUpdated;
import org.barracuda.model.actor.player.Stats;
import org.barracuda.model.item.Inventory.InventoryUpdated;

/**
 * Controls general player behaviour. Try to not clutter this with too many
 * irrelevant observers
 * 
 * @author brock
 *
 */
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

	/**
	 * Called when the player's inventory has been updated
	 * 
	 * @param event
	 */
	public void on_inventoryUpdate(@Observes InventoryUpdated event) {
		channel.write(event.getInventory());
	}

	/**
	 * Called when the player's inventory has been updated
	 * 
	 * @param event
	 */
	public void on_skillUpdate(@Observes SkillUpdated event) {
		channel.write(event.getSkill());
	}

	/**
	 * Called when the player has gained a level in a skill
	 * 
	 * @param event
	 */
	public void on_skillUpdate(@Observes Levelup event) {
		StringBuilder builder = new StringBuilder().append("Congratulations, you have gained ")
				.append(event.getLevelsGained() == 1 ? "a level" : event.getLevelsGained() + " levels").append(" in ")
				.append(Stats.SKILL_NAME[event.getSkill().getId()]).append("! You are now level ")
				.append(Skill.getLevelForExperience(event.getSkill().getId()));
		channel.write(new TextMessage(builder.toString()));
	}

}
