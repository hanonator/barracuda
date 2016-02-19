package org.barracuda.core.game.contract;

/**
 * Sets the interface of a sidebar
 * 
 * @author brock
 *
 */
public class SidebarInterface {
	
	/**
	 * The default sidebar interfaces for the 317 client
	 */
	public static final int DEFAULTS[][] = new int[][] {
		new int[] {
			1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 0
		},
		new int[] {
			3917, 638, 3213, 1644, 5608, 1151, 5065, 5715, 2449, 4445, 147, 6299, 2423
		},
	};

	/**
	 * The index of the tab in the sidebar that needs to be set
	 */
	private final int index;
	
	/**
	 * The id of the interface that is being shown
	 */
	private final int interfaceId;

	/**
	 * Constructor
	 * 
	 * @param index
	 * @param interfaceId
	 */
	public SidebarInterface(int index, int interfaceId) {
		this.index = index;
		this.interfaceId = interfaceId;
	}
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Sent when the user wants to force a tab in the side panel to be selected
	 * 
	 * @author brock
	 *
	 */
	public static class Select {
		
		/**
		 * Index of the tab
		 */
		private final int index;

		/**
		 * Constructor
		 * 
		 * @param index
		 */
		public Select(int index) {
			this.index = index;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}
		
	}

}
