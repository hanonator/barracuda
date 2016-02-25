package org.barracuda.model.event;

import org.barracuda.model.actor.player.Skill;

/**
 * Level up event
 * 
 * @author brock
 *
 */
public class Levelup {
	
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