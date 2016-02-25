package org.barracuda.model.actor.sync.player;

import java.util.Set;

import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.LocationMetaData;
import org.barracuda.model.actor.sync.RenderingContext;
import org.barracuda.model.actor.sync.SynchronizationContext;

/**
 * The context for a single synchronization cycle
 * 
 * @author brock
 */
public class PlayerSynchronizationContext implements SynchronizationContext<Player> {

	/**
	 * The players currently visible to the player
	 */
	private final Set<Player> localPlayers;
	
	/**
	 * The rendering context, containing the rendered player
	 */
	private final RenderingContext renderingContext;
	
	/**
	 * Information about the location meta data
	 */
	private final LocationMetaData locationMetaData;
	
	/**
	 * Constructor
	 * 
	 * @param localPlayers
	 * @param renderingContext
	 * @param locationMetaData
	 */
	public PlayerSynchronizationContext(Set<Player> localPlayers, RenderingContext renderingContext, LocationMetaData locationMetaData) {
		this.localPlayers = localPlayers;
		this.renderingContext = renderingContext;
		this.locationMetaData = locationMetaData;
	}

	/**
	 * @return the localPlayers
	 */
	public Set<Player> getLocalPlayers() {
		return localPlayers;
	}

	/**
	 * @return the renderingContext
	 */
	public RenderingContext getRenderingContext() {
		return renderingContext;
	}

	/**
	 * @return the locationMetaData
	 */
	public LocationMetaData getLocationMetaData() {
		return locationMetaData;
	}

}
