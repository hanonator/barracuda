package org.barracuda.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.ServiceException;
import org.barracuda.core.net.netty.NettyService;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.event.Event;

/**
 * Application initialization and entry point
 * 
 * @author brock
 *
 */
public class Application {

	/**
	 * The networking service
	 */
	private static final NettyService service = new NettyService();

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Application.class);

	/**
	 * The Horvik object
	 * 
	 * TODO: Try to not have to put this to static
	 */
	private static final Horvik horvik = new Horvik();

	/**
	 * Program entry point.
	 * 
	 * @param args
	 * @throws ServiceException
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) {
		try {
			logger.info("Starting application on {}", InetAddress.getLocalHost().getHostName());
			
			/*
			 * Initialize the container
			 */
			horvik.initializeContainer();
			
			/*
			 * Start the networking service
			 */
			service.start();
		} catch (Exception ex) {
			logger.fatal("An exception occured during initialization of the server", ex);
		}
	}

	public static HorvikContainer getContainer() {
		return horvik.getContainer();
	}

	public static Event<Object> getEvent() {
		return horvik.getEvent();
	}

}
