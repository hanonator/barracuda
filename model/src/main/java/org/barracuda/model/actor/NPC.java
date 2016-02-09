package org.barracuda.model.actor;

import org.barracuda.model.actor.npc.NPCConfiguration;
import org.barracuda.model.actor.npc.NPCDefinition;

public class NPC extends Actor {

	/**
	 * The type of the NPC in the client
	 */
	private int type;
	
	/**
	 * Gets the NPC definition for the given npc
	 * @return
	 */
	public NPCDefinition getDefinition() {
		return NPCDefinition.get(type);
	}
	
	/**
	 * Gets the NPC configuration for the given npc
	 * @return
	 */
	public NPCConfiguration getConfiguration() {
		return NPCConfiguration.get(type);
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
