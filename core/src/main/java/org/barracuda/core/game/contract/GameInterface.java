package org.barracuda.core.game.contract;

/**
 * Opens an interface in the main game window
 * 
 * @author brock
 *
 */
public class GameInterface {

	/**
	 * The interface id
	 */
	private final int interface_id;

	/**
	 * Constructor
	 * 
	 * @param interface_id
	 */
	public GameInterface(int interface_id) {
		this.interface_id = interface_id;
	}

	/**
	 * @return the interface_id
	 */
	public int getInterface_id() {
		return interface_id;
	}

}