package org.barracuda.model.actor.npc;

import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;

/**
 * The properties of an NPC as defined by the server.
 * 
 * This mainly lists properties that affect gameplay such as
 * combat stats, position/territory, behaviour, ...
 * 
 * @author brock
 *
 */
public class NPCConfiguration {

	/**
	 * The collection of NPC configurations
	 */
	private static final NPCConfiguration[] configurations = null;

	/**
	 * Gets the definition for the given npc-type
	 * 
	 * @param type
	 * @return
	 */
	public static NPCConfiguration get(int type) {
		return configurations[type];
	}
	
	/**
	 * 
	 * @param initialized
	 */
	public static void initialize(@Observes ContainerInitialized initialized) {
		
	}

}
