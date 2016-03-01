package org.barracuda.content.skill.gather;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Player;

import com.google.gson.Gson;

public enum ToolType {
	HATCHET, PICKAXE, SMALL_NET, LARGE_NET, HARPOON, LOBSTER_POT, FLY_FISHING_ROD, FISHING_ROD;
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ToolType.class);
	
	/**
	 * The collection of tools for this tool type
	 */
	private final Set<Tool> tools = new TreeSet<>();
	
	/**
	 * Attempts to load all of the tools from a configuration file
	 * 
	 * @param event
	 */
	public static void loadTools(@Observes ContainerInitialized event, Gson gson) {
		InputStream stream = ClassLoader.getSystemResourceAsStream("static/game/misc/tools.json");
		Tool[] temp = gson.fromJson(new InputStreamReader(stream, Charset.forName("UTF-8")), Tool[].class);
		for (Tool definition : temp) {
			definition.getType().tools.add(definition);
			logger.debug("Tool: id: {}, type: {}, level: {}", definition.getId(), definition.getType(), definition.getLevel());
		}
	}
	
	/**
	 * Attempts to get the best available tool for the player
	 * @return
	 */
	public Tool getBestAvailableTool(Player player) {
		for (Tool tool : tools) {
			if (player.getInventory().contains(tool.getId())) {
				return tool;
			}
		}
		return null;
	}
	
}
