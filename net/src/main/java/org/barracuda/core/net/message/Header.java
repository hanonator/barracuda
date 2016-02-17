package org.barracuda.core.net.message;

/**
 * The message header
 * 
 * @author brock
 *
 */
public interface Header extends Serializable {

	/**
	 * The message's opcode
	 * 
	 * @return
	 */
	int opcode();

	/**
	 * Length of the message
	 * 
	 * @return
	 */
	int length();

}
