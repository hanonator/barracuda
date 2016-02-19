package org.barracuda.model.actor.event;

import org.barracuda.core.net.message.resolve.Silent;

/**
 * An event sent by the client once the player has finished loading the region
 * 
 * This should only be used to initialize region based entities in the client
 * 
 * @author koga
 *
 */
@Silent
public class RegionLoaded {

}
