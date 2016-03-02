package org.barracuda.content.skill.gather;

public class Tool implements Comparable<Tool> {

	/**
	 * Item id of the tool
	 */
	private int id;

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
	private int animation;
	
	/**
	 * The type of the tool
	 */
	private ToolType type;

	@Override
	public int compareTo(Tool tool) {
		return Integer.compare(level, tool.level);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + level;
		result = prime * result + skill;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tool other = (Tool) obj;
		if (id != other.id)
			return false;
		if (level != other.level)
			return false;
		if (skill != other.skill)
			return false;
		return true;
	}


	/**
	 * @return the animation
	 */
	public int getAnimation() {
		return animation;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(int animation) {
		this.animation = animation;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
