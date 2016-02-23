package org.barracuda.content.skill.artisan;

import java.util.Arrays;

public class ProductDefinition {

	/**
	 * The animation played when working with the resources
	 */
	private int animation;
	
	/**
	 * The skill that is being trained
	 */
	private int skill;
	
	/**
	 * The resources required to create the item
	 */
	private int[] resources;
	
	/**
	 * The possible products to be crafted from the resources
	 */
	private Product[] products;

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
	 * @return the resources
	 */
	public int[] getResources() {
		return Arrays.copyOf(resources, resources.length);
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(int[] resources) {
		this.resources = Arrays.copyOf(resources, resources.length);
	}

	/**
	 * @return the products
	 */
	public Product[] getProducts() {
		return Arrays.copyOf(products, products.length);
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Product[] products) {
		this.products = Arrays.copyOf(products, products.length);
	}

	/**
	 * Gets the product at the given index
	 * @param index
	 * @return
	 */
	public Product getProduct(int index) {
		return products[index];
	}

}
