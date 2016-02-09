package org.barracuda.model.actor.player;

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
	 * Gets the requested skill
	 * 
	 * @param id
	 * @return
	 */
	public Skill get(int id) {
		return skills[id];
	}

}
