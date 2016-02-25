package org.barracuda.model.actor.sync;

import java.util.Map;

import org.barracuda.core.net.message.Message;
import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.Player;
import org.barracuda.model.realm.Realm;

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
	C create(T entity, Realm realm);
	
	/**
	 * Synchronizes the player with the client
	 * 
	 * TODO: Add context, realm and contexts in some form of synchronizer context
	 * 
	 * @param entity
	 * @param context
	 */
	Message synchronize(Player player, C context, Realm realm, Map<T, C> contexts);
	
	/**
	 * Destroys the synchronizer for the given entity
	 * 
	 * @param entity
	 * @param context
	 */
	void destroy(Player entity, C context);

}
