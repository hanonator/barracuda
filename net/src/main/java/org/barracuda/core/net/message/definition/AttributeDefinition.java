package org.barracuda.core.net.message.definition;

public class AttributeDefinition {

	/**
	 * Name of the field that has to be set to the given value
	 * 
	 * @return
	 */
	private final String field;
	
	/**
	 * The type of field
	 * 
	 * @return
	 */
	private final AttributeType type;

	/**
	 * Constructor
	 * @param field
	 * @param type
	 */
	public AttributeDefinition(String field, AttributeType type) {
		this.field = field;
		this.type = type;
	}

	/**
	 * Constructor
	 * @param field
	 * @param type
	 */
	public AttributeDefinition(Attribute attribute) {
		this.field = attribute.field();
		this.type = attribute.type();
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the type
	 */
	public AttributeType getType() {
		return type;
	}

}
