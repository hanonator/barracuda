package org.barracuda.model.actor.sync.attribute;

/**
 * Text that appears above the entity but does not appear in the chatbox
 * 
 * @author brock
 *
 */
public class ChatForced implements Attribute {

	/**
	 * The text that will appear above the head of the entity but not in the
	 * chatbox
	 */
	private final String text;

	/**
	 * Constructor
	 * 
	 * @param text
	 */
	public ChatForced(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}
