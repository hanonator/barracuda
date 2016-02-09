package org.barracuda.model.actor.sync;

import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.Player;

/**
 * A worker class that synchronizes a collection of entities between the server
 * and the client
 * 
 * @author brock
 *
 * @param <T>
 */
public interface Synchronizer<T extends Actor, C extends SynchronizationContext<T>> {

	/**
	 * Creates the synchronization context for the given entity
	 * 
	 * @param entity
	 * @return
	 */
	C create(T entity);
	
	/**
	 * Synchronizes the player with the client
	 * 
	 * @param entity
	 * @param context
	 */
	void synchronize(Player player, C context);
	
	/**
	 * Destroys the synchronizer for the given entity
	 * 
	 * @param entity
	 * @param context
	 */
	void destroy(T entity, C context);

}
