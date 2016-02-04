package org.barracuda.core.net.message.resolve;

import org.barracuda.core.game.GameSession;
import org.barracuda.core.net.message.Message;

/**
 * Decodes the message
 * 
 * @author brock
 *
 */
public interface MessageDecoder {

	/**
	 * Decodes the message
	 * 
	 * @param message
	 * @return
	 */
	Object decode(Message message, GameSession session) throws Exception;

}
