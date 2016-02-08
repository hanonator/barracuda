package org.barracuda.model.actor.npc;

import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;

/**
 * The static properties of an NPC as defined by the client
 * 
 * This mainly affects the way the NPC is represented with
 * things like width/height, animations, ...
 * 
 * @author brock
 *
 */
public class NPCDefinition {

	/**
	 * The collection of NPC definitions
	 */
	private static final NPCDefinition[] definitions = null;

	/**
	 * Gets the definition for the given npc-type
	 * 
	 * @param type
	 * @return
	 */
	public static NPCDefinition get(int type) {
		return definitions[type];
	}
	
	/**
	 * 
	 * @param initialized
	 */
	public static void initialize(@Observes ContainerInitialized initialized) {
		
	}

}
