package org.barracuda.core.game.contract.ui;

/**
 * Replaces a label on an interface with the given value
 * 
 * @author brock
 *
 */
public class Label {

	/**
	 * The id of the label
	 */
	private final int id;
	
	/**
	 * The text that needs the label needs to be set to
	 */
	private final String text;

	/**
	 * Constructor
	 * 
	 * @param text
	 * @param id
	 */
	public Label(String text, int id) {
		this.id = id;
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}