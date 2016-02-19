package org.barracuda.content.skill.artisan.view;

class CraftInterfaceElement {

	/**
	 * The label id
	 */
	private final int labelId;
	
	/**
	 * The model id
	 */
	private final int modelId;

	/**
	 * Constructor
	 * 
	 * @param labelId
	 * @param modelId
	 */
	public CraftInterfaceElement(int labelId, int modelId) {
		this.labelId = labelId;
		this.modelId = modelId;
	}

	/**
	 * @return the labelId
	 */
	public int getLabelId() {
		return labelId;
	}

	/**
	 * @return the modelId
	 */
	public int getModelId() {
		return modelId;
	}

}
