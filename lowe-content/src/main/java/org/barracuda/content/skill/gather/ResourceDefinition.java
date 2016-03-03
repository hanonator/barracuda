package org.barracuda.content.skill.gather;

import java.util.Arrays;

public class ResourceDefinition {

	/**
	 * The resources. This is currently an even distribution between
	 * all of the available resources.
	 */
	private Resource[] resources;

	/**
	 * The skill needed to gather the resource
	 */
	private int skill;
	
	/**
	 * The tool that is required to gather from the deposit
	 */
	private ToolType tool;
	
	/**
	 * The rate at which resources are gathered
	 */
	private double rate;
	
	/**
	 * Amount of items in the resource on average
	 */
	private int count;
	
	/**
	 * Id of the depleted object
	 */
	private int depletedId;
	
	/**
	 * @return the resources
	 */
	public Resource[] getResources() {
		return Arrays.copyOf(resources, resources.length);
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Resource[] resources) {
		this.resources = Arrays.copyOf(resources, resources.length);
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
	 * @return the tool
	 */
	public ToolType getTool() {
		return tool;
	}

	/**
	 * @param tool the tool to set
	 */
	public void setTool(ToolType tool) {
		this.tool = tool;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the depletedId
	 */
	public int getDepletedId() {
		return depletedId;
	}

	/**
	 * @param depletedId the depletedId to set
	 */
	public void setDepletedId(int depletedId) {
		this.depletedId = depletedId;
	}

}
