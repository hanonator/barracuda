package org.barracuda.model.item;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.Player;

@Discoverable
@SessionScoped
public class Inventory extends Container {

	/**
	 * The capacity of the inventory.
	 */
	public static final int CAPACITY = 28;
	
	/**
	 * The interface id
	 */
	public static final int INTERFACE = 3214;

	/**
	 * The player for this inventory
	 */
	@Inject
	private Player player;

	/**
	 * Constructor
	 * @param player
	 */
	public Inventory() {
		super(CAPACITY);
	}

}