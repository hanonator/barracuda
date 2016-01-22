package org.horvik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.horvik.bean.BeanManager;

public class Horvik {

	/**
	 * The classpath that is being scanned for annotated classes
	 * 
	 * TODO: Maybe find a way load this from properties file 
	 */
	private static final String CLASS_PATH = "";

	/**
	 * The static instance of the container
	 */
	private static final HorvikContainer container = new HorvikContainer(CLASS_PATH);
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Horvik.class);

	/**
	 * Attempts to initialize the container
	 */
	public void initializeContainer() throws HorvikException {
		try {
			BeanManager manager = new BeanManager();
			
			/*
			 * Register the scopes
			 */
//			manager.addContext(new ContextHolder<>(new SessionContext(), SessionContext.class));
//			manager.addContext(new ContextHolder<>(new ApplicationContext(), ApplicationContext.class));
			
			/*
			 * Discover the beans
			 */
			discoverBeans(manager);
//
//			/*
//			 * Initialize the services
//			 * 
//			 * FIXME: Find a way to not need to include the service registration here but rather have them
//			 * be discovered by the bean discovery
//			 */
//			for (Class<?> service_class : container.getTypesAnnotatedWith(Service.class)) {
//				container.registerService(service_class, service_class.newInstance());
//				logger.info("Service registered: {}", service_class.getName());
//			}
		} catch (Exception ex) {
			throw log("error initializing container", ex);
		}
	}
	
	private void discoverBeans(BeanManager manager) {
		
	}
	
	/**
	 * Gets the container
	 * @return
	 */
	public HorvikContainer getContainer() {
		return container;
	}

	/**
	 * Throws and logs an exception
	 * 
	 * @param message
	 * @param exception
	 * @return
	 */
	private static HorvikException log(String message, Exception exception) {
		logger.fatal(message, exception);
		return new HorvikException(message, exception);
	}

}
