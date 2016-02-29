package org.barracuda.content.skill.artisan.view;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.barracuda.core.game.contract.ui.PromptNumber;
import org.barracuda.core.game.event.ui.ButtonPressed;
import org.barracuda.core.game.event.ui.InterfaceClosed;
import org.barracuda.core.game.event.ui.NumberEntered;
import org.barracuda.core.net.Channel;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;

import com.google.gson.Gson;

@Discoverable
@ApplicationScoped
public class CraftInterfaceController {

	/**
	 * The collection of buttonId <-> CraftInterfaceButton mappings
	 */
	private static final Map<Integer, CraftInterfaceButton> buttons = new HashMap<>();

	/**
	 * The player operating the craft interface
	 */
	@Inject
	private Player player;

	/**
	 * The player operating the craft interface
	 */
	@Inject
	private Channel channel;

	/**
	 * Loads all of the CraftInterfaceButton configurations
	 * 
	 * @param event
	 */
	public static void loadCraftButtons(@Observes ContainerInitialized event, Gson gson) {
		CraftInterfaceButton[] buttons = gson.fromJson(new InputStreamReader(ClassLoader.getSystemResourceAsStream("game/artisan/buttons.json")),
				CraftInterfaceButton[].class);
		for (CraftInterfaceButton button : buttons) {
			CraftInterfaceController.buttons.put(button.getId(), button);
		}
	}

	/**
	 * Called when the player has pressed a button. This will make it so the
	 * correct event is being called on the player's active craft interface
	 * 
	 * @param event
	 */
	public void on_button(@Observes ButtonPressed event) {
		if (buttons.containsKey(event.getId())) {
			CraftInterfaceButton button = buttons.get(event.getId());
			
			if (button.getAmount() == -1) {
				channel.write(new PromptNumber());
			}
			else {
				
			}
		}
	}

	/**
	 * Called when the player has entered a value after choosing Make X
	 * 
	 * @param event
	 */
	public void on_amount(@Observes NumberEntered event) {
		
	}

	/**
	 * Called when the player closes an interface
	 * 
	 * @param event
	 */
	public void on_close(@Observes InterfaceClosed event) {

	}

}
