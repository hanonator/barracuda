package org.barracuda.horvik;

import java.lang.reflect.Parameter;
import java.util.HashSet;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.bean.ManagedBean;
import org.barracuda.horvik.bean.Null;
import org.barracuda.horvik.context.Service;
import org.barracuda.horvik.context.application.ApplicationContext;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.context.request.RequestContext;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.context.session.SessionContext;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Event;
import org.barracuda.horvik.event.ObserverMethod;
import org.barracuda.horvik.event.Observes;

import javassist.Modifier;

@ApplicationScoped
public class Horvik {

	/**
	 * The classpath that is being scanned for annotated classes
	 * 
	 * TODO: Maybe find a way load this from properties file 
	 */
	private static final String CLASS_PATH = "org.barracuda";
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Horvik.class);

	/**
	 * The static instance of the container
	 */
	private final HorvikContainer container = new HorvikContainer(CLASS_PATH);
	
	/**
	 * The parent event instance
	 */
	private final Event<Object> event = new Event<>(Object.class, new HashSet<>());

	/**
	 * Attempts to initialize the container
	 * 	
	 * @throws HorvikException
	 */
	public void initializeContainer() throws HorvikException {
		try {
			logger.info("Starting Horvik container");
			/*
			 * Register all of the scopes
			 */
			container.addContext(SessionScoped.class, new SessionContext(container));
			container.addContext(ApplicationScoped.class, new ApplicationContext(container));
			container.addContext(RequestScoped.class, new RequestContext(container)); // FIXME: Let it inject the decoded messages rather than new instances of empty classes
			
			/*
			 * Attempt to discover the beans
			 */
			discoverBeans();
			
			/*
			 * Finally push the method that notifies the listeners this container has succesfully initialized
			 */
			event.select(ContainerInitialized.class).fire(new ContainerInitialized());
		} catch (Exception ex) {
			throw logger.throwing(Level.FATAL, new HorvikException("could not initialize container", ex));
		}
	}
	
	/**
	 * Attempts to discover the beans in the workspace
	 */
	@SuppressWarnings("unchecked")
	private final void discoverBeans() {
		/*
		 * Default beans
		 */
		container.<ApplicationContext>getContext(ApplicationScoped.class).push(HorvikContainer.class, container);
		container.registerBean(new ManagedBean<>(HorvikContainer.class, container));
		container.<ApplicationContext>getContext(ApplicationScoped.class).push(Horvik.class, this);
		container.registerBean(new ManagedBean<>(Horvik.class, container));
		
		/*
		 * Registers the beans
		 */
		container.getTypesAnnotatedWith(Discoverable.class).forEach(type -> {
			Discoverable discoverable = type.getAnnotation(Discoverable.class);
			container.registerBean(new ManagedBean<>(type, container));
			if (discoverable != null && discoverable.value() != Null.class) {
				container.registerBean(discoverable.value(), new ManagedBean<>(type, container));
			}
			logger.info("Bean -> {}", type.getName());
		});
		
		/*
		 * Registers the services
		 * 
		 * TODO: Make this less shit
		 */
		container.getTypesAnnotatedWith(Service.class).forEach(type -> {
			Bean<?> bean = new ManagedBean<>(type, container);
			Object instance = container.getInjectedReference(bean, null);
			container.registerService(type, instance);
			container.registerBean(bean);
			container.<ApplicationContext>getContext(ApplicationScoped.class).push((Class<Object>) type, instance);
			logger.info("Service -> {}", type.getName());
		});
		
		/*
		 * Observer methods
		 */
		container.getMethodsWithAnyParamAnnotated(Observes.class).forEach(method -> {
			for (Parameter parameter : method.getParameters()) {
				if (parameter.isAnnotationPresent(Observes.class)) {
					if (!Modifier.isStatic(method.getModifiers())) {
						event.addObserver(new ObserverMethod<>((Class<Object>) parameter.getType(), container.getBean(method.getDeclaringClass()), method, container));
					}
					else {
						event.addObserver(new ObserverMethod<>((Class<Object>) parameter.getType(), null, method, container));
					}
					logger.info("Observer -> method {} in {} listening to {}", method.getName(), method.getDeclaringClass().getName(), parameter.getType().getSimpleName());
				}
			}
		});
	}
	
	/**
	 * Gets the container
	 * @return
	 */
	public HorvikContainer getContainer() {
		return container;
	}

	public Event<Object> getEvent() {
		return event;
	}

}
