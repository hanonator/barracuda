package org.barracuda.model.actor.sync;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.NPC;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.attribute.Attribute;

import io.netty.buffer.ByteBuf;

/**
 * An actor's attributes
 * 
 * @author brock
 *
 */
public class RenderingHints {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(RenderingHints.class);
	
	/**
	 * The collection of renderers
	 */
	private static final Map<Class<?>, Map<Class<?>, Renderer<? extends Attribute>>> renderers = new HashMap<>();

	/**
	 * The collection of attributes
	 */
	private final Set<Attribute> attributes = new HashSet<>();
	
	/**
	 * The actor class
	 */
	private final Class<? extends Actor> entity_class;
	
	/**
	 * Constructor
	 * @param entity_class
	 */
	public RenderingHints(Class<? extends Actor> entity_class) {
		this.entity_class = entity_class;
	}

	/**
	 * Attempts to find all of the available renderers
	 * 
	 * @param event
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void find_renderers(@Observes ContainerInitialized event, HorvikContainer container) throws Exception {
		renderers.put(Player.class, new HashMap<>());
		renderers.put(NPC.class, new HashMap<>());
		container.getTypesAnnotatedWith(Renders.class).forEach(type -> {
			try {
				Renders annotation = type.getDeclaredAnnotation(Renders.class);
				renderers.get(annotation.entity()).put(annotation.attribute(), (Renderer<? extends Attribute>) type.newInstance());
				logger.info("{} -> renders {} for type {}", type.getName(), annotation.attribute(), annotation.entity());
			} catch (Exception ex) {
				logger.fatal("Could not instantiate renderer");
				ex.printStackTrace();
			}
		});
	}

	/**
	 * Assembles the attributes in a binary chunk of data that can be
	 * synchronized with the client
	 * 
	 * @param buffer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ByteBuf assemble(ByteBuf buffer) {
		AtomicInteger bit_set = new AtomicInteger(0);
		attributes.forEach(attribute -> bit_set.set(bit_set.get() | renderers.get(entity_class).get(attribute.getClass()).getIdentifier()));
		buffer.writeByte(bit_set.get());
		attributes.forEach(attribute -> ((Renderer<Attribute>) renderers.get(entity_class).get(attribute.getClass())).serialize(attribute, buffer));
		return buffer;
	}

	/**
	 * Adds an attribute to the collection
	 * 
	 * @param attribute
	 * @return
	 */
	public boolean add(Attribute attribute) {
		if (!contains(attribute.getClass())) {
			return attributes.add(attribute);
		}
		return false;
	}
	
	/**
	 * Returns true if an attribute of the given class is already present
	 * 
	 * @param attribute_class
	 * @return
	 */
	public boolean contains(Class<? extends Attribute> attribute_class) {
		return attributes.stream().anyMatch(attribute -> attribute.getClass() == attribute_class);
	}

	/**
	 * Checks to see if there are any attributes present in the collection currently
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return attributes.isEmpty();
	}
	
	/**
	 * Removes all of the attributes
	 */
	public void clear() {
		attributes.clear();
	}

}
