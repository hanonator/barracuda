package org.barracuda.model.actor.player;

import org.barracuda.model.actor.Player;

public class Stats {

	/**
	 * The skill names.
	 */
	public static final String[] SKILL_NAME = { "Attack", "Defence", "Strength", "Hitpoints", "Range", "Prayer",
			"Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining",
			"Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting" };

	/**
	 * Constants for the skill numbers.
	 */
	public static final int ATTACK = 0, DEFENCE = 1, STRENGTH = 2, HITPOINTS = 3, RANGE = 4, PRAYER = 5, MAGIC = 6,
			COOKING = 7, WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11, CRAFTING = 12, SMITHING = 13,
			MINING = 14, HERBLORE = 15, AGILITY = 16, THIEVING = 17, SLAYER = 18, FARMING = 19, RUNECRAFTING = 20;

	/**
	 * The player's skills
	 */
	private final Skill[] skills = new Skill[SKILL_NAME.length];

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * Constructor
	 * 
	 * @param player
	 */
	public Stats(Player player) {
		this.player = player;
		for (int i = 0; i < skills.length; i++) {
			skills[i] = new Skill(i, i == HITPOINTS ? Skill.getXPForLevel(10) : 0, i == HITPOINTS ? 10 : 1, this.player);
		}
	}

	/**
	 * 
	 * @param index
	 * @param experience
	 */
	public void addExperience(int index, int experience) {
		skills[index].addExperience(experience);
	}
	
	/**
	 * Increment or decrement the level of the skills towards their absolute level
	 */
	public void normalize() {
		// TODO: Make this normalize
	}

	/**
	 * Gets the requested skill
	 * 
	 * @param id
	 * @return
	 */
	public Skill get(int id) {
		return skills[id];
	}

}
