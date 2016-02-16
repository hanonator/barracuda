package org.barracuda.model.actor.sync.attribute;

public class Appearance {

	/**
	 * The gender of the character
	 */
	private final int gender;
	
	/**
	 * The character's torso id
	 */
	private final int torso;
	
	/**
	 * The character's legs id
	 */
	private final int legs;
	
	/**
	 * The character's hands id
	 */
	private final int hands;
	
	/**
	 * The character's feet id
	 */
	private final int feet;

	/**
	 * Creates a default appearance
	 */
	public Appearance() {
		this (0, 25, 39, 35, 44);
	}

	public Appearance(int gender, int torso, int legs, int hands, int feet) {
		this.gender = gender;
		this.torso = torso;
		this.legs = legs;
		this.hands = hands;
		this.feet = feet;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @return the torso
	 */
	public int getTorso() {
		return torso;
	}

	/**
	 * @return the legs
	 */
	public int getLegs() {
		return legs;
	}

	/**
	 * @return the hands
	 */
	public int getHands() {
		return hands;
	}

	/**
	 * @return the feet
	 */
	public int getFeet() {
		return feet;
	}

}
