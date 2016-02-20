package org.barracuda.core.net.message;

import org.barracuda.core.net.message.serialize.Serializable;

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
