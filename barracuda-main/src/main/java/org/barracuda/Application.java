package org.barracuda;

import java.net.InetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.netty.NettyService;
import org.barracuda.horvik.Horvik;

/**
 * Application initialization and entry point
 * 
 * @author brock
 *
 */
public class Application {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Application.class);

	/**
	 * Program entry point.
	 * 
	 * @param args
	 * @throws ServiceException
	 */
	public static void main(String[] args) {
		try {
			logger.info("Starting application on {}", InetAddress.getLocalHost().getHostName());
			
			/*
			 * Initialize the container
			 */
			Horvik horvik = new Horvik();
			horvik.initializeContainer();
			
			/*
			 * Start the networking service
			 */
			NettyService service = horvik.getContainer().getService(NettyService.class);
			service.start();
		} catch (Exception ex) {
			logger.fatal("An exception occured during initialization of the server", ex);
		}
	}

}
