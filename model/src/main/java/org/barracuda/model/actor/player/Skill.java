package org.barracuda.model.actor.player;

import org.barracuda.model.actor.Player;

public class Skill {

	/**
	 * The largest allowed experience.
	 */
	public static final int MAXIMUM_EXP = 200_000_000;


	/**
	 * The id of the skill, this should match the skill id in the client and is
	 * generally in order of release
	 */
	private int id;

	/**
	 * The amount of experience the player has currently in this skill
	 */
	private int experience;

	/**
	 * The level the player is currently at in the skill. This can differ from
	 * the actual level the player should be as skills can get altered due to
	 * gameplay
	 */
	private int level;

	/**
	 * The player whose skill this is
	 */
	private final Player player;

	/**
	 * Constructor
	 * 
	 * @param player
	 */
	public Skill(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets a level by experience.
	 * @param skill The skill id.
	 * @return The level.
	 */
	public static int getLevelForExperience(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
				return lvl;
		}
		return 99;
	}
	
	/**
	 * Gets a experience from the level.
	 * @param level The level.
	 * @return The experience.
	 */
	public static int getXPForLevel(int level) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int)Math.floor(points / 4);
		}
		return 0;
	}

	/**
	 * Adds the given amount of experience to the total amount of experience. If the
	 * player levels up, adjust the stat accordingly
	 * 
	 * @param experience
	 */
	public void addExperience(int experience) {
		int skill_level = getLevelForExperience(this.experience);
		this.experience += experience;
		if (skill_level != getLevelForExperience(this.experience)) {
			level += getLevelForExperience(this.experience) - skill_level;
			player.notify(new Levelup(this, getLevelForExperience(this.experience) - skill_level));
		}
		if (this.experience > MAXIMUM_EXP) {
			this.experience = MAXIMUM_EXP;
		}
		player.getChannel().write(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Level up event
	 * 
	 * @author brock
	 *
	 */
	public static class Levelup {
		
		/**
		 * The skill that is being leveled up
		 */
		private final Skill skill;
		
		/**
		 * The amount of levels gained
		 */
		private final int levelsGained;

		/**
		 * Constructor
		 * 
		 * @param skill
		 * @param levelsGained
		 */
		public Levelup(Skill skill, int levelsGained) {
			this.skill = skill;
			this.levelsGained = levelsGained;
		}

		public Skill getSkill() {
			return skill;
		}

		public int getLevelsGained() {
			return levelsGained;
		}
		
	}

}
