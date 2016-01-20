package org.barracuda.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.Service;
import org.barracuda.core.net.ServiceException;
import org.barracuda.core.net.netty.NettyService;

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
	private static final Service service = new NettyService();

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Application.class);

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
			 * Start the networking service
			 */
			service.start();
		} catch (Exception ex) {
			logger.fatal("An exception occured during initialization of the server", ex);
		}
	}

}
