package org.barracuda.content.skill;

public class RequirementNotMetException extends RuntimeException {

	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = 1857722704088216107L;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public RequirementNotMetException(String message) {
		super(message);
	}

}
