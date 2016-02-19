package org.barracuda.content.skill.artisan.view;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author brock
 *
 */
public abstract class AbstractCraftInterface implements CraftInterface {

	/**
	 * The listener that handles the listening
	 */
	private CraftInterfaceListener listener;

	/**
	 * Collection of items displayed on the interface
	 */
	protected final Set<Integer> items = new HashSet<>();

	@Override
	public CraftInterface item(int id) {
		this.items.add(id);
		return this;
	}

	@Override
	public CraftInterface listener(CraftInterfaceListener listener) {
		this.listener = listener;
		return this;
	}

	/**
	 * @return the listener
	 */
	CraftInterfaceListener getListener() {
		return listener;
	}

}
