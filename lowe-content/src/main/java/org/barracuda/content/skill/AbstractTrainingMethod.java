package org.barracuda.content.skill;

/**
 * 
 * @author brock
 *
 */
public abstract class AbstractTrainingMethod implements TrainingMethod {

	/**
	 * Gets the hash for when the player combines two items
	 * 
	 * @param primaryItem
	 * @param secondaryItem
	 * @return
	 */
	protected static long combine(long primaryItem, long secondaryItem) {
		return (Math.max(primaryItem, secondaryItem) << 32) | Math.min(primaryItem, secondaryItem);
	}

	/**
	 * Gets the hash for when the player triggers an item by clicking on them
	 * 
	 * @param item
	 * @param option
	 * @return
	 */
	protected static long click(long item, long option) {
		return (option << 32) | item;
	}

}
