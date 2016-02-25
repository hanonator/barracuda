package org.barracuda.model.event;

import org.barracuda.model.actor.player.Skill;

/**
 * Indicates the skill has been updated
 * 
 * @author brock
 *
 */
public class ExperienceGained {
	
	/**
	 * The skill that has been updated
	 */
	private final Skill skill;

	/**
	 * constructor
	 * 
	 * @param skill
	 */
	public ExperienceGained(Skill skill) {
		this.skill = skill;
	}

	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}
	
}