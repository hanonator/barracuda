package org.barracuda.model.actor;

import org.barracuda.model.actor.player.Credentials;
import org.barracuda.model.actor.player.misc.Detail;

public class Player extends Actor {

	/**
	 * The detail mode the player is playing in
	 */
	private Detail detail;
	
	/**
	 * The player's credentials
	 */
	private Credentials credentials;

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
