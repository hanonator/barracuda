package org.barracuda.core.game.controller;

import org.barracuda.core.game.contract.TextMessage;
import org.barracuda.core.game.event.UserCommand;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;
import org.barracuda.model.item.Item;

@Discoverable
@ApplicationScoped
public class CommandController {

	/**
	 * The player executing the command
	 */
	@Inject
	private Player player;

	/**
	 * The channel to which to write
	 */
	@Inject
	private Channel channel;

	/**
	 * Called when the player executes a command. Commands are text messages
	 * sent with a '::' prefix
	 * 
	 * @param command
	 */
	public void on_command(@Observes UserCommand command) {
		switch(command.getCommand()) {
		case "item":
			try {
				int id = Integer.valueOf(command.getArgument(0));
				int amount = command.getArgumentCount() == 2 ? Integer.valueOf(command.getArgument(1)) : 1;
				
				player.getInventory().add(new Item(id, amount));
			} catch (Exception ex) {
				channel.write(new TextMessage("Usage: ::item id [amount]"));
			}
			break;
		}
	}
}
