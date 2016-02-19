package org.barracuda.core.game.contract;

/**
 * Opens an interface in both the player's sidebar and main window
 * 
 * @author brock
 *
 */
public class SidebarInventoryComboInterface {

	/**
	 * The id of the overlay interface over to the side
	 */
	private final int overlay;
	
	/**
	 * The interface id of the main window
	 */
	private final int window;

	/**
	 * Constructor
	 * 
	 * @param overlay
	 * @param window
	 */
	public SidebarInventoryComboInterface(int overlay, int window) {
		this.overlay = overlay;
		this.window = window;
	}

	/**
	 * @return the overlay
	 */
	public int getOverlay() {
		return overlay;
	}

	/**
	 * @return the window
	 */
	public int getWindow() {
		return window;
	}

}