package org.barracuda.content.skill.artisan.view;

import java.util.Collection;

import org.barracuda.core.net.Channel;

/**
 * 
 * @author brock
 *
 */
public interface CraftInterface {

	/**
	 * Opens the craft interface
	 * 
	 * @return
	 */
	CraftInterface open(Channel channel);
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	CraftInterface listener(CraftInterfaceListener listener);
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	CraftInterface item(int id);
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	default CraftInterface items(Collection<Integer> ids) {
		ids.forEach(this::item);
		return this;
	}

}
