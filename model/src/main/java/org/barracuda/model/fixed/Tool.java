package org.barracuda.model.fixed;

public class Tool {

	/**
	 * The level required to use the tool
	 */
	private int level;
	
	/**
	 * The skill that requires the use of the tool
	 */
	private int skill;
	
	/**
	 * The advantage gained from using the tool
	 */
	private double strength;
	
	/**
	 * The type of the tool
	 */
	private ToolType type;

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the skill
	 */
	public int getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(int skill) {
		this.skill = skill;
	}

	/**
	 * @return the strength
	 */
	public double getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(double strength) {
		this.strength = strength;
	}

	/**
	 * @return the type
	 */
	public ToolType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ToolType type) {
		this.type = type;
	}

}
