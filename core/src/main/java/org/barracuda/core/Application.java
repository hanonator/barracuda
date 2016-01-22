package org.barracuda.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.ServiceException;
import org.barracuda.core.net.message.resolve.MessageRepository;
import org.barracuda.core.net.netty.NettyService;
import org.horvik.Horvik;
import org.horvik.HorvikContainer;

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
			
			// FIXME
			horvik.getContainer().getService(MessageRepository.class).initialize(null, horvik.getContainer());
			
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

}
