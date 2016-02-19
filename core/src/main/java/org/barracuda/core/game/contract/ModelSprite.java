package org.barracuda.core.game.contract;

/**
 * Renders an item model as a sprite on an interface
 * 
 * @author brock
 *
 */
public class ModelSprite {

	/**
	 * Id of the item that needs to be shown
	 */
	private final int itemId;
	
	/**
	 * The zoom of the model
	 */
	private final int zoom;
	
	/**
	 * The id of the interface id the item needs to be displayed on
	 */
	private final int interfaceId;

	/**
	 * Constructor
	 * 
	 * @param itemId
	 * @param zoom
	 * @param interfaceId
	 */
	public ModelSprite(int itemId, int zoom, int interfaceId) {
		this.itemId = itemId;
		this.zoom = zoom;
		this.interfaceId = interfaceId;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @return the zoom
	 */
	public int getZoom() {
		return zoom;
	}

	/**
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

}