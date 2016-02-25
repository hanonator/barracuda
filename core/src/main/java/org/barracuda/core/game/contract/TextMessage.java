package org.barracuda.core.game.contract;

/**
 * Sends a text message to the player
 * 
 * @author brock
 *
 */
public class TextMessage {

	/**
	 * The message
	 */
	private final String message;

	/**
	 * Formatted plain text message
	 * 
	 * @param message
	 * @param objects
	 */
	public TextMessage(String message, Object... objects) {
		this.message = String.format(message, objects);
	}

	/**
	 * Plain text messagve
	 * 
	 * @param message
	 */
	public TextMessage(String message) {
		this.message = message;
	}

	/**
	 * Makes a new TextMessage from the toString() method of the given object
	 * 
	 * @param object
	 */
	public TextMessage(Object object) {
		this(object.toString());
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


}