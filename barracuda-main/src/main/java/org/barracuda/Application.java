package org.barracuda;

import java.io.File;
import java.net.InetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.cache.Cache;
import org.barracuda.core.net.netty.NettyService;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.environment.ContainerInitialized;

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
			 * Add the cache as an injectable reference in the container.
			 */
			File cache_dir = new File(ClassLoader.getSystemClassLoader().getResource("static/cache/").getFile());
			horvik.createApplicationBean(Cache.class, new Cache(cache_dir));
			
			/*
			 * Finally push the method that notifies the listeners this container has succesfully initialized
			 */
			horvik.getEvent().select(ContainerInitialized.class).fire(new ContainerInitialized());
			
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
