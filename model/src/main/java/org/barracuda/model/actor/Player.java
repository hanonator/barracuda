package org.barracuda.model.actor;

import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.barracuda.model.actor.player.Credentials;
import org.barracuda.model.actor.player.misc.Detail;

@Discoverable
@SessionScoped
public class Player extends Actor {

	/**
	 * The detail mode the player is playing in
	 */
	private Detail detail;
	
	/**
	 * The player's credentials
	 */
	private Credentials credentials;
	
	/**
	 * The horvik container the player belongs to
	 */
	private final Horvik container;
	
	/**
	 * The session for this player object
	 */
	@Inject private Session session;

	/**
	 * Constructor
	 * 
	 * @param container
	 */
	public Player(Horvik container) {
		this.container = container;
	}

	/**
	 * Dispatches an event to the container
	 * 
	 * @param object
	 */
	public void notify(Object event) {
		container.getEvent().fire(event, session);
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
