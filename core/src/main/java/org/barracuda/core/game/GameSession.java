package org.barracuda.core.game;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;

import io.netty.util.AttributeKey;

/**
 * TODO: Why does this exist?
 * 
 * @author brock
 *
 */
@Discoverable
@SessionScoped
public class GameSession {

	/**
	 * The public attribute key
	 */
	public static final AttributeKey<GameSession> ATTRIBUTE_KEY = AttributeKey.newInstance("game_session");

}
