package org.barracuda.core.game.contract.ui;

/**
 * Opens an interface in the player's chatbox
 * 
 * @author brock
 *
 */
public class ChatboxInterface {

	/**
	 * The id of the interface that needs to be opened
	 */
	private final int interfaceId;

	/**
	 * Constructor
	 * 
	 * @param interfaceId
	 */
	public ChatboxInterface(int interfaceId) {
		this.interfaceId = interfaceId;
	}

	/**
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

}