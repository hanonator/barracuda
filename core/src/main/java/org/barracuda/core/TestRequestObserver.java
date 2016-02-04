package org.barracuda.core;

import org.barracuda.core.net.Channel;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.inject.Inject;

/**
 * An observer looking listening to TestRequest objects. Prints out debug information
 * and closes the connection immediately
 * 
 * @author brock
 *
 */
@Discoverable
@SessionScoped
public class TestRequestObserver {

	/**
	 * The session instance
	 */
	@Inject
	private Session session;
	
	/**
	 * The channel for the session
	 */
	@Inject
	private Channel channel;

	/**
	 * Listens to TestRequest objects
	 * 
	 * @param request
	 */
	public void test(@Observes TestRequest request, HorvikContainer container) {
		System.out.println("Request received: " + request);
		System.out.println("	- Session: " + session);
		System.out.println("	- Container: " + container);
		
		channel.close();
	}

}
