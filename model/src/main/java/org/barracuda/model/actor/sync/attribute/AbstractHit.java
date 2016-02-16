package org.barracuda.model.actor.sync.attribute;

/**
 * @author brock
 */
public abstract class AbstractHit implements Attribute {

	/**
	 * Amount displayed within the sprite
	 */
	private final int amount;
	
	/**
	 * The sprite used to display the hit
	 */
	private final Sprite sprite;

	/**
	 * Constructor
	 * 
	 * @param amount
	 * @param sprite
	 */
	public AbstractHit(int amount, Sprite sprite) {
		this.amount = amount;
		this.sprite = sprite;
	}

	/**
	 * Constructor that calculates the sprite based upon the the damage
	 * dealt
	 * 
	 * @param amount
	 */
	public AbstractHit(int amount) {
		this.amount = amount;
		this.sprite = amount > 0 ? Sprite.RED : Sprite.BLUE;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * 
	 * @author brock
	 *
	 */
	public static enum Sprite {
		
		/**
		 * A blue hit sprite, usually used when the player does no damage
		 * on his hit
		 */
		BLUE,
		
		/**
		 * A red hit sprite, usually used when the player damages the opponent
		 * with his/her weapon
		 */
		RED,
		
		/**
		 * A green hit sprite, usually used when the player damages the opponent
		 * by the use of poison
		 */
		GREEN;
		
	}

}
